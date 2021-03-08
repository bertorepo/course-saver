//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : CoursesConductedRowMapper                                                                                                                                                             
// Class Name   : CoursesConductedRowMapper.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/02/22 | WS)J.Barbadillo       | New Creation             
// 1.0.1   | 2021/03/08 | WS)R.Molina           | Updated
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
	                ZonedDateTime.ofInstant(rs.getTimestamp("SCHEDULED_START_DATETIME").toInstant(),
	                        ZoneId.systemDefault());
          ZonedDateTime rescheduledStartDateTime = 
                ZonedDateTime.ofInstant(rs.getTimestamp("RESCHEDULED_START_DATETIME").toInstant(),
                        ZoneId.systemDefault());
          
          ZonedDateTime scheduledEndDateTime = 
	                ZonedDateTime.ofInstant(rs.getTimestamp("SCHEDULED_END_DATETIME").toInstant(),
	                        ZoneId.systemDefault());
        ZonedDateTime rescheduledEndDateTime = 
              ZonedDateTime.ofInstant(rs.getTimestamp("RESCHEDULED_END_DATETIME").toInstant(),
                      ZoneId.systemDefault());

	      CoursesConducted conductedCourse = new CoursesConducted.Builder(id, name, scheduledStartDateTime,
	    		rescheduledStartDateTime,scheduledEndDateTime, rescheduledEndDateTime).build();
	  
	    return conductedCourse ; 
	 }
}
