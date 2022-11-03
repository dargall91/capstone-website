package com.capstone.wea.model.sqlresult.mappers;

import com.capstone.wea.model.sqlresult.MessageStatsResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StatsResultsMapper implements RowMapper<MessageStatsResult> {
    private MessageStatsResult result;
    public StatsResultsMapper(MessageStatsResult result) {
        this.result = result;
    }
    @Override
    public MessageStatsResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        result.setDate(rs.getString("CMACDateTime"));
        result.setDeviceCount(rs.getInt("DeviceCount"));
        result.setAvgTime(rs.getTime("AvgTime"));
        result.setLongTime(rs.getTime("LongTime"));
        result.setShortTime(rs.getTime("ShortTime"));
        result.setAvgDelay(rs.getTime("AvgDelay"));

        return result;
    }
}
