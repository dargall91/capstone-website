package com.capstone.wea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JacksonXmlRootElement(localName = "CMAC_alert_info")
public class CMACAlertInfo {
    @Id
    private int messageNumber;

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
}
