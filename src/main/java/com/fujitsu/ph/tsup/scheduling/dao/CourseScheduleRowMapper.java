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

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;

public class CourseScheduleRowMapper implements RowMapper<CourseSchedule> {
    
    @Override
    public CourseSchedule mapRow(ResultSet cs, int rowNum) throws SQLException {
        
        
        
        Long id = cs.getLong("id");
        Long courseId = cs.getLong("courseId");
        String courseName = cs.getString("courseName");
        Long instructorId = cs.getLong("instructorId");
        String instructorLastName = cs.getString("instructorLastName");
        String instructorFirstName = cs.getString("instructorFirstName");
        Long venueId = cs.getLong("venueId");
        String venueName = cs.getString("venueName");
        int minRequired = cs.getInt("minRequired");
        int maxAllowed = cs.getInt("maxAllowed");
        int totalParticipants = cs.getInt("totalParticipants");
        char status = cs.getString("status").charAt(0);
       
        CourseSchedule courseSchedule = new CourseSchedule.Builder(id, courseId, courseName, instructorId, 
                instructorLastName, instructorFirstName, venueId, venueName, 
                minRequired, maxAllowed, totalParticipants, status).build();
     
        return courseSchedule;
    }

}
