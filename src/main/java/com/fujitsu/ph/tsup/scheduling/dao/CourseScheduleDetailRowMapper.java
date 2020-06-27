package com.fujitsu.ph.tsup.scheduling.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

import com.fujitsu.ph.tsup.attendance.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;

import org.springframework.jdbc.core.RowMapper;

public class CourseScheduleDetailRowMapper implements RowMapper <CourseScheduleDetail>{
    
    
    
    @Override
    public  CourseScheduleDetail mapRow(ResultSet csd, int rowNum) throws SQLException {
        
        
        CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail();
        
        
        
    
        Long id = csd.getLong("id");
        Long courseScheduleId = csd.getLong("courseScheduleId");
        ZonedDateTime scheduledStartDateTime = csd.getZonedDateTime("scheduledStartDateTime");
        ZonedDateTime scheduledEndDateTime = csd.getZonedDateTime("scheduledStartDateTime");
        float duration = csd.getFloat("duration");
        
        courseScheduleDetail.setId(id);
        courseScheduleDetail.setCourseScheduleId(courseScheduleId);
        courseScheduleDetail.setScheduledStartDateTime(scheduledStartDateTime);
        courseScheduleDetail.setScheduledEndDateTime(scheduledStartDateTime);
        courseScheduleDetail.setDuration(duration);
        
        return courseScheduleDetail;
}
}