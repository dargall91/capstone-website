CREATE PROCEDURE IF NOT EXISTS GetDeviceStats(
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

	SELECT CMACMessage.messageNumber, COUNT(*) AS deviceCount,
	CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(timeReceived, sentDateTime)))) AS TIME) AS averageTime,
	MIN(TIMEDIFF(timeReceived, sentDateTime)) AS shortestTime,
    MIN(timeReceived) AS firstReceived,
    MIN(timeDisplayed) AS firstDisplayed,
	CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(timeDisplayed, timeReceived)))) AS TIME) AS averageDisplayDelay,
	SUM(NOT receivedInside) AS receivedOutside,
	SUM(NOT displayedInside) AS displayedOutside,
    AVG((SELECT SUM(distanceFromPolygon) FROM CollectedDeviceData WHERE CollectedDeviceData.messageNumber = IFNULL(messageNumber, CMACMessage.messageNumber) AND messagePresented IS TRUE)) as averageDistance,
    (SELECT MIN(distanceFromPolygon) FROM CollectedDeviceData WHERE CollectedDeviceData.messageNumber = IFNULL(messageNumber, CMACMessage.messageNumber) AND messagePresented IS TRUE) as minDistance,
    (SELECT MAX(distanceFromPolygon) FROM CollectedDeviceData WHERE CollectedDeviceData.messageNumber = IFNULL(messageNumber, CMACMessage.messageNumber) AND messagePresented IS TRUE) as maxDistance,
    SUM(messagePresented IS TRUE) as presented
	FROM CollectedDeviceData JOIN CMACMessage
	ON CMACMessage.messageNumber = CollectedDeviceData.messageNumber
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
    LIMIT 9 OFFSET offsetVal;
END