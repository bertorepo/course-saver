package com.fujitsu.ph.tsup.enrollment.dao;

//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :EnrollmentRowMapperCourseParticipant.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.01    | 06/26/2020 | WS) M.Lumontad        | New Creation
//0.01    | 06/29/2020 | WS) G.Cabiling        | Updated
//0.01    | 07/08/2020 | WS) K.Freo            | Updated
//0.01    | 07/29/2020 | WS) K.Freo            | Updated
//0.01    | 07/30/2020 | WS) M.Lumontad        | Updated
//=================================================================================================
/**
* <pre>
* Interface for EnrollmentDaoImpl.java
* <pre>
* 
* @version 0.01
* @author m.lumontad
* @author k.freo                     
*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;

public class EnrollmentRowMapperCourseParticipant implements RowMapper<CourseParticipant> {

    @Override
    public CourseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("COURSE_PARTICIPANT_ID");
        Long courseScheduleDetailId = rs.getLong("COURSE_SCHEDULE_DETAIL_ID");
        Long courseScheduleId = rs.getLong("COURSE_SCHEDULE_ID");
        String courseName = rs.getString("COURSE_NAME");
        String instructorName = rs.getString("INSTRUCTOR_LAST_NAME") + ", " + rs.getString("INSTRUCTOR_FIRST_NAME");
        String venueName = rs.getString("VENUE_NAME");
        Long participantId = rs.getLong("PARTICIPANT_ID");
        String participantName = rs.getString("PARTICIPANT_LAST_NAME") + ", " + rs.getString("PARTICIPANT_FIRST_NAME");
        float duration = rs.getFloat("DURATION");
        ZonedDateTime registrationDate = ZonedDateTime.ofInstant(rs.getTimestamp("REGISTRATION_DATE").toInstant(),
                ZoneId.of("UTC"));
        ZonedDateTime scheduledStartDateTime = ZonedDateTime.ofInstant(
                rs.getTimestamp("SCHEDULED_START_DATETIME").toLocalDateTime().toInstant(ZoneOffset.UTC),
                ZoneId.of("UTC"));
        ZonedDateTime scheduledEndDateTime = ZonedDateTime.ofInstant(
                rs.getTimestamp("SCHEDULED_END_DATETIME").toLocalDateTime().toInstant(ZoneOffset.UTC),
                ZoneId.of("UTC"));
       
        CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(courseScheduleDetailId,
                courseScheduleId, scheduledStartDateTime, scheduledEndDateTime, duration).build();
        
//        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
//        courseScheduleDetailSet.add(courseScheduleDetail);
        
        CourseParticipant courseParticipant = new CourseParticipant.Builder(id, courseScheduleId, courseName,
                instructorName, venueName, participantId, participantName, registrationDate)
                        .addDetail(courseScheduleDetail).build();
        
        return courseParticipant;
    }

}