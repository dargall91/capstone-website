package com.capstone.wea.entities;

import com.capstone.wea.model.cap.CAPMessageModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@JacksonXmlRootElement(localName = "CMAC_Alert_Attributes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMACMessage {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageNumber;
    @JsonProperty("CMAC_cap_identifier")
    @Column(length = Integer.MAX_VALUE)
    private String capIdentifier;
    @JsonProperty("CMAC_sender")
    private String sender;
    @JsonProperty("CMAC_sent_date_time")
    private OffsetDateTime sentDateTime;
    @JsonProperty("CMAC_status")
    private String status;
    @JsonProperty("CMAC_message_type")
    private String messageType;
    @JsonProperty("CMAC_cap_alert_uri")
    private String alertUri;
    @JsonProperty("CMAC_cap_sent_date_time")
    private OffsetDateTime capSentDateTime;
    @JsonIgnore
    private OffsetDateTime expires;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="messageNumber")
    @JsonProperty("CMAC_alert_info")
    private CMACAlertInfo alertInfo;

    public CMACMessage() { }

    public CMACMessage(CAPMessageModel capMessageModel) {
        capIdentifier = capMessageModel.getIdentifier();
        sender = capMessageModel.getSender().toLowerCase();
        sentDateTime = OffsetDateTime.parse(capMessageModel.getSent()).atZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();
        status = capMessageModel.getStatus();
        messageType = capMessageModel.getMsgType();
        capSentDateTime = sentDateTime;
        expires = OffsetDateTime.parse(capMessageModel.getInfo().getExpires()).atZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();
        alertInfo = new CMACAlertInfo(capMessageModel.getInfo());
    }

    @JsonProperty("Cmac_message_number")
    public String getMessageNumberHex() {
        return String.format("%08X", messageNumber);
    }

    @JsonIgnore
    public Integer getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getCapIdentifier() {
        return capIdentifier;
    }

    public void setCapIdentifier(String capIdentifier) {
        this.capIdentifier = capIdentifier;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public OffsetDateTime getSentDateTime() {
        return sentDateTime.withOffsetSameInstant(ZoneOffset.UTC);
    }

    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = OffsetDateTime.parse(sentDateTime).atZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();
    }

    public OffsetDateTime getCapSentDateTime() {
        return capSentDateTime.withOffsetSameInstant(ZoneOffset.UTC);
    }

    public void setCapSentDateTime(String capSentDateTime) {
        this.capSentDateTime = OffsetDateTime.parse(capSentDateTime).atZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();;
    }

    public CMACAlertInfo getAlertInfo() {
        return alertInfo;
    }

    public void setAlertInfo(CMACAlertInfo alertInfo) {
        this.alertInfo = alertInfo;
    }

    public void setExpires(OffsetDateTime expires) {
        this.expires = expires;
    }
}
