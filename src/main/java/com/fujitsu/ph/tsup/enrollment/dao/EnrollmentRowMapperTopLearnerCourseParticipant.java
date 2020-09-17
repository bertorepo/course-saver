package com.fujitsu.ph.tsup.enrollment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;

public class EnrollmentRowMapperTopLearnerCourseParticipant implements RowMapper<CourseParticipant> {

	@Override
	public CourseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Long participant_Id = rs.getLong("PARTICIPANT_ID");
		String participantName = rs.getString("PARTICIPANT_LAST_NAME") +", "+rs.getString("PARTICIPANT_FIRST_NAME");
		int courseAttended = rs.getInt("COURSE_ATTENDED");

		System.out.println("\n(RM)Participant ID: " + participant_Id);
		System.out.println("Course Attended: "+ courseAttended);
    	System.out.println("(RM)Participant Name: " + participantName + "\n");
		CourseParticipant courseParticipant = new CourseParticipant.Builder(participant_Id, participantName).build();
		return courseParticipant;
	}

}
