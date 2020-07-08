package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;

import java.time.ZoneId;
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

//==================================================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : AttendanceDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By                                                | Content
//--------+------------+---------------------------------------------------------+-----------------
//0.01    | 06/26/2020 | WS) K.Abad, WS) H.Francisco, WS) J.Iwarat, WS) R.Ramos    | New Creation
//0.02    | 06/30/2020 | WS) K.Abad                                                | Update
//0.03    | 06/30/2020 | WS) J.Iwarat                                              | Update
//0.04    | 07/08/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//0.05    | 07/08/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//0.06    | 07/08/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//==================================================================================================
/**
 * <pre>
 * The data access class for attendance related database access
 * </pre>
 * 
 * @version 0.06
 * @author k.abad
 * @author h.francisco
 * @author j.iwarat
 * @author r.ramos
 * 
 */
@Repository 
public class AttendanceDaoImpl implements AttendanceDao {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * <pre>
     * Finds the scheduled courses starting from today onwards
     * 
     * <pre>
     * 
     * @param fromDateTime
     * @param toDateTime
     * @param instructorId
     * @return CourseSchedule
     * @author j.iwarat
     */
    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime,
            Long instructorId) {
                String sql = "SELECT " 
                + "CSCHED.ID AS ID, " 
                + "CSCHED.COURSE_ID AS COURSE_ID," 
                + "C.NAME AS COURSE_NAME,"
                + "CSCHED.INSTRUCTOR_ID AS INSTRUCTOR_ID," 
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME,"
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME," 
                + "CSCHED.VENUE_ID AS VENUE_ID," 
                + "V.NAME AS VENUE_NAME,"
                + "CSCHED.MIN_REQUIRED AS MIN_REQUIRED," 
                + "CSCHED.MAX_ALLOWED AS MAX_ALLOWED,"
                + "("
                + "SELECT COUNT(PARTICIPANT_ID) "
                + "    FROM COURSE_PARTICIPANT"
                + "    WHERE COURSE_SCHEDULE_ID = CSCHED.ID"
                + ") AS PARTICIPANT_ID,"
                + "CSCHED.STATUS AS STATUS,"
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME,"
                + "CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME "
                + "FROM COURSE_SCHEDULE AS CSCHED " 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID " 
                + "INNER JOIN COURSE AS C "
                + "ON CSCHED.COURSE_ID = C.ID " 
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON CSCHED.INSTRUCTOR_ID = E.ID "
                + "INNER JOIN VENUE AS V " 
                + "ON CSCHED.VENUE_ID = V.ID " 
                + "LEFT JOIN COURSE_PARTICIPANT AS CPART "
                + "ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID "
                + "WHERE CSCHEDDET.SCHEDULED_START_DATETIME BETWEEN :fromDateTime AND :toDateTime "
                + "AND CSCHED.INSTRUCTOR_ID = :instructorId "
                + "AND CSCHED.STATUS = 'A' "
                + "ORDER BY CSCHED.ID, CSCHEDDET.SCHEDULED_START_DATETIME ";

        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("fromDateTime", fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                .addValue("instructorId", instructorId);

        List<CourseSchedule> listCourseSchedule = template.query(sql, courseScheduleParameters,
                new CourseScheduleRowMapper());
        Set<CourseSchedule> setCourseSchedule = new HashSet<CourseSchedule>(listCourseSchedule);
        return setCourseSchedule;
    }

    /**
     * <pre>
     * Finds the course schedule by id
     * 
     * <pre>
     * 
     * @param id
     * @return CourseParticipant
     * @author k.abad
     */
    @Override
    public Set<CourseParticipant> findCourseScheduleById(Long id) {
        String sql = "SELECT "
                + "CSCHED.ID AS ID, " 
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME,"            
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "V.NAME AS VENUE_NAME, "
                + "CPART.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "("
                + "SELECT LAST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS PARTICIPANT_LAST_NAME, "
                + "("
                + "    SELECT FIRST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS PARTICIPANT_FIRST_NAME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "CPART.REGISTRATION_DATE AS REGISTRATION_DATE, "
                + "E.EMAIL_ADDRESS AS EMAIL, "
                + "E.NUMBER AS EMPLOYEE_NUMBER "

                + "FROM COURSE_SCHEDULE AS CSCHED "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE AS C "
                + "ON CSCHED.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E "
                + "ON CSCHED.INSTRUCTOR_ID = E.ID "
                + "INNER JOIN VENUE AS V "
                + "ON CSCHED.VENUE_ID = V.ID "
                + "INNER JOIN COURSE_PARTICIPANT AS CPART "
                + "ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID "
                + "WHERE CSCHED.ID = :id AND CSCHED.STATUS = 'A'";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        
        List<CourseParticipant> courseList = template
                .query(sql, namedParameters, new CourseParticipantRowMapper());
        
        Set<CourseParticipant> course = new HashSet<CourseParticipant>(courseList);
        return course; 
    }

    /**
     * <pre>
     * Finds the course participants by course schedule detail id
     * </pre>
     * 
     * @param id
     * @return courseAttendanceSet
     * @author r.ramos
     * @author k.abad
     */
    @Override
    public Set<CourseAttendance> findCourseScheduleDetailParticipantsById(Long id) {
        String sql = "SELECT "
                + "CSCHED.ID AS ID, "
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, "
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "V.NAME AS VENUE_NAME, "
                + "CPART.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "("
                + "SELECT LAST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS PARTICIPANT_LAST_NAME, "
                + "("
                + "    SELECT FIRST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS PARTICIPANT_FIRST_NAME, "
                + "CSCHED.STATUS AS STATUS, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME, "
                + "CATTEN.LOG_IN_DATETIME AS LOG_IN_DATETIME "

                + "FROM COURSE_SCHEDULE AS CSCHED "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE AS C "
                + "ON CSCHED.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E "
                + "ON CSCHED.INSTRUCTOR_ID = E.ID "
                + "INNER JOIN VENUE AS V "
                + "ON CSCHED.VENUE_ID = V.ID "
                + "INNER JOIN COURSE_PARTICIPANT AS CPART "
                + "ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE_ATTENDANCE AS CATTEN "
                + "ON CSCHED.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID " 
                + "WHERE CSCHEDDET.ID = :id AND CSCHED.STATUS = 'A'";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        
        List<CourseAttendance> listCourseAttendance = template
                .query(sql, namedParameters, new CourseAttendanceRowMapper());
        
        Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>(listCourseAttendance);
        return courseAttendanceSet;
    }

    /**
     * <pre>
     * Finds the course attendance by id
     * 
     * <pre>
     * 
     * @param id
     * @return CourseAttendance
     * @author j.iwarat
     */
    @Override
    public Set<CourseAttendance> findCourseAttendanceByCourseScheduleDetailId(Long id) {
        String sql = "SELECT " 
                + "CSCHED.ID AS ID,  " 
                + "C.NAME AS COURSE_NAME,  "  
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, " 
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME,   " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME,   " 
                + "V.NAME AS VENUE_NAME, " 
                + "CATTEN.PARTICIPANT_ID AS PARTICIPANT_ID, " 
                + "("
                + "SELECT LAST_NAME "
                + "    FROM tsup.EMPLOYEE  "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS PARTICIPANT_LAST_NAME, " 
                + "("
                + "    SELECT FIRST_NAME "
                + "    FROM tsup.EMPLOYEE " 
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS PARTICIPANT_FIRST_NAME, " 
                + "CSCHEDDET.DURATION AS DURATION, " 
                + "CSCHED.STATUS AS STATUS, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, " 
                + "CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME, " 
                + "CATTEN.LOG_IN_DATETIME AS LOG_IN_DATETIME " 
                + "FROM COURSE_SCHEDULE AS CSCHED  "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + " ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID  " 
                + "INNER JOIN COURSE AS C " 
                + "ON CSCHED.COURSE_ID = C.ID   "
                + "INNER JOIN EMPLOYEE AS E  "
                + "ON CSCHED.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V  " 
                + "ON CSCHED.VENUE_ID = V.ID " 
                + "INNER JOIN COURSE_ATTENDANCE AS CATTEN  " 
                + "ON CSCHED.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID " 
                + "WHERE COURSE_SCHEDULE_DETAIL_ID = :id " 
                + "AND CSCHED.STATUS = 'A'";
        
        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("id", id);
        
        List<CourseAttendance> listCourseAttendance = template.query(sql, courseScheduleParameters, 
                new CourseAttendanceRowMapper());
        
        Set<CourseAttendance> setCourseAttendance = new HashSet<CourseAttendance>(listCourseAttendance);
        return setCourseAttendance;

    }

    /**
     * <pre>
     * Creates the course attendance
     * 
     * <pre>
     * 
     * @param courseAttendance
     * @return CourseAttendance
     * @author h.francisco
     * @author r.ramos
     */
    @Override
    public void saveAttendance(CourseAttendance courseAttendance) {
        String sql = "INSERT INTO "
                + "COURSE_ATTENDANCE(COURSE_SCHEDULE_DETAIL_ID, PARTICIPANT_ID, "
                + "STATUS, LOG_IN_DATETIME, LOG_OUT_DATETIME, EMAIL)"
                + "VALUES(:courseScheduleDetailId, :participantId, :status, "
                + ":loginDateTime, '2020-07-01 10:00:00+08', 'r.ramos@fujitsu.com')";

        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseScheduleDetailId", courseAttendance.getCourseScheduleDetailId())
                .addValue("participantId", courseAttendance.getParticipantId())
                .addValue("status", courseAttendance.getStatus())
                .addValue("loginDateTime", courseAttendance.getLoginDateTime().withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        generatedKeyHolder.getKeys().get("id");
    }

    /**
     * <pre>
     * Updates the course attendance
     * 
     * <pre>
     * 
     * @param courseAttendance
     * @return CourseAttendance
     * @author h.francisco
     * @author r.ramos
     */
    @Override
    public void updateAttendance(CourseAttendance courseAttendance) {
        String sql = "UPDATE COURSE_ATTENDANCE SET course_schedule_detail_id = :courseScheduleDetailId, "
                + "participant_id = :participantId, status = :status, "
                + "log_in_datetime = :loginDateTime, log_out_datetime = '2020-07-01 10:00:00+08', "
                + "email = 'r.ramos@fujitsu.com' "
                + "WHERE id = :id ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", courseAttendance.getId())
                .addValue("courseScheduleDetailId", courseAttendance.getCourseScheduleDetailId())
                .addValue("participantId", courseAttendance.getParticipantId())
                .addValue("status", courseAttendance.getStatus())
                .addValue("loginDateTime", courseAttendance.getLoginDateTime().withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        generatedKeyHolder.getKeys().get("id");
    }

    /**
     * <pre>
     * Finds the course participants by course schedule detail id
     * 
     * <pre>
     * 
     * @param id
     * @return CourseAttendance
     * @author h.francisco
     * @author k.abad
     */
    @Override
    public Set<CourseAttendance> findCourseScheduleDetailById(Long id) {
        String sql = "SELECT "
                + "CSCHED.ID AS ID, "
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, "
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "V.NAME AS VENUE_NAME, "
                + "CPART.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "("
                + "SELECT LAST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS PARTICIPANT_LAST_NAME, "
                + "("
                + "    SELECT FIRST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS PARTICIPANT_FIRST_NAME, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "CSCHED.STATUS AS STATUS, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME, "
                + "CATTEN.LOG_IN_DATETIME AS LOG_IN_DATETIME "

                + "FROM COURSE_SCHEDULE AS CSCHED "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE AS C "
                + "ON CSCHED.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E "
                + "ON CSCHED.INSTRUCTOR_ID = E.ID "
                + "INNER JOIN VENUE AS V "
                + "ON CSCHED.VENUE_ID = V.ID "
                + "INNER JOIN COURSE_PARTICIPANT AS CPART "
                + "ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE_ATTENDANCE AS CATTEN "
                + "ON CSCHED.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID " 
                + "WHERE CSCHEDDET.ID = :id AND CSCHED.STATUS = 'A'";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);
        
        List<CourseAttendance> attendanceList = template
                .query(sql, namedParameters, new CourseAttendanceRowMapper());
        
        Set<CourseAttendance> setCourse = new HashSet<CourseAttendance>(attendanceList);
        return setCourse;
    }
}
