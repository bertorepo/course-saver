package com.fujitsu.ph.tsup.scheduling.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

import com.fujitsu.ph.tsup.attendance.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;

import org.springframework.jdbc.core.RowMapper;

public class CourseScheduleDetailRowMapper implements RowMapper <CourseScheduleDetailForm>{
    
    @Override
    public  CourseScheduleDetailForm mapRow(ResultSet csd, int rowNum) throws SQLException {

        Long id = csd.getLong("id");
        Long courseScheduleId = csd.getLong("courseScheduleId");
        ZonedDateTime scheduledStartDateTime = csd.getZonedDateTime("scheduledStartDateTime");
        ZonedDateTime scheduledEndDateTime = csd.getZonedDateTime("scheduledStartDateTime");
        float duration = csd.getFloat("duration");

        return courseScheduleDetail;
}
}