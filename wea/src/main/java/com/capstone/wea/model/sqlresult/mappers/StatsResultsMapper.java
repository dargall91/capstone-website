package com.capstone.wea.model.sqlresult.mappers;

import com.capstone.wea.model.sqlresult.MessageStatsResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StatsResultsMapper implements RowMapper<MessageStatsResult> {
    @Override
    public MessageStatsResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        MessageStatsResult result = new MessageStatsResult(String.format("%08X", rs.getInt("CMACMessageNumber")));
        result.setDate(rs.getString("CMACDateTime"));
        result.setMessageType(rs.getString("CMACMessageType"));
        result.setDeviceCount(rs.getInt("DeviceCount"));
        result.setAvgTime(rs.getTime("AvgTime"));
        result.setLongTime(rs.getTime("LongTime"));
        result.setShortTime(rs.getTime("ShortTime"));
        result.setAvgDelay(rs.getTime("AvgDelay"));
        result.setReceivedOutsideCount(rs.getInt("ReceivedOutsideCount"));
        result.setDisplayedOutsideCount(rs.getInt("DisplayedOutsideCount"));
        result.setReceivedAfterExpiredCount(rs.getInt("ReceivedExpiredCount"));
        result.setDisplayedAfterExpiredCount(rs.getInt("DisplayedExpiredCount"));

        return result;
    }
}
