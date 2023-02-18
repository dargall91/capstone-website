package com.capstone.wea.model;

import com.capstone.wea.Util.Util;
import com.capstone.wea.model.sqlresult.Coordinate;
import com.capstone.wea.repositories.projections.MessageDataProjection;
import com.capstone.wea.repositories.projections.CollectedStatsProjections;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class MessageStats {
    private int messageNumber;
    private String capIdentifier;
    private String messageType;
    private String sentDateTime;
    private String expiresDateTime;
    private List<Coordinate> coordinates;
    private List<String> geocodes = new ArrayList<>();
    private List<String> areaNames = new ArrayList<>();
    private int deviceCount;
    private String averageTime;
    private String shortestTime;
    private String firstReceived;
    private String averageDisplayDelay;
    private String firstDisplayed;
    private int receivedOutside;
    private int displayedOutside;

    public MessageStats(CollectedStatsProjections deviceStats, MessageDataProjection messageData) {
        setDeviceStats(deviceStats);
        setMessageData(messageData);
    }

    private void setDeviceStats(CollectedStatsProjections deviceStats) {
        if (deviceStats == null) {
            return;
        }

        deviceCount = deviceStats.getDeviceCount();
        averageTime = deviceStats.getAverageTime();
        shortestTime = deviceStats.getShortestTime();
        firstReceived = deviceStats.getFirstReceived();
        averageDisplayDelay = deviceStats.getAverageDisplayDelay();
        firstDisplayed = deviceStats.getFirstDisplayed();
        receivedOutside = deviceStats.getReceivedOutside();
        displayedOutside = deviceStats.getDisplayedOutside();
    }

    private void setMessageData(MessageDataProjection messageData) {
        messageNumber = messageData.getMessageNumber();
        capIdentifier = messageData.getCapIdentifier();
        messageType = messageData.getMessageType();
        sentDateTime = messageData.getSentDateTime();
        expiresDateTime = messageData.getExpiresDateTime();

        String polygonString = messageData.getPolygon();
        String circleString = messageData.getCircle();

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
        }

        geocodes = List.of(messageData.getGeocodes().split(","));
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

    public List<String> getAreaNames() {
        return areaNames;
    }

    public void setAreaNames(List<String> areaNames) {
        this.areaNames = areaNames;
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

    public String getShortestTime() {
        return shortestTime;
    }

    public void setShortestTime(String shortestTime) {
        this.shortestTime = shortestTime;
    }

    public String getFirstReceived() {
        return firstReceived;
    }

    public void setFirstReceived(String firstReceived) {
        this.firstReceived = firstReceived;
    }

    public String getAverageDisplayDelay() {
        return averageDisplayDelay;
    }

    public void setAverageDisplayDelay(String averageDisplayDelay) {
        this.averageDisplayDelay = averageDisplayDelay;
    }

    public String getFirstDisplayed() {
        return firstDisplayed;
    }

    public void setFirstDisplayed(String firstDisplayed) {
        this.firstDisplayed = firstDisplayed;
    }

    public int getReceivedOutside() {
        return receivedOutside;
    }

    public void setReceivedOutside(int receivedOutside) {
        this.receivedOutside = receivedOutside;
    }

    public int getDisplayedOutside() {
        return displayedOutside;
    }

    public void setDisplayedOutside(int displayedOutside) {
        this.displayedOutside = displayedOutside;
    }
}
