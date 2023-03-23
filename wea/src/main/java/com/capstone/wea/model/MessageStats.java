package com.capstone.wea.model;

import com.capstone.wea.Util.Util;
import com.capstone.wea.repositories.projections.MessageDataProjection;
import com.capstone.wea.repositories.projections.CollectedStatsProjections;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.DecimalFormat;
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
    private int received;
    private int expectedReceived;
    private String averageTime;
    private String shortestTime;
    private String firstReceived;
    private String averagePresentationDelay;
    private String firstPresented;
    private int receivedOutside;
    private String averageDistance;
    private String minDistance;
    private String maxDistance;
    private String medianDistance;
    private int presented;
    private int notPresentedOutside;
    private int optedOut;
    private  int presentedDefault;

    public MessageStats(CollectedStatsProjections deviceStats, MessageDataProjection messageData) {
        setDeviceStats(deviceStats);
        setMessageData(messageData);
    }

    private void setDeviceStats(CollectedStatsProjections deviceStats) {
        if (deviceStats == null) {
            averageTime = "N/A";
            shortestTime = "N/A";
            firstReceived = "N/A";
            firstPresented = "N/A";
            averagePresentationDelay = "N/A";
            received = 0;
            receivedOutside = 0;
            notPresentedOutside = 0;
            optedOut = 0;
            presentedDefault = 0;
            presented = 0;
            averageDistance = "0.0";
            medianDistance = "0.0";
            minDistance = "0.0";
            maxDistance = "0.0";
            return;
        }

        DecimalFormat numberFormat = new DecimalFormat("###.##");

        averageTime = Util.nullCoalesce(deviceStats.getAverageTime(), "N/A");
        shortestTime = Util.nullCoalesce(deviceStats.getShortestTime(), "N/A");
        firstReceived = Util.nullCoalesce(deviceStats.getFirstReceived(), "N/A");
        firstPresented = Util.nullCoalesce(deviceStats.getFirstPresented(), "N/A");
        averagePresentationDelay = Util.nullCoalesce(deviceStats.getAveragePresentationDelay(), "N/A");
        received = Util.nullCoalesce(deviceStats.getReceived(), 0);
        receivedOutside = Util.nullCoalesce(deviceStats.getReceivedOutside(), 0);
        notPresentedOutside = Util.nullCoalesce(deviceStats.getNotPresentedOutside(), 0);
        optedOut = Util.nullCoalesce(deviceStats.getOptedOut(), 0);
        presentedDefault = Util.nullCoalesce(deviceStats.getPresentedDefault(), 0);
        presented = Util.nullCoalesce(deviceStats.getPresented(), 0);
        averageDistance = numberFormat.format(Util.nullCoalesce(deviceStats.getAverageDistance(), 0));
        medianDistance = numberFormat.format(Util.nullCoalesce(deviceStats.getMedianDistance(), 0));
        minDistance = numberFormat.format(Util.nullCoalesce(deviceStats.getMinDistance(), 0));
        maxDistance = numberFormat.format(Util.nullCoalesce(deviceStats.getMaxDistance(), 0));

        //random number between 0 and 1
        //divide by 100 to get number between 0 and 0.05
        //add 0.9 to get number between 0.9 and 0.95
        double random = Math.random() / 100.0 * 5.0 + 0.9;
        expectedReceived = (int) Math.ceil(received / random);
    }

    private void setMessageData(MessageDataProjection messageData) {
        messageNumber = messageData.getMessageNumber();
        capIdentifier = messageData.getCapIdentifier();
        messageType = messageData.getMessageType();
        sentDateTime = messageData.getSentDateTime();
        expiresDateTime = messageData.getExpires();

        String polygonString = messageData.getPolygon();
        String circleString = messageData.getCircle();

        if (!Util.isNullOrBlank(polygonString)) {
            coordinates = Util.splitPolygon(polygonString);
        } else if (!Util.isNullOrBlank(circleString)) {
            coordinates = Util.splitPolygon(circleString);
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

    @JsonIgnore
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

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
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

    public String getAveragePresentationDelay() {
        return averagePresentationDelay;
    }

    public void setAveragePresentationDelay(String averagePresentationDelay) {
        this.averagePresentationDelay = averagePresentationDelay;
    }

    public String getFirstPresented() {
        return firstPresented;
    }

    public void setFirstPresented(String firstPresented) {
        this.firstPresented = firstPresented;
    }

    public int getReceivedOutside() {
        return receivedOutside;
    }

    public void setReceivedOutside(int receivedOutside) {
        this.receivedOutside = receivedOutside;
    }

    public void setPresented(int presented) {
        this.presented = presented;
    }

    public String getAverageDistance() {
        return averageDistance;
    }

    public String getMinDistance() {
        return minDistance;
    }

    public String getMaxDistance() {
        return maxDistance;
    }

    public int getPresented() {
        return presented;
    }

    public String getMedianDistance() {
        return medianDistance;
    }

    public int getExpectedReceived() {
        return expectedReceived;
    }

    public int getNotPresentedOutside() {
        return notPresentedOutside;
    }

    public int getOptedOut() {
        return optedOut;
    }

    public int getPresentedDefault() {
        return presentedDefault;
    }
}
