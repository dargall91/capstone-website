package com.capstone.wea.parser;

import com.capstone.wea.entities.CMACMessage;
import com.capstone.wea.model.cap.CAPMessageModel;
import com.capstone.wea.model.cap.IPAWSMessageList;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Provides methods for parsing CAP and CMAC XML messages
 */
public class XMLParser {
    /**
     * Parses the polygon smoothing sample message. The message's sent times will be set to now and the expiration time
     * will be set to 12 hours from now
     */
    public static CMACMessage parsePolygonSmoothingMessage() {
        CMACMessage message = null;
        try {
            File polygonSmoothingFile = new File("src/main/resources/polygonSmoothing.xml");
            XmlMapper mapper = new XmlMapper();
            mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            message = mapper.readValue(polygonSmoothingFile, CMACMessage.class);
            message.setSentDateTime(OffsetDateTime.now(ZoneOffset.UTC).withNano(0).toString());
            message.setCapSentDateTime(OffsetDateTime.now(ZoneOffset.UTC).withNano(0).toString());
            message.setExpires(OffsetDateTime.now(ZoneOffset.UTC).plusHours(12).withNano(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    /**
     * Parses the list of CAP messages retrieved from IPAWS
     *
     * @return A CAPMessageModel object
     */
    public static IPAWSMessageList parseIpawsUrlResult(URL url) {
        IPAWSMessageList list = null;
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            list = mapper.readValue(url, IPAWSMessageList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
