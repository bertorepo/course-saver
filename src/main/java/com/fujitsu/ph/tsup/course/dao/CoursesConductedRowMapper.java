//==================================================================================================																																											
// Project Name : Training Sign Up
// System Name  : CoursesConductedRowMapper																																								
// Class Name   : CoursesConductedRowMapper.java 																																											
//																																											
// <<Modification History>> 																																											
// Version | Date       | Updated By            | Content																																											
//---------+------------+-----------------------+--------------------------------------------------- 																																											
// 1.0.0   | 2021/02/22 | WS)J.Barbadillo       | New Creation																																											
//==================================================================================================
package com.fujitsu.ph.tsup.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.course.model.CoursesConducted;

/**
 * <pre>
 * The controller for CoursesConductedRowMapper
 * </pre>
 * 
 * @author j.barbadillo
 * @version 1.0.0
 */

public class CoursesConductedRowMapper implements RowMapper<CoursesConducted> {
	
	 /**
     * <pre>
     * Maps the Rows returned by ResultSet
     * </pre>
     * @param ResultSet rs
     * @param int rowNum
     * @throws SQLException
     */
	@Override 
	  public CoursesConducted mapRow(ResultSet rs, int rowNum) throws SQLException {
	  
		  Long id = rs.getLong("ID");
		  String name = rs.getString("COURSE_NAME");
		  
		  ZonedDateTime scheduledStartDateTime = 
	                ZonedDateTime.ofInstant(rs.getTimestamp("PLAN_DATE").toInstant(),
	                        ZoneId.of("UTC"));
          ZonedDateTime rescheduledStartDateTime = 
                ZonedDateTime.ofInstant(rs.getTimestamp("ACTUAL_DATE").toInstant(),
                        ZoneId.of("UTC"));

	      CoursesConducted conductedCourse = new CoursesConducted.Builder(id, name, scheduledStartDateTime,
	    		rescheduledStartDateTime).build();
	  
	    return conductedCourse ; 
	 }

}
