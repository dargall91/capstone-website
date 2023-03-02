package com.capstone.wea.model.cap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "geocode")
public class CAPGeocodeModel {
    @JsonProperty("valueName")
    private String valueName;
    @JsonProperty("value")
    private String value;

    public String getValueName() {
        return valueName;
    }

    public String getValue() {
        return value;
    }
}
