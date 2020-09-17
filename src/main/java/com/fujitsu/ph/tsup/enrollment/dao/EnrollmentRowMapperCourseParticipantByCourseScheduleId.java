package com.fujitsu.ph.tsup.enrollment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;

public class EnrollmentRowMapperCourseParticipantByCourseScheduleId implements RowMapper<CourseParticipant>{

	@Override
	public CourseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Long participantId = rs.getLong("EMP_ID");
		String employeeNumber = rs.getString("EMPLOYEE_ID");
		String participantName = rs.getString("EMPLOYEE_LAST_NAME") +", "+ rs.getString("EMPLOYEE_FIRST_NAME");
		String employeeEmail =rs.getString("EMAIL");
		
		System.out.println("\n(RM)EmployeeNumber: " + employeeNumber);
		System.out.println("(RM)ParticipantName: " + participantName);
		System.out.println("(RM)EmployeeEmail: " + employeeEmail + "\n");
		
		CourseParticipant courseParticipant = new CourseParticipant.Builder(participantId, employeeNumber, participantName, employeeEmail).build();
		
		Set<CourseParticipant> courseParticipantSet = new HashSet<>();
		courseParticipantSet.add(courseParticipant);
		return courseParticipant;
	}

}
