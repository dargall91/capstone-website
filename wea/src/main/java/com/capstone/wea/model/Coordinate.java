package com.capstone.wea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @JsonIgnore
    public Double getLatDouble() {
        return Double.parseDouble(lat);
    }

    @JsonIgnore
    public Double getLonDouble() {
        return Double.parseDouble(lon);
    }
}
