package com.capstone.wea.model.cmac;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.jdbc.core.JdbcTemplate;

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
    private CMACMessageAlertInfo alertInfo;

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
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setAlertUri(String alertUri) {
        this.alertUri = alertUri;
    }

    public void setCapIdentifier(String capIdentifier) {
        this.capIdentifier = capIdentifier;
    }

    public void setCapSentDateTime(String capSentDateTime) {
        this.capSentDateTime = capSentDateTime;
    }

    public void setAlertInfo(CMACMessageAlertInfo alertInfo) {
        this.alertInfo = alertInfo;
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
        String query = "INSERT INTO alert_db.cmac_message " +
                "VALUES ('" + messageNumber + "', '" + capIdentifier + "', '" + sender + "', '" + sentDateTime + "', " +
                "'" + messageType + "');";

        //failed to insert
        if (dbTemplate.update(query) == 0) {
            return false;
        }

        //If another part of this message fails to insert, delete all entries for this message in all tables
        if (!alertInfo.addToDatabse(dbTemplate, messageNumber, capIdentifier)) {
            removeFromDatabase(dbTemplate);
            return false;
        }

        return true;
    }

    private void removeFromDatabase(JdbcTemplate dbTemplate) {
        String query = "DELETE FROM alert_db.cmac_circle_coordinates " +
                "WHERE CMACMessageNumber = '" + messageNumber + "';";

        query = "DELETE FROM alert_db.cmac_polygon_coordinates " +
                "WHERE CMACMessageNumber = '" + messageNumber + "';";

        query = "DELETE FROM alert_db.cmac_area_description " +
                "WHERE CMACMessageNumber = '" + messageNumber + "';";

        query = "DELETE FROM alert_db.cmac_alert " +
                "WHERE CMACMessageNumber = '" + messageNumber + "';";

        query = "DELETE FROM alert_db.cmac_message " +
                "WHERE CMACMessageNumber = '" + messageNumber + "';";
    }
}
