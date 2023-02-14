package com.capstone.wea.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CMAC_Alert_Text")
public class CMACAlertText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("CMAC_text_language")
    private String language;
    @JsonProperty("CMAC_short_text_alert_message_length")
    private int shortLength;
    @JsonProperty("CMAC_short_text_alert_message")
    private String shortMessage;
    @JsonProperty("CMAC_long_text_alert_message_length")
    private int longLength;
    @JsonProperty("CMAC_long_text_alert_message")
    private String longMessage;

    public CMACAlertText() { }

    public CMACAlertText(String headline, String description) {
        if (headline == null || headline.isBlank()) {
            headline = "No short message provided";
        }

        if (description == null || description.isBlank()) {
            description = "No long message provided";
        }

        language = "English";
        shortLength = headline.length();
        shortMessage = headline.replace("\n", " ");
        longLength = description.length();
        longMessage = description.replace("\n", " ");
    }
}