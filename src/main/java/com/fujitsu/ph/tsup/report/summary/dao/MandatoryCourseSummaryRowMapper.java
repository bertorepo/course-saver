package com.fujitsu.ph.tsup.report.summary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.report.summary.model.MandatoryCoursesForm;

public class MandatoryCourseSummaryRowMapper implements RowMapper<MandatoryCoursesForm> {

    /**
     * <pre>
     *  Maps the Rows returned by ResultSet
     * </pre>
     * 
     * @param ResultSet rs
     * @param int rowNum
     * @throws SQLException
     */
    public MandatoryCoursesForm mapRow(ResultSet rs, int rowNum) throws SQLException {

        MandatoryCoursesForm mandatoryCoursesForm = new MandatoryCoursesForm();
        Long totalNoOfJDUMem = rs.getLong("TOTAL_NUMBER_OF_JDU");
        Long totalNoOfJDUMemFin = rs.getLong("TOTAL_NUMBER_OF_JDU_WHO_FINISHED_TRAINING");
        Long totalNoOfJDUMemFinLastWk = rs.getLong("TOTAL_NUMBER_OF_JDU_WHO_FINISHED_TRAINING_LASTWEEK");

        mandatoryCoursesForm.setTotalNoOfJDUMem(totalNoOfJDUMem);
        mandatoryCoursesForm.setTotalNoOfJDUMemFin(totalNoOfJDUMemFin);
        mandatoryCoursesForm.setTotalNoOfJDUMemFinLastWk(totalNoOfJDUMemFinLastWk);

        return mandatoryCoursesForm;

    }
}