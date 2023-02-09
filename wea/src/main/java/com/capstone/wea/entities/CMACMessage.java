package com.capstone.wea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

@Entity
@JacksonXmlRootElement(localName = "CMAC_Alert_Attributes")
public class CMACMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("CMAC_message_number")
    private int messageNumber;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="messageNumber")
    @JsonProperty("CMAC_alert_info")
    private CMACAlertInfo alertInfo;
}
