package com.capstone.wea.model.sqlresult.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoordinatesMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        String coordinates = rs.getString("Latitude") + " " + rs.getString("Longitude");

        return coordinates;
    }
}
