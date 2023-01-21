package com.capstone.wea.model.cmac;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CMAC_alert_info")
public class CMACAlertInfoModel {
    @JsonProperty("CMAC_category")
    private String category;
    @JsonProperty("CMAC_severity")
    private String severity;
    @JsonProperty("CMAC_urgency")
    private String urgency;
    @JsonProperty("CMAC_certainty")
    private String certainty;
    @JsonProperty("CMAC_expires_date_time")
    private String expires;

    @JsonProperty("CMAC_sender_name")
    private String senderName;

    @JsonProperty("CMAC_referenced_message_number")
    private String referenceNumber;

    @JsonProperty("CMAC_Alert_Area")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CMACAlertAreaModel> alertAreaList;

    @JsonProperty("CMAC_Alert_Text")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CMACAlertTextModel> alertTextList;

    public String getExpires() {
        return expires;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getCategory() {
        return category;
    }

    public String getSeverity() {
        return severity;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getCertainty() {
        return certainty;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public void setCertainty(String certainty) {
        this.certainty = certainty;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setAlertAreaList(List<CMACAlertAreaModel> alertAreaList) {
        this.alertAreaList = alertAreaList;
    }

    public void setAlertTextList(List<CMACAlertTextModel> alertTextList) {
        this.alertTextList = alertTextList;
    }

    public boolean addToDatabase(JdbcTemplate dbTemplate, int messageNumber, String capIdentifier) throws Exception {
        for (int i = 0; i < alertAreaList.size(); i++) {
            alertAreaList.get(i).addToDatabase(dbTemplate, messageNumber, capIdentifier, i);
        }

        for (CMACAlertTextModel alertText : alertTextList) {
            if (!alertText.addToDatabase(dbTemplate, messageNumber, capIdentifier)) {
                return false;
            }
        }

        return true;
    }
}
