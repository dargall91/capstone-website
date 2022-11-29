package com.capstone.wea.controller;

import com.capstone.wea.model.cap.CAPMessageModel;
import com.capstone.wea.model.cap.IPAWSMessageList;
import com.capstone.wea.model.cmac.*;
import com.capstone.wea.model.sqlresult.*;
import com.capstone.wea.model.sqlresult.mappers.*;
import com.capstone.wea.parser.XMLParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping("/wea")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WEAController {
    @Autowired
    private JdbcTemplate dbTemplate;
    private final int PAGE_SIZE = 9;
    private final String IPAWS_PIN_PARAMETER = "?pin=NnducW4wcTdldjE";
    private final String IPAWS_TEST_URL = "https://tdl.apps.fema.gov/IPAWSOPEN_EAS_SERVICE/rest/";
    private final String IPAWS_PROD_URL = "https://apps.fema.gov/IPAWSOPEN_EAS_SERVICE/rest/";
    private final String EAS_FEED = "eas/recent/";
    private final String PUBLIC_NON_EAS_FEED = "public_non_eas/recent/";
    private final String PUBLIC_FEED = "public/recent/";
    private final String WEA_FEED = "PublicWEA/recent/";

    /**
     * Having now worked with C# for a few months, I really miss this method...
     *
     * @return True if the string is null or empty, false if it is not
     */
    private boolean isNullOrEmpty(String value) {
        return (value == null || value.isEmpty());
    }

    /**
     * Hits the IPAWS API to see if there are any new messages. If new messages are found
     * they are added to the database.
     *
     * @param env The IPAWS environment API to hit; valid values are "test" and "prod";
     *            If an invalid value is passed, the test environment will be used
     * @param dateTime String representation of how far back to query; If this time is within 5
     *                 minutes of the current time results wil include all messages sent within
     *                 the last 5 minutes; The maximum amount of time you can go back is 30
     *                 minutes
     * @param feed The feed API to hit; the valid values are: "eas", "non-eas", "public", and "wea";
     *             If an invalid feed is provided, "wea" will be used
     * @return True if new messages are found, false if they are not
     */
    private boolean getMessageFromIpaws(String env, ZonedDateTime dateTime, String feed) throws MalformedURLException {
        StringBuilder ipawsUrl = new StringBuilder();
        if (env.equalsIgnoreCase("prod")) {
            ipawsUrl.append(IPAWS_PROD_URL);
        } else {
            ipawsUrl.append(IPAWS_TEST_URL);
        }

        if (feed.equalsIgnoreCase("eas")) {
            ipawsUrl.append(EAS_FEED);
        } else if (feed.equalsIgnoreCase("non-eas")) {
            ipawsUrl.append(PUBLIC_NON_EAS_FEED);
        } else if (feed.equalsIgnoreCase("public")) {
            ipawsUrl.append(PUBLIC_FEED);
        } else {
            ipawsUrl.append(WEA_FEED);
        }

        if (dateTime == null) {
            dateTime = ZonedDateTime.now(Clock.systemUTC()).withNano( 0);
        } else if (ZonedDateTime.now(Clock.systemUTC()).withNano(0).minusMinutes(30).isAfter(dateTime.withNano(0))) {
            dateTime = ZonedDateTime.now(Clock.systemUTC()).withNano( 0).minusMinutes(30);
        } else {
            //drop nanoseconds
            dateTime = dateTime.withNano(0);
        }

        ipawsUrl.append(dateTime.format(DateTimeFormatter.ISO_INSTANT).toString())
                .append(IPAWS_PIN_PARAMETER);

        URL getIpaws = new URL(ipawsUrl.toString());

        try {
            XmlMapper mapper = new XmlMapper();
            IPAWSMessageList model = mapper.readValue(getIpaws, IPAWSMessageList.class);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //TODO: parse

        return true;
    }

    /**
     * Endpoint to request a WEA message from the server.
     * For now, the message is static, but if we decide to
     * go this route for message retrieval it will be
     * randomized in the future
     *
     * @return HTTP 200 OK and an XML formatted WEA message
     */
    @GetMapping(value = "/getMessage", produces = "application/xml")
    public ResponseEntity<CMACMessageModel> getMessage() {
        CMACMessageModel model = XMLParser.parseCMAC("src/main/resources/sampleCmacMessage.xml");

        //uncomment to easily add a message to the database when this endpoint is hit
        //requires changing the message number in sameCmacMessage to prevent primary key conflicts
        //model.addToDatabase(dbTemplate);

        return ResponseEntity.ok(model);
    }

    /**
     * Endpoint to which the data collected from the
     * mobile device will be sent. The uploaded data
     * will be added to the database. This endpoint
     * is simply for testing purposes to make sure an
     * upload is successful before the database is
     * created
     *
     * @param userData An XML body containing the data
     *                 collected from the user
     *
     * @return HTTP 201 CREATED and the URI of the
     *         uploaded data
     */
    @PutMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestBody CollectedDeviceData userData) {
        String query = "INSERT INTO alert_db.device_upload_data VALUES('" + userData.getMessageNumber() + "', NULL, NULL, NULL, " +
                "NULL, '" + userData.getLocationReceived() + "', '" + userData.getLocationDisplayed() + "', '" +
                userData.getTimeReceived() + "', '" + userData.getTimeDisplayed() + "', " +
                userData.isReceivedOutsideArea() + ", " + userData.isDisplayedOutsideArea() + ", " +
                userData.isReceivedAfterExpired() + ", " + userData.isDisplayedAfterExpired() + ");";
        dbTemplate.update(query);

        //gets the UploadID of the most recently inserted row
        query = "SELECT LAST_INSERT_ID();";
        Integer id = dbTemplate.queryForObject(query, Integer.class);

        URI location = ServletUriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/wea/getUpload?identifier=" + id)
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
    @GetMapping(value = "/getUpload", produces = "application/xml")
    public ResponseEntity<CollectedDeviceData> getUpload(@RequestParam int identifier) {
        String query = "SELECT * " +
                "FROM alert_db.device_upload_data " +
                "WHERE device_upload_data.UploadID = '" + identifier + "';";

        CollectedDeviceData data = dbTemplate.queryForObject(query, new CollectedDeviceDataMapper());

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
    @GetMapping("/{sender}/messages/{page}/filter")
    public ResponseEntity<ObjectNode> getMessageList(@PathVariable String sender, @PathVariable int page,
                                                     @RequestParam(required = false) String messageNumber,
                                                     @RequestParam(required = false) String messageType,
                                                     @RequestParam(required = false) String fromDate,
                                                     @RequestParam(required = false) String toDate,
                                                     @RequestParam(required = false) String sortBy,
                                                     @RequestParam(required = false) String sortOrder) {
        //common name query
        String nameQuery = "SELECT CMACSenderName " +
                "FROM alert_db.cmac_message " +
                "WHERE CMACSender = '" + sender + "' " +
                "GROUP BY CMACSenderName;";

        //set base query
        String baseQuery = "SELECT cmac_message.CMACMessageNumber, CMACDateTime, CMACMessageType, " +
                "COUNT(*) AS DeviceCount, " +
                "CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(TimeReceived, CMACDateTime)))) AS TIME) AS AvgTime, " +
                "MAX(TIMEDIFF(TimeReceived, CMACDateTime)) AS LongTime, " +
                "MIN(TIMEDIFF(TimeReceived, CMACDateTime)) AS ShortTime, " +
                "CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(TimeDisplayed, TimeReceived)))) AS TIME) AS AvgDelay, " +
                "SUM(ReceivedOutsideArea) AS ReceivedOutsideCount, " +
                "SUM(DisplayedOutsideArea) AS DisplayedOutsideCount, " +
                "SUM(ReceivedAfterExpired) AS ReceivedExpiredCount, " +
                "SUM(DisplayedAfterExpired) AS DisplayedExpiredCount " +
                "FROM alert_db.device_upload_data JOIN alert_db.cmac_message " +
                "ON cmac_message.CMACMessageNumber = device_upload_data.CMACMessageNumber ";

        //set filtering for query
        StringBuilder filters = new StringBuilder("WHERE CMACSender = '" + sender + "' ");

        if (!isNullOrEmpty(messageNumber)) {
            filters.append("&& cmac_message.CMACMessagenumber = '" + messageNumber + "' ");
        }

        if (!isNullOrEmpty(messageType)) {
            filters.append("&& CMACMessageType LIKE '%" + messageType + "%' ");
        }

        if (!isNullOrEmpty(fromDate)) {
            filters.append("&& CMACDateTime >= '" + fromDate + "' ");
        }

        if (!isNullOrEmpty(toDate)) {
            filters.append("&& CMACDateTime < DATE_ADD('" + toDate + "', INTERVAL 1 DAY) ");
        }

        String grouping = "GROUP BY cmac_message.CMACMessageNumber ";

        //set sorting and ordering
        if (isNullOrEmpty(sortBy) || (!sortBy.equalsIgnoreCase("number")
                && !sortBy.equalsIgnoreCase("date")) || sortBy.equalsIgnoreCase("date")) {
            sortBy = "CMACDateTime";
        } else {
            sortBy = "cmac_message.CMACMessagenumber";
        }

        if (isNullOrEmpty(sortOrder) || (!sortOrder.equalsIgnoreCase("ASC"))
                && !sortOrder.equalsIgnoreCase("DESC")) {
            sortOrder = "DESC";
        } else {
            sortOrder = sortOrder.toUpperCase();
        }

        String sorting = "ORDER BY " + sortBy + " " + sortOrder + " ";

        //make sure page is positive
        if (page < 1) {
            page = 1;
        }

        String limit = "LIMIT " + (PAGE_SIZE + 1) + " OFFSET " +
                (PAGE_SIZE * (page - 1)) + ";";

        StringBuilder query = new StringBuilder(2000)
                .append(baseQuery)
                .append(filters)
                .append(grouping)
                .append(sorting)
                .append(limit);

        //override default exception response to avoid showing stacktrace, which may contain table names
        List<MessageStatsResult> resultList;
        String commoneName;
        try {
            resultList = dbTemplate.query(query.toString(), new StatsResultsMapper());
            commoneName = dbTemplate.queryForObject(nameQuery, String.class);
        } catch (BadSqlGrammarException e) {
            e.printStackTrace();
            throw new InternalError("Bad SQL Grammar");
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.set("messageStats", mapper.valueToTree(resultList.subList(0, Math.min(resultList.size(), 9))));
        root.set("commonName", mapper.valueToTree(commoneName));
        root.set("prev", BooleanNode.valueOf(page > 1));
        root.set("next", BooleanNode.valueOf(resultList.size() > 9));


        return ResponseEntity.ok(root);
    }

    /**
     * Parses the sample CAP message file and returns it in an
     * HTTP response body. This endpoint is for testing purposes
     * only
     *
     * @return HTTP 200 OK and an XML CAP message
     */
    @GetMapping(value = "/parseCapMessage", produces = "application/xml")
    public ResponseEntity<CAPMessageModel> parseCapMessage() {
        CAPMessageModel result = XMLParser.parseCAP("src/main/resources/sampleCapMessage.xml");

        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint to test CAP to CmAC conversion. This endpoint
     * converts a CAP message to CMAC, stores it in the database,
     * and returns the CMAC message in the response body
     *
     * @return HTTP 200 OK and an XML CMAC message body if the
     *         message was successfully added to the database,
     *         otherwise HTTP 400 BAD REQUEST
     */
    @GetMapping(value = "/capToCmac", produces = "application/xml")
    public ResponseEntity<CMACMessageModel> capToCmac() {
        CAPMessageModel result = XMLParser.parseCAP("src/main/resources/sampleCapMessage.xml");

        CMACMessageModel cmac = result.toCmac();

        try {
            if (!cmac.addToDatabase(dbTemplate)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add message to database");
            }
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Key Exception: Could not add message" +
                    " to database because a message with the same CMAC_message_number already exists");
        }

        return ResponseEntity.ok(cmac);
    }

    /**
     * Debugging endpoint for IPAWS connection validation
     *
     * @return HTTP 200 OK and an XML CMAC message body if the
     *         message was successfully added to the database,
     *         otherwise HTTP 400 BAD REQUEST
     */
    @GetMapping(value = "/getIpawsAlerts")
    public ResponseEntity<Boolean> getIpawsAlerts() {
        Boolean result;
        try {
            getMessageFromIpaws("prod", ZonedDateTime.now(Clock.systemUTC()).minusMinutes(40), "public");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return ResponseEntity.ok(result);
    }
}
