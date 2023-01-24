package com.capstone.wea.model.cmac;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Map;

@JacksonXmlRootElement(localName = "CMAC_device_data")
public class CollectedDeviceData {
    @JsonProperty("CMAC_user_time_received")
    private LocalDateTime timeReceived;
    @JsonProperty("CMAC_user_time_displayed")
    private LocalDateTime timeDisplayed;
    @JsonProperty("CMAC_user_location_received")
    private String locationReceived;
    @JsonProperty("CMAC_user_location_displayed")
    private String locationDisplayed;
    @JsonProperty("CMAC_message_number")
    private String messageNumber;
    @JsonProperty("CMAC_cap_identifier")
    private String capIdentifier;
    @JsonProperty("Received_Outside_Area")
    private boolean receivedOutsideArea;
    @JsonProperty("Displayed_Outside_Area")
    private boolean displayedOutsideArea;
    @JsonProperty("Received_After_Expired")
    private boolean receivedAfterExpired;
    @JsonProperty("Displayed_After_Expired")
    private boolean displayedAfterExpired;
    @JsonProperty("id")
    private int uploadId;

    /**
     * Gets the unique ID of this collected data in the database
     * @return The unique identifier for this data
     */
    public int getUploadId() {
        return uploadId;
    }

    public LocalDateTime getTimeReceived() {
        return timeReceived;
    }

    public LocalDateTime getTimeDisplayed() {
        return timeDisplayed;
    }

    public String getLocationReceived() {
        return locationReceived;
    }

    public String getLocationDisplayed() {
        return locationDisplayed;
    }

    public String getMessageNumber() {
        return messageNumber;
    }

    public int getMessageNumberInt() {
        return Integer.parseInt(messageNumber, 16);
    }

    public String getCapIdentifier() {
        return capIdentifier;
    }

    public boolean isReceivedOutsideArea() {
        return receivedOutsideArea;
    }

    public boolean isDisplayedOutsideArea() {
        return displayedOutsideArea;
    }

    public boolean isReceivedAfterExpired() {
        return receivedAfterExpired;
    }

    public boolean isDisplayedAfterExpired() {
        return displayedAfterExpired;
    }

    /**
     * Sets the time this device received the message
     *
     * @param timeReceived The date and time of receipt
     */
    public void setTimeReceived(LocalDateTime timeReceived) {
        this.timeReceived = timeReceived;
    }

    /**
     * Sets the time this device displayed the message
     *
     * @param timeDisplayed The date and time of display
     */
    public void setTimeDisplayed(LocalDateTime timeDisplayed) {
        this.timeDisplayed = timeDisplayed;
    }

    /**
     * Sets the location this device received the message
     *
     * @param locationReceived The Geocode where the device was
     *                         when the message was received
     */
    public void setLocationReceived(String locationReceived) {
        this.locationReceived = locationReceived;
    }

    /**
     * Sets the location this device displayed the message
     *
     * @param locationDisplayed The Geocode where the device was
     *                         when the message was displayed
     */
    public void setLocationDisplayed(String locationDisplayed) {
        this.locationDisplayed = locationDisplayed;
    }

    /**
     * The CMAC_message_number for which the stats collected
     * are for
     *
     * @param messageNumber The CMAC_message_number
     */
    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    /**
     * Sets the CMAC_cap_identifier for which these stats are for
     * @param capIdentifier
     */
    public void setCapIdentifier(String capIdentifier) {
        this.capIdentifier = capIdentifier;
    }

    /**
     * Sets the unique upload identifier for this data in the database
     *
     * @param uploadId This data's upload ID
     */
    public void setUploadId(int uploadId) {
        this.uploadId = uploadId;
    }

    public void setReceivedOutsideArea(boolean receivedOutsideArea) {
        this.receivedOutsideArea = receivedOutsideArea;
    }

    public void setDisplayedOutsideArea(boolean displayedOutsideArea) {
        this.displayedOutsideArea = displayedOutsideArea;
    }

    public void setReceivedAfterExpired(boolean receivedAfterExpired) {
        this.receivedAfterExpired = receivedAfterExpired;
    }

    public void setDisplayedAfterExpired(boolean displayedAfterExpired) {
        this.displayedAfterExpired = displayedAfterExpired;
    }

    public String addToDatabase(DataSource dataSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("device_upload_data")
                .usingGeneratedKeyColumns("UploadId");

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("CMACMessageNumber", getMessageNumberInt())
                .addValue("CMACCapIdentifier", capIdentifier)
                .addValue("LocationReceived", locationReceived)
                .addValue("LocationDisplayed", locationDisplayed)
                .addValue("TimeReceived", timeReceived)
                .addValue("TimeDisplayed", timeDisplayed)
                .addValue("ReceivedOutside", receivedOutsideArea)
                .addValue("DisplayedOutside", displayedOutsideArea)
                .addValue("ReceivedExpired", receivedAfterExpired)
                .addValue("DisplayedExpired", displayedAfterExpired);

        Number uploadId = simpleJdbcInsert.executeAndReturnKey(params);

        return uploadId.toString();
    }
}
