package com.fujitsu.ph.tsup.scheduling.dao;

import java.time.ZoneId;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/26/2020 | WS) J.Macabugao | New Creation
//0.01    | 06/26/2020 | WS) JC.Jimenez  | New Creation
//0.01    | 06/26/2020 | WS) J.Balanon   | New Creation
//=======================================================

/**
* <pre>
* The data access class for schedule related database access
* <pre>
* @version 0.01
* @author jc.jimenez
* @author j.balanon
* @author j.macabugao
*
*/

import java.time.ZonedDateTime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    /**
     * JDBC Template for Named Parameters
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * <pre>
     * Finds the scheduled courses starting from today onwards
     * 
     * <pre>
     * 
     * @param ZonedDateTime scheduledStartDateTime
     * @param ZonedDateTime scheduledEndDateTime
     */
    
    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime,
            ZonedDateTime toDateTime) {

        String query = "SELECT " 
                + "CSCHED.ID AS ID, " 
                + "CSCHED.COURSE_ID AS COURSE_ID, "
                + "C.NAME AS COURSE_NAME, " 
                + "CSCHED.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "CSCHED.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, "
                + "CSCHED.MIN_REQUIRED AS MIN_REQUIRED, " 
                + "CSCHED.MAX_ALLOWED AS MAX_ALLOWED, "
                + "CSCHED.STATUS AS STATUS, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME, "
                + "CSCHEDDET.DURATION AS DURATION "
                + "FROM COURSE_SCHEDULE AS CSCHED " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + " ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C "
                + " ON CSCHED.COURSE_ID = C.ID " 
                + "INNER JOIN EMPLOYEE AS E"
                + " ON CSCHED.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V " 
                + " ON CSCHED.VENUE_ID = V.ID "
                + "WHERE CSCHEDDET.SCHEDULED_START_DATETIME BETWEEN :fromDateTime AND :toDateTime "
                + "ORDER BY CSCHED.ID, CSCHEDDET.SCHEDULED_START_DATETIME";

        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("fromDateTime", fromDateTime.toOffsetDateTime())
                .addValue("toDateTime", toDateTime.toOffsetDateTime());

        List<CourseSchedule> courseScheduleList = template.query(query, courseScheduleParameters,
                new CourseScheduleRowMapper());
        Set<CourseSchedule> courseSchedule = new HashSet<>(courseScheduleList);

        return courseSchedule;
    }

    /**
     * <pre>
     * Finds all courses
     * 
     * <pre>
     */
    @Override
    public Set<CourseForm> findAllCourses() {
        String query = "SELECT * FROM COURSE";

        List<CourseForm> courseList = template.query(query, new CourseRowMapper());
        Set<CourseForm> courses = new HashSet<>(courseList);

        return courses;
    }

    /**
     * <pre>
     * Finds all instructors
     * 
     * <pre>
     */
    
    @Override
    public Set<InstructorForm> findAllInstructors() {
        String query = "SELECT E.ID, E.FIRST_NAME, E.LAST_NAME "
                     + "FROM EMPLOYEE AS E "
                     + "INNER JOIN EMPLOYEE_AUTH AS EA "
                     + "ON E.USERNAME = EA.USERNAME "
                     + "WHERE EA.AUTH_NAME = 'Instructor'";

        List<InstructorForm> instructorList = template.query(query, new InstructorRowMapper());
        Set<InstructorForm> instructors = new HashSet<>(instructorList);

        return instructors;
    }

    /**
     * <pre>
     * Finds all venues
     * 
     * <pre>
     */
    @Override
    public Set<VenueForm> findAllVenues() {
        String query = "SELECT * FROM VENUE";

        List<VenueForm> venueList = template.query(query, new VenueRowMapper());
        Set<VenueForm> venues = new HashSet<>(venueList);

        return venues;
    }
    
    /**
     * <pre>
     * Saves the CourseSchedule and CourseScheduleDetail object
     * 
     * <pre>
     * 
     * @param CourseSchedule courseSchedule
     */
    @Override
    public void saveCourseSchedule(CourseSchedule courseSchedule) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String courseScheduleSql = "INSERT INTO COURSE_SCHEDULE"
                + "(COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS) "
                + "VALUES (:course_id, :instructor_id, :venue_id, :min_required, :max_allowed, :status)";

        SqlParameterSource courseSchedParameters = new MapSqlParameterSource()
                .addValue("course_id", courseSchedule.getCourseId())
                .addValue("instructor_id", courseSchedule.getInstructorId())
                .addValue("venue_id", courseSchedule.getVenueId())
                .addValue("min_required", courseSchedule.getMinRequired())
                .addValue("max_allowed", courseSchedule.getMaxAllowed())
                .addValue("status", String.valueOf(courseSchedule.getStatus()));
        template.update(courseScheduleSql, courseSchedParameters, generatedKeyHolder);

        String courseScheduleDetailSql = "INSERT INTO COURSE_SCHEDULE_DETAIL"
                + "(COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, DURATION)"
                + "VALUES (:course_schedule_id, :scheduled_start_datetime, :scheduled_end_datetime, "
                + ":duration)";

        Set<CourseScheduleDetail> courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
        
        Long key = (Long) generatedKeyHolder.getKeys().get("id");
        System.out.println("\nGenerated Course Schedule ID: "+ key +"\n");

        for (CourseScheduleDetail courseSchedDetail : courseScheduleDetail) {
            KeyHolder courseSchedDetailGeneratedKeyHolder = new GeneratedKeyHolder();
            SqlParameterSource courseSchedDetailParameters = new MapSqlParameterSource()
                    .addValue("course_schedule_id", key)
                    .addValue("scheduled_start_datetime", courseSchedDetail.getScheduledStartDateTime().toOffsetDateTime())
                    .addValue("scheduled_end_datetime", courseSchedDetail.getScheduledEndDateTime().toOffsetDateTime())
                    .addValue("duration", courseSchedDetail.getDuration());
            template.update(courseScheduleDetailSql, courseSchedDetailParameters, courseSchedDetailGeneratedKeyHolder);
            
            Long courseSchedDetailKey = (Long) courseSchedDetailGeneratedKeyHolder.getKeys().get("id");
            System.out.println("\nGenerated Course Schedule Detail ID: "+ courseSchedDetailKey +"\n");
        } 
    }

    /**
     * <pre>
     * Update a course schedule 
     * 
     * <pre>
     * 
     * @param Long id
     */
	@Override
	public void updateCourseSchedule(Long id) {
		
		
	}

	/**
     * <pre>
     * Delete a course schedule 
     * 
     * <pre>
     * 
     * @param Long id
     */
	@Override
	public void deleteCourseScheduleById(Long id) {
		
		
	}
}
