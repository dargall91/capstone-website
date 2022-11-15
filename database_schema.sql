CREATE SCHEMA alert_db;
USE alert_db;

CREATE TABLE cmac_message (
    CMACMessageNumber VARCHAR(16) NOT NULL,
    CMACCapIdentifier VARCHAR(180) NOT NULL,
    CMACSender VARCHAR(180) NOT NULL,
    CMACDateTime DATETIME NOT NULL,
    CMACMessageType VARCHAR(165) NOT NULL,
    CMACSenderName VARCHAR(180) NOT NULL,
    CMACExpiresDateTime DATETIME NOT NULL,
    CONSTRAINT PK_CMACMessage PRIMARY KEY (CMACMessageNumber)
);

CREATE TABLE cmac_polygon_coordinates (
    CMACMessageNumber VARCHAR(16) NOT NULL,
    Latitude DECIMAL(5,2) NOT NULL,
    Longitude DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (CMACMessageNumber) REFERENCES cmac_message(CMACMessageNumber)
);

CREATE TABLE cmac_circle_coordinates (
    CMACMessageNumber VARCHAR(16) NOT NULL,
    Latitude DECIMAL(5,2) NOT NULL,
    Longitude DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (CMACMessageNumber) REFERENCES cmac_message(CMACMessageNumber)
);

CREATE TABLE cmac_area_description (
    CMACMessageNumber VARCHAR(16) NOT NULL,
    AreaName VARCHAR(30) NOT NULL,
    CMASGeocode INT NOT NULL,
    FOREIGN KEY (CMACMessageNumber) REFERENCES cmac_message(CMACMessageNumber)
);

CREATE TABLE device_upload_data (
    CMACMessageNumber VARCHAR(16) NOT NULL,
    UploadID INT NOT NULL AUTO_INCREMENT,
    DeviceOS VARCHAR(180) DEFAULT NULL,
    DeviceOSVersion VARCHAR(180) DEFAULT NULL,
    DeviceModel VARCHAR(180) DEFAULT NULL,
    LocationReceived INT DEFAULT NULL,
    LocationDisplayed INT DEFAULT NULL,
    TimeReceived DATETIME DEFAULT NULL,
    TimeDisplayed DATETIME DEFAULT NULL,
    ReceivedOutsideArea BIT DEFAULT 0,
    DisplayedOutsideArea BIT DEFAULT 0,
    ReceivedAfterExpired BIT DEFAULT 0,
    DisplayedAfterExpired BIT DEFAULT 0,
    CONSTRAINT PK_UploadID PRIMARY KEY (UploadID),
    FOREIGN KEY (CMACMessageNumber) REFERENCES cmac_message(CMACMessageNumber)
);
