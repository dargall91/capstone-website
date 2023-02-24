package com.capstone.wea.model.cap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "info")
public class CAPInfoModel {
    @JsonProperty("category")
    private String category;
    @JsonProperty("event")
    private String event;
    @JsonProperty("responseType")
    private String responseType;
    @JsonProperty("urgency")
    private String urgency;
    @JsonProperty("severity")
    private String severity;
    @JsonProperty("certainty")
    private String certainty;
    @JsonProperty("eventCode")
    @JacksonXmlElementWrapper(useWrapping = false)
    private CAPEventCodeModel eventCode;
    @JsonProperty("expires")
    private String expires;
    @JsonProperty("senderName")
    private String senderName;
    @JsonProperty("headline")
    private String headline;
    @JsonProperty("description")
    private String description;
    @JsonProperty("instruction")
    private String instruction;
    @JsonProperty("contact")
    private String contact;
    @JsonProperty("area")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CAPAreaModel> area;

    public String getCategory() {
        return category;
    }

    public String getEvent() {
        return event;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getSeverity() {
        return severity;
    }

    public String getCertainty() {
        return certainty;
    }

    public CAPEventCodeModel getEventCode() {
        return eventCode;
    }

    public String getExpires() {
        return expires;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getContact() {
        return contact;
    }

    public List<CAPAreaModel> getArea() {
        return area;
    }
}
