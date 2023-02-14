package com.capstone.wea.entities;

import com.capstone.wea.model.cap.CAPAreaModel;
import com.capstone.wea.model.cap.CAPInfoModel;
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

    public CMACAlertInfo() { }

    public CMACAlertInfo(CAPInfoModel capInfoModel) {
        category = capInfoModel.getCategory();
        severity = capInfoModel.getSeverity();
        urgency = capInfoModel.getUrgency();
        certainty = capInfoModel.getCertainty();
        expires = capInfoModel.getExpires();
        senderName = capInfoModel.getSenderName();

        for (CAPAreaModel capAreaModel : capInfoModel.getArea()) {
            alertAreaList.add(new CMACAlertArea(capAreaModel));
        }

        alertTextList.add(new CMACAlertText(capInfoModel.getHeadline(), capInfoModel.getDescription()));
    }
}
