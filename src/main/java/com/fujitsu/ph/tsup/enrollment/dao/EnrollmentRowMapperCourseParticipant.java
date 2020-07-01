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
//=================================================================================================
/**
* <pre>
* Interface for EnrollmentDaoImpl.java
* <pre>
* 
* @version 0.01
* @author m.lumontad    
* @author g.cabiling                  
*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;

public class EnrollmentRowMapperCourseParticipant implements RowMapper<CourseParticipant> {

    @Override
    public CourseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Long courseScheduleId = rs.getLong("courseScheduleId");
//        Long participantId = rs.getLong("participantId");
//        CourseParticipant courseParticipant = new CourseParticipant.Builder(courseScheduleId, participantId).build();
//        return courseParticipant;
    	
    	Long id = rs.getLong("ID");
		Long courseScheduleId = rs.getLong("COURSE_SCHEDULE_ID");
		String courseName = rs.getString("COURSE_NAME");
		String instructorName = rs.getString("INSTRUCTOR_LAST_NAME" + " INSTRUCTOR_FIRST_NAME");
		String venueName = rs.getString("VENUE_NAME");
		ZonedDateTime registrationDate = ZonedDateTime.ofInstant(rs.getTimestamp("REGISTRATION_DATE").toInstant(),
				ZoneId.of("UTC"));
		String reason = rs.getString("REASON");
		ZonedDateTime declineDate = ZonedDateTime.ofInstant(rs.getTimestamp("DECLINE_DATE").toInstant(),
				ZoneId.of("UTC"));
		Long participantId = rs.getLong("PARTICIPANT_ID");

		ZonedDateTime scheduledStartDateTime = ZonedDateTime
				.ofInstant(rs.getTimestamp("SCHEDULED_START_DATETIME").toInstant(), ZoneId.of("UTC"));

		ZonedDateTime scheduledEndDateTime = ZonedDateTime
				.ofInstant(rs.getTimestamp("SCHEDULED_END_DATETIME").toInstant(), ZoneId.of("UTC"));

		CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(id, scheduledStartDateTime,
				scheduledEndDateTime).build();

		Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();

		courseScheduleDetailSet.add(courseScheduleDetail);

		CourseParticipant courseParticipant = new CourseParticipant.Builder(id, courseScheduleId, courseName,
				instructorName, venueName, courseScheduleDetailSet, registrationDate, reason, declineDate,
				participantId).build();

		return courseParticipant;
    }

}
