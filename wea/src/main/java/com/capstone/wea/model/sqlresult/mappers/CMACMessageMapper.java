package com.capstone.wea.model.sqlresult.mappers;

import com.capstone.wea.model.cmac.CMACAlertInfoModel;
import com.capstone.wea.model.cmac.CMACMessageModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CMACMessageMapper implements RowMapper<Boolean> {
    private CMACMessageModel message;
    public CMACMessageMapper(CMACMessageModel message) {
        this.message = message;
    }

    @Override
    public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
        message.setMessageNumber(String.format("%08X", rs.getInt("CMACMessageNumber")));
        message.setCapIdentifier(rs.getString("CMACCapIdentifier"));
        message.setSender(rs.getString("CMACSender"));
        message.setSentDateTime(rs.getString("CMACDateTime").replace(" " , "T") + "Z");
        message.setStatus(rs.getString("CMACStatus"));
        message.setMessageType(rs.getString("CMACMessageType"));

        CMACAlertInfoModel alertInfo = new CMACAlertInfoModel();

        alertInfo.setSenderName(rs.getString("CMACSenderName"));
        alertInfo.setExpires(rs.getString("CMACExpiresDateTime").replace(" " , "T") + "Z");
        alertInfo.setCategory(rs.getString("CMACCategory"));
        alertInfo.setSeverity(rs.getString("CMACSeverity"));
        alertInfo.setUrgency(rs.getString("CMACUrgency"));
        alertInfo.setCertainty(rs.getString("CMACCertainty"));
        alertInfo.setReferenceNumber(rs.getString("CMACReferencedCapIdentifier"));

        message.setAlertInfo(alertInfo);

        return true;
    }
}
