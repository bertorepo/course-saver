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
				     + "    CSD.SCHEDULED_START_DATETIME AS PLAN_DATE, "
				     + "    COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS ACTUAL_DATE "
				     + "FROM TSUP.COURSE_SCHEDULE AS CS	"
				     + "INNER JOIN TSUP.COURSE_SCHEDULE_DETAIL AS CSD "
				     + "	ON CS.ID = CSD.COURSE_SCHEDULE_ID "
				     + "INNER JOIN TSUP.COURSE AS C	"
				     + "    ON CS.COURSE_ID = C.ID	"
				     + "WHERE COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) BETWEEN :scheduledStartDateTime "
				     + "AND :scheduledEndDateTime "
				     + "AND CS.STATUS = 'D' "
				     + "ORDER BY ID, SCHEDULED_START_DATETIME; ";
		
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
