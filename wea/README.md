# WEA API

This document provides an overview on how to start the server and use the API.

## Database Setup

The database will automatically create and update itself just by running the application.

MySql will need to be installed on your device.

Create a new MySql database with username and password set to 'root'.

## Starting the Server

Java JDK 17 is required to run this project. This SpringBoot application can be run in your IDE as you would any other
application, or by executing gradle commands in the terminal. There are now two different builds you can use for
the application. To run the dev build, use the command `./gradlew bootRun`. To run the prod build, use the command
`./gradlew prod`.

If the above commands do not work, just run 'gradle bootRun'.

If you run into any further issues, run 'gradle clean build bootRun'.

## Making API Requests

The host for WEA API endpoints is always `http://<your_ip>:8080/wea/api/` where `<your_ip>` is the local IP address
of the machine running this program, or `localhost` if you are hitting the api from the same machine as the one
running the program. In this projects root directory, there is a json file named `API_Tests. har`. This is an `HAR - 
HTTP Archive Format` file that can imported in either Insomnia or Postman to test the API endpoints.

At this time, the WEA API _does not_ support HTTPS.

## Endpoint List

The WEA API has the following endpoints:

- [Get a Message](#message)
- [Upload Device Data](#upload)
- [Get an Upload](#getUpload)
- [Get Message Stats by AO](#stats)

### <a id="message" /> Get a Message

The **Get a Message** endpoint is used by mobile devices to simulate receiving a CMAC message from an AO. On a
successful GET, the response message body will be a CMAC compliant XML object.

#### Endpoint Usage

| Endpoint    | HTTP Verb | Request Body | Parameters | Success Response | Response Data   |
| ----------- | --------- | ------------ | ---------- | ---------------- | --------------- |
| /getMessage | GET       | ---          | ---        | 200              | application/xml |

#### Example Request:

    GET http://localhost:8080/wea/api/getMessage

#### Example Response Body:

    <?xml version = "1.0" encoding = "UTF-8"?>
    <CMAC_Alert_Attributes xmlns = "cmac:2.0">
        <CMAC_protocol_version>2.0</CMAC_protocol_version>
        <CMAC_sending_gateway_id>http://wea_alert_gateway.gov</CMAC_sending_gateway_id>
        <CMAC_message_number>00001056</CMAC_message_number>
        <CMAC_sender>w-nws.webmaster@noaa.gov</CMAC_sender>
        <CMAC_sent_date_time>2017-06-03T01:32:50Z</CMAC_sent_date_time>
        <CMAC_status>Actual</CMAC_status>
        <CMAC_message_type>Alert</CMAC_message_type>
        <CMAC_cap_alert_uri>http://wea_alert_gateway.gov/CMAM1056</CMAC_cap_alert_uri>
        <CMAC_cap_identifier>NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z</CMAC_cap_identifier>
        <CMAC_cap_sent_date_time>2017-06-03T01:32:50Z</CMAC_cap_sent_date_time>
        <CMAC_alert_info>
            <CMAC_category>Met</CMAC_category>
            <CMAC_severity>Severe</CMAC_severity>
            <CMAC_urgency>Expected</CMAC_urgency>
            <CMAC_certainty>Likely</CMAC_certainty>
            <CMAC_expires_date_time>2017-06-03T02:30:00Z</CMAC_expires_date_time>
            <CMAC_sender_name>NWS San Angelo TX</CMAC_sender_name>
            <CMAC_Alert_Area>
                <CMAC_area_description>Fisher; Jones; Taylor; Callahan</CMAC_area_description>
                <CMAC_polygon>32.21,-99.62 32.27,-100.15 32.52,-100.15 32.52,-100.16 32.72,-
                    100.17 32.85,-99.61 32.21,-99.62</CMAC_polygon>
                <CMAC_cmas_geocode>48151</CMAC_cmas_geocode>
                <CMAC_cmas_geocode>48253</CMAC_cmas_geocode>
                <CMAC_cmas_geocode>48441</CMAC_cmas_geocode>
                <CMAC_cmas_geocode>48059</CMAC_cmas_geocode>
                <CMAC_cap_geocode>
                    <valueName>SAME</valueName>
                    <value>048151</value>
                </CMAC_cap_geocode>
                <CMAC_cap_geocode>
                    <valueName>SAME</valueName>
                    <value>048253</value>
                </CMAC_cap_geocode>
                <CMAC_cap_geocode>
                    <valueName>SAME</valueName>
                    <value>048441</value>
                </CMAC_cap_geocode>
                <CMAC_cap_geocode>
                    <valueName>SAME</valueName>
                    <value>048059</value>
                </CMAC_cap_geocode>
            </CMAC_Alert_Area>
            <CMAC_Alert_Text>
                <CMAC_text_language>English</CMAC_text_language>
                <CMAC_short_text_alert_message_length>52
                </CMAC_short_text_alert_message_length>
                <CMAC_short_text_alert_message>Flash Flood Warning this area until 9:30 PM CDT.
                    NWS</CMAC_short_text_alert_message>
                <CMAC_long_text_alert_message_length>187
                </CMAC_long_text_alert_message_length>
                <CMAC_long_text_alert_message>Flash Flood Warning this area until 9:30 PM CDT.
                    Avoid flood areas. Do not drive on flooded roads. Check local radio and television
                    stations for more information. National Weather Service</CMAC_long_text_alert_message>
            </CMAC_Alert_Text>
            <CMAC_Alert_Text>
                <CMAC_text_language>Spanish</CMAC_text_language>
                <CMAC_short_text_alert_message_length>68
                </CMAC_short_text_alert_message_length>
                <CMAC_short_text_alert_message>Aviso de inundación de destello esta área hasta
                    las 9:30 PM CDT. NWS</CMAC_short_text_alert_message>
                <CMAC_long_text_alert_message_length>247
                </CMAC_long_text_alert_message_length>
                <CMAC_long_text_alert_message>Advertencia de inundación de emergencia esta área
                    hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras
                    inundadas. Consulte las emisoras de radio y televisión locales para obtener más
                    información. National Weather Service</CMAC_long_text_alert_message>
            </CMAC_Alert_Text>
        </CMAC_alert_info>
    </CMAC_Alert_Attributes>

### <a id="upload" /> Upload Device Data

The **Upload Device Data** endpoint is used by mobile devices to upload the collected data to the server. On a
successful PUT, the HTTP response header will contain a Location that points to the uploaded data

#### Endpoint Usage

| Endpoint | HTTP Verb | Request Body | Parameters | Success Response | Response Data |
| -------- | --------- | ------------ | ---------- | ---------------- | ------------- |
| /upload  | PUT       | XML          | ---        | 201              | Location      |

#### Example Request:

    PUT http://localhost:8080/wea/api/upload

#### Example Request Body:

    {
        "messageNumber" : "0000000C",
        "capIdentifier": "urn:oid:2.49.0.1.840.0.9bb69179ba44e3e26201b9f31e9bda2d1fa963e7.001.1",
        "timeReceived": "2023-02-18T11:54:30Z",
        "timeDisplayed": "2023-02-18T11:54:35Z"
    }

#### Example Response Location Header:

    Location | http://localhost:8080/wea/api/getUpload?identifier=1

### <a id="getUpload" />Get an Upload

The **Get an Upload** endpoint is used to confirm that a device's data was successfully uploaded to the server. On a
successful get, the HTTP response body will be an XML object that contains the uploaded data and the upload's unique
identifier.

#### Endpoint Usage

| Endpoint   | HTTP Verb | Request Body | Success Response | Response Data   |
| ---------- | --------- | ------------ | ---------------- | --------------- |
| /getUpload | PUT       | XML          | 200              | application/xml |

#### Request Parameters

| Name       | Definition                                     | Data Type | Required |
| ---------- | ---------------------------------------------- | --------- | -------- |
| identifier | the unique upload id that identifies this data | integer   | Yes      |

#### Example Request:

    GET http://localhost:8080/wea/api/getUpload?identifier=1

#### Example Response Body:

    {
        "messageNumber": 1,
        "capIdentifier": "urn:oid:2.49.0.1.840.0.9bb69179ba44e3e26201b9f31e9bda2d1fa963e7.001.1",
        "timeReceived": "2023-02-18T06:54:30-05:00",
        "timeDisplayed": "2023-02-18T06:54:35-05:00",
        "receivedInside": false,
        "displayedInside": false,
        "messagePresented": false,
        "locationAvailable": false,
        "distanceFromPolygon": 0.0
    }

### <a id="stats" /> Get Message Stats by AO

The **Get Message Stats by AO** endpoint is used to get the stats of a subset of all messages in the database from a
specified AO. Result subsets are divided into pages with up to 9 results per page. The page must be specified in the
request url. On a successful GET, the HTTP response body will contain a JSON array of JSON objects, one object for
each message by the AO, and each object will contain that message's unique CMAC_message_number, CMAC_sent_date_time,
and the stats collected from all the devices that received the message. An AO's registered sender name will contain
the special character '@' which must be encoded as "%40" in order to successfully query the database.A definition of
each JSON key in the response body is provided in after the example response body.

This endpoint supports multiple ways to filter and sort responses. If no such parameters are provided, this endpoint
will by default sort messages from by newest to oldest with no filters. When updating the filters, it is important to
request the results for page 1 as additional pages may be blank after filtering. All dates are inclusive.

#### Endpoint Usage

| Endpoint                          | HTTP Verb | Request Body | Success Response | Response Data    |
| --------------------------------- | --------- | ------------ | ---------------- | ---------------- |
| /{sender}/messages/{page}/filters | GET       | ---          | 200              | application/json |

#### URL Path Variables:

| Name   | Definition                                                                          | Data Type | Required |
| ------ | ----------------------------------------------------------------------------------- | --------- | -------- |
| sender | The AO of the messages that should be retrieved; this should be a CMAC_sender value | string    | Yes      |
| page   | The page number of the results that should be retrieved                             | integer   | Yes      |

#### Request Parameters:

| Name          | Definition                                                                                                              | Data Type                   | Required |
| ------------- | ----------------------------------------------------------------------------------------------------------------------- | --------------------------- | -------- |
| messageType   | If this parameter is provided, only messages with a CMAC_message_type that contains the provided value will be returned | string                      | No       |
| messageNumber | If this parameter is provided, only messages with a matching CMAC_message_number will be returned                       | string                      | No       |
| fromDate      | If this parameter is provided, only messages with a CMAC_sent_date equal to or after this will be returned              | Date (yyyy-mm-dd)           | No       |
| toDate        | If this parameter is provided, only messages with a CMAC_sent_date equal to or before this will be returned             | Date (yyyy-mm-dd)           | No       |
| sortBy        | Specifies how to order the results by: message number or sent date. Default if invalid or not provided: "date"          | string ("date" or "number") | No       |
| sortOrder     | Specifies how the results should be sorted: ascending or descending. Default if invalid or not provided: "desc"         | string ("asc" or "desc")    | No       |

#### Example Request:

This example gets all messages sent between 2022-10-10 and 2022-11-13 ordered by their message numbers in
ascending order

    GET http://localhost:8080/wea/api/w-nws.webmaster%40noaa.gov/messages/1/filter?fromDate=2022-10-01&toDate=2022-11-13&sortBy=number&sortOrder=asc

#### Example Response Body:

    {
        "messageStats": [
    	{
    		"messageNumber": "0000000C",
    		"capIdentifier": "urn:oid:2.49.0.1.840.0.6f50c3a937ba04fa681c49f24e7404819037f1cd.001.1",
    		"messageType": "Alert",
    		"sentDateTime": "2023-02-18 02:09:00.0",
    		"expiresDateTime": "2023-02-18 13:00:00.0",
    		"coordinates": [
    			{
    				"lat": "44.24",
    				"lon": "-107.79"
    			},
    			{
    				"lat": "44.25",
    				"lon": "-107.85"
    			},
    			{
    				"lat": "44.26",
    				"lon": "-107.87"
    			},
    			{
    				"lat": "44.25",
    				"lon": "-107.98"
    			},
    			{
    				"lat": "44.27",
    				"lon": "-108.0"
    			},
    			{
    				"lat": "44.32",
    				"lon": "-108.03"
    			},
    			{
    				"lat": "44.51",
    				"lon": "-108.07"
    			},
    			{
    				"lat": "44.52",
    				"lon": "-108.06"
    			},
    			{
    				"lat": "44.48",
    				"lon": "-108.05"
    			},
    			{
    				"lat": "44.47",
    				"lon": "-108.05"
    			},
    			{
    				"lat": "44.45",
    				"lon": "-108.03"
    			},
    			{
    				"lat": "44.43",
    				"lon": "-108.04"
    			},
    			{
    				"lat": "44.29",
    				"lon": "-107.99"
    			},
    			{
    				"lat": "44.27",
    				"lon": "-107.95"
    			},
    			{
    				"lat": "44.27",
    				"lon": "-107.89"
    			},
    			{
    				"lat": "44.27",
    				"lon": "-107.87"
    			},
    			{
    				"lat": "44.25",
    				"lon": "-107.78"
    			},
    			{
    				"lat": "44.17",
    				"lon": "-107.69"
    			},
    			{
    				"lat": "44.17",
    				"lon": "-107.71"
    			},
    			{
    				"lat": "44.24",
    				"lon": "-107.79"
    			}
    		],
    		"areaNames": [
    			"Gilliam County Oregon",
    			"Morrow County Oregon",
    			"Umatilla County Oregon",
    			"Wheeler County Oregon",
    			"Klickitat County Washington",
    			"Yakima County Washington"
    		],
    		"received": 4,
    		"expectedReceived": 5,
    		"averageTime": "00:01:00",
    		"shortestTime": "00:01:00",
    		"firstReceived": "2023-02-18 02:10:00.0",
    		"averagePresentationDelay": "00:00:09",
    		"firstPresented": "2023-02-18 02:10:09.0",
    		"receivedOutside": 1,
    		"averageDistance": "6.67",
    		"minDistance": "1",
    		"maxDistance": "10",
    		"medianDistance": "5.0",
    		"presented": 3,
            "notPresentedOutside": 0,
            "optedOut": 1,
            "presentedDefault": 2
    	},
        "commonName": "National Weather Service",
        "prev": false,
        "next": false
    }

#### Json Response Key Definitions

| JSON Key                 | Definition                                                                                                                                                               |
| ------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| messageStats             | an array of JSON objects that contain the collected stats for a cmac message                                                                                             |
| messageNumber            | The CMAC_message_number of the message                                                                                                                                   |
| capIdentifier            | The CMAC_cap_identifier of the message                                                                                                                                   |
| messageType              | The CMAC_message_type oof the alert                                                                                                                                      |
| sentDateTime             | The CMAC_sent_date_time of the alert                                                                                                                                     |
| expiresDateTime          | The CMAC_expires_date_time of the alert                                                                                                                                  |
| averageTime              | The average time between when the message was issued and all devices received it                                                                                         |
| shortestTime             | the shortest time between when the message was issued and all devices received it                                                                                        |
| firstReceived            | The date and time when the first device to receive the message received it                                                                                               |
| averagePresentationDelay | The average time lapse between when all devices received the message and when the alert was presented on the device (excluding devices that did not receive the message) |
| firstPresented           | The date and time when the first device to display the message presented it                                                                                              |
| received                 | The number of devices that received the message                                                                                                                          |
| expectedReceived         | The number of devices that were expected to receive the message                                                                                                          |
| receivedOutside          | The number of devices that received the message outside of the target area                                                                                               |
| presented                | The number of devices presented the message                                                                                                                              |
| notPresentedOutside      | The number of devices that received the message outside of the target area but did not present it                                                                        |
| presentedDefault         | The number of devices that received the message and presented it by default for any reason (ex: device location not enabled)                                             |
| optedOut                 | The number of devices that received the message did not display it because the used opted out of that type of message                                                    |
| averageDistance          | The average distance outside of the target area for all devices that received the message outside the target area                                                        |
| medianDistance           | The median distance outside of the target area for all devices that received the message outside the target area                                                         |
| minDistance              | The minimum distance outside of the target area for all devices that received the message outside the target area                                                        |
| maxDistance              | The maximum distance outside of the target area for all devices that received the message outside the target area                                                        |
| coordinates              | an array of coordinates that comprise this messages polygon; if the message has no polygon this item will be null                                                        |
| lat                      | the latitude of the polygon coordinate                                                                                                                                   |
| lon                      | the longitude of the polygon coordinate                                                                                                                                  |
| areaNames                | the list of areas by name that corresponds to each geocode in the list                                                                                                   |
| commonName               | the common name of the AO of these messages                                                                                                                              |
| prev                     | a boolean value that represents if there is a previous page of results; this is always true if page > 1                                                                  |
| next                     | a boolean value that represents if there is an additional page of results after this one                                                                                 |
