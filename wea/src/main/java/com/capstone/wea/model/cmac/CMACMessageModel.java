package com.capstone.wea.model.cmac;

import com.capstone.wea.model.sqlresult.mappers.CMACAlertAreaMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;

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
     * @param dbTemplate The database to which to add this
     *                   mmessage
     * @return True if the message was added, false if it
     * was not
     */
    public boolean addToDatabase(JdbcTemplate dbTemplate) {
        String referenceNumber = alertInfo.getReferenceNumber() == null ?
                "NULL" : "'" + alertInfo.getReferenceNumber() + "'";
        String query = "INSERT INTO alert_db.cmac_message " +
                "VALUES (NULL, '" + capIdentifier + "', '" + sender + "', '" +
                sentDateTime.replace("Z", "") + "', '" + status + "', '" + messageType + "', '" +
                alertInfo.getSenderName() + "', '" + alertInfo.getExpires().replace("Z", "") + "', '" +
                alertInfo.getCategory() + "', '" + alertInfo.getSeverity() + "', '" + alertInfo.getUrgency() + "', '" +
                alertInfo.getCertainty() + "', " + referenceNumber + ");";

        //TODO: cap identifier should be the sole primary key so that attempts to insert a duplicate from IPAWS will
        // fail. Also only add messages of type Test, Alert, and Update
        try {
            int messageNumberInt;
            //failed to insert
            if (dbTemplate.update(query) == 0) {
                return false;
            } else {
                messageNumberInt = dbTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            }

            messageNumber = String.format("%08X", messageNumberInt);

            //If another part of this message fails to insert, delete all entries for this message in all tables
            if (!alertInfo.addToDatabase(dbTemplate, messageNumberInt, capIdentifier)) {
                removeFromDatabase(dbTemplate);
                return false;
            }
        } catch (BadSqlGrammarException e) {
            removeFromDatabase(dbTemplate);
            return false;
        }


        return true;
    }

    private void removeFromDatabase(JdbcTemplate dbTemplate) {
        int msgNum = Integer.parseInt(messageNumber, 16);

        String query = "DELETE FROM alert_db.cmac_circle_coordinates " +
                "WHERE CMACMessageNumber = " + msgNum + ";";

        dbTemplate.update(query);

        query = "DELETE FROM alert_db.cmac_polygon_coordinates " +
                "WHERE CMACMessageNumber = " + msgNum + ";";

        dbTemplate.update(query);

        query = "DELETE FROM alert_db.cmac_area_description " +
                "WHERE CMACMessageNumber = " + msgNum + ";";

        dbTemplate.update(query);

        query = "DELETE FROM alert_db.cmac_alert_text " +
                "WHERE CMACMessageNumber = " + msgNum + ";";

        dbTemplate.update(query);

        query = "DELETE FROM alert_db.cmac_message " +
                "WHERE CMACMessageNumber = " + msgNum + ";";

        dbTemplate.update(query);
    }
}
