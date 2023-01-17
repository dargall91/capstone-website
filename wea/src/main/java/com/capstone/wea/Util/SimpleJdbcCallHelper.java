package com.capstone.wea.Util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.Map;
import java.util.Objects;

public class SimpleJdbcCallHelper {
    private SimpleJdbcCall simpleJdbcCall;

    public SimpleJdbcCallHelper(JdbcTemplate jdbcTemplate) {
        simpleJdbcCall = new SimpleJdbcCall(Objects.requireNonNull(jdbcTemplate.getDataSource()));
    }

    /**
     * Executes the specified stored procedure
     *
     * @param procedure Name of the procedure
     * @return
     */
    public Map<String, Object> executeStoredProcedure(String procedure) {
        return simpleJdbcCall.withProcedureName(procedure).execute();
    }

    /**
     * Executes the specified stored procedure
     *
     * @param procedure Name of the procedure
     * @param params The parameters to be used by the stored procedure
     * @return
     */
    public Map<String, Object> executeStoredProcedure(String procedure, SqlParameterSource params) {
        return simpleJdbcCall.withProcedureName(procedure).execute(params);
    }
}
