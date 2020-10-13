package com.fujitsu.ph.tsup.enrollment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;

public class EnrollmentRowMapperCourseParticipantByCourseScheduleIdAndParticipantId implements RowMapper<CourseParticipant>  {

	@Override
	public CourseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("ID");
		Long courseScheduleId = rs.getLong("COURSE_SCHEDULE_ID");
		Long participantId = rs.getLong("PARTICIPANT_ID");
		
		ZonedDateTime registrationDateTime = ZonedDateTime
				.ofInstant(rs.getTimestamp("REGISTRATION_DATE_TIME")
				.toInstant(), ZoneId.systemDefault());
		
		CourseParticipant courseParticipant = new CourseParticipant.Builder(id, courseScheduleId, participantId, registrationDateTime).build();
		return courseParticipant;
	}

}
