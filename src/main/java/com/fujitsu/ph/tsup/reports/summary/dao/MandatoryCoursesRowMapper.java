//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : MandatoryCoursesRowMapper                                                                                                                                                             
// Class Name   : MandatoryCoursesRowMapper.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/02/22 | WS)J.Barbadillo       | New Creation             
//==================================================================================================
package com.fujitsu.ph.tsup.reports.summary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.course.model.CoursesConducted;

/**
 * <pre>
 * 	The RowMapper for MandatoryCourses
 * </pre>
 * 
 * @author j.barbadillo
 * @version 1.0.0
 */
public class MandatoryCoursesRowMapper implements RowMapper<MandatoryCourses>{

	  /**
	    * <pre>
	    * 	Maps the Rows returned by ResultSet
	    * </pre>
	    * @param ResultSet rs
	    * @param int rowNum
	    * @throws SQLException
	    */
		public MandatoryCoursesRowMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
		  
			  Long id = rs.getLong("ID");
			  String name = rs.getString("COURSE_NAME");
		
	
			  MandatoryCourses mandatoryCourses = new  MandatoryCourses.Builder(id, name).build();
		  
		    return mandatoryCourses ; 
		 }
		
}
