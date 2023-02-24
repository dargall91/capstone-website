package com.capstone.wea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lon")
    private String lon;
    public Coordinate(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
