package com.capstone.wea.model.cmac;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.List;

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

    public void addToDatabase(JdbcTemplate jdbcTemplate, int messageNumber, String capIdentifier, int areaId)
            throws Exception {
        MapSqlParameterSource keyParams = new MapSqlParameterSource()
                .addValue("CMACMessageNumber", messageNumber)
                .addValue("CMACCapIdentifier", capIdentifier);

        addAreaNamesToDatabase(jdbcTemplate, keyParams, areaId);
        addCoordinatesToDatabase(jdbcTemplate, keyParams, polygon, "cmac_polygon_coordinates", areaId);
        addCoordinatesToDatabase(jdbcTemplate, keyParams, circle, "cmac_circle_coordinates", areaId);
    }

    private void addAreaNamesToDatabase(JdbcTemplate jdbcTemplate, MapSqlParameterSource keyParams, int areaId)
            throws Exception {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("cmac_area_description");

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
            throw new Exception("Geocode count mismatch");
        }

        //TODO: add another column to cmac_area_description: SAME (BIT), update addArea method for this
        List<SqlParameterSource> paramList = new ArrayList<>();

        for (int i = 0; i < areaNames.length; i++) {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("AreaName", areaNames[i])
                    .addValue("CMASGeocode", geocodeList.get(i + startIndex))
                    .addValue("AreaId", areaId)
                    .addValue("SAME", startIndex == 0)
                    .addValues(keyParams.getValues());

            paramList.add(params);
        }

        //batch throws exception if any insert fails, no need to validate
        SqlParameterSource[] paramArray = paramList.toArray(new SqlParameterSource[paramList.size()]);
        simpleJdbcInsert.executeBatch(paramArray);
    }

    private void addCoordinatesToDatabase(JdbcTemplate jdbcTemplate, MapSqlParameterSource keyParams,
                                             String coordinateString, String table, int areaId) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName(table);
        String[] coordinateList;

        if (coordinateString == null || coordinateString.isEmpty()) {
            coordinateList = new String[] {};
        } else {
            coordinateList = coordinateString.split(" ");
        }

        List<SqlParameterSource> paramList = new ArrayList<>();

        for (String coordinate : coordinateList) {
            //index 0 = lat, index 1 = lon
            String[] splitCoordinates = coordinate.split(",");

            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("Latitude", splitCoordinates[0])
                    .addValue("Longitude", splitCoordinates[1])
                    .addValue("AreaId", areaId)
                    .addValues(keyParams.getValues());

            paramList.add(params);
        }

        //batch throws exception if any insert fails, no need to validate
        SqlParameterSource[] paramArray = paramList.toArray(new SqlParameterSource[paramList.size()]);
        simpleJdbcInsert.executeBatch(paramArray);
    }
}
