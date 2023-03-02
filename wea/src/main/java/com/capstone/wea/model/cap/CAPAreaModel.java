package com.capstone.wea.model.cap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "area")
public class CAPAreaModel {
    @JsonProperty("areaDesc")
    private String areaDesc;
    @JsonProperty("polygon")
    private String polygon;
    @JsonProperty("circle")
    private String circle;
    @JsonProperty("geocode")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CAPGeocodeModel> geocode;

    public String getAreaDesc() {
        return areaDesc;
    }

    public String getPolygon() {
        return polygon;
    }

    public String getCircle() {
        return circle;
    }

    public List<CAPGeocodeModel> getGeocode() {
        return geocode;
    }
}
