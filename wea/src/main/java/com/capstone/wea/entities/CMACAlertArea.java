package com.capstone.wea.entities;

import com.capstone.wea.model.cap.CAPAreaModel;
import com.capstone.wea.model.cap.CAPGeocodeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CMAC_Alert_Area")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CMACAlertArea {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long alertAreaId;
    @JsonProperty("CMAC_area_description")
    @Column(length = Integer.MAX_VALUE)
    private String areaDescription;
    @JsonProperty("CMAC_polygon")
    @Column(length = Integer.MAX_VALUE)
    private String polygon;
    @JsonProperty("CMAC_circle")
    @Column(length = Integer.MAX_VALUE)
    private String circle;
    @ElementCollection
    @CollectionTable(name = "CMACCmasGeocode")
    @JsonProperty("CMAC_cmas_geocode")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> geocodeList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="alertAreaId")
    @JsonProperty("CMAC_cap_geocode")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CMACCapGeocode> capGeocodeList = new ArrayList<>();

    public CMACAlertArea() { }

    public CMACAlertArea(CAPAreaModel capAreaModel) {
        areaDescription = capAreaModel.getAreaDesc();
        polygon = capAreaModel.getPolygon();
        circle = capAreaModel.getCircle();

        for (CAPGeocodeModel capGeocodeModel : capAreaModel.getGeocode()) {
            capGeocodeList.add(new CMACCapGeocode(capGeocodeModel));

            if (capGeocodeModel.getValueName().equals("SAME")) {
                geocodeList.add(capGeocodeModel.getValue());
            }
        }
    }

    public long getAlertAreaId() {
        return alertAreaId;
    }

    public void setAlertAreaId(long alertAreaId) {
        this.alertAreaId = alertAreaId;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public List<String> getGeocodeList() {
        return geocodeList;
    }

    public void setGeocodeList(List<String> geocodeList) {
        this.geocodeList = geocodeList;
    }
}
