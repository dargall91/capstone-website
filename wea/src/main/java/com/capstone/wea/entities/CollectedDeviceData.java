package com.capstone.wea.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectedDeviceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private int messageNumber;
    @Column(length = Integer.MAX_VALUE)
    private String capIdentifier;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime timeReceived;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime timeDisplayed;
    private boolean receivedInside;
    private boolean messagePresented;
    private boolean locationAvailable;
    private double distanceFromPolygon;
    private boolean optedOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(LocalDateTime timeReceived) {
        this.timeReceived = timeReceived;
    }

    public LocalDateTime getTimeDisplayed() {
        return timeDisplayed;
    }

    public void setTimeDisplayed(LocalDateTime timeDisplayed) {
        this.timeDisplayed = timeDisplayed;
    }

    public boolean isReceivedInside() {
        return receivedInside;
    }

    public void setReceivedInside(boolean receivedInside) {
        this.receivedInside = receivedInside;
    }

    public boolean isMessagePresented() {
        return messagePresented;
    }

    public void setMessagePresented(boolean messagePresented) {
        this.messagePresented = messagePresented;
    }

    public boolean isLocationAvailable() {
        return locationAvailable;
    }

    public void setLocationAvailable(boolean locationAvailable) {
        this.locationAvailable = locationAvailable;
    }

    public double getDistanceFromPolygon() {
        return distanceFromPolygon;
    }

    public void setDistanceFromPolygon(double distanceFromPolygon) {
        this.distanceFromPolygon = distanceFromPolygon;
    }

    public boolean isOptedOut() {
        return optedOut;
    }

    public void setOptedOut(boolean optedOut) {
        this.optedOut = optedOut;
    }
}
