package com.capstone.wea.repositories.projections;

public interface CollectedStatsProjections {
    int getMessageNumber();
    String getAverageTime();
    String getShortestTime();
    String getFirstReceived();
    String getFirstPresented();
    String getAveragePresentationDelay();
    Integer getReceived();
    Integer getReceivedOutside();
    Integer getNotPresentedOutside();
    Integer getOptedOut();
    Integer getPresentedDefault();
    Integer getPresented();
    Double getAverageDistance();
    Double getMedianDistance();
    Double getMinDistance();
    Double getMaxDistance();
}
