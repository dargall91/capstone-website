package com.capstone.wea.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Geocode {
    @Id
    private String geocode;
    private String name;

    public String getGeocode() {
        return geocode;
    }

    public String getName() {
        return name;
    }
}
