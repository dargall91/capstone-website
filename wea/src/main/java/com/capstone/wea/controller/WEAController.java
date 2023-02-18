package com.capstone.wea.controller;

import com.capstone.wea.Util.IPAWSInterface;
import com.capstone.wea.Util.Util;
import com.capstone.wea.entities.CMACMessage;
import com.capstone.wea.model.MessageStats;
import com.capstone.wea.model.cap.IPAWSMessageList;

import com.capstone.wea.repositories.projections.AreaProjection;
import com.capstone.wea.repositories.projections.CollectedStatsProjections;
import com.capstone.wea.repositories.DeviceRepository;
import com.capstone.wea.repositories.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RequestMapping("/wea/api/")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WEAController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Endpoint to request a WEA message from the server.
     * For now, the message is static, but if we decide to
     * go this route for message retrieval it will be
     * randomized in the future
     *
     * @return HTTP 200 OK and an XML formatted WEA message
     */
    @GetMapping(value = "getMessage", produces = "application/xml")
    public ResponseEntity<?> getMessage() throws MalformedURLException {
        //first check for oldest non-expired messages in database
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC).withNano(0);
        CMACMessage oldestMessage = messageRepository.findFirstByExpiresAfter(now);

        if (oldestMessage != null) {
            return ResponseEntity.ok(oldestMessage);
        }

        try {
            //no non-expired messages in database, check for messages from IPAWS
            IPAWSMessageList ipawsResult = IPAWSInterface.getMessageFromIpaws("prod", "public");

            //if there are no new messages from IPAWS, throw error, otherwise return oldest message
            if (ipawsResult.getAlertList().size() == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No new messages found");
            } else {
                for (int i = 0; i < ipawsResult.getAlertList().size(); i++) {
                    CMACMessage exists =
                            messageRepository.findByCapIdentifier(ipawsResult.getAlertList().get(i).getIdentifier());
                    //if message with cap identifier exists, skip it
                    if (exists != null) {
                        continue;
                    }

                    CMACMessage message = new CMACMessage(ipawsResult.getAlertList().get(i));
                    messageRepository.save(message);

                    if (i == 0) {
                        oldestMessage = message;
                    }
                }
                if (oldestMessage != null) {
                    return ResponseEntity.ok(oldestMessage);
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
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
//    @PutMapping(value = "upload")
    @PostMapping(value = "upload")
    public ResponseEntity<String> upload(@RequestBody com.capstone.wea.entities.CollectedDeviceData userData) {
        userData = deviceRepository.save(userData);
//        String uploadId = userData.addToDatabase(jdbcTemplate.getDataSource());

        URI location = ServletUriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/wea/api/getUpload?identifier=" + userData.getId())
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
    public ResponseEntity<?> getUpload(@RequestParam int identifier) {
        String query = "SELECT * " +
                "FROM alert_db.device_upload_data " +
                "WHERE device_upload_data.UploadID = '" + identifier + "';";

        Optional<com.capstone.wea.entities.CollectedDeviceData> data = deviceRepository.findById((long) identifier);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data.get());
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
    public ResponseEntity<?> getMessageList(@PathVariable String sender, @PathVariable int page,
                                            @RequestParam(required = false) String messageNumber,
                                            @RequestParam(required = false) String messageType,
                                            @RequestParam(required = false) String fromDate,
                                            @RequestParam(required = false) String toDate,
                                            @RequestParam(required = false) String sortBy,
                                            @RequestParam(required = false) String sortOrder) {
        String commonName;
        if (sender.equals("w-nws.webmaster@noaa.gov")) {
            commonName = "National Weather Service";
        } else {
            CMACMessage message = messageRepository.findFirstBySender(sender);
            commonName = message.getAlertInfo().getSenderName();
        }

        //page cannot be zero or negative
        page = page < 1 ? 1 : page;

        //default sort order is date -- used if not provided, or not valid
        boolean orderByDate = Util.isNullOrBlank(sortBy) || !sortBy.equalsIgnoreCase("number");

        //default sort order is descending -- used if not provided, or not valid
        boolean orderByDesc = Util.isNullOrBlank(sortOrder) || !sortOrder.equalsIgnoreCase("asc");
        Sort sort;

        sort = orderByDate ? Sort.by("sentDateTime") : Sort.by("messageNumber");
        sort = orderByDesc ? sort.descending() : sort.ascending();

        Pageable pageable = PageRequest.of(page - 1, 9, sort);

        Page<CMACMessage> messageList = messageRepository.findAllBySender(sender, pageable);
        List<MessageStats> messageStatsList = new ArrayList<>();

        List<CollectedStatsProjections> statsList = deviceRepository.getDeviceStats(sender, page,
                Util.isNullOrBlank(messageNumber) ? null : Integer.parseInt(messageNumber, 16), messageType,
                fromDate, toDate, orderByDate, orderByDesc);

        List<AreaProjection> areaList = messageRepository.getMessageAreas(sender, page,
                Util.isNullOrBlank(messageNumber) ? null : Integer.parseInt(messageNumber, 16), messageType,
                fromDate, toDate, orderByDate, orderByDesc);

        for (int i = 0; i < areaList.size(); i++) {
            messageStatsList.add(new MessageStats(statsList.get(i), areaList.get(i)));
        }

        //TODO: deviceCount should be 90-95% of number that should have been hit
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.set("messageStats", mapper.valueToTree(messageStatsList));///.subList(0, Math.min(resultList.size(), 9))));
        root.set("commonName", mapper.valueToTree(commonName));
        root.set("prev", BooleanNode.valueOf(page > 1));
        root.set("next", BooleanNode.valueOf(statsList.size() > 9));

        return ResponseEntity.ok(root);

//
//
//        for (CMACMessage message : messageList.getContent()) {
//            MessageStats messageStats = new MessageStats(message);
//
//            CollectedStatsProjections collectedDeviceStats =
//                    deviceRepository.findAverageTimeReceivedByMessageNumber(message.getMessageNumber());
//
//
//
//            messageStats.setDeviceStats(collectedDeviceStats);
//
//            messageStatsList.add(messageStats);
//        }
//
//        Page<MessageStats> messageStats = new PageImpl<>(messageStatsList, pageable, messageList.getTotalElements());
//        return ResponseEntity.ok(test);
        /*
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
                    String areaNameQuery = "SELECT CMASGeocodes " +
                            "FROM alert_db.cmac_area_description " +
                            "WHERE CMACMessageNumber = " + result.getMessageNumberInt() + ";";

                    String geocodeString = jdbcTemplate.queryForObject(areaNameQuery, String.class);

                    result.setGeocodeList(List.of(geocodeString.split(",")));

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

        return ResponseEntity.ok(root);*/
    }
}
