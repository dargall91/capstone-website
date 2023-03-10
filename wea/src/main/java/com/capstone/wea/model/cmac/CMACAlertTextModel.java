package com.capstone.wea.model.cmac;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CMAC_Alert_Text")
public class CMACAlertTextModel {
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

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setShortLength(int shortLength) {
        this.shortLength = shortLength;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
        shortLength = this.shortMessage.length();
    }

    public void setLongLength(int longLength) {
        this.longLength = longLength;
    }

    public void setLongMessage(String longMessage) {
        this.longMessage = longMessage;
        longLength = this.longMessage.length();
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public String getLongMessage() {
        return longMessage;
    }

    public boolean addToDatabase(JdbcTemplate jdbcTemplate, int messageNumber, String capIdentifier) {
        String fullLanguageName;

        if (language.equalsIgnoreCase("en-us") || language.equalsIgnoreCase("english")) {
            fullLanguageName = "English";
        } else {
            fullLanguageName = "Spanish";
        }

        SimpleJdbcInsert simpleJdbcCall = new SimpleJdbcInsert(jdbcTemplate).withTableName("cmac_alert_text");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("CMACMessageNumber", messageNumber)
                .addValue("CMACCapIdentifier", capIdentifier)
                .addValue("CMACLanguage", fullLanguageName)
                .addValue("CMACShortMessage", shortMessage)
                .addValue("CMACLongMessage", longMessage);

        int updateCount = simpleJdbcCall.execute(params);
        
        return updateCount != 0;
    }
}
