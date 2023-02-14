package com.capstone.wea.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JacksonXmlRootElement(localName = "CMAC_alert_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMACAlertInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="AlertInfoId")
    @JsonProperty("CMAC_Alert_Area")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CMACAlertArea> alertAreaList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="AlertInfoId")
    @JsonProperty("CMAC_Alert_Text")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CMACAlertText> alertTextList = new ArrayList<>();
}
