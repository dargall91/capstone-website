package com.capstone.wea.entities;

import com.capstone.wea.model.cap.CAPGeocodeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

@Entity
@JacksonXmlRootElement(localName = "CMAC_cap_geocode")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMACCapGeocode {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long geocodeId;
    @JsonProperty("valueName")
    private String valueName;
    @JsonProperty("value")
    private String value;

    public CMACCapGeocode() {
    }

    public CMACCapGeocode(CAPGeocodeModel capGeocodeModel) {
        valueName = capGeocodeModel.getValueName();
        value = capGeocodeModel.getValue();
    }

    public long getGeocodeId() {
        return geocodeId;
    }

    public void setGeocodeId(long geocodeId) {
        this.geocodeId = geocodeId;
    }
}