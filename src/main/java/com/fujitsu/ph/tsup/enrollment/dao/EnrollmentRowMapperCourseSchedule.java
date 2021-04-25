package com.fujitsu.ph.tsup.enrollment.dao;

//=================================================================================================
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :EnrollmentRowMapperCourseSchedule.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.01    | 06/26/2020 | WS) M.Lumontad        | New Creation
//0.01    | 06/29/2020 | WS) J.Yu              | Update
//0.01    | 07/15/2020 | WS) T.Oviedo          | Update
//=================================================================================================
/**
* <pre>
* Interface for EnrollmentDaoImpl.java
* <pre>
* 
* @version 0.01
* @author m.lumontad                      
*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.jdbc.core.RowMapper;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;

public class EnrollmentRowMapperCourseSchedule implements RowMapper<CourseSchedule> {

    @Override
    public CourseSchedule mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("ID");
        Long courseScheduleDetailId = resultSet.getLong("COURSE_SCHEDULE_DETAIL_ID");//Added
        Long courseId = resultSet.getLong("COURSE_ID");
        String courseName = resultSet.getString("COURSE_NAME");
        String courseDetails = resultSet.getString("DETAILS");
        String deadline = resultSet.getString("DEADLINE");
        String mandatory = resultSet.getString("MANDATORY");
        Long instructorId = resultSet.getLong("INSTRUCTOR_ID");
        String instructorLastName = resultSet.getString("INSTRUCTOR_LAST_NAME");
        String instructorFirstName = resultSet.getString("INSTRUCTOR_FIRST_NAME");
        Long venueId = resultSet.getLong("VENUE_ID");
        String venueName = resultSet.getString("VENUE_NAME");
        int minRequired = resultSet.getInt("MIN_REQUIRED");
        int maxAllowed = resultSet.getInt("MAX_ALLOWED");
        int totalParticipants = resultSet.getInt("TOTAL_PARTICIPANTS");
        char status = resultSet.getString("STATUS").charAt(0);
        ZonedDateTime scheduledStartDateTime = ZonedDateTime.ofInstant(
                resultSet.getTimestamp("SCHEDULED_START_DATETIME").toInstant(), ZoneId.systemDefault());
        ZonedDateTime scheduledEndDateTime = ZonedDateTime.ofInstant(
                resultSet.getTimestamp("SCHEDULED_END_DATETIME").toInstant(), ZoneId.systemDefault());
        float duration = resultSet.getFloat("DURATION");
        
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(courseScheduleDetailId, id, scheduledStartDateTime, scheduledEndDateTime,duration).build();
        
        CourseSchedule courseSchedule = new CourseSchedule.Builder(id, courseId, courseName, instructorId, 
                instructorLastName, instructorFirstName,deadline, mandatory, venueId, venueName, minRequired, 
                maxAllowed, totalParticipants, status).addDetail(courseScheduleDetail).addCourseDetail(courseDetails).build();
       
        return courseSchedule; 
    }
}