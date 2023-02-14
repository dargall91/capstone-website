package com.capstone.wea.entities;

import com.capstone.wea.model.cap.CAPMessageModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

@Entity
@JacksonXmlRootElement(localName = "CMAC_Alert_Attributes")
public class CMACMessage {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageNumber;
    @JsonProperty("Cmac_cap_identifier")
    private String capIdentifier;
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
    @JsonProperty("CMAC_cap_sent_date_time")
    private String capSentDateTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    @JsonProperty("CMAC_alert_info")
    private CMACAlertInfo alertInfo;

    public CMACMessage() { }

    public CMACMessage(CAPMessageModel capMessageModel) {
        capIdentifier = capMessageModel.getIdentifier();
        sender = capMessageModel.getSender().toLowerCase();
        sentDateTime = capMessageModel.getSent();
        status = capMessageModel.getStatus();
        messageType = capMessageModel.getMsgType();
        alertInfo = new CMACAlertInfo(capMessageModel.getInfo());
    }

    @JsonProperty("Cmac_message_number")
    public String getMessageNumber() {
        return String.format("%08X", messageNumber);
    }
}
