//==================================================================================================																																											
// Project Name : Training Sign Up
// System Name  : MandatoryCoursesDaoImpl																																								
// Class Name   : MandatoryCoursesDaoImpl.java 																																											
//																																											
// <<Modification History>> 																																											
// Version | Date       | Updated By            | Content																																											
//---------+------------+-----------------------+--------------------------------------------------- 																																											
// 1.0.0   | 2021/04/21 | WS)J.Barbadillo       | New Creation		
//==================================================================================================
package com.fujitsu.ph.tsup.reports.summary.dao;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.course.dao.CoursesConductedRowMapper;

import com.fujitsu.ph.tsup.reports.summary.model.*;

/**
 * <pre>
 * The Dao for the MandatoryCourses
 * </pre>
 * 
 * @author j.barbadillo
 * @version 1.0.0
 */

@Repository
public class MandatoryCoursesDaoImpl {
	
	/**
	 * Logger Factory
	 */
	private static Logger logger = LoggerFactory.getLogger(MandatoryCoursesDaoImpl .class);
	
	/**
     * JDBC Template for Named Parameters
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

	//NEED TO ADJUST
	public Set<MandatoryCourses> findMandatoryCourses(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime) {
			
		String query = "SELECT "
					+ "	    C.ID AS ID, "
					+ "		C.NAME AS COURSE_NAME" 																							
					+ "FROM TSUP.COURSE_SCHEDULE AS CSCHED "																							
					+ "INNER JOIN TSUP.COURSE_SCHEDULE_DETAIL AS CSD "																							
					+ "		ON CSCHED.ID = CSD.COURSE_SCHEDULE_ID "																				
					+ "INNER JOIN TSUP.COURSE_ATTENDANCE AS CA "																					
					+ "		ON CA.ID = CSD.ID "																							
					+ "INNER JOIN tsup.COURSE AS C "																								
					+ "		ON CSCHED.COURSE_ID = C.ID "																							
					+ "WHERE COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) " 																								
					+ "BETWEEN :scheduledStartDateTime "
					+ "AND :scheduledEndDateTime "																					
					+ "AND C.MANDATORY = 'YES';";																						
		
		SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
				.addValue("scheduledStartDateTime", selectedStartDateTime.toOffsetDateTime())
                .addValue("scheduledEndDateTime", selectedEndDateTime.toOffsetDateTime());
		
		List<MandatoryCourses> conductedCourseList = template.query(query, mandatoryCoursesParameters,
				new  MandatoryCoursesRowMapper());
			Set<MandatoryCourses> mandatoryCourses = new HashSet<>(conductedCourseList);

		    logger.debug("Result: {}", conductedCourseList);
			
			return mandatoryCourses;
		   
		return null;
		
	}
	
	// IF DEPARTMENT ID IS 2 WHICH IS G3CC
	// NEED TO ADJUST
	public int findTotalNumberOfJdu(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime) {
			
		String query = "SELECT "
					+ "	  COUNT(*) AS TOTAL_NUMBER_OF_JDU "  																							
					+ "FROM TSUP.EMPLOYEE AS E "																							
					+ "WHERE  E.DEPARTMENT_ID = :id;";	
		
		 SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
                 .addValue(":id", 2);
		 return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
		 
	}
	
	// NEED TO ADJUST
	public int findTotalNumberOfJduWhoFinishedTraining(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime) {
			
		String query =  "SELECT "																					
					+ "		COUNT(CA.ID) AS TOTAL_NUMBER_OF_JDU_WHO_FINISHED_TRAINING "																				
					+ "FROM TSUP.COURSE_SCHEDULE AS CSCHED "																					
			        + "INNER JOIN TSUP.COURSE_SCHEDULE_DETAIL AS CSCHEDDET "																					
				    + "		ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "																				
			        + "INNER JOIN TSUP.COURSE_ATTENDANCE AS CA "																					
			        + "		ON CA.ID = CSCHEDDET.ID "																				
			        + "INNER JOIN tsup.COURSE AS C "																				
			        + "		ON CSCHED.COURSE_ID = C.ID "																				
			        + "WHERE CA.LOG_OUT_DATETIME "
			        + "BETWEEN :scheduledStartDateTime "
					+ "AND :scheduledEndDateTime "																					
					+ "AND C.MANDATORY = 'YES';";					
		
		SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
				.addValue("scheduledStartDateTime", selectedStartDateTime.toOffsetDateTime())
                .addValue("scheduledEndDateTime", selectedEndDateTime.toOffsetDateTime());
		
		
		 return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
	}
	
	// NEED TO ADJUST
	public int findTotalNumberOfJduWhoFinishedTrainingLastweek(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime) {
			
		String query =  "SELECT "																					
					+ "		COUNT(CA.ID) AS TOTAL_NUMBER_OF_JDU_WHO_FINISHED_TRAINING_LASTWEEK  "																				
					+ "FROM TSUP.COURSE_SCHEDULE AS CSCHED "																					
			        + "INNER JOIN TSUP.COURSE_SCHEDULE_DETAIL AS CSCHEDDET "																					
				    + "		ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "																				
			        + "INNER JOIN TSUP.COURSE_ATTENDANCE AS CA "																					
			        + "		ON CA.ID = CSCHEDDET.ID "																				
			        + "INNER JOIN tsup.COURSE AS C "																				
			        + "		ON CSCHED.COURSE_ID = C.ID "																				
			        + "WHERE C.MANDATORY = 'YES' AND "																					 
			        + "		((CA.LOG_OUT_DATETIME >= date_trunc('week', CURRENT_TIMESTAMP - interval '1 week' ) "																					
			        + "			AND CA.LOG_OUT_DATETIME < date_trunc('week', CURRENT_TIMESTAMP)));";																				
			        																						

		SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
				.addValue("scheduledStartDateTime", selectedStartDateTime.toOffsetDateTime())
                .addValue("scheduledEndDateTime", selectedEndDateTime.toOffsetDateTime());

		 return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
	}
	
	
	
}
