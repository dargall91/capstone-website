CREATE PROCEDURE IF NOT EXISTS GetDeviceStats(
    sender VARCHAR(180),
    messageNumberList VARCHAR(200),
    messageType VARCHAR(20),
    fromDate VARCHAR(10),
    toDate VARCHAR(10),
    orderByDate BIT,
    orderByDesc BIT
)
BEGIN
	SELECT CMACMessage.messageNumber,
    CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(timeReceived, sentDateTime)))) AS TIME) AS averageTime,
    MIN(TIMEDIFF(timeReceived, sentDateTime)) AS shortestTime,
    MIN(timeReceived) AS firstReceived,
    MIN(timeDisplayed) AS firstPresented,
    CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(timeDisplayed, timeReceived)))) AS TIME) AS averagePresentationDelay,
    COUNT(*) as received,
    SUM(NOT receivedInside) AS receivedOutside,
    SUM(NOT receivedInside AND NOT messagePresented) AS notPresentedOutside,
    SUM(NOT messagePresented AND optedOut) as optedOut,
    SUM(messagePresented AND NOT locationAvailable) as presentedDefault,
    SUM(messagePresented IS TRUE) AS presented,
    (
    	SELECT AVG(distanceFromPolygon) as distance
    	FROM CollectedDeviceData
    	WHERE FIND_IN_SET(CollectedDeviceData.messageNumber, messageNumberList)
    	AND messagePresented IS TRUE
    ) AS averageDistance,
    (
    	SELECT AVG(distanceFromPolygon)
    	FROM
    	(
    		SELECT distanceFromPolygon, ROW_NUMBER() OVER (ORDER BY distanceFromPolygon) AS RowNumber, COUNT(*) OVER () AS Count
    		FROM CollectedDeviceData
    		WHERE FIND_IN_SET(CollectedDeviceData.messageNumber, messageNumberList)
    		AND messagePresented IS TRUE
    	) AS median
    	WHERE 2 * RowNumber IN (Count, Count + 1, Count + 2)
    ) AS medianDistance,
    (
    	SELECT MIN(distanceFromPolygon)
    	FROM CollectedDeviceData
        WHERE FIND_IN_SET(CollectedDeviceData.messageNumber, messageNumberList)
    ) AS minDistance,
    (
    	SELECT MAX(distanceFromPolygon)
    	FROM CollectedDeviceData
    	WHERE FIND_IN_SET(CollectedDeviceData.messageNumber, messageNumberList)
    ) AS maxDistance
    FROM CollectedDeviceData JOIN CMACMessage
    ON CMACMessage.messageNumber = CollectedDeviceData.messageNumber
    WHERE CMACMessage.sender = sender
    AND FIND_IN_SET(CMACMessage.messageNumber, messageNumberList)
    AND CMACMessage.messageType = IFNULL(messageType, CMACMessage.messageType)
    AND sentDateTime >= IFNULL(fromDate, DATE("2016-01-01"))
    AND sentDateTime < DATE_ADD(IFNULL(toDate, UTC_DATE()), INTERVAL 1 DAY)
    GROUP BY CMACMessage.messageNumber
    ORDER BY
		CASE WHEN NOT orderByDate AND NOT orderByDesc THEN CMACMessage.messageNumber END ASC,
        CASE WHEN NOT orderByDate AND orderByDesc THEN CMACMessage.messageNumber END DESC,
        CASE WHEN orderByDate AND NOT orderByDesc THEN sentDateTime END ASC,
        CASE WHEN orderByDate AND orderByDesc THEN sentDateTime END DESC;
END