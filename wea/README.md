# WEA API

This document provides an overview on how to start the server and use the API.

## Database Setup

Before running this application, make sure your database is up-to-date with the latest schema and has some sample 
data to query. You can use Alex's `database_schema.sql` query to set up the database and then created your own sample 
data, or you can use the `alert_db_dummy_data_setup.sql` query found this project's root directory to create both the 
schema and populate it with sample data. It is recommended that you re-run one of these queries at the beginning of each
sprint and anytime you pull down the latest changes related to the server to make sure your local database works as
intended.

If you are making changes to the database schema, **DO NOT** directly modify the `alert_db_dummy_data_setup.sql` file. 
These changes should be made in the `database_schema.sql`. The `alert_db_dummy_data.sql` file is an export created by
MySQL and any changes made will be overwritten if a new export is produced. It may also fail to execute because it can 
make the insert statement s invalid.

## Starting the Server

Java JDK 17 is required to run this project. This SpringBoot application can be run in your IDE as you would any other 
application, or by navigating to this project's root directory in the terminal and using the `./gradlew bootRun` 
command in linux, or `gradle bootRun` in Windows. 

## Making API Requests

The host for WEA API endpoints is always `http://<your_ip>:8080/wea/api/` where `<your_ip>` is the local IP address 
of the machine running this program, or `localhost` if you are hitting the api from the same machine as the one 
running the program. In this projects root directory, there is a json file named `API_Tests. har`. This is an `HAR - 
HTTP Archive Format` file that can imported in either Insomnia or Postman to test the API endpoints.

At this time, the WEA API *does not* support HTTPS.

## Endpoint List

The WEA API has the following endpoints:

* [Get a Message](#message)
* [Upload Device Data](#upload)
* [Get an Upload](#getUpload)
* [Get Message Stats by AO](#stats)

### <a id="message" /> Get a Message

The **Get a Message** endpoint is used by mobile devices to simulate receiving a CMAC message from an AO. On a 
successful GET, the response message body will be a CMAC compliant XML object.

#### Endpoint Usage
| Endpoint    | HTTP Verb | Request Body | Parameters | Success Response | Response Data   |
|-------------|-----------|--------------|------------|------------------|-----------------|
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
|----------|-----------|--------------|------------|------------------|---------------|
| /upload  | PUT       | XML          | ---        | 201              | Location      |

#### Example Request:

    PUT http://localhost:8080/wea/api/upload

#### Example Request Body:

    <?xml version = "1.0" encoding = "UTF-8"?>
    <CMAC_device_data>
        <CMAC_user_time_received>2017-06-03T01:33:29</CMAC_user_time_received>
        <CMAC_user_time_displayed>2017-06-03T01:33:36</CMAC_user_time_displayed>
        <CMAC_user_location_received>48253</CMAC_user_location_received>
        <CMAC_user_location_displayed>48253</CMAC_user_location_displayed>
        <CMAC_message_number>00001059</CMAC_message_number>
        <Received_Outside_Area>false</Received_Outside_Area>
        <Displayed_Outside_Area>false</Displayed_Outside_Area>
        <Received_After_Expired>false</Received_After_Expired>
        <Displayed_After_Expired>false</Displayed_After_Expired>
    </CMAC_device_data>

#### Example Response Location Header:

    Location | http://localhost:8080/wea/api/getUpload?identifier=1

### <a id="getUpload" />Get an Upload

The **Get an Upload** endpoint is used to confirm that a device's data was successfully uploaded to the server. On a 
successful get, the HTTP response body will be an XML object that contains the uploaded data and the upload's unique 
identifier.

#### Endpoint Usage
| Endpoint   | HTTP Verb | Request Body | Success Response  | Response Data   |
|------------|-----------|--------------|-------------------|-----------------|
| /getUpload | PUT       | XML          | 200               | application/xml |

#### Request Parameters

| Name       | Definition                                     | Data Type | Required |
|------------|------------------------------------------------|-----------|----------|
| identifier | the unique upload id that identifies this data | integer   | Yes      |

#### Example Request:

    GET http://localhost:8080/wea/api/getUpload?identifier=12

#### Example Response Body:

    <CMAC_device_data>
        <CMAC_user_time_received>2017-06-03T01:33:29</CMAC_user_time_received>
        <CMAC_user_time_displayed>2017-06-03T01:33:36</CMAC_user_time_displayed>
        <CMAC_user_location_received>48253</CMAC_user_location_received>
        <CMAC_user_location_displayed>48253</CMAC_user_location_displayed>
        <CMAC_message_number>00001059</CMAC_message_number>
        <Received_Outside_Area>false</Received_Outside_Area>
        <Displayed_Outside_Area>false</Displayed_Outside_Area>
        <Received_After_Expired>false</Received_After_Expired>
        <Displayed_After_Expired>false</Displayed_After_Expired>
        <id>12</id>
    </CMAC_device_data>

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
|-----------------------------------|-----------|--------------|------------------|------------------|
| /{sender}/messages/{page}/filters | GET       | ---          | 200              | application/json |

#### URL Path Variables:
| Name   | Definition                                                                          | Data Type | Required |
|--------|-------------------------------------------------------------------------------------|-----------|----------|
| sender | The AO of the messages that should be retrieved; this should be a CMAC_sender value | string    | Yes      |
| page   | The page number of the results that should be retrieved                             | integer   | Yes      |

#### Request Parameters:
| Name          | Definition                                                                                                              | Data Type                   | Required |
|---------------|-------------------------------------------------------------------------------------------------------------------------|-----------------------------|----------|
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
                "messageNumber": "00000005",
                "messageType": "Alert",
                "date": "2022-11-11 02:04:24",
                "averageTime": "00:11:42",
                "shortestTime": "00:01:08",
                "longestTime": "00:37:23",
                "averageDelay": "00:00:21",
                "deviceCount": 4,
                "receivedOutsideCount": 2,
                "displayedOutsideCount": 2,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            },
            {
                "messageNumber": "00000006",
                "messageType": "Alert",
                "date": "2022-11-12 08:29:59",
                "averageTime": "00:01:55",
                "shortestTime": "00:00:33",
                "longestTime": "00:03:02",
                "averageDelay": "00:13:35",
                "deviceCount": 4,
                "receivedOutsideCount": 1,
                "displayedOutsideCount": 0,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            },
            {
                "messageNumber": "00000007",
                "messageType": "Alert",
                "date": "2022-11-13 11:48:01",
                "averageTime": "00:02:47",
                "shortestTime": "00:00:15",
                "longestTime": "00:07:09",
                "averageDelay": "00:00:20",
                "deviceCount": 5,
                "receivedOutsideCount": 1,
                "displayedOutsideCount": 1,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            },
            {
                "messageNumber": "00000008",
                "messageType": "Alert",
                "date": "2022-11-12 17:18:31",
                "averageTime": "00:00:55",
                "shortestTime": "00:00:27",
                "longestTime": "00:01:47",
                "averageDelay": "00:00:23",
                "deviceCount": 3,
                "receivedOutsideCount": 0,
                "displayedOutsideCount": 0,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            },
            {
                "messageNumber": "00000009",
                "messageType": "Alert",
                "date": "2022-11-13 17:18:31",
                "averageTime": "00:04:46",
                "shortestTime": "00:04:01",
                "longestTime": "00:05:30",
                "averageDelay": "00:00:35",
                "deviceCount": 2,
                "receivedOutsideCount": 1,
                "displayedOutsideCount": 0,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            },
            {
                "messageNumber": "0000000A",
                "messageType": "Alert",
                "date": "2022-10-13 17:18:31",
                "averageTime": "00:02:16",
                "shortestTime": "00:00:58",
                "longestTime": "00:03:45",
                "averageDelay": "00:00:16",
                "deviceCount": 5,
                "receivedOutsideCount": 2,
                "displayedOutsideCount": 2,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            },
            {
                "messageNumber": "0000000B",
                "messageType": "Alert",
                "date": "2022-10-13 17:18:31",
                "averageTime": "00:02:45",
                "shortestTime": "00:00:16",
                "longestTime": "00:07:19",
                "averageDelay": "00:00:10",
                "deviceCount": 3,
                "receivedOutsideCount": 0,
                "displayedOutsideCount": 0,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            },
            {
                "messageNumber": "0000000C",
                "messageType": "Alert",
                "date": "2022-11-13 20:01:39",
                "averageTime": "00:01:16",
                "shortestTime": "00:00:11",
                "longestTime": "00:02:37",
                "averageDelay": "00:00:14",
                "deviceCount": 4,
                "receivedOutsideCount": 0,
                "displayedOutsideCount": 0,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            },
            {
                "messageNumber": "0000000D",
                "messageType": "Update",
                "date": "2022-11-13 20:53:21",
                "averageTime": "00:01:42",
                "shortestTime": "00:00:11",
                "longestTime": "00:03:26",
                "averageDelay": "00:00:18",
                "deviceCount": 4,
                "receivedOutsideCount": 0,
                "displayedOutsideCount": 0,
                "receivedAfterExpiredCount": 0,
                "displayedAfterExpiredCount": 0,
                "coordinates": [
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    },
                    {
                        "lat": "32.27",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.15"
                    },
                    {
                        "lat": "32.52",
                        "lon": "-100.16"
                    },
                    {
                        "lat": "32.72",
                        "lon": "-100.17"
                    },
                    {
                        "lat": "32.85",
                        "lon": "-99.61"
                    },
                    {
                        "lat": "32.21",
                        "lon": "-99.62"
                    }
                ],
                "areas": null
            }
        ],
        "commonName": "National Weather Service",
        "prev": false,
        "next": false
    }

#### Json Response Key Definitions
| JSON Key                   | Definition                                                                                                        |
|----------------------------|-------------------------------------------------------------------------------------------------------------------|
| messageStats               | an array of JSON objects that contain the collected stats for a cmac message                                      |
| messageNumber              | The CMAC_message_number of the message for which these stats were collected                                       |
| messageType                | The CMAC_message_type oof the alert                                                                               |
| date                       | The CMAC_sent_date_time of the alert                                                                              |
| averageTime                | the average time between when the message was sent and all devices received it                                    |
| shortestTime               | the shortest time between when the message was sent and all devices received it                                   |
| longestTime                | the longest time between when the message was sent and all devices received it                                    |
| averageDelay               | the average delay between when all devices received the message and when the alert was displayed on the device    |
| deviceCount                | the number of devices that received the message                                                                   |
| receivedOutsideCount       | the number of devices that received the message outside of the target area                                        |
| displayedOutsideCount      | the average devices for which the alert was displayed on that device outside the target area                      |
| receivedAfterExpiredCount  | the number of devices that received the message after it expired                                                  |
| displayedAfterExpiredCount | the number of devices that displayed the message to the user after it expired                                     |
| coordinates                | an array of coordinates that comprise this messages polygon; if the message has no polygon this item will be null |
| lat                        | the latitude of the polygon coordinate                                                                            |
| lon                        | the longitude of the polygon coordinate                                                                           |
| areas                      | an array of names of all the areas targeted by this message; if the message has a polygon this item will be null  |
| commonName                 | the common name of the AO of these messages                                                                       |
| prev                       | a boolean value that represents if there is a previous page of results; this is always true if page > 1           |
| next                       | a boolean value that represents if there is an additional page of results after this one                          |