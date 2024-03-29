package com.capstone.wea.Util;

import com.capstone.wea.model.cap.IPAWSMessageList;
import com.capstone.wea.parser.XMLParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class for interfacing with IPAWS
 */
public class IPAWSInterface {
    private static final String IPAWS_PIN_PARAMETER = "?pin=NnducW4wcTdldjE";
    private static final String IPAWS_TEST_URL = "https://tdl.apps.fema.gov/IPAWSOPEN_EAS_SERVICE/rest/";
    private static final String IPAWS_PROD_URL = "https://apps.fema.gov/IPAWSOPEN_EAS_SERVICE/rest/";
    private static final String EAS_FEED = "eas/recent/";
    private static final String PUBLIC_NON_EAS_FEED = "public_non_eas/recent/";
    private static final String PUBLIC_FEED = "public/recent/";
    private static final String WEA_FEED = "PublicWEA/recent/";

    /**
     * Hits the IPAWS API to see if there are any new messages sent out within the last 40 minutes. If new messages are
     * found
     * they are added to the database.
     *
     * @param env The IPAWS environment API to hit; valid values are "test" and "prod";
     *            If an invalid value is passed, the test environment will be used
     * @param feed The feed API to hit; the valid values are: "eas", "non-eas", "public", and "wea";
     *             If an invalid feed is provided, "wea" will be used
     * @return The oldest CMACMessage found, or null if none were found
     */
    public static IPAWSMessageList getMessageFromIpaws(String env, String feed) throws MalformedURLException {
        StringBuilder ipawsUrl = new StringBuilder();
        if (env.equalsIgnoreCase("prod")) {
            ipawsUrl.append(IPAWS_PROD_URL);
        } else {
            ipawsUrl.append(IPAWS_TEST_URL);
        }

        if (feed.equalsIgnoreCase("eas")) {
            ipawsUrl.append(EAS_FEED);
        } else if (feed.equalsIgnoreCase("non-eas")) {
            ipawsUrl.append(PUBLIC_NON_EAS_FEED);
        } else if (feed.equalsIgnoreCase("public")) {
            ipawsUrl.append(PUBLIC_FEED);
        } else {
            ipawsUrl.append(WEA_FEED);
        }

        ZonedDateTime dateTime = ZonedDateTime.now(Clock.systemUTC()).withNano( 0).minusMinutes(30);
        ipawsUrl.append(dateTime.format(DateTimeFormatter.ISO_INSTANT))
                .append(IPAWS_PIN_PARAMETER);

        URL getIpaws = new URL(ipawsUrl.toString());

        return XMLParser.parseIpawsUrlResult(getIpaws);
    }
}
