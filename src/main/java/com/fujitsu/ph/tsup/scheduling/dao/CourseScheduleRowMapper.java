package com.fujitsu.ph.tsup.scheduling.dao;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/26/2020 | WS) J.Balanon   | New Creation
//
//
//=======================================================

/**
* <pre>
* Custom RowMapper Class for findAllScheduledCourses()
* <pre>
* @version 0.01
* @author j.balanon
*
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;

public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {
    
    @Override
    public CourseSchedule mapRow(ResultSet cs, int rowNum) throws SQLException {
        
        Long id = cs.getLong("id");
        Long courseId = cs.getLong("course_id");
        String courseName = cs.getString("course_name");
        Long instructorId = cs.getLong("instructor_id");
        String instructorLastName = cs.getString("instructor_last_name");
        String instructorFirstName = cs.getString("instructor_first_name");
        Long venueId = cs.getLong("venue_id");
        String venueName = cs.getString("venue_name");
        int minRequired = cs.getInt("min_required");
        int maxAllowed = cs.getInt("max_allowed");
        int totalParticipants = cs.getInt("total_participants");
        char status = cs.getString("status").charAt(0);
        
        ZonedDateTime scheduledStartDateTime = 
                ZonedDateTime.ofInstant(cs.getTimestamp("scheduled_start_datetime").toInstant(),
                        ZoneId.of("UTC"));
        
        ZonedDateTime scheduledEndDateTime = 
                ZonedDateTime.ofInstant(cs.getTimestamp("scheduled_end_datetime").toInstant(),
                        ZoneId.of("UTC"));
        
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(id, scheduledStartDateTime, scheduledEndDateTime).build();
        
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        
        courseScheduleDetailSet.add(courseScheduleDetail);
       
        CourseSchedule courseSchedule = new CourseSchedule.Builder(id, courseId, courseName, instructorId, 
                instructorLastName, instructorFirstName, venueId, venueName, minRequired, maxAllowed, 
                totalParticipants, status)
                .addDetail(courseScheduleDetailSet)
                .build();
        
        return courseSchedule;
    }

}
