package com.capstone.wea.model.cmac;

import com.capstone.wea.model.sqlresult.mappers.CMACAlertAreaMapper;
import com.capstone.wea.model.sqlresult.mappers.CMACAlertTextMapper;
import com.capstone.wea.model.sqlresult.mappers.CMACMessageMapper;
import com.capstone.wea.model.sqlresult.mappers.CoordinatesMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CMAC_Alert_Attributes")
public class CMACMessageModel {
    @JacksonXmlProperty(isAttribute = true)
    private String xmlns = "cmac:2.0";
    @JsonProperty("CMAC_protocol_version")
    private String protocolVersion = "2.0";
    @JsonProperty("CMAC_sending_gateway_id")
    private String sendingGatewayId = "http://wea_alert_gateway.gov";
    @JsonProperty("CMAC_message_number")
    private String messageNumber;

    @JsonProperty("CMAC_sender")
    private String sender;

    @JsonProperty("CMAC_sent_date_time")
    private String sentDateTime;

    @JsonProperty("CMAC_status")
    private String status;

    @JsonProperty("CMAC_message_type")
    private String messageType;

    @JsonProperty("CMAC_cap_alert_uri")
    private String alertUri;

    @JsonProperty("CMAC_cap_identifier")
    private String capIdentifier;

    @JsonProperty("CMAC_cap_sent_date_time")
    private String capSentDateTime;

    @JsonProperty("CMAC_alert_info")
    private CMACAlertInfoModel alertInfo;

    public CMACMessageModel() { }

    /**
     * Constructs a new CMACMessage from the database with the specified messageNumber and capIdentifier
     *
     * @param messageNumber the CMACMessageNumber
     * @param capIdentifier the CMACCapIdentifier
     * @param jdbcTemplate the JdbcTemplate which has a connection tot eh database
     */
    public CMACMessageModel(int messageNumber, String capIdentifier, JdbcTemplate jdbcTemplate) {
        String query = "SELECT * " +
            "FROM alert_db.cmac_message " +
            "WHERE CMACMessageNumber = " + messageNumber + " " +
            "AND CMACCapIdentifier = '" + capIdentifier + "';";

        jdbcTemplate.query(query, new CMACMessageMapper(this));

        query = "SELECT CMACLanguage, CMACShortMessage, CMACLongMessage " +
                "FROM alert_db.cmac_alert_text " +
                "WHERE CMACMessageNumber = " + messageNumber + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "';";

        addAlertTextList(jdbcTemplate.query(query, new CMACAlertTextMapper()));

        List<CMACAlertAreaModel> cmacAreaList = new ArrayList<>();
        cmacAreaList.add(new CMACAlertAreaModel());

        //Get CMAC AlertArea data
        query = "SELECT AreaNames, CMASGeocodes, CMACPolygon, CMACCircle " +
                "FROM alert_db.cmac_area_description " +
                "WHERE CMACMessageNumber = " + messageNumber + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "' " +
                "LIMIT 1;";

        jdbcTemplate.query(query, new CMACAlertAreaMapper(cmacAreaList));

        //polygon coordinates
        query = "SELECT Latitude, Longitude " +
                "FROM alert_db.cmac_polygon_coordinates " +
                "WHERE CMACMessageNumber = " + messageNumber + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "' " +
                "AND AreaId = " + 1 + ";";

        List<String> polyCoordList = jdbcTemplate.query(query, new CoordinatesMapper());

        StringBuilder polygon = new StringBuilder();
        for (String polyPair : polyCoordList) {
            if (!polygon.toString().isBlank()) {
                polygon.append(",").append(polyPair);
            } else {
                polygon.append(polyPair);
            }
        }

        //circle coordinates
        query = "SELECT Latitude, Longitude " +
                "FROM alert_db.cmac_circle_coordinates " +
                "WHERE CMACMessageNumber = " + messageNumber + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "' " +
                "AND AreaId = " + 1 + ";";

        List<String> circCoordList = jdbcTemplate.query(query, new CoordinatesMapper());

        StringBuilder circle = new StringBuilder();
        for (String circlePair : circCoordList) {
            if (!circle.toString().isBlank()) {
                circle.append(",").append(circlePair);
            } else {
                circle.append(circlePair);
            }
        }

        addAlertAreaList(cmacAreaList);
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public void setSendingGatewayId(String sendingGatewayId) {
        this.sendingGatewayId = sendingGatewayId;
    }

    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = sentDateTime;
        capSentDateTime = sentDateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setAlertUri() {
        alertUri = "http://localhost:8080/wea/getMessage" + messageNumber;
    }

    public void setCapIdentifier(String capIdentifier) {
        this.capIdentifier = capIdentifier;
    }

    public void setAlertInfo(CMACAlertInfoModel alertInfo) {
        this.alertInfo = alertInfo;
    }

    public void addAlertAreaList(List<CMACAlertAreaModel> alertAreaList) {
        alertInfo.setAlertAreaList(alertAreaList);
    }

    public void addAlertTextList(List<CMACAlertTextModel> alertTextList) {
        alertInfo.setAlertTextList(alertTextList);
    }

    /**
     * Adds this CMAC message to the specified database
     *
     * @param jdbcTemplate The database to which to add this
     *                     message
     * @return True if the message was added, false if it
     * was not
     */
    public boolean addToDatabase(JdbcTemplate jdbcTemplate) {
        SimpleJdbcInsert simpleJdbcCall = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("cmac_message")
                .usingGeneratedKeyColumns("CMACMessageNumber");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("CMACCapIdentifier", capIdentifier)
                .addValue("CMACSender", sender)
                .addValue("CMACDateTime", OffsetDateTime.parse(sentDateTime))
                .addValue("CMACStatus", status)
                .addValue("CMACMessageType", messageType)
                .addValue("CMACSenderName", alertInfo.getSenderName())
                .addValue("CMACExpiresDateTime", OffsetDateTime.parse(alertInfo.getExpires()))
                .addValue("CMACCategory", alertInfo.getCategory())
                .addValue("CMACSeverity", alertInfo.getSeverity())
                .addValue("CMACUrgency", alertInfo.getUrgency())
                .addValue("CMACCertainty", alertInfo.getCertainty())
                .addValue("CMACReferencedCapIdentifier", alertInfo.getReferenceNumber());

        try {
            Number messageNumberInt = simpleJdbcCall.executeAndReturnKey(params);
            messageNumber = messageNumberInt.toString();

            //If another part of this message fails to insert, delete all entries for this message in all tables
            if (!alertInfo.addToDatabase(jdbcTemplate, messageNumberInt.intValue(), capIdentifier)) {
                removeFromDatabase(jdbcTemplate);
                return false;
            }
        } catch (Exception e) {
            removeFromDatabase(jdbcTemplate);
            return false;
        }

        return true;
    }

    private void removeFromDatabase(JdbcTemplate dbTemplate) {
        int msgNum = Integer.parseInt(messageNumber, 16);

        String query = "DELETE FROM alert_db.cmac_circle_coordinates " +
                "WHERE CMACMessageNumber = " + msgNum + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "';";

        dbTemplate.update(query);

        query = "DELETE FROM alert_db.cmac_polygon_coordinates " +
                "WHERE CMACMessageNumber = " + msgNum + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "';";

        dbTemplate.update(query);

        query = "DELETE FROM alert_db.cmac_area_description " +
                "WHERE CMACMessageNumber = " + msgNum + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "';";

        dbTemplate.update(query);

        query = "DELETE FROM alert_db.cmac_alert_text " +
                "WHERE CMACMessageNumber = " + msgNum + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "';";

        dbTemplate.update(query);

        query = "DELETE FROM alert_db.cmac_message " +
                "WHERE CMACMessageNumber = " + msgNum + " " +
                "AND CMACCapIdentifier = '" + capIdentifier + "';";

        dbTemplate.update(query);
    }
}
