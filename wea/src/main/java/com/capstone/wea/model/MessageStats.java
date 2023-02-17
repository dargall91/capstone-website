package com.capstone.wea.model;

import com.capstone.wea.Util.Util;
import com.capstone.wea.entities.CMACMessage;
import com.capstone.wea.model.sqlresult.Coordinate;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

public class MessageStats {
    private int messageNumber;
    private String capIdentifier;
    private String messageType;
    private List<Coordinate> coordinates;

    public MessageStats(CMACMessage message) {
        messageNumber = message.getMessageNumber();
        capIdentifier = message.getCapIdentifier();
        messageType = message.getMessageType();

        String polygonString = message.getAlertInfo().getAlertAreaList().get(0).getPolygon();
        String circleString = message.getAlertInfo().getAlertAreaList().get(0).getCircle();

        if (!Util.isNullOrBlank(polygonString)) {
            coordinates = new ArrayList<>();
            List<String> splitPolygonString = List.of(polygonString.split(" "));

            for (String coordinatePair : splitPolygonString) {
                List<String> latLong = List.of(coordinatePair.split(","));
                coordinates.add(new Coordinate(latLong.get(0), latLong.get(1)));
            }
        } else if (!Util.isNullOrBlank(circleString)) {
            coordinates = new ArrayList<>();
        } else {

        }
    }

    public int getMessageNumber() {
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

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
