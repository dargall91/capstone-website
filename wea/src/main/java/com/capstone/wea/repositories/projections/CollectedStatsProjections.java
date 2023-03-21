package com.capstone.wea.repositories.projections;

public interface CollectedStatsProjections {
    int getMessageNumber();
    int getDeviceCount();
    String getAverageTime();
    String getShortestTime();
    String getFirstReceived();
    String getAverageDisplayDelay();
    String getFirstDisplayed();
    int getReceivedOutside();
    int getDisplayedOutside();
    Double getAverageDistance();
    Double getMinDistance();
    Double getMaxDistance();
    Integer getPresented();
}
