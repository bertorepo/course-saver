package com.fujitsu.ph.tsup.scheduling.dao;


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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.TopLearnersForm;
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
        
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String query = "SELECT " 
                + "CSCHED.ID AS ID, " 
                + "CSCHED.COURSE_ID AS COURSE_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "C.DETAIL AS DETAILS, " 
                + "CSCHED.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "CSCHED.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, "
                + "CSCHED.MIN_REQUIRED AS MIN_REQUIRED, " 
                + "CSCHED.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID)"
                + " FROM COURSE_PARTICIPANT"
                + " WHERE COURSE_SCHEDULE_ID = CSCHED.ID) AS TOTAL_PARTICIPANTS, "
                + "CSCHED.STATUS AS STATUS, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + " CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + " CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CSCHEDDET.DURATION AS DURATION "
                + "FROM COURSE_SCHEDULE AS CSCHED " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + " ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C "
                + " ON CSCHED.COURSE_ID = C.ID " 
                + "INNER JOIN EMPLOYEE AS E"
                + " ON CSCHED.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V " 
                + " ON CSCHED.VENUE_ID = V.ID ";
        
        if(!user.getRoles().contains("Instructor") || user.getRoles().contains("PMO")) {
            query += "WHERE COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, " 
                    + " CSCHEDDET.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
                    + "ORDER BY ID, SCHEDULED_START_DATETIME";
        } else {
            query += "WHERE COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, " 
                    + " CSCHEDDET.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
                    + "AND (STATUS NOT IN ('C', 'D'))"
                    + "ORDER BY ID, SCHEDULED_START_DATETIME";
            
        }
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
        
        Set<CourseScheduleDetail> courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
        
        String courseScheduleDetailSql = "INSERT INTO COURSE_SCHEDULE_DETAIL"
                + "(COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, DURATION)"
                + "VALUES (:course_schedule_id, :scheduled_start_datetime, :scheduled_end_datetime, "
                + ":duration)";

        for (CourseScheduleDetail courseSchedDetail : courseScheduleDetail) {
            SqlParameterSource courseSchedParameters = new MapSqlParameterSource()
                    .addValue("course_id", courseSchedule.getCourseId())
                    .addValue("instructor_id", courseSchedule.getInstructorId())
                    .addValue("venue_id", courseSchedule.getVenueId())
                    .addValue("min_required", courseSchedule.getMinRequired())
                    .addValue("max_allowed", courseSchedule.getMaxAllowed())
                    .addValue("status", String.valueOf(courseSchedule.getStatus()));
            template.update(courseScheduleSql, courseSchedParameters, generatedKeyHolder);
            
            Long key = (Long) generatedKeyHolder.getKeys().get("id");
            System.out.println("\nGenerated Course Schedule ID: "+ key +"\n");
            
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
	public void updateCourseSchedule(CourseSchedule courseSchedule) {
		String sql = "UPDATE COURSE_SCHEDULE "
		           + "SET COURSE_ID = :course_id, INSTRUCTOR_ID = :instructor_id, VENUE_ID = :venue_id, "
		           + "MIN_REQUIRED = :min_required, MAX_ALLOWED = :max_allowed  "
		           + "WHERE ID = :cs_id";
		
		SqlParameterSource courseSchedParameters = new MapSqlParameterSource()
                .addValue("course_id", courseSchedule.getCourseId())
                .addValue("instructor_id", courseSchedule.getInstructorId())
                .addValue("venue_id", courseSchedule.getVenueId())
                .addValue("min_required", courseSchedule.getMinRequired())
                .addValue("max_allowed", courseSchedule.getMaxAllowed())
                .addValue("cs_id", courseSchedule.getId());
        template.update(sql, courseSchedParameters);
        
        String courseSchedDetSql = "UPDATE COURSE_SCHEDULE_DETAIL "
                                 + "SET COURSE_SCHEDULE_ID = :course_schedule_id, "
                                 + "RESCHEDULED_START_DATETIME = :scheduled_start_datetime, "
                                 + "RESCHEDULED_END_DATETIME = :scheduled_end_datetime, "
                                 + "DURATION = :duration "
                                 + "WHERE ID = :csdet_id";
        
        for(CourseScheduleDetail courseSchedDetail : courseSchedule.getCourseScheduleDetail()) {
            SqlParameterSource courseSchedDetailParameters = new MapSqlParameterSource()
                    .addValue("course_schedule_id", courseSchedDetail.getCourseScheduleId())
                    .addValue("scheduled_start_datetime", courseSchedDetail.getScheduledStartDateTime().toOffsetDateTime())
                    .addValue("scheduled_end_datetime", courseSchedDetail.getScheduledEndDateTime().toOffsetDateTime())
                    .addValue("duration", courseSchedDetail.getDuration())
                    .addValue("csdet_id", courseSchedDetail.getId());
            template.update(courseSchedDetSql, courseSchedDetailParameters);
        }
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
	    String courseSchedSql = "DELETE FROM COURSE_SCHEDULE WHERE ID = :id ";
	    String courseSchedDetSql = "DELETE FROM COURSE_SCHEDULE_DETAIL WHERE COURSE_SCHEDULE_ID = :cs_id";
	    
	    SqlParameterSource courseSchedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(courseSchedSql, courseSchedParameters);
        
        SqlParameterSource courseSchedDetParameters = new MapSqlParameterSource()
                .addValue("cs_id", id);
        template.update(courseSchedDetSql, courseSchedDetParameters);
        

	}

    /**
     * <pre>
     * Count all Enrolled Courses By Instructor
     * 
     * <pre>
     * 
     * @param Long id
     */
    @Override
    public int countAllEnrolledCoursesByInstructorId(long id) {
        String query = "SELECT COUNT(COURSE_ID) "
                     + "FROM COURSE_SCHEDULE AS CSCHED "
                     + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "  
                     + " ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "
                     + "WHERE INSTRUCTOR_ID = :id "
                     + "AND NOW() BETWEEN CSCHEDDET.SCHEDULED_START_DATETIME AND CSCHEDDET.SCHEDULED_END_DATETIME;";
        
        SqlParameterSource countParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, countParameters, Integer.class);
    }

    /**
     * <pre>
     * Find monthly top learners 
     * <pre>
     */
    @Override
    public List<TopLearnersForm> findMonthlyTopLearners() {
        String query = " SELECT "  
                     + "CATTEN.PARTICIPANT_ID AS PARTICIPANT_ID, "
                     + "E.LAST_NAME AS PARTICIPANT_LAST_NAME, " 
                     + "E.FIRST_NAME AS PARTICIPANT_FIRST_NAME " 
                     + "FROM COURSE_ATTENDANCE AS CATTEN "  
                     + "INNER JOIN EMPLOYEE AS E" 
                     + " ON CATTEN.PARTICIPANT_ID = E.ID "  
                     + "WHERE STATUS = 'P'" 
                     + " AND EXTRACT(MONTH FROM LOG_IN_DATETIME) = EXTRACT(MONTH FROM NOW()) " 
                     + "GROUP BY CATTEN.PARTICIPANT_ID, E.LAST_NAME, E.FIRST_NAME " 
                     + "ORDER BY COUNT(CATTEN.PARTICIPANT_ID) DESC;";
        List<TopLearnersForm> monthlyTopLearners = template.query(query, new TopLearnersRowMapper());
        return monthlyTopLearners;
    }

    /**
     * <pre>
     * Find quarterly top learners 
     * <pre>
     */
    @Override
    public List<TopLearnersForm> findQuarterlyTopLearners() {
        String query = " SELECT "  
                + "CATTEN.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "E.LAST_NAME AS PARTICIPANT_LAST_NAME, " 
                + "E.FIRST_NAME AS PARTICIPANT_FIRST_NAME " 
                + "FROM tsup.COURSE_ATTENDANCE AS CATTEN "  
                + "INNER JOIN tsup.EMPLOYEE AS E" 
                + " ON CATTEN.PARTICIPANT_ID = E.ID "  
                + "WHERE STATUS = 'P'" 
                + " AND EXTRACT(QUARTER FROM LOG_IN_DATETIME) = EXTRACT(QUARTER FROM NOW()) " 
                + "GROUP BY CATTEN.PARTICIPANT_ID, E.LAST_NAME, E.FIRST_NAME " 
                + "ORDER BY COUNT(CATTEN.PARTICIPANT_ID) DESC;";
        List<TopLearnersForm> quarterlyTopLearners = template.query(query, new TopLearnersRowMapper());
        return quarterlyTopLearners;
    }

    /**
     * <pre>
     * Find a course schedule by id
     * 
     * <pre>
     * 
     * @param Long id
     */
    @Override
    public CourseSchedule findCourseScheduleById(Long id) {
        String query = "SELECT " 
                + "CSCHED.ID AS ID, " 
                + "CSCHED.COURSE_ID AS COURSE_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "C.DETAIL AS DETAILS, " 
                + "CSCHED.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "CSCHED.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, "
                + "CSCHED.MIN_REQUIRED AS MIN_REQUIRED, " 
                + "CSCHED.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID)"
                + " FROM COURSE_PARTICIPANT"
                + " WHERE COURSE_SCHEDULE_ID = CSCHED.ID) AS TOTAL_PARTICIPANTS, "
                + "CSCHED.STATUS AS STATUS, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + " CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + " CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
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
                + "WHERE CSCHED.ID = :id ";

        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("id", id);

        CourseSchedule courseSchedule = template.queryForObject(query, courseScheduleParameters,
                new CourseScheduleRowMapper());

        return courseSchedule;
    }

    /**
     * <pre>
     * Find course schedule by course id
     * 
     * <pre>
     * 
     * @param Long id
     */
    @Override
    public Set<CourseSchedule> findCourseScheduleByCourseId(Long id) {
        String query = "SELECT " 
                + "CSCHED.ID AS ID, " 
                + "CSCHED.COURSE_ID AS COURSE_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "C.DETAIL AS DETAILS, " 
                + "CSCHED.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "CSCHED.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, "
                + "CSCHED.MIN_REQUIRED AS MIN_REQUIRED, " 
                + "CSCHED.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID)"
                + " FROM COURSE_PARTICIPANT"
                + " WHERE COURSE_SCHEDULE_ID = CSCHED.ID) AS TOTAL_PARTICIPANTS, "
                + "CSCHED.STATUS AS STATUS, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + " CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + " CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
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
                + "WHERE CSCHED.COURSE_ID = :c_id "
                + "ORDER BY ID, SCHEDULED_START_DATETIME";

        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("c_id", id);

        List<CourseSchedule> courseScheduleList = template.query(query, courseScheduleParameters,
                new CourseScheduleRowMapper());
        Set<CourseSchedule> courseSchedule = new HashSet<>(courseScheduleList);

        return courseSchedule;
    }

    /**
     * <pre>
     * Update a course schedule status
     * 
     * <pre>
     * 
     * @param courseSchedules
     */
    @Override
    public void updateCourseScheduleStatus(Set<CourseSchedule> courseSchedules) {
        String sql = "UPDATE COURSE_SCHEDULE "
                + "SET STATUS = :status "
                + "WHERE ID = :id AND COURSE_ID = :course_id";
        
        for(CourseSchedule courseSchedule : courseSchedules) {
            SqlParameterSource courseSchedParameters = new MapSqlParameterSource()
                    .addValue("status", courseSchedule.getStatus())
                    .addValue("id", courseSchedule.getId())
                    .addValue("course_id", courseSchedule.getCourseId());
            template.update(sql, courseSchedParameters);
        }
    }

}