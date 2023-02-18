package com.capstone.wea.controller;

import com.capstone.wea.Util.IPAWSInterface;
import com.capstone.wea.Util.Util;
import com.capstone.wea.entities.CMACMessage;
import com.capstone.wea.model.MessageStats;
import com.capstone.wea.model.cap.IPAWSMessageList;

import com.capstone.wea.repositories.projections.MessageDataProjection;
import com.capstone.wea.repositories.projections.CollectedStatsProjections;
import com.capstone.wea.repositories.DeviceRepository;
import com.capstone.wea.repositories.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sql.DataSource;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RequestMapping("/wea/api/")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WEAController {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Endpoint to request a WEA message from the server. First, it checks if there are any non-expired messages in
     * the database. If there are, the oldest one is returned. If there are not, the IPAWS API is queried. If at least
     * one message is found on IPAWS, the oldest one is returned. If no messages are found, 404 is returned.
     *
     * @return HTTP 200 OK and an XML formatted WEA message
     */
    @GetMapping(value = "getMessage", produces = "application/xml")
    public ResponseEntity<?> getMessage() {
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
     * Endpoint to which the data collected from the mobile device will be sent. The uploaded data will be added to
     * the database.
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

        URI location = ServletUriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/wea/api/getUpload?identifier=" + userData.getId())
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Gets a data upload represented by a unique identifier
     *
     * @param identifier Unique upload identifier
     * @return HTTP 200 OK and the uploaded data
     *         in XML format, or HTTP 404 NOT
     *         FOUND if the identifier is invalid
     */
    @GetMapping(value = "getUpload")
    public ResponseEntity<?> getUpload(@RequestParam long identifier) {
        Optional<com.capstone.wea.entities.CollectedDeviceData> data = deviceRepository.findById(identifier);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data.get());
    }

    /**
     * Gets the list of all CMAC_messages sent by a specified CMAC_sender and the collected stats for each message.
     * Results are returned in pages, with up to nine messages per page
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
        ClassPathResource getDeviceStats = new ClassPathResource("device_stats_procedure.sql");
        ClassPathResource getMessageData = new ClassPathResource("message_data_procedure.sql");
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(getDeviceStats, getMessageData);
        resourceDatabasePopulator.setSeparator(ScriptUtils.EOF_STATEMENT_SEPARATOR);
        resourceDatabasePopulator.execute(dataSource);

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

        List<MessageStats> messageStatsList = new ArrayList<>();

        List<CollectedStatsProjections> deviceStats = deviceRepository.getDeviceStats(sender, page,
                Util.isNullOrBlank(messageNumber) ? null : Integer.parseInt(messageNumber, 16), messageType,
                fromDate, toDate, orderByDate, orderByDesc);

        List<MessageDataProjection> messageData = messageRepository.getMessageData(sender, page,
                Util.isNullOrBlank(messageNumber) ? null : Integer.parseInt(messageNumber, 16), messageType,
                fromDate, toDate, orderByDate, orderByDesc);

        for (int i = 0, j = 0; i < messageData.size() && i < 9; i++) {
            if (deviceStats.size() > j && deviceStats.get(j).getMessageNumber() == messageData.get(i).getMessageNumber()) {
                messageStatsList.add(new MessageStats(deviceStats.get(j), messageData.get(i)));
                j++;
            } else {
                messageStatsList.add(new MessageStats(null, messageData.get(i)));
            }
        }

        //TODO: deviceCount should be 90-95% of number that should have been hit
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.set("messageStats", mapper.valueToTree(messageStatsList));
        root.set("commonName", mapper.valueToTree(commonName));
        root.set("prev", BooleanNode.valueOf(page > 1));
        root.set("next", BooleanNode.valueOf(messageData.size() > 9));

        return ResponseEntity.ok(root);
    }
}
