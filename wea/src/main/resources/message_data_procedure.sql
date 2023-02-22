CREATE PROCEDURE IF NOT EXISTS GetMessageData(
	sender VARCHAR(180),
    pageNum INT,
    messageNumber INT,
    messageType VARCHAR(20),
    fromDate VARCHAR(10),
    toDate VARCHAR(10),
    orderByDate BIT,
    orderByDesc BIT
)
BEGIN
	DECLARE offsetVal INT DEFAULT 9 * (IF(pageNum < 1, 1, pageNum) - 1);

    SELECT Coordinates.messageNumber, sentDateTime, expires, Coordinates.messageType, capIdentifier, polygon, circle,
    geocodes
    FROM (
		SELECT CMACMessage.messageNumber, sentDateTime, expires, CMACMessage.messageType, CMACMessage.capIdentifier,
		polygon, circle
		FROM CMACMessage JOIN CMACAlertArea
		ON CMACAlertArea.alertAreaId = CMACMessage.messageNumber
		WHERE CMACMessage.sender = sender
		AND CMACMessage.messageNumber = IFNULL(messageNumber, CMACMessage.messageNumber)
		AND CMACMessage.messageType = IFNULL(messageType, CMACMessage.messageType)
		AND sentDateTime >= IFNULL(fromDate, DATE("2016-01-01"))
		AND sentDateTime < DATE_ADD(IFNULL(toDate, CURDATE()), INTERVAL 1 DAY)
		GROUP BY CMACMessage.messageNumber
		ORDER BY
			CASE WHEN NOT orderByDate AND NOT orderByDesc THEN CMACMessage.messageNumber END ASC,
			CASE WHEN NOT orderByDate AND orderByDesc THEN CMACMessage.messageNumber END DESC,
			CASE WHEN orderByDate AND NOT orderByDesc THEN sentDateTime END ASC,
			CASE WHEN orderByDate AND orderByDesc THEN sentDateTime END DESC
		LIMIT 10 OFFSET offsetVal
	) as Coordinates
    JOIN (
		SELECT group_concat(geocodeList) AS geocodes, CMACAlertArea_alertAreaId
		FROM CMACCmasGeocode
		WHERE CMACCmasGeocode.CMACAlertArea_alertAreaId = IFNULL(messageNumber, CMACCmasGeocode.CMACAlertArea_alertAreaId)
		GROUP BY CMACAlertArea_alertAreaId
	) AS Geocodes
	ON Coordinates.messageNumber = Geocodes.CMACAlertArea_alertAreaId;
END