package com.capstone.wea.parser;

import com.capstone.wea.model.cap.CAPMessageModel;
import com.capstone.wea.model.cap.IPAWSMessageList;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.net.URL;

/**
 * Provides methods for parsing CAP and CMAC XML messages
 */
public class XMLParser {
    /**
     * Parses a CAP message from a given file
     *
     * @param path The file's path
     * @return A CAPMessageModel object
     */
    public static CAPMessageModel parseSampleCap(String path) {
        CAPMessageModel model = null;
        try {
            File message = new File(path);
            XmlMapper mapper = new XmlMapper();
            mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            model = mapper.readValue(message, CAPMessageModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
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
