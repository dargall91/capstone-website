package com.capstone.wea.model.sqlresult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.util.List;

public class MessageStatsResult {
    @JsonProperty("messageNumber")
    private String messageNumber;

    @JsonIgnore
    private int messageNumberInt;

    @JsonProperty("messageType")
    private String messageType;

    @JsonProperty("date")
    private String date;
    @JsonProperty("averageTime")
    private Time avgTime;

    @JsonProperty("shortestTime")
    private Time shortTime;

    @JsonProperty("longestTime")
    private Time longTime;

    @JsonProperty("averageDelay")
    private Time avgDelay;

    @JsonProperty("deviceCount")
    private int deviceCount;

    @JsonProperty("receivedOutsideCount")
    private int receivedOutsideCount;

    @JsonProperty("displayedOutsideCount")
    private int displayedOutsideCount;

    @JsonProperty("receivedAfterExpiredCount")
    private int receivedAfterExpiredCount;

    @JsonProperty("displayedAfterExpiredCount")
    private int displayedAfterExpiredCount;

    @JsonProperty("coordinates")
    private List<PolygonCoordinate> polygon;

    @JsonProperty("areas")
    private List<String> areaNames;

    public MessageStatsResult(int messageNumber) {
        messageNumberInt = messageNumber;
        this.messageNumber = String.format("%08X", messageNumber);
    }

    public int getMessageNumberInt() {
        return messageNumberInt;
    }

    /**
     * Sets the CMAC_message number for this message
     *
     * @param messageNumber the message number
     */
    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }

    /**
     * Sets the date that this message was issued by
     * the AO
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the average time between when a message
     * was sent and when all the devices received it
     *
     * @param avgTime the average time
     */
    public void setAvgTime(Time avgTime) {
        this.avgTime = avgTime;
    }

    /**
     * Sets the shortest difference in time between
     * all devices received a message and when it
     * was sent
     *
     * @param shortTime the shortest time
     */
    public void setShortTime(Time shortTime) {
        this.shortTime = shortTime;
    }

    /**
     * Sets the longest difference in time between
     * all devices received a message and when it
     * was sent
     *
     * @param longTime the longest time
     */
    public void setLongTime(Time longTime) {
        this.longTime = longTime;
    }

    /**
     * Sets the average amount of time between
     * when all devices received a message and
     * when it was displayed on the device
     *
     * @param avgDelay the average delay
     */
    public void setAvgDelay(Time avgDelay) {
        this.avgDelay = avgDelay;
    }

    /**
     * Sets the number of devices that received a message
     *
     * @param deviceCount the number of devices
     */
    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    /**
     * Sets the number of devices that were outside the
     * message's target area when the message was received
     *
     * @param receivedOutsideCount the number of devices
     */
    public void setReceivedOutsideCount(int receivedOutsideCount) {
        this.receivedOutsideCount = receivedOutsideCount;
    }

    /**
     * Sets the number of devices that were outside the
     * message's target area when the message was displayed
     *
     * @param displayedOutsideCount the number of devices
     */
    public void setDisplayedOutsideCount(int displayedOutsideCount) {
        this.displayedOutsideCount = displayedOutsideCount;
    }

    /**
     * Sets the alert's CMAC_message_type type (Alert, Update, etc)
     *
     * @param messageType the CMAC_message_type
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * Sets the number of messages that received the message after that
     * message's expiration time
     *
     * @param receivedAfterExpiredCount The number of device
     */
    public void setReceivedAfterExpiredCount(int receivedAfterExpiredCount) {
        this.receivedAfterExpiredCount = receivedAfterExpiredCount;
    }

    /**
     * Sets the number of messages that displayed the message on the device
     * after the message's expiration time
     *
     * @param displayedAfterExpiredCount  The number of device
     */
    public void setDisplayedAfterExpiredCount(int displayedAfterExpiredCount) {
        this.displayedAfterExpiredCount = displayedAfterExpiredCount;
    }

    /**
     * Sets the list of polygon coordinates
     *
     * @param polygon
     */
    public void setPolygon(List<PolygonCoordinate> polygon) {
        this.polygon = polygon;
    }

    /**
     * Sets the list of area names
     *
     * @param areaNames
     */
    public void setAreaNames(List<String> areaNames) {
        this.areaNames = areaNames;
    }
}
