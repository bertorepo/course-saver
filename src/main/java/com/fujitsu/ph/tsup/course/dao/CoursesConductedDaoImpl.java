//==================================================================================================																																											
// Project Name : Training Sign Up
// System Name  : CoursesConductedDaoImp																																								
// Class Name   : CoursesConductedDaoImpl.java 																																											
//																																											
// <<Modification History>> 																																											
// Version | Date       | Updated By            | Content																																											
//---------+------------+-----------------------+--------------------------------------------------- 																																											
// 1.0.0   | 2021/02/22 | WS)J.Barbadillo       | New Creation																																											
//==================================================================================================
package com.fujitsu.ph.tsup.course.dao;

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

import com.fujitsu.ph.tsup.course.model.CoursesConducted;

/**
 * <pre>
 * The controller for the CoursesConductedDaoImpl
 * </pre>
 * 
 * @author j.barbadillo
 * @version 1.0.0
 */

@Repository
public class CoursesConductedDaoImpl implements CoursesConductedDao {
	
	/**
	 * Logger Factory
	 */
	private static Logger logger = LoggerFactory.getLogger(CoursesConductedDaoImpl.class);
	
	/**
     * JDBC Template for Named Parameters
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

	
	@Override
	public Set<CoursesConducted> findAllCoursesConducted(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime) {
			
		String query = "SELECT "
				     + "	CSD.ID AS ID, "
				     + "    C.NAME AS COURSE_NAME, "
				     + "    CSD.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
				     + "    COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) "
				     + "	AS RESCHEDULED_START_DATETIME, "
				     + "    CSD.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME,  "
				     + "    COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) "
				     + "	AS RESCHEDULED_END_DATETIME "
				     + "FROM TSUP.COURSE_SCHEDULE AS CS	"
				     + "INNER JOIN TSUP.COURSE_SCHEDULE_DETAIL AS CSD "
				     + "	ON CS.ID = CSD.COURSE_SCHEDULE_ID "
				     + "INNER JOIN TSUP.COURSE AS C	"
				     + "    ON CS.COURSE_ID = C.ID	"
				     + "WHERE COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME)"
				     + "BETWEEN :scheduledStartDateTime "
				     + "AND :scheduledEndDateTime "
				     + "AND CS.STATUS = 'D' "
				     + "ORDER BY COURSE_NAME ASC, SCHEDULED_START_DATETIME ASC;";
		
		SqlParameterSource conductedCourseParameters = new MapSqlParameterSource()
				.addValue("scheduledStartDateTime", selectedStartDateTime.toOffsetDateTime())
                .addValue("scheduledEndDateTime", selectedEndDateTime.toOffsetDateTime());

		List<CoursesConducted> conductedCourseList = template.query(query, conductedCourseParameters,
			new  CoursesConductedRowMapper());
		Set<CoursesConducted> courseConducted = new HashSet<>(conductedCourseList);

	    logger.debug("Result: {}", conductedCourseList);
		
		return courseConducted;

	}

	
}
