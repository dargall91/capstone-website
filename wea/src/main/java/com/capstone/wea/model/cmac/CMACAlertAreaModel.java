package com.capstone.wea.model.cmac;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "CMAC_Alert_Area")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CMACAlertAreaModel {
    @JsonProperty("CMAC_area_description")
    private String areaDescription;

    @JsonProperty("CMAC_polygon")
    private String polygon;

    @JsonProperty("CMAC_circle")
    private String circle;

    @JsonProperty("CMAC_cmas_geocode")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> geocodeList;
    @JsonProperty("CMAC_cap_geocode")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CMACCapGeocodeModel> capGeocodeList;

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public void setGeocodeList(List<String> geocodeList) {
        this.geocodeList = geocodeList;
    }

    public void setCapGeocodeList(List<CMACCapGeocodeModel> capGeocodeList) {
        this.capGeocodeList = capGeocodeList;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public String getPolygon() {
        return polygon;
    }

    public String getCircle() {
        return circle;
    }

    public List<String> getGeocodeList() {
        return geocodeList;
    }

    public void addArea(String areaDescription, String geocode) {
        if (this.areaDescription != null) {
            this.areaDescription += "; " + areaDescription;
        } else {
            this.areaDescription = areaDescription;
        }

        if (capGeocodeList == null) {
            capGeocodeList = new ArrayList<>();
        }

        capGeocodeList.add(new CMACCapGeocodeModel("SAME", geocode));

        if (geocodeList == null) {
            geocodeList = new ArrayList<>();
        }

        geocodeList.add(geocode);
    }

    public boolean addToDatabase(JdbcTemplate jdbcTemplate, int messageNumber, String capIdentifier, int areaId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

        MapSqlParameterSource keyParams = new MapSqlParameterSource()
                .addValue("messageNumber", messageNumber)
                .addValue("capIdentifier", capIdentifier);

        if (!addAreaNamesToDatabase(simpleJdbcCall, keyParams, areaId)) {
            return false;
        }

        if (!addCoordinatesToDatabase(simpleJdbcCall, keyParams, polygon, "InsertPolygonCoordinates", areaId)) {
            return false;
        }

        return addCoordinatesToDatabase(simpleJdbcCall, keyParams, circle, "InsertCircleCoordinates", areaId);
    }

    private boolean addAreaNamesToDatabase(SimpleJdbcCall simpleJdbcCall, MapSqlParameterSource keyParams, int areaId) {
        String[] areaNames = areaDescription.split("; ");

        int sameCount = 0;
        int ugcCount = 0;
        int startIndex;

        //count SAME vs UGC
        for (CMACCapGeocodeModel geocode : capGeocodeList) {
            if (geocode.getName().equalsIgnoreCase("same")) {
                sameCount++;
            } else {
                ugcCount++;
            }
        }

        //the number of names must equal the number of geocodes
        if (sameCount == areaNames.length) {
            startIndex = 0;
        } else if (ugcCount == areaNames.length) {
            startIndex = sameCount;
        } else {
            return false;
        }

        //TODO: add another column to cmac_area_description: SAME (BIT), update addArea method for this
        for (int i = 0; i < areaNames.length; i++) {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("areaName", areaNames[i])
                    .addValue("geocode", geocodeList.get(i + startIndex))
                    .addValue("areaId", areaId)
                    .addValues(keyParams.getValues());

            Map<String, Object> updateCount = simpleJdbcCall.withProcedureName("InsertAreaDescription").execute(params);

            //failed to insert, remove all prior successful inserts
            if ((Integer) updateCount.get("#update-count-1") == 0) {
                return false;
            }
        }

        return true;
    }

    private boolean addCoordinatesToDatabase(SimpleJdbcCall simpleJdbcCall, MapSqlParameterSource keyParams,
                                             String coordinateString, String procedureName, int areaId) {
        String[] coordinateList;

        if (coordinateString == null || coordinateString.isEmpty()) {
            coordinateList = new String[] {};
        } else {
            coordinateList = coordinateString.split(" ");
        }

        for (String coordinate : coordinateList) {
            //index 0 = lat, index 1 = lon
            String[] splitCoordinates = coordinate.split(",");

            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("latitude", splitCoordinates[0])
                    .addValue("longitude", splitCoordinates[1])
                    .addValue("areaId", areaId)
                    .addValues(keyParams.getValues());

            Map<String, Object> updateCount = simpleJdbcCall.withProcedureName(procedureName).execute(params);

            //failed to insert, remove all prior successful inserts
            if ((Integer) updateCount.get("#update-count-1") == 1) {
                return false;
            }
        }

        return true;
    }
}
