package com.capstone.wea.controller;

import com.capstone.wea.Util.IPAWSInterface;
import com.capstone.wea.Util.Util;
import com.capstone.wea.model.cmac.*;
import com.capstone.wea.model.sqlresult.*;
import com.capstone.wea.model.sqlresult.mappers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/wea/api/")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WEAController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    /**
     * Endpoint to request a WEA message from the server.
     * For now, the message is static, but if we decide to
     * go this route for message retrieval it will be
     * randomized in the future
     *
     * @return HTTP 200 OK and an XML formatted WEA message
     */
    @GetMapping(value = "getMessage", produces = "application/xml")
    public ResponseEntity<CMACMessageModel> getMessage() {
        if (simpleJdbcCall == null) {
            simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        }

        //first check for oldest non-expired messages in database
        Map<String, Object> oldestEntry;
        CMACMessageModel oldestMessage;

        try {
            oldestEntry = simpleJdbcCall.withProcedureName("GetOldestMessage").execute();

            //no non-expired messages in database, check for messages from IPAWS
            if (oldestEntry.get("messageNumber") == null) {
                oldestMessage = IPAWSInterface.getMessageFromIpaws("prod", jdbcTemplate, "public");

                //if there are no new messages from IPAWS, throw error, otherwise return oldest message
                if (oldestMessage == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No new messages found");
                } else {
                    return ResponseEntity.ok(oldestMessage);
                }
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }

        CMACMessageModel resultMessage =
                new CMACMessageModel(Integer.parseInt(oldestEntry.get("messageNumber").toString()),
                oldestEntry.get("capIdentifier").toString(), jdbcTemplate);

        return ResponseEntity.ok(resultMessage);
    }

    /**
     * Endpoint to which the data collected from the
     * mobile device will be sent. The uploaded data
     * will be added to the database.
     *
     * @param userData An XML body containing the data
     *                 collected from the user
     *
     * @return HTTP 201 CREATED and the URI of the
     *         uploaded data
     */
    @PutMapping(value = "upload")
    public ResponseEntity<String> upload(@RequestBody CollectedDeviceData userData) {
        String uploadId = userData.addToDatabase(jdbcTemplate.getDataSource());

        URI location = ServletUriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/wea/api/getUpload?identifier=" + uploadId)
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Gets a data upload represented by a unique
     * identifier. This endpoint is only used to
     * confirm successful uploads from devices
     * during testing
     *
     * @param identifier Unique upload identifier
     * @return HTTP 200 OK and the uploaded data
     *         in XML format, or HTTP 404 NOT
     *         FOUND if the identifier is invalid
     */
    @GetMapping(value = "getUpload", produces = "application/xml")
    public ResponseEntity<CollectedDeviceData> getUpload(@RequestParam int identifier) {
        String query = "SELECT * " +
                "FROM alert_db.device_upload_data " +
                "WHERE device_upload_data.UploadID = '" + identifier + "';";

        CollectedDeviceData data = jdbcTemplate.queryForObject(query, new CollectedDeviceDataMapper());

        return ResponseEntity.ok(data);
    }

    /**
     * Gets the list of all CMAC_messages sent by a
     * specified CMAC_sender and the collected stats
     * for each message. Results are returned in pages,
     * with up to nine messages per page
     *
     * @param sender A CMAC_sender. '@' characters
     *               must be encoded as %40
     * @param page The page of results to get
     * @return HTTP 200 OK and a JASON array of
     *         objects containing each message's stats
     */
    @GetMapping("{sender}/messages/{page}/filter")
    public ResponseEntity<ObjectNode> getMessageList(@PathVariable String sender, @PathVariable int page,
                                                     @RequestParam(required = false) String messageNumber,
                                                     @RequestParam(required = false) String messageType,
                                                     @RequestParam(required = false) String fromDate,
                                                     @RequestParam(required = false) String toDate,
                                                     @RequestParam(required = false) String sortBy,
                                                     @RequestParam(required = false) String sortOrder) {
        if (simpleJdbcCall == null) {
            simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        }

        //page cannot be zero or negative
        page = page < 1 ? 1 : page;

        //default sort order is date -- used if not provided, or not valid
        boolean orderByDate = Util.isNullOrBlank(sortBy) || !sortBy.equalsIgnoreCase("number");

        //default sort order is descending -- used if not provided, or not valid
        boolean orderByDesc = Util.isNullOrBlank(sortOrder) || !sortOrder.equalsIgnoreCase("asc");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("sender", sender)
                .addValue("pageNum", page)
                .addValue("messageNumber",
                        Util.isNullOrBlank(messageNumber) ? null : Integer.parseInt(messageNumber, 16))
                .addValue("messageType", messageType)
                .addValue("fromDate", fromDate)
                .addValue("toDate", toDate)
                .addValue("orderByDate", orderByDate)
                .addValue("orderByDesc", orderByDesc);

        //override default exception response to avoid showing stacktrace, which may contain table names
        List<MessageStatsResult> resultList = new ArrayList<>();
        String commonName;
        try {
            Map<String, Object> queryResult = simpleJdbcCall.withProcedureName("GetMessageList").execute(params);

            @SuppressWarnings("unchecked")
            List<LinkedCaseInsensitiveMap<Object>> queryResultMapList =
                    (List<LinkedCaseInsensitiveMap<Object>>) queryResult.get("#result-set-1");

            for (LinkedCaseInsensitiveMap<Object> resultMap : queryResultMapList){
                resultList.add(new MessageStatsResult(resultMap));
            }

            //NWS can have multiple common names but the query expects a singe result
            if (sender.equals("w-nws.webmaster@noaa.gov")) {
                commonName = "National Weather Service";
            } else {
                //common name query
                String nameQuery = "SELECT CMACSenderName " +
                        "FROM alert_db.cmac_message " +
                        "WHERE CMACSender = '" + sender + "' " +
                        "GROUP BY CMACSenderName;";

                commonName = jdbcTemplate.queryForObject(nameQuery, String.class);
            }

            //get polygon/area name list for each message in list
            for (MessageStatsResult result : resultList){
                String coordinatesQuery = "SELECT Latitude, Longitude " +
                        "FROM alert_db.cmac_polygon_coordinates " +
                        "WHERE CMACMessageNumber = " + result.getMessageNumberInt() + ";";

                List<Coordinate> coordinates = jdbcTemplate.query(coordinatesQuery, new CMACCoordinateMapper());

                //if no polygon coordinates, look for circle coordinates
                if (coordinates.size() == 0) {
                    coordinatesQuery = "SELECT Latitude, Longitude " +
                            "FROM alert_db.cmac_circle_coordinates " +
                            "WHERE CMACMessageNumber = " + result.getMessageNumberInt() + ";";

                    coordinates = jdbcTemplate.query(coordinatesQuery, new CMACCoordinateMapper());
                }

                //if no coordinates, use geocodes
                if (coordinates.size() > 0) {
                    result.setPolygon(coordinates);
                } else {
                    String areaNameQuery = "SELECT CMASGeocode " +
                            "FROM alert_db.cmac_area_description " +
                            "WHERE CMACMessageNumber = " + result.getMessageNumberInt() + ";";

                    List<String> geocodes = jdbcTemplate.queryForList(areaNameQuery, String.class);

                    result.setGeocodeList(geocodes);

                    String geocodeTypeQuery = "SELECT SAME " +
                            "FROM alert_db.cmac_area_description " +
                            "WHERE CMACMessageNumber = " + result.getMessageNumberInt() + ";";

                    Boolean same = jdbcTemplate.queryForObject(geocodeTypeQuery, Boolean.class);

                    if (Boolean.TRUE.equals(same)) {
                        result.setGeocodeType("SAME");
                    } else {
                        result.setGeocodeType("UGC");
                    }
                }
            }
        } catch (BadSqlGrammarException e) {
            e.printStackTrace();
            throw new InternalError("Bad SQL Grammar");
        }

        //TODO: deviceCount should be 90-95% of number that should have been hit
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.set("messageStats", mapper.valueToTree(resultList.subList(0, Math.min(resultList.size(), 9))));
        root.set("commonName", mapper.valueToTree(commonName));
        root.set("prev", BooleanNode.valueOf(page > 1));
        root.set("next", BooleanNode.valueOf(resultList.size() > 9));

        return ResponseEntity.ok(root);
    }
}
