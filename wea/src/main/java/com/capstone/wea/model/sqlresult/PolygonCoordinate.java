package com.capstone.wea.model.sqlresult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolygonCoordinate {
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lon")
    private String lon;
    public PolygonCoordinate(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
