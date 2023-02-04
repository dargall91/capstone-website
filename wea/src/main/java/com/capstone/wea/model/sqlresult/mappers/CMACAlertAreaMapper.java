package com.capstone.wea.model.sqlresult.mappers;

import com.capstone.wea.model.cmac.CMACAlertAreaModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CMACAlertAreaMapper implements RowMapper<Boolean> {
    private List<CMACAlertAreaModel> alertAreaList;

    public CMACAlertAreaMapper(List<CMACAlertAreaModel> alertAreaList) {
        this.alertAreaList = alertAreaList;
    }
    @Override
    public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
        alertAreaList.get(0).addArea(rs.getString("AreaNames"), rs.getString("CMASGeocodes"),
                rs.getString("CMACPolygon"), rs.getString("CMACCircle"));

        return true;
    }
}
