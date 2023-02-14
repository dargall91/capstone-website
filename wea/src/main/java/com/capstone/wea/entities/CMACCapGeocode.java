package com.capstone.wea.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

@Entity
@JacksonXmlRootElement(localName = "CMAC_cap_geocode")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMACCapGeocode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("valueName")
    private String valueName;
    @JsonProperty("value")
    private String value;

}