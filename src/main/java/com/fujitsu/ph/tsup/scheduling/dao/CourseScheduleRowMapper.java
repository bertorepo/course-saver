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

/**
 * 
 * @author j.balanon
 *
 */
public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {
    
    @Override
    public CourseSchedule mapRow(ResultSet cs, int rowNum) throws SQLException {
        
        Long id = cs.getLong("ID");
        Long courseId = cs.getLong("COURSE_ID");
        String courseName = cs.getString("COURSE_NAME");
        Long instructorId = cs.getLong("INSTRUCTOR_ID");
        String instructorLastName = cs.getString("INSTRUCTOR_LAST_NAME");
        String instructorFirstName = cs.getString("INSTRUCTOR_FIRST_NAME");
        Long venueId = cs.getLong("VENUE_ID");
        String venueName = cs.getString("VENUE_NAME");
        int minRequired = cs.getInt("MIN_REQUIRED");
        int maxAllowed = cs.getInt("MAX_ALLOWED");
        char status = cs.getString("STATUS").charAt(0);
        
        Long courseScheduleDetailId = cs.getLong("COURSE_SCHEDULE_DETAIL_ID");
        
        ZonedDateTime scheduledStartDateTime = 
                ZonedDateTime.ofInstant(cs.getTimestamp("SCHEDULED_START_DATETIME").toInstant(),
                        ZoneId.of("UTC"));
        
        ZonedDateTime scheduledEndDateTime = 
                ZonedDateTime.ofInstant(cs.getTimestamp("SCHEDULED_END_DATETIME").toInstant(),
                        ZoneId.of("UTC"));
        
        float duration = cs.getFloat("DURATION");
        
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(courseScheduleDetailId, 
                        id, scheduledStartDateTime, scheduledEndDateTime, duration).build();
        
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        
        courseScheduleDetailSet.add(courseScheduleDetail);
       
        CourseSchedule courseSchedule = new CourseSchedule.Builder(id, courseId, courseName, instructorId, 
                instructorLastName, instructorFirstName, venueId, venueName, minRequired, maxAllowed, status)
                .addDetail(courseScheduleDetailSet)
                .build();
        
        return courseSchedule;
    }
}
