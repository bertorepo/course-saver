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
public class MandatoryCoursesDaoImpl implements MandatoryCoursesDao {
	
	/**
	 * Logger Factory
	 */
	private static Logger logger = LoggerFactory.getLogger(MandatoryCoursesDaoImpl .class);
	
	/**
     * JDBC Template for Named Parameters
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

	
    @Override
	public Set<MandatoryCourses> findMandatoryCourses() {
			
		String query = "SELECT "
					+ "C.ID AS ID, "
					+ "C.NAME AS COURSE_NAME " 																							
					+ "FROM TSUP.COURSE C "																						
					+ "WHERE C.MANDATORY = 'YES';";																						

		List<MandatoryCourses> mandatoryCoursesList = template.query(query,
				new  MandatoryCoursesRowMapper());
			Set<MandatoryCourses> mandatoryCourses = new HashSet<>(mandatoryCoursesList );

		    logger.debug("Result: {}", mandatoryCoursesList );
			
			return mandatoryCourses;
		   
		
	}
	
	
    @Override
	public int findTotalNumberOfJdu() {
			
		String query = "SELECT "
					+ "	  COUNT(*) AS TOTAL_NUMBER_OF_JDU "  																							
					+ "FROM TSUP.EMPLOYEE AS E "	
					+ "INNER JOIN DEPARTMENT D "
					+ "	ON D.ID = E.DEPARTMENT_ID "
					+ "WHERE  D.DEPARTMENT_NAME = :DEPARTMENT_NAME;";	
		
		 SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
                 .addValue("DEPARTMENT_NAME", "FDC-G3CC");
		 return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
		 
	}
	
	
    @Override
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
			        + "INNER JOIN TSUP.CERTIFICATE_UPLOAD AS CUPLOAD "																			
			        + "		ON CUPLOAD.COURSE_ID = C.ID "
			        + "WHERE CUPLOAD.CERTIFICATE IS NOT NULL "
			        + "AND C.MANDATORY = 'YES';";					
		
		SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
				.addValue("scheduledStartDateTime", selectedStartDateTime.toOffsetDateTime())
                .addValue("scheduledEndDateTime", selectedEndDateTime.toOffsetDateTime());
		
		
		 return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
	}
	
	
    @Override
	public int findTotalNumberOfJduWhoFinishedTrainingLastweek(ZonedDateTime selectedStartDateTime,
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
			        + "INNER JOIN TSUP.CERTIFICATE_UPLOAD AS CUPLOAD "																			
			        + "		ON CUPLOAD.COURSE_ID = C.ID "
			        + "WHERE CUPLOAD.CERTIFICATE IS NOT NULL "
			        + "AND C.MANDATORY = 'YES' "
			        + "AND CA.LOG_OUT_DATETIME <= (NOW()::DATE-EXTRACT(DOW from NOW())::INTEGER)";																				
			        																						

		SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
				.addValue("scheduledStartDateTime", selectedStartDateTime.toOffsetDateTime())
                .addValue("scheduledEndDateTime", selectedEndDateTime.toOffsetDateTime());

		 return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
	}
	
	
	
}
