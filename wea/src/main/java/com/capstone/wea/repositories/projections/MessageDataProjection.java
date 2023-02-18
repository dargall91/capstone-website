package com.capstone.wea.repositories.projections;

public interface MessageDataProjection {
    int getMessageNumber();
    String getCapIdentifier();
    String getMessageType();
    String getSentDateTime();
    String getExpiresDateTime();
    String getPolygon();
    String getCircle();
    String getGeocodes();
}
