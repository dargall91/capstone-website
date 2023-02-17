package com.capstone.wea.entities;

import com.capstone.wea.model.cap.CAPAreaModel;
import com.capstone.wea.model.cap.CAPInfoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Entity
@JacksonXmlRootElement(localName = "CMAC_alert_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMACAlertInfo {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alertInfoId;
    @JsonProperty("CMAC_category")
    private String category;
    @JsonProperty("CMAC_severity")
    private String severity;
    @JsonProperty("CMAC_urgency")
    private String urgency;
    @JsonProperty("CMAC_certainty")
    private String certainty;
    @JsonProperty("CMAC_expires_date_time")
    private OffsetDateTime expires;
    @JsonProperty("CMAC_sender_name")
    @Column(length = 200)
    private String senderName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="alertInfoId")
    @JsonProperty("CMAC_Alert_Area")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CMACAlertArea> alertAreaList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="alertInfoId")
    @JsonProperty("CMAC_Alert_Text")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CMACAlertText> alertTextList = new ArrayList<>();

    public CMACAlertInfo() { }

    public CMACAlertInfo(CAPInfoModel capInfoModel) {
        category = capInfoModel.getCategory();
        severity = capInfoModel.getSeverity();
        urgency = capInfoModel.getUrgency();
        certainty = capInfoModel.getCertainty();
        expires = OffsetDateTime.parse(capInfoModel.getExpires()).atZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();;
        senderName = capInfoModel.getSenderName();

        for (CAPAreaModel capAreaModel : capInfoModel.getArea()) {
            alertAreaList.add(new CMACAlertArea(capAreaModel));
        }

        alertTextList.add(new CMACAlertText(capInfoModel.getHeadline(), capInfoModel.getDescription()));
    }

    public int getAlertInfoId() {
        return alertInfoId;
    }

    public void setAlertInfoId(int alertInfoId) {
        this.alertInfoId = alertInfoId;
    }

    public OffsetDateTime getExpires() {
        return expires.withOffsetSameInstant(ZoneOffset.UTC);
    }

    public void setExpires(String expires) {
        this.expires = OffsetDateTime.parse(expires);
    }

    public List<CMACAlertArea> getAlertAreaList() {
        return alertAreaList;
    }

    public void setAlertAreaList(List<CMACAlertArea> alertAreaList) {
        this.alertAreaList = alertAreaList;
    }
}
