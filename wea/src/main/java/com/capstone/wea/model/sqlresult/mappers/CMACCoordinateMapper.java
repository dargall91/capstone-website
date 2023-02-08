package com.capstone.wea.model.sqlresult.mappers;

import com.capstone.wea.model.sqlresult.Coordinate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CMACCoordinateMapper implements RowMapper<Coordinate> {
    @Override
    public Coordinate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Coordinate coordinate = new Coordinate(rs.getString("Latitude"), rs.getString("Longitude"));

        return coordinate;
    }
}
