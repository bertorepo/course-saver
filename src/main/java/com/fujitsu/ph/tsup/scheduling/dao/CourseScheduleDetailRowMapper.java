package com.fujitsu.ph.tsup.scheduling.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDetailForm;
//import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;

import org.springframework.jdbc.core.RowMapper;

public class CourseScheduleDetailRowMapper implements RowMapper <CourseScheduleDetailForm>{
    
    @Override
    public  CourseScheduleDetailForm mapRow(ResultSet csd, int rowNum) throws SQLException {
        
        CourseScheduleDetailForm courseScheduleDetail = new CourseScheduleDetailForm();

        Long id = csd.getLong("id");
        ZonedDateTime scheduledStartDateTime = 
                ZonedDateTime.ofInstant(csd.getTimestamp("scheduledStartDateTime").toInstant(),
                        ZoneId.of("UTC"));
        ZonedDateTime scheduledEndDateTime = 
                ZonedDateTime.ofInstant(csd.getTimestamp("scheduledEndDateTime").toInstant(),
                        ZoneId.of("UTC"));
        float duration = csd.getFloat("duration");
        
        courseScheduleDetail.setId(id);
        courseScheduleDetail.setScheduledEndDateTime(scheduledEndDateTime);
        courseScheduleDetail.setScheduledStartDateTime(scheduledStartDateTime);
        courseScheduleDetail.setDuration(duration);

        return courseScheduleDetail;
}
}