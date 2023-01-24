package com.capstone.wea.model.sqlresult.mappers;

import com.capstone.wea.model.sqlresult.PolygonCoordinate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CMACCoordinateMapper implements RowMapper<PolygonCoordinate> {
    @Override
    public PolygonCoordinate mapRow(ResultSet rs, int rowNum) throws SQLException {
        PolygonCoordinate coordinate = new PolygonCoordinate(rs.getString("Latitude"), rs.getString("Longitude"));

        return coordinate;
    }
}
