package com.capstone.wea.repositories.projections;

public interface CollectedStatsProjections {
    int getMessageNumber();
    String getCapIdentifier();
    String getMessageType();
    String getSentDateTime();
    String getExpiresDateTime();
    int getDeviceCount();
    String getAverageTime();
    String getFirstReceived();
    String getFirstDisplayed();
}
