package com.capstone.wea.model;

import com.capstone.wea.Util.Util;
import com.capstone.wea.model.sqlresult.Coordinate;
import com.capstone.wea.repositories.projections.AreaProjection;
import com.capstone.wea.repositories.projections.CollectedStatsProjections;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MessageStats {
    private int messageNumber;
    private String capIdentifier;
    private String messageType;
    private String sentDateTime;
    private String expiresDateTime;
    private int deviceCount;
    private String averageTime;
    private String firstReceived;
    private String firstDisplayed;
    private List<Coordinate> coordinates;
    private List<String> geocodes;

    public MessageStats(CollectedStatsProjections messageStats, AreaProjection area) {
        messageNumber = messageStats.getMessageNumber();
        capIdentifier = messageStats.getCapIdentifier();
        messageType = messageStats.getMessageType();
        sentDateTime = messageStats.getSentDateTime();
        expiresDateTime = messageStats.getExpiresDateTime();
        averageTime = messageStats.getAverageTime();
        deviceCount = messageStats.getDeviceCount();
        firstReceived = messageStats.getFirstReceived();
        firstDisplayed = messageStats.getFirstDisplayed();

        String polygonString = area.getPolygon();
        String circleString = area.getCircle();

        if (!Util.isNullOrBlank(polygonString)) {
            coordinates = new ArrayList<>();
            List<String> splitPolygonString = List.of(polygonString.split(" "));

            for (String coordinatePair : splitPolygonString) {
                List<String> latLong = List.of(coordinatePair.split(","));
                coordinates.add(new Coordinate(latLong.get(0), latLong.get(1)));
            }
        } else if (!Util.isNullOrBlank(circleString)) {
            coordinates = new ArrayList<>();
            List<String> splitCircleString = List.of(circleString.split(" "));

            for (String coordinatePair : splitCircleString) {
                List<String> latLong = List.of(coordinatePair.split(","));
                coordinates.add(new Coordinate(latLong.get(0), latLong.get(1)));
            }
        } else {
            //geocodes = List.of(area.getGeocodeList().split(","));
        }
    }

    public String getMessageNumber() {
        return String.format("%08X", messageNumber);
    }

    @JsonIgnore
    public int getMessageNumberInt() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getCapIdentifier() {
        return capIdentifier;
    }

    public void setCapIdentifier(String capIdentifier) {
        this.capIdentifier = capIdentifier;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public String getExpiresDateTime() {
        return expiresDateTime;
    }

    public void setExpiresDateTime(String expiresDateTime) {
        this.expiresDateTime = expiresDateTime;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(String averageTime) {
        this.averageTime = averageTime;
    }

    public String getFirstReceived() {
        return firstReceived;
    }

    public void setFirstReceived(String firstReceived) {
        this.firstReceived = firstReceived;
    }

    public String getFirstDisplayed() {
        return firstDisplayed;
    }

    public void setFirstDisplayed(String firstDisplayed) {
        this.firstDisplayed = firstDisplayed;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getGeocodes() {
        return geocodes;
    }

    public void setGeocodes(List<String> geocodes) {
        this.geocodes = geocodes;
    }
}
