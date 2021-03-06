package com.fujitsu.ph.tsup.enrollment.dao;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.course.category.dao.CourseCategoryRowMapper;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.course.dao.CourseRowMapper;
import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.enrollment.model.SearchForm;
import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
import com.fujitsu.ph.tsup.scheduling.dao.InstructorRowMapper;
import com.fujitsu.ph.tsup.scheduling.dao.VenueRowMapper;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;
import com.fujitsu.ph.tsup.enrollment.model.Certificate;
import com.fujitsu.ph.tsup.enrollment.model.EnrolledMemberForm;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :EnrollmentDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.01    | 06/26/2020 | WS) M.Lumontad        | New Creation
//0.02    | 06/29/2020 | WS) G.Cabiling        | Updated
//0.03    | 06/30/2020 | WS) K.Freo            | Updated
//0.04    | 07/07/2020 | WS) J.Yu              | Updated
//0.05    | 07/14/2020 | WS) T.Oviedo          | Updated
//0.06    | 09/14/2020 | WS) J.Yu              | Updated
//0.07    | 09/14/2020 | WS) M.Lumontad        | Updated
//0.08	  | 04/19/2021 | WS) M.Atayde		   | Updated
//0.09	  | 05/27/2021 | WS) L.Celoso		   | Updated
//0.10    | 06/14/2021 | WS) L.Celoso          | Updated
//0.10	  | 06/16/2021 | WS) M.Taboada		   | Updated
//=================================================================================================
/**
 * <pre>
 * EnrollmentDaoImpl.java is data access class for enrollment related database
 * access
 * 
 * <pre>
 * 
 * @version 0.01
 * @author m.lumontad
 */
@Repository
public class EnrollmentDaoImpl implements EnrollmentDao {
    @Autowired
    private NamedParameterJdbcTemplate template;
    
    /**
     * Finds the scheduled courses by the given fromDateTime and toDateTime
     * 
     * @param fromDateTime
     * @param toDateTime
     * @return Course Schedule Set
     * @author J.yu
     **/
    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime, String courseCategoryId,String courseNameId, String instructorId, String venueId, String mandatory, String deadline, Pageable pageable) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String query = "SELECT C.NAME AS COURSE_NAME, " 
                + "CS.ID AS ID, " 
                + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "CC.CATEGORY AS COURSE_CATEGORY, " //Added 2021/06/07
                + "CS.COURSE_ID AS COURSE_ID, "
                + "C.DETAIL AS DETAILS, " 
                + "C.MANDATORY AS MANDATORY," // Added
                + "C.DEADLINE AS DEADLINE," // Added
                + "CS.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "CS.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, " 
                + "CS.MIN_REQUIRED AS MIN_REQUIRED, "
                + "CS.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM COURSE_PARTICIPANT "
                + "WHERE COURSE_SCHEDULE_ID = CS.ID), " 
                + "CS.STATUS AS STATUS, "
                + "COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, " 
                + "CSD.DURATION AS DURATION "
                + "FROM COURSE_SCHEDULE AS CS " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSD "
                + "ON CS.ID = CSD.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C " 
                + "ON CS.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CS.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V "
                + "ON CS.VENUE_ID = V.ID "
                + "INNER JOIN COURSE_CATEGORY AS CC "
                + "ON C.COURSE_CATEGORY_ID = CC.ID "; 
        if(!user.getRoles().contains("Instructor") || user.getRoles().contains("PMO")) {
            query +=  "WHERE COALESCE(CSD.RESCHEDULED_START_DATETIME, "
                    + "CSD.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
                    + "AND CS.STATUS = 'A' "
                    + "AND TO_CHAR(C.course_category_id, 'FM9999') LIKE :courseCategoryID "
                    + "AND TO_CHAR(CS.COURSE_ID, 'FM9999') LIKE :courseNameId "
                    + "AND TO_CHAR(CS.INSTRUCTOR_ID, 'FM9999') LIKE :instructorId "
                    + "AND TO_CHAR(CS.VENUE_ID, 'FM9999') LIKE :venueId "
                    + "AND C.MANDATORY LIKE :mandatory "
                    + "AND C.DEADLINE LIKE :deadline "
                    + "ORDER BY SCHEDULED_START_DATETIME "
                    + "LIMIT " + pageable.getPageSize() + " "
                    + "OFFSET " + pageable.getOffset();
	        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
	                .addValue("fromDateTime", fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
	                .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
            		.addValue("courseCategoryID", courseCategoryId)
            		.addValue("courseNameId", courseNameId)
            		.addValue("instructorId", instructorId)
            		.addValue("venueId", venueId)
		    		.addValue("mandatory", mandatory)
		    		.addValue("deadline", deadline);
	        List<CourseSchedule> courseScheduleList = template.query(query, courseScheduleParameters,
	                new EnrollmentRowMapperCourseSchedule());
	        Set<CourseSchedule> courseScheduleSet = new LinkedHashSet<>(courseScheduleList);
	        return courseScheduleSet;
	    } else {           
            query +=  "WHERE COALESCE(CSD.RESCHEDULED_START_DATETIME, "
                    + "CSD.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
                    + "AND CS.INSTRUCTOR_ID = :instructorId "
                    + "AND CS.STATUS = 'A' "
                    + "ORDER BY SCHEDULED_START_DATETIME ";
            SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                    .addValue("fromDateTime", fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                    .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
            List<CourseSchedule> courseScheduleList = template.query(query, courseScheduleParameters,
                    new EnrollmentRowMapperCourseSchedule());
            Set<CourseSchedule> courseScheduleSet = new LinkedHashSet<>(courseScheduleList);
            return courseScheduleSet;
        }
    }
    /**
     * Method to sort the table tsup.course_schedule by tsup.course_schedule.id
     * and tsup.course_schedule.status
     *
     * @param id
     * @param status
     * @return findCourseScheduleById(Long id)
     * @author J.yu
     **/
    @Override
    public CourseSchedule findCourseScheduleById(Long id) {
        String query = "SELECT C.NAME AS COURSE_NAME, " 
                + "CS.ID AS ID, "
                + "C.DETAIL AS DETAILS, " 
                + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "// Added
                + "CC.CATEGORY AS COURSE_CATEGORY, " //Added 2021/06/07
                + "CS.COURSE_ID AS COURSE_ID, " 
                + "CS.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "C.MANDATORY AS MANDATORY," // Added
                + "C.DEADLINE AS DEADLINE," // Added
                + "CS.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, " 
                + "CS.MIN_REQUIRED AS MIN_REQUIRED, "
                + "CS.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM COURSE_PARTICIPANT "
                + "WHERE COURSE_SCHEDULE_ID = CS.ID), " 
                + "CS.STATUS AS STATUS, "
                + "COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, " 
                + "CSD.DURATION AS DURATION "
                + "FROM COURSE_SCHEDULE AS CS " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSD "
                + "ON CS.ID = CSD.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C " 
                + "ON CS.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CS.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V "
                + "ON CS.VENUE_ID = V.ID " 
                + "INNER JOIN COURSE_CATEGORY AS CC "
                + "ON C.COURSE_CATEGORY_ID = CC.ID " 
                + "WHERE CS.ID = :id AND CS.STATUS = 'A' "
                + "ORDER BY C.NAME, CSD.SCHEDULED_START_DATETIME";
        SqlParameterSource findCourseScheduleByIdParameter = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, findCourseScheduleByIdParameter, new EnrollmentRowMapperCourseSchedule());
    }
    /**
     * Method to Sort the table tsup.course_participant by tsup.course_participant.course_schedule_id
     * and tsup.course_participant_id
     * Method to Sort the Table Course Participant by COURSE SCHEDULE ID and
     * 
     * @param courseScheduleId
     * @param participantId
     * @return findCourseParticipantByCourseScheduleIdAndParticipantId(Long courseScheduleId, Long participantId)
     * @author t.oviedo
     **/
    @Override
    public CourseParticipant findCourseParticipantByCourseScheduleIdAndParticipantId(Long courseScheduleId,
            Long participantId) {
        String query = "SELECT CP.ID AS ID, CP.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, "
                + "CP.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "CP.REGISTRATION_DATE AS REGISTRATION_DATE_TIME "
                + "FROM COURSE_PARTICIPANT AS CP "
                + "WHERE CP.COURSE_SCHEDULE_ID = :courseScheduleId "
                + "AND CP.PARTICIPANT_ID = :participantId";
        try {
            SqlParameterSource NamedParameters = new MapSqlParameterSource()
                    .addValue("courseScheduleId", courseScheduleId)
                    .addValue("participantId", participantId);
            return template.queryForObject(query, NamedParameters,
                    new EnrollmentRowMapperCourseParticipantByCourseScheduleIdAndParticipantId());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    /**
     * Method to Save data to Table tsup.course_participant
     **/
    @Override
    public void saveCourseParticipant(CourseParticipant courseParticipant) {
        String saveCourseParticipantSql = "INSERT INTO COURSE_PARTICIPANT "
                + "(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) "
                + "VALUES (:courseScheduleId, :participantId, :registrationDate)";
        SqlParameterSource saveCourseParticipantParameters = new MapSqlParameterSource()
                .addValue("courseScheduleId", courseParticipant.getCourseScheduleId())
                .addValue("participantId", courseParticipant.getParticipantId())
                .addValue("registrationDate", courseParticipant.getRegistrationDate()
                        .withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
        template.update(saveCourseParticipantSql, saveCourseParticipantParameters);
        String saveAttendance = "INSERT INTO COURSE_ATTENDANCE"
                + "(COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID, STATUS, LOG_IN_DATETIME, LOG_OUT_DATETIME, EMAIL) "
                + "VALUES (:courseScheduleDetailId, :participantId, 'A', null, null, :email)";
        SqlParameterSource saveAttendanceParameters = new MapSqlParameterSource()
                .addValue("courseScheduleDetailId", courseParticipant.getCourseScheduleDetail().getId())
                .addValue("participantId", courseParticipant.getParticipantId())
                .addValue("email", courseParticipant.getEmail());
        
        template.update(saveAttendance, saveAttendanceParameters);
    }
    /**
     * Finds the scheduled courses starting from today onwards
     * 
     * @param participantId
     * @param fromDateTime
     * @param toDateTime
     * 
     * @author m.lumontad
     */
    @Override
    public Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId, ZonedDateTime fromDateTime,
            ZonedDateTime toDateTime) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String query = "SELECT CPART.ID AS COURSE_PARTICIPANT_ID, " 
                + "C.ID AS COURSE_ID, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "CSCHED.ID AS COURSE_SCHEDULE_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "C.DETAIL AS DETAILS, " 
                + "C.MANDATORY AS MANDATORY," // Added
                + "C.DEADLINE AS DEADLINE," // Added
                + "CSCHED.STATUS AS COURSE_STATUS," //Added
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, "
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, " 
                + "V.NAME AS VENUE_NAME, "
                + "CPART.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "(SELECT LAST_NAME FROM EMPLOYEE WHERE ID = CPART.PARTICIPANT_ID) AS PARTICIPANT_LAST_NAME, "
                + "(SELECT FIRST_NAME FROM EMPLOYEE WHERE ID = CPART.PARTICIPANT_ID) AS PARTICIPANT_FIRST_NAME, "
                + "CSCHEDDET.DURATION AS DURATION, " 
                + "CPART.REGISTRATION_DATE AS REGISTRATION_DATE, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CATTEN.STATUS AS ATTENDANCE_STATUS "
                + "FROM tsup.COURSE_SCHEDULE AS CSCHED  " 
                + "INNER JOIN tsup.COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHEDDET.COURSE_SCHEDULE_ID = CSCHED.ID " 
                + "INNER JOIN tsup.COURSE_PARTICIPANT AS CPART "
                + "ON CPART.COURSE_SCHEDULE_ID = CSCHED.ID " 
                + "INNER JOIN tsup.COURSE AS C "
                + "ON C.ID = CSCHED.COURSE_ID " 
                + "INNER JOIN tsup.EMPLOYEE AS E " 
                + "ON E.ID = CSCHED.INSTRUCTOR_ID "
                + "INNER JOIN tsup.VENUE AS V " 
                + "ON V.ID = CSCHED.VENUE_ID "
                + "INNER JOIN tsup.COURSE_ATTENDANCE AS CATTEN "
                + "ON CPART.PARTICIPANT_ID = CATTEN.PARTICIPANT_ID AND CSCHEDDET.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID ";
        if(!user.getRoles().contains("Instructor") || user.getRoles().contains("PMO")) {
            query +=  "WHERE COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                    + "CSCHEDDET.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
                    + "AND CPART.PARTICIPANT_ID = :participantId " 
                    + "AND CSCHED.STATUS = 'A' "
                    + "ORDER BY CSCHED.ID, CSCHEDDET.SCHEDULED_START_DATETIME ";
        SqlParameterSource courseEnrolledParameters = new MapSqlParameterSource()
                .addValue("participantId", participantId)
                .addValue("fromDateTime", fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
        List<CourseParticipant> courseEnrolledList = template.query(query, courseEnrolledParameters,
                new EnrollmentRowMapperCourseParticipant());
        Set<CourseParticipant> courseEnrolled = new LinkedHashSet<>(courseEnrolledList);
        return courseEnrolled;
    } else {
        query +=  "WHERE COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
                + "AND CPART.PARTICIPANT_ID = :participantId " 
                + "AND CSCHED.STATUS = 'A' "
                + "ORDER BY CSCHED.ID, CSCHEDDET.SCHEDULED_START_DATETIME ";
        SqlParameterSource courseEnrolledParameters = new MapSqlParameterSource()
                .addValue("participantId", participantId)
                .addValue("fromDateTime", fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
        List<CourseParticipant> courseEnrolledList = template.query(query, courseEnrolledParameters,
                new EnrollmentRowMapperCourseParticipant());
        Set<CourseParticipant> courseEnrolled = new LinkedHashSet<>(courseEnrolledList);
        return courseEnrolled;
    }
  }
    /**
     * <pre>
     *
     * FindCourseParticipantById
     *
     * @author k.freo
     * 
     *         <pre>
     */
    @Override
    public CourseParticipant findCourseParticipantById(Long id) {
        String sql = "SELECT CPART.ID AS COURSE_PARTICIPANT_ID, " 
                + "CSCHED.ID AS ID, "
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, " 
                + "C.ID AS COURSE_ID, "
                + "C.NAME AS COURSE_NAME, " 
                + "C.DETAIL AS DETAILS, " 
                + "C.MANDATORY AS MANDATORY," // Added
                + "C.DEADLINE AS DEADLINE," // Added
                + "CSCHED.STATUS AS COURSE_STATUS," //Added
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, "
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, " 
                + "V.NAME AS VENUE_NAME, "
                + "CPART.REGISTRATION_DATE AS REGISTRATION_DATE, " 
                + "(SELECT LAST_NAME  " 
                + "FROM EMPLOYEE "
                + "WHERE ID = CPART.PARTICIPANT_ID) AS PARTICIPANT_LAST_NAME, " 
                + "(SELECT FIRST_NAME FROM EMPLOYEE " 
                + "WHERE ID = CPART.PARTICIPANT_ID) AS PARTICIPANT_FIRST_NAME,"
                + "(SELECT REASON FROM COURSE_NON_PARTICIPANT " 
                + " WHERE ID = CPART.PARTICIPANT_ID) AS REASON, "
                + "(SELECT DECLINE_DATE FROM COURSE_NON_PARTICIPANT "
                + " WHERE ID = CPART.PARTICIPANT_ID) AS DECLINE_DATE, " 
                + "CPART.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "CATTEN.STATUS AS ATTENDANCE_STATUS "
                + "FROM COURSE_SCHEDULE AS CSCHED " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C "
                + "ON CSCHED.COURSE_ID = C.ID " 
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CSCHED.INSTRUCTOR_ID = E.ID  "
                + "INNER JOIN VENUE AS V  " 
                + "ON CSCHED.VENUE_ID = V.ID " 
                + "INNER JOIN COURSE_PARTICIPANT AS CPART  "
                + "ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID "
                + "INNER JOIN tsup.COURSE_ATTENDANCE AS CATTEN "
                + "ON CPART.PARTICIPANT_ID = CATTEN.PARTICIPANT_ID AND CSCHEDDET.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID "
                + "WHERE CPART.ID = :part_id " 
                + "AND CSCHED.STATUS = 'A'";
        SqlParameterSource NamedParameters = new MapSqlParameterSource().addValue("part_id", id);
        CourseParticipant courseParticipant = template.queryForObject(sql, NamedParameters,
                new EnrollmentRowMapperCourseParticipant());
        return courseParticipant;
    }
    
    /**
     * <pre>
     *
     * saveCourseNonParticipant
     *
     * @author k.freo
     * 
     * <pre>
     */
    @Override
    public void saveCourseNonParticipant(CourseParticipant courseParticipant) {
        String courseParticipantSql = "INSERT INTO COURSE_NON_PARTICIPANT "
                + "(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE) "
                + "SELECT COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, :reason, :declineDate "
                + "FROM COURSE_PARTICIPANT " 
                + "WHERE ID = :id";
        SqlParameterSource coursenonpartParameters = new MapSqlParameterSource()
                .addValue("id", courseParticipant.getId()).addValue("reason", courseParticipant.getReason())
                .addValue("declineDate",
                        courseParticipant.getDeclineDate().withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
        template.update(courseParticipantSql, coursenonpartParameters);
        System.out.println("\nCourse Participant ID who decline: " + courseParticipant.getId() + "\n");
        
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder(); 
        String sql = "DELETE FROM COURSE_ATTENDANCE "
                   + "WHERE COURSE_SCHEDULE_DETAIL_ID = :cscheddet_id "
                   + "AND PARTICIPANT_ID = :part_id "; 
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("cscheddet_id", courseParticipant.getCourseScheduleDetail().getId())
                .addValue("part_id", user.getId());
        template.update(sql, namedParameters, generatedKeyHolder);
        
        Long key = (Long) generatedKeyHolder.getKeys().get("id");
        System.out.println("\nCourse Participant ID to be deleted: " + key + "\n");
    }
    
    /**
     * <pre>
     *
     * deleteCourseParticipantById
     *
     * @author k.freo
     * 
     *         <pre>
     */
    @Override
    public void deleteCourseParticipantById(Long id) {
    	 
         KeyHolder generatedKeyHolder = new GeneratedKeyHolder(); String sql =
         "DELETE FROM COURSE_PARTICIPANT WHERE id = :id"; SqlParameterSource
         namedParameters = new MapSqlParameterSource().addValue("id", id);
         template.update(sql, namedParameters, generatedKeyHolder);
         
         Long key = (Long) generatedKeyHolder.getKeys().get("id");
         System.out.println("\nCourse Participant ID to be deleted: " + key + "\n");
         
    }
    
    @Override
    public void changeCourseScheduleStatus(CourseSchedule courseSchedule) {
        String sql = "UPDATE COURSE_SCHEDULE SET status = :status WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", courseSchedule.getStatus())
                .addValue("id", courseSchedule.getId());
        template.update(sql, namedParameters);
    }
    /**
     * Find All Active Course Schedule
     * 
     * @return
     */
    @Override
    public Set<CourseSchedule> findAllActiveCourseSchedule() {
        
        String query = "SELECT C.NAME AS COURSE_NAME, " 
                + "CS.ID AS ID, " 
                + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "CC.CATEGORY AS COURSE_CATEGORY, " //Added 2021/06/07
                + "CS.COURSE_ID AS COURSE_ID, "
                + "C.DETAIL AS DETAILS, " 
                + "CS.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "C.MANDATORY AS MANDATORY," // Added
                + "C.DEADLINE AS DEADLINE," // Added
                + "CS.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, " 
                + "CS.MIN_REQUIRED AS MIN_REQUIRED, "
                + "CS.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM COURSE_PARTICIPANT "
                + "WHERE COURSE_SCHEDULE_ID = CS.ID), " 
                + "CS.STATUS AS STATUS, "
                + "COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, " 
                + "CSD.DURATION AS DURATION "
                + "FROM COURSE_SCHEDULE AS CS " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSD "
                + "ON CS.ID = CSD.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C " 
                + "ON CS.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CS.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V "
                + "ON CS.VENUE_ID = V.ID " 
                + "INNER JOIN COURSE_CATEGORY AS CC "
                + "ON C.COURSE_CATEGORY_ID = CC.ID " 
                + "WHERE CS.STATUS = 'A' " 
                + "ORDER BY SCHEDULED_START_DATETIME";
        List<CourseSchedule> courseScheduleList = template.query(query, new EnrollmentRowMapperCourseSchedule());
        Set<CourseSchedule> courseScheduleSet = new LinkedHashSet<>(courseScheduleList);
        return courseScheduleSet;
    }
    /**
     * Cancel Course Schedule By Id
     * 
     * @param courseScheduleSet
     */
    @Override
    public void cancelCourseSchedulesById(Set<CourseSchedule> courseScheduleSet) {
        
        String sql = "UPDATE COURSE_SCHEDULE SET status = :status WHERE id = :id";
        for (CourseSchedule courseSchedule : courseScheduleSet) {
            System.out.println("(DAO) COURSE_SCHEDULE_ID" + courseSchedule.getId());
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("status", courseSchedule.getStatus()).addValue("id", courseSchedule.getId());
            template.update(sql, namedParameters);
        }
    }
    @Override
    public Set<CourseSchedule> findAllCourseScheduleBelowMinimumParticipants() {
        String sql = "SELECT C.NAME AS COURSE_NAME, " 
                + "CS.ID AS ID, " 
                + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "CC.CATEGORY AS COURSE_CATEGORY, " //Added 2021/06/07
                + "CS.COURSE_ID AS COURSE_ID, "
                + "C.DETAIL AS DETAILS, " 
                + "CS.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "C.MANDATORY AS MANDATORY," // Added
                + "C.DEADLINE AS DEADLINE," // Added
                + "CS.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, " 
                + "CS.MIN_REQUIRED AS MIN_REQUIRED, "
                + "CS.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM COURSE_PARTICIPANT "
                + "WHERE COURSE_SCHEDULE_ID = CS.ID), " 
                + "CS.STATUS AS STATUS, "
                + "COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, " 
                + "CSD.DURATION AS DURATION "
                + "FROM COURSE_SCHEDULE AS CS " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSD "
                + "ON CS.ID = CSD.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C " 
                + "ON CS.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CS.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V "
                + "ON CS.VENUE_ID = V.ID " 
                + "INNER JOIN COURSE_CATEGORY AS CC "
                + "ON C.COURSE_CATEGORY_ID = CC.ID " 
                + "WHERE CS.STATUS = 'A' "
                + "AND (SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM COURSE_PARTICIPANT WHERE COURSE_SCHEDULE_ID = CS.ID) < MIN_REQUIRED "
                + "ORDER BY SCHEDULED_START_DATETIME";
        List<CourseSchedule> courseScheduleList = template.query(sql, new EnrollmentRowMapperCourseSchedule());
        Set<CourseSchedule> courseScheduleSet = new LinkedHashSet<>(courseScheduleList);
        return courseScheduleSet;
    }
    @Override
    public List<TopLearnerForm> findTopLearnerByMonth() {
        String query = " SELECT " 
                + "CATTEN.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "E.LAST_NAME AS PARTICIPANT_LAST_NAME, " 
                + "E.FIRST_NAME AS PARTICIPANT_FIRST_NAME "
                + "FROM COURSE_ATTENDANCE AS CATTEN " 
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CATTEN.PARTICIPANT_ID = E.ID "
                + "WHERE STATUS = 'P' " 
                + "AND EXTRACT(MONTH FROM LOG_IN_DATETIME) = EXTRACT(MONTH FROM NOW()) "
                + "GROUP BY CATTEN.PARTICIPANT_ID, E.LAST_NAME, E.FIRST_NAME "
                + "ORDER BY COUNT(CATTEN.PARTICIPANT_ID) DESC;";
        List<TopLearnerForm> topLearnerByMonth = template.query(query, new EnrollmentRowMapperTopLearner());
        return topLearnerByMonth;
    }
    @Override
    public List<TopLearnerForm> findTopLearnerByQuarter() {
        String query = "SELECT CATTEN.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "E.LAST_NAME AS PARTICIPANT_LAST_NAME, " 
                + "E.FIRST_NAME AS PARTICIPANT_FIRST_NAME "
                + "FROM COURSE_ATTENDANCE AS CATTEN " 
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CATTEN.PARTICIPANT_ID = E.ID "
                + "WHERE STATUS = 'P' " 
                + "AND EXTRACT(QUARTER FROM LOG_IN_DATETIME) = EXTRACT(QUARTER FROM NOW()) "
                + "GROUP BY CATTEN.PARTICIPANT_ID, E.LAST_NAME, E.FIRST_NAME "
                + "ORDER BY COUNT(CATTEN.PARTICIPANT_ID) DESC;";
        List<TopLearnerForm> topLearnerByQuarter = template.query(query, new EnrollmentRowMapperTopLearner());
        return topLearnerByQuarter;
    }
    @Override
    public Set<CourseSchedule> findAllCourseScheduleByMonth() {
        
        String sql = "SELECT C.NAME AS COURSE_NAME, "
                + "C.DETAIL AS DETAILS, " 
                + "CS.ID AS ID, " 
                + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "CC.CATEGORY AS COURSE_CATEGORY, " //Added 2021/06/07
                + "CS.COURSE_ID AS COURSE_ID, " 
                + "CS.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "C.MANDATORY AS MANDATORY," // Added
                + "C.DEADLINE AS DEADLINE," // Added
                + "CS.VENUE_ID AS VENUE_ID, " 
                + "V.NAME AS VENUE_NAME, " 
                + "CS.MIN_REQUIRED AS MIN_REQUIRED, "
                + "CS.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM COURSE_PARTICIPANT "
                + "WHERE COURSE_SCHEDULE_ID = CS.ID), " 
                + "CS.STATUS AS STATUS, "
                + "COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CSD.DURATION AS DURATION " 
                + "FROM COURSE_SCHEDULE AS CS "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSD " 
                + "ON CS.ID = CSD.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE AS C " 
                + "ON CS.COURSE_ID = C.ID " 
                + "INNER JOIN EMPLOYEE AS E "
                + "ON CS.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V " 
                + "ON CS.VENUE_ID = V.ID "
                + "INNER JOIN COURSE_CATEGORY AS CC "
                + "ON C.COURSE_CATEGORY_ID = CC.ID " 
                + "WHERE CS.STATUS = 'A' "
                + "AND EXTRACT(MONTH FROM CSD.SCHEDULED_START_DATETIME) = EXTRACT(MONTH FROM NOW()) "
                + "ORDER BY SCHEDULED_START_DATETIME";
        List<CourseSchedule> courseScheduleList = template.query(sql, new EnrollmentRowMapperCourseSchedule());
        Set<CourseSchedule> courseScheduleSet = new LinkedHashSet<>(courseScheduleList);
        return courseScheduleSet;
    }
    @Override
    public Set<CourseSchedule> findAllCourseScheduleByQuarter() {
        
        String sql = "SELECT C.NAME AS COURSE_NAME, "
                + "C.DETAIL AS DETAILS, " + "CS.ID AS ID, " + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "// Added
                + "CC.CATEGORY AS COURSE_CATEGORY, " //Added 2021/06/07
                + "CS.COURSE_ID AS COURSE_ID, " + "CS.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "C.MANDATORY AS MANDATORY, " // Added
                + "C.DEADLINE AS DEADLINE, " // Added
                + "CS.VENUE_ID AS VENUE_ID, " + "V.NAME AS VENUE_NAME, " + "CS.MIN_REQUIRED AS MIN_REQUIRED, "
                + "CS.MAX_ALLOWED AS MAX_ALLOWED, "
                + "(SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM COURSE_PARTICIPANT "
                + "WHERE COURSE_SCHEDULE_ID = CS.ID), " + "CS.STATUS AS STATUS, "
                + "COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CSD.DURATION AS DURATION " + "FROM COURSE_SCHEDULE AS CS "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSD " + "ON CS.ID = CSD.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE AS C " + "ON CS.COURSE_ID = C.ID " + "INNER JOIN EMPLOYEE AS E "
                + "ON CS.INSTRUCTOR_ID = E.ID " + "INNER JOIN VENUE AS V " + "ON CS.VENUE_ID = V.ID "
                + "INNER JOIN COURSE_CATEGORY AS CC "
                + "ON C.COURSE_CATEGORY_ID = CC.ID " 
                + "WHERE CS.STATUS = 'A' "
                + "AND EXTRACT(QUARTER FROM CSD.SCHEDULED_START_DATETIME) = EXTRACT(QUARTER FROM NOW()) "
                + " ORDER BY SCHEDULED_START_DATETIME";
        List<CourseSchedule> courseScheduleList = template.query(sql, new EnrollmentRowMapperCourseSchedule());
        Set<CourseSchedule> courseScheduleSet = new LinkedHashSet<>(courseScheduleList);
        return courseScheduleSet;
    }
    @Override
    public void reschedule(CourseScheduleDetail courseScheduleDetail) {
        
        String query = "UPDATE COURSE_SCHEDULE_DETAIL SET RESCHEDULED_START_DATETIME = :startDateTime, "
                + "		RESCHEDULED_END_DATETIME = :endDateTime," + "		DURATION = :duration "
                + "		WHERE ID = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("startDateTime", courseScheduleDetail.getScheduledStartDateTime().toOffsetDateTime())
                .addValue("endDateTime", courseScheduleDetail.getScheduledEndDateTime().toOffsetDateTime())
                .addValue("duration", courseScheduleDetail.getDuration()).addValue("id", courseScheduleDetail.getId());
        template.update(query, namedParameters);
    }
    @Override
    public Set<CourseParticipant> findAllParticipantByCourseScheduleId(Long courseParticipant) {
        
        String query = "SELECT E.NUMBER AS EMPLOYEE_ID, " 
        		+ "						E.ID as EMP_ID, "
                + "			E.LAST_NAME AS EMPLOYEE_LAST_NAME, " 
                + "			E.FIRST_NAME AS EMPLOYEE_FIRST_NAME, "
                + "			E.EMAIL_ADDRESS AS EMAIL "
                + "		FROM COURSE_PARTICIPANT AS CP INNER JOIN EMPLOYEE AS E ON CP.PARTICIPANT_ID = E.ID "
                + "		WHERE CP.COURSE_SCHEDULE_ID = :courseScheduleId";
        SqlParameterSource courseEnrolledParameters = new MapSqlParameterSource().addValue("courseScheduleId",
                courseParticipant);
        List<CourseParticipant> courseEnrolledList = template.query(query, courseEnrolledParameters,
                new EnrollmentRowMapperCourseParticipantByCourseScheduleId());
        Set<CourseParticipant> courseEnrolled = new LinkedHashSet<>(courseEnrolledList);
        return courseEnrolled;
    }
    @Override
    public Set<CourseParticipant> findAllMemberNotEnrolledByCourseScheduleId(CourseParticipant courseParticipant) {
    	System.out.println("START OF DAO");
    	System.out.println("DAO COURSE SCHEDULE ID: " + courseParticipant.getCourseScheduleId());
    	System.out.println("DAO EMPLOYEE NUMBER: " + courseParticipant.getEmployeeNumber());
        
        String query = "SELECT E.NUMBER AS EMPLOYEE_ID, " + "						"
        		+ "						E.ID as EMP_ID, "
                + "						E.LAST_NAME AS EMPLOYEE_LAST_NAME, "
                + "						E.FIRST_NAME AS EMPLOYEE_FIRST_NAME, "
                + "						E.EMAIL_ADDRESS AS EMAIL, "
                + "						E.DEPARTMENT_ID AS DEPARTMENT " 
                + "				FROM TSUP.EMPLOYEE E "
                + "				WHERE DEPARTMENT_ID = (SELECT E.DEPARTMENT_ID FROM tsup.EMPLOYEE E WHERE E.NUMBER = :employeeNumber) " 
                + "				AND E.NUMBER "
                + "					NOT IN (SELECT E.NUMBER " 
                + "							FROM TSUP.EMPLOYEE E "
                + "							INNER JOIN TSUP.COURSE_PARTICIPANT CP "
                + "							ON E.ID = CP.PARTICIPANT_ID "
                + "							WHERE CP.COURSE_SCHEDULE_ID = :courseScheduleId)";
        SqlParameterSource courseEnrolledParameters = new MapSqlParameterSource()
                .addValue("courseScheduleId", courseParticipant.getCourseScheduleId())
                .addValue("employeeNumber", courseParticipant.getEmployeeNumber());
        List<CourseParticipant> courseEnrolledList = template.query(query, courseEnrolledParameters,
                new EnrollmentRowMapperCourseParticipantByCourseScheduleId());
        Set<CourseParticipant> courseEnrolled = new LinkedHashSet<>(courseEnrolledList);
        return courseEnrolled;
    }
    @Override
    public Set<CourseParticipant> findMemberNotEnrolledByCourseScheduleId(SearchForm searchForm) {
        
        String sql = "SELECT E.NUMBER AS EMPLOYEE_ID, " 
                + "E.ID as EMP_ID, "
                + "E.LAST_NAME AS EMPLOYEE_LAST_NAME, " 
                + "E.FIRST_NAME AS EMPLOYEE_FIRST_NAME, "
                + "E.EMAIL_ADDRESS AS EMAIL, " 
                + "E.DEPARTMENT_ID AS DEPARTMENT " 
                + "FROM TSUP.EMPLOYEE E "
                + "WHERE DEPARTMENT_ID = (SELECT E.DEPARTMENT_ID FROM tsup.EMPLOYEE E WHERE E.NUMBER = :employeeNumber) "
                + "AND LOWER(CONCAT(E.FIRST_NAME, E.LAST_NAME, E.USERNAME)) LIKE :search " + "AND E.NUMBER  "
                + "NOT IN (SELECT E.NUMBER " + "	FROM TSUP.EMPLOYEE E " + "	INNER JOIN TSUP.COURSE_PARTICIPANT CP "
                + "	ON E.ID = CP.PARTICIPANT_ID "
                + "	WHERE E.DEPARTMENT_ID = (SELECT E.DEPARTMENT_ID FROM tsup.EMPLOYEE E WHERE E.NUMBER = :employeeNumber) "
                + "	AND CP.COURSE_SCHEDULE_ID = :courseScheduleId) " + "";
        System.out.println("search: " + "%" + searchForm.getSearch() + "%");
        SqlParameterSource courseEnrolledParameters = new MapSqlParameterSource()
                .addValue("employeeNumber", searchForm.getEmployeeNumber())
                .addValue("search", "%" + searchForm.getSearch() + "%")
                .addValue("courseScheduleId", searchForm.getCourseScheduleId());
        List<CourseParticipant> courseEnrolledList = template.query(sql, courseEnrolledParameters,
                new EnrollmentRowMapperCourseParticipantByCourseScheduleId());
        Set<CourseParticipant> courseEnrolled = new LinkedHashSet<>(courseEnrolledList);
        return courseEnrolled;
    }
    @Override
    public Set<CourseSchedule> findCourseScheduleByCourseId(CourseSchedule courseSchedule) {
        
        String query = "SELECT C.NAME AS COURSE_NAME, "
                + "C.DETAIL AS DETAILS, " + "CS.ID AS ID, " + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "// Added
                + "CC.CATEGORY AS COURSE_CATEGORY, " //Added 2021/06/07
                + "CS.COURSE_ID AS COURSE_ID, " + "CS.INSTRUCTOR_ID AS INSTRUCTOR_ID, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "CS.VENUE_ID AS VENUE_ID, " + "V.NAME AS VENUE_NAME, " + "CS.MIN_REQUIRED AS MIN_REQUIRED, "
                + "CS.MAX_ALLOWED AS MAX_ALLOWED, " + "C.DETAIL AS COURSE_DETAIL, " + "C.MANDATORY AS MANDATORY, " + "C.DEADLINE AS DEADLINE, "
                + "(SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM COURSE_PARTICIPANT "
                + "WHERE COURSE_SCHEDULE_ID = CS.ID), " + "CS.STATUS AS STATUS, "
                + "COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, " + "CSD.DURATION AS DURATION "
                + "FROM COURSE_SCHEDULE AS CS " + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSD "
                + "ON CS.ID = CSD.COURSE_SCHEDULE_ID " + "INNER JOIN COURSE AS C " + "ON CS.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E " + "ON CS.INSTRUCTOR_ID = E.ID " + "INNER JOIN VENUE AS V "
                + "ON CS.VENUE_ID = V.ID " 
                + "INNER JOIN COURSE_CATEGORY AS CC "
                + "ON C.COURSE_CATEGORY_ID = CC.ID " + "WHERE CS.STATUS = 'A' "
                + "	AND (SELECT COUNT(PARTICIPANT_ID) AS TOTAL_PARTICIPANTS FROM TSUP.COURSE_PARTICIPANT "
                + "         WHERE COURSE_SCHEDULE_ID = CS.ID) < CS.MAX_ALLOWED " + "	AND CS.COURSE_ID = :courseId"
                + " AND NOT CS.ID = :courseScheduleId";
        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("courseId", courseSchedule.getCourseId())
                .addValue("courseScheduleId", courseSchedule.getId());
        List<CourseSchedule> courseScheduleList = template.query(query, courseScheduleParameters,
                new EnrollmentRowMapperCourseSchedule());
        Set<CourseSchedule> courseScheduleSet = new LinkedHashSet<>(courseScheduleList);
        return courseScheduleSet;
    }
    @Override
    public void updateCourseParticipant(CourseParticipant courseParticipant) {
        String query = "UPDATE COURSE_PARTICIPANT "
                + "		SET COURSE_SCHEDULE_ID = :courseScheduleId, REGISTRATION_DATE = now() "
                + "		WHERE COURSE_SCHEDULE_ID = :id AND PARTICIPANT_ID = :participantId;";
        SqlParameterSource updateCourseParticipantParameters = new MapSqlParameterSource()
                .addValue("courseScheduleId", courseParticipant.getCourseScheduleId())
                .addValue("id", courseParticipant.getId())
                .addValue("participantId", courseParticipant.getParticipantId());
        template.update(query, updateCourseParticipantParameters);
        
        
        String updateAttendance = "UPDATE tsup.COURSE_ATTENDANCE AS CATTEN\r\n"
                + "SET COURSE_SCHEDULE_DETAIL_ID = SCHED_REPLACE.COURSE_SCHEDULE_DETAIL_ID\r\n"
                + "FROM (SELECT SCHEDDET1.ID AS COURSE_SCHEDULE_DETAIL_ID\r\n"
                + "        FROM tsup.COURSE_SCHEDULE AS SCHED1\r\n"
                + "          INNER JOIN tsup.COURSE_SCHEDULE_DETAIL AS SCHEDDET1\r\n"
                + "          ON SCHED1.ID = SCHEDDET1.COURSE_SCHEDULE_ID\r\n"
                + "         WHERE SCHED1.ID = :courseScheduleId) AS SCHED_REPLACE\r\n"
                + "WHERE CATTEN.COURSE_SCHEDULE_DETAIL_ID = (SELECT SCHEDDET2.ID\r\n"
                + "        FROM tsup.COURSE_SCHEDULE AS SCHED2\r\n"
                + "          INNER JOIN tsup.COURSE_SCHEDULE_DETAIL AS SCHEDDET2\r\n"
                + "          ON SCHED2.ID = SCHEDDET2.COURSE_SCHEDULE_ID\r\n"
                + "         WHERE SCHED2.ID = :id)\r\n"
                + "        AND CATTEN.PARTICIPANT_ID = :participantId";
        SqlParameterSource updateCourseAttendanceParameters = new MapSqlParameterSource()
                .addValue("courseScheduleId", courseParticipant.getCourseScheduleId())
                .addValue("id", courseParticipant.getId())
                .addValue("participantId", courseParticipant.getParticipantId());
        template.update(updateAttendance, updateCourseAttendanceParameters);
    }
    
    /**
     * finds and only enables the upload certificate button for mandatory courses from the database
     * 
     * @return mandatoryCourses
     * @author M.Atayde
     **/
    @Override
    public List<String> findCourseScheduleIfMandatory() {
    	
    	String query = "SELECT name"
    			+ " from TSUP.COURSE"
    			+ " WHERE MANDATORY = :isMandatory AND MANDATORY_TYPE = 'GDC'";
    	
    	SqlParameterSource courseMandatoryParameters = new MapSqlParameterSource()
    			.addValue("isMandatory", "Yes");
    	List<String> mandatoryCourses = template.queryForList(query, courseMandatoryParameters, String.class);
        return mandatoryCourses;
    }
    
    /**
     * uploads the certificate to the database
     * @param certificate
     * @author M.Atayde
     **/
    @Override
    public void uploadCertificate(Certificate certificate) {
    	
    	String query = "INSERT INTO CERTIFICATE_UPLOAD"
    			+ " (employee_id, course_id, certificate, upload_date, fileDownloadUri)"
    			+ " VALUES(:employee_id, :course_id, :certificate, :upload_date, :filedownloaduri)";
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
    			.addValue("certificate", certificate.getCertificate())
    			.addValue("employee_id", certificate.getUser())
    			.addValue("course_id", certificate.getCourseId())
    			.addValue("upload_date", certificate.getUploadDate()
    			.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
    			.addValue("filedownloaduri", certificate.getFileDownloadUri());		
    	template.update(query, sqlParameterSource);
    }
    
	@Override
	public String findCertificateName(long userId, long courseId) {
		String query =  
				"SELECT filedownloaduri from tsup.certificate_upload"
				+ " where upload_date =(select max(upload_date)"
				+ " from tsup.certificate_upload"
				+ " where course_id = :course_id and employee_id = :employee_id)";
		
		try {
			SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
					.addValue("course_id", courseId)
					.addValue("employee_id", userId);
			
			String certificateName = template.queryForObject(query, sqlParameterSource, String.class);
			return certificateName;
				} catch (EmptyResultDataAccessException e) {
			    return null;
			}
		}
	
    @Override
    public Set<CourseCategory> findAllCourseCategory() {

        String query = "SELECT * FROM COURSE_CATEGORY ORDER BY category";
        List<CourseCategory> courseCategoryList = template.query(query, new CourseCategoryRowMapper());
        Set<CourseCategory> courseCategory = new LinkedHashSet<>(courseCategoryList);
        return courseCategory;
    }
    
    @Override
    public Set<Course> findAllCourseName() {

        String query = "SELECT * FROM COURSE ORDER BY name";
        List<Course> courseNameList = template.query(query, new CourseRowMapper());
        Set<Course> courseName = new LinkedHashSet<>(courseNameList);
        return courseName;
    }
    
    @Override
    public Set<InstructorForm> findAllInstructor() {

    	 String query = "SELECT E.ID, E.FIRST_NAME, E.LAST_NAME "
                 + "FROM EMPLOYEE AS E "
                 + "INNER JOIN EMPLOYEE_AUTH AS EA "
                 + "ON E.USERNAME = EA.USERNAME "
                 + "WHERE EA.AUTH_NAME = 'Instructor'"
                 + "ORDER BY E.LAST_NAME ASC";
        List<InstructorForm> instructorList = template.query(query, new InstructorRowMapper());
        Set<InstructorForm> instructor = new LinkedHashSet<>(instructorList);
        return instructor;
    }
    
    /**
     * <pre>
     * Finds all venues
     * 
     * <pre>
     */
    @Override
    public Set<VenueForm> findAllVenue() {
        String query = "SELECT * FROM VENUE ORDER BY name ASC";

        List<VenueForm> venueList = template.query(query, new VenueRowMapper());
        Set<VenueForm> venue = new LinkedHashSet<>(venueList);

        return venue;
    }
    
    /**
     * <pre>
     * Method for removing selected enrolled members in a course schedule
     * 
     * <pre>
     */
    @Override
    public void removeBatchMember(EnrolledMemberForm enrolledMember) {
    	
        List<Long> items = Arrays.asList(enrolledMember.getBatchId().split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
    	
        // Delete record from the COURSE_PARTICIPANT table
        String deleteFromCoursePaticipant = "DELETE FROM COURSE_PARTICIPANT " +
        									"WHERE COURSE_SCHEDULE_ID = :course_id " +
        									"AND PARTICIPANT_ID IN (:participant_id) ";
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
    			.addValue("course_id", enrolledMember.getCourseId())
    			.addValue("participant_id", items);	
    	template.update(deleteFromCoursePaticipant, sqlParameterSource);
    	
    	// Delete record from the COURSE_ATTENDANCE table
    	String deleteFromCourseAttendance = "DELETE FROM COURSE_ATTENDANCE " + 
    										"WHERE COURSE_SCHEDULE_DETAIL_ID = :course_id " +
    										"AND PARTICIPANT_ID IN (:participant_id) ";
    	template.update(deleteFromCourseAttendance, sqlParameterSource);
    }
    
    /**
     * Method to Save data to Table tsup.course_participant
     **/
    @Override
    public void enrollBatchMember(EnrolledMemberForm enrolledMember) {
    	List<Long> items = Arrays.asList(enrolledMember.getBatchId().split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
    	
        // Insert record to the COURSE_PARTICIPANT table
        String enrollToCoursePaticipant = "INSERT INTO COURSE_PARTICIPANT " +
        									"(COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE) " +
        									"SELECT :course_id, E.id, NOW() FROM EMPLOYEE E WHERE E.id IN (:participant_id); ";
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
    			.addValue("course_id", enrolledMember.getCourseId())
    			.addValue("participant_id", items);	
    	template.update(enrollToCoursePaticipant, sqlParameterSource);
    	
    	// Insert record to the COURSE_ATTENDANCE table
    	String enrollToCourseAttendance = "INSERT INTO tsup.COURSE_ATTENDANCE " + 
    										"(COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID,STATUS,LOG_IN_DATETIME,LOG_OUT_DATETIME, EMAIL) " +
    										"SELECT :course_id, E.id, 'A', null, null, E.email_address FROM tsup.EMPLOYEE E WHERE E.id IN (:participant_id); ";
    	template.update(enrollToCourseAttendance, sqlParameterSource);
    }
    
    /**
     * Method to Count the available course
     **/
    @Override
    public int countCourse(ZonedDateTime fromDateTime, ZonedDateTime toDateTime, String courseCategoryId,String courseNameId, String instructorId, String venueId, String mandatory, String deadline) {
    	
    	FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	String query = "SELECT COUNT(*) " 
                + "FROM COURSE_SCHEDULE AS CS " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSD "
                + "ON CS.ID = CSD.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C " 
                + "ON CS.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CS.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V "
                + "ON CS.VENUE_ID = V.ID "
                + "INNER JOIN COURSE_CATEGORY AS CC "
                + "ON C.COURSE_CATEGORY_ID = CC.ID "; 
        if(!user.getRoles().contains("Instructor") || user.getRoles().contains("PMO")) {
            query +=  "WHERE COALESCE(CSD.RESCHEDULED_START_DATETIME, "
                    + "CSD.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
                    + "AND CS.STATUS = 'A' "
                    + "AND TO_CHAR(C.course_category_id, 'FM9999') LIKE :courseCategoryID "
                    + "AND TO_CHAR(CS.COURSE_ID, 'FM9999') LIKE :courseNameId "
                    + "AND TO_CHAR(CS.INSTRUCTOR_ID, 'FM9999') LIKE :instructorId "
                    + "AND TO_CHAR(CS.VENUE_ID, 'FM9999') LIKE :venueId "
                    + "AND C.MANDATORY LIKE :mandatory "
                    + "AND C.DEADLINE LIKE :deadline ";
	        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
	                .addValue("fromDateTime", fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
	                .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
            		.addValue("courseCategoryID", courseCategoryId)
            		.addValue("courseNameId", courseNameId)
            		.addValue("instructorId", instructorId)
            		.addValue("venueId", venueId)
		    		.addValue("mandatory", mandatory)
		    		.addValue("deadline", deadline);
	        return template.queryForObject(query, courseScheduleParameters, Integer.class);
	    } else {           
            query +=  "WHERE COALESCE(CSD.RESCHEDULED_START_DATETIME, "
                    + "CSD.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
                    + "AND CS.INSTRUCTOR_ID = :instructorId "
                    + "AND CS.STATUS = 'A' "
                    + "ORDER BY SCHEDULED_START_DATETIME ";
            SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                    .addValue("fromDateTime", fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                    .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
	        return template.queryForObject(query, courseScheduleParameters, Integer.class);
        }
    }
    
    /**
     *
     *Method for getting all email of employees to be enrolled
     */  
    @Override
    public Set<CourseParticipant> getAllEmails(String batchId){
    	
        List<Long> items = Arrays.asList(batchId.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
    	String query = "SELECT E.NUMBER AS EMPLOYEE_ID, " 
        		+ "			E.ID as EMP_ID, "
                + "			E.LAST_NAME AS EMPLOYEE_LAST_NAME, " 
                + "			E.FIRST_NAME AS EMPLOYEE_FIRST_NAME, "
                + "			E.EMAIL_ADDRESS AS EMAIL "
                + "		FROM EMPLOYEE AS E "
                + "		WHERE E.ID IN (:participantIds)";
    	
    	SqlParameterSource courseEnrolledParameters = new MapSqlParameterSource().addValue("participantIds",items);
        List<CourseParticipant> employeeList = template.query(query, 
        													  courseEnrolledParameters,  
        													  new EnrollmentRowMapperCourseParticipantByCourseScheduleId());
        Set<CourseParticipant> employee = new LinkedHashSet<>(employeeList);

        return employee;
    }
}