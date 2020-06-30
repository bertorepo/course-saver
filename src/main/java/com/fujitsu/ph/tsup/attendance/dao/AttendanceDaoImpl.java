package com.fujitsu.ph.tsup.attendance.dao;

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

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;



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
//==================================================================================================
/**
 * <pre>
* The data access class for attendance related database access
 * </pre>
 * 
 * @version 0.01
 * @author k.abad
 * @author h.francisco
 * @author j.iwarat
 * @author r.ramos
 * 
 */
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
                + "CSCHED.ID AS ID," 
                + "CSCHED.COURSE_ID AS COURSE_ID," 
                + "C.NAME AS COURSE_NAME,"
                + "CSCHED.INSTRUCTOR_ID AS INSTRUCTOR_ID," 
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME,"
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME," 
                + "CSCHED.VENUE_ID AS VENUE_ID," 
                + "V.NAME AS VENUE_NAME,"
                + "CSCHED.MIN_REQUIRED AS MIN_REQUIRED," 
                + "CSCHED.MAX_REQUIRED AS MAX_REQUIRED,"
                + "COUNT(CPART.PARTICIPANT_ID) AS TOTAL_PARTICIPANTS," 
                + "CSCHED.STATUS AS STATUS"
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME"
                + "CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME"

                + "FROM COURSE_SCHEDULE AS CSCHED" 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET"
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID" 
                + "INNER JOIN COURSE AS C"
                + "ON CSCHED.COURSE_ID = C.ID" 
                + "INNER JOIN EMPLOYEE AS E" 
                + "ON CSCHED.INSTRUCTOR_ID = E.ID"
                + "INNER JOIN VENUE AS V" 
                + "ON CSCHED.VENUE_ID = V.ID" 
                + "INNER JOIN COURSE_PARTICIPANT AS CPART"
                + "ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID"
                + "WHERE CSCHEDDET.SCHEDULED_START_DATETIME BETWEEN :fromDateTime AND :toDateTime"
                + "ORDER BY CSCHED.ID AND CSCHEDDET.SCHEDULED_START_DATETIME";

        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource().addValue("fromDateTime", fromDateTime)
                .addValue("toDateTime", toDateTime);

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
        String sql = "SELECT CSCHEDDET.SCHEDULED_START_DATETIME,"
                +" CSCHEDDET.SCHEDULED_END_DATETIME,"
                +" CSCHEDDET.DURATION,"
                +" C.NAME," 
                +" E.LAST_NAME," 
                +" E.FIRST_NAME,"
                +" V.NAME,"
                +" CPART.COURSE_SCHEDULE_ID,"
                + "CPART.PARTICIPANT_ID,"
                + " CPART.REGISTRATION_DATE"

                + "FROM COURSE_SCHEDULE AS CSCHED"
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID"
                + "INNER JOIN COURSE AS C ON CSCHED.COURSE_ID = C.ID"
                + "INNER JOIN EMPLOYEE AS E ON CSCHED.INSTRUCTOR_ID = E.ID"
                + "INNER JOIN VENUE AS V ON CSCHED.VENUE_ID = V.ID"
                + "INNER JOIN COURSE_PARTICIPANT AS CPART ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID"
                + "WHERE CSCHED.ID = :id AND CSCHED.STATUS = 'A'";

        List<CourseParticipant> courseList = template.query(sql, new CourseParticipantRowMapper());
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
     */
    @Override
    public Set<CourseAttendance> findCourseScheduleDetailParticipantsById(Long id) {
        String sql = " SELECT CSCHED.ID AS ID,"
                + " CSCHEDDET.ID AS CSCHEDDET_ID,"
                + " C.NAME AS COURSE_NAME, "
                + " CSCHED.INSTRUCTOR_ID AS INSTRUCTOR_ID,"
                + " E.LAST_NAME AS INSTRUCTOR_LAST_NAME, "
                + " E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME"
                + ", CSCHED.VENUE_ID AS VENUE_ID,"
                + " V.NAME AS VENUE_NAME,"
                + " COUNT(CPART.PARTICIPANT_ID) AS TOTAL_PARTICIPANTS, "
                + " CSCHED.STATUS AS STATUS,"
                + " CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
                + " CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME "

                + " FROM COURSE_SCHEDULE AS CSCHED "
                + " INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "
                + " INNER JOIN COURSE AS C ON CSCHED.COURSE_ID = C.ID "
                + " INNER JOIN EMPLOYEE AS E ON CSCHED.INSTRUCTOR_ID = E.ID "
                + " INNER JOIN VENUE AS V ON CSCHED.VENUE_ID = V.ID "
                + " INNER JOIN COURSE_PARTICIPANT AS CPART ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID "
                + " WHERE COURSE_SCHEDULE_DETAIL.id = :id " 
                + " AND status = A ";

        List<CourseAttendance> listCourseAttendance = template.query(sql, new CourseAttendanceRowMapper());
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
                + "CSCHED.ID AS ID," 
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID,"
                + "C.NAME AS COURSE_NAME," 
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME,"
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME," 
                + "V.NAME AS VENUE_NAME,"
                + "CPART.PARTICIPANT_ID AS PARTICIPANT_ID" 
                + "E.LAST_NAME AS PARTICIPANT_LAST_NAME,"
                + "E.FIRST_NAME AS PARTICIPANT_FIRST_NAME," 
                + "CSCHEDDET.DURATION AS DURATION"
                + "CSCHED.STATUS AS STATUS" 
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME"
                + "CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME"
                + "CPART.REGISTRATION_DATE AS REGISTRATION_DATE" 
                + "CATTEN.LOG_IN_DATETIME AS LOG_IN_DATETIME"

                + "FROM COURSE_SCHEDULE AS CSCHED" 
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET"
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID" 
                + "INNER JOIN COURSE AS C"
                + "ON CSCHED.COURSE_ID = C.ID" 
                + "INNER JOIN EMPLOYEE AS E" 
                + "ON CSCHED.INSTRUCTOR_ID = E.ID"
                + "INNER JOIN VENUE AS V" 
                + "ON CSCHED.VENUE_ID = V.ID" 
                + "INNER JOIN COURSE_ATTENDANCE AS CATTEN"
                + "ON CSCHED.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID" 
                + "WHERE COURSE_SCHEDULE_DETAIL.id = argument.id"
                + "AND status = A";

        List<CourseAttendance> listCourseAttendance = template.query(sql, new CourseAttendanceRowMapper());
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
     * @author @author h.francisco
     */
    @Override
    public Set<CourseAttendance> saveAttendance(CourseAttendance courseAttendance) {
        String sql = "INSERT INTO "
                + "COURSE_ATTENDANCE(course_schedule_detail_id, course_name, instructor_name, "
                + "venue_name, participant_id, participant_name, scheduled_start_date_time, "
                + "scheduled_end_date_time, duration, registration_date, login_date_time, status)"
                + "VALUES(:courseScheduleDetailId, :courseName, :instructorName, :venueName, "
                + ":participantId, :participantName, :scheduledStartDateTime, :scheduledEndDateTime, "
                + ":duration, :registrationDate, :loginDateTime, :status)";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseScheduleDetailId", courseAttendance.getCourseScheduleDetailId())
                .addValue("courseName", courseAttendance.getCourseName())
                .addValue("instructorName", courseAttendance.getInstructorName())
                .addValue("venueName", courseAttendance.getVenueName())
                .addValue("participantId", courseAttendance.getParticipantId())
                .addValue("participantName", courseAttendance.getParticipantName())
                .addValue("scheduledStartDateTime", courseAttendance.getScheduleStartDateTime())
                .addValue("scheduledEndDateTime", courseAttendance.getScheduleEndDateTime())
                .addValue("duration", courseAttendance.getDuration())
                .addValue("registrationDate", courseAttendance.getRegistrationDate())
                .addValue("loginDateTime", courseAttendance.getLoginDateTime())
                .addValue("status", courseAttendance.getStatus());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        generatedKeyHolder.getKeys().get("id");
        return null;
    }

    /**
     * <pre>
     * Updates the course attendance
     * 
     * <pre>
     * 
     * @param courseAttendance
     * @return CourseAttendance
     * @author @author h.francisco
     */
    @Override
    public Set<CourseAttendance> updateAttendance(CourseAttendance courseAttendance) {
        String sql = "UPDATE COURSE_ATTENDANCE SET course_schedule_detail_id = :courseScheduleDetailId, "
                + "course_name = :courseName, instructor_name = :instructorName, venue_name = :venueName, "
                + "participant_id = :participantId, participant_name = :participantName, "
                + "scheduled_start_date_time = :scheduledStartDateTime, scheduled_end_date_time = :scheduledEndDateTime, "
                + "duration = :duration, registration_date = :registrationDate, login_date_time = :loginDateTime, "
                + "status = :status)"
                + "WHERE id = " + courseAttendance.getId();

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseScheduleDetailId", courseAttendance.getCourseScheduleDetailId())
                .addValue("courseName", courseAttendance.getCourseName())
                .addValue("instructorName", courseAttendance.getInstructorName())
                .addValue("venueName", courseAttendance.getVenueName())
                .addValue("participantId", courseAttendance.getParticipantId())
                .addValue("participantName", courseAttendance.getParticipantName())
                .addValue("scheduledStartDateTime", courseAttendance.getScheduleStartDateTime())
                .addValue("scheduledEndDateTime", courseAttendance.getScheduleEndDateTime())
                .addValue("duration", courseAttendance.getDuration())
                .addValue("registrationDate", courseAttendance.getRegistrationDate())
                .addValue("loginDateTime", courseAttendance.getLoginDateTime())
                .addValue("status", courseAttendance.getStatus());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        generatedKeyHolder.getKeys().get("id");
        return null;
    }

    /**
     * <pre>
     * Finds the course participants by course schedule detail id
     * 
     * <pre>
     * 
     * @param id
     * @return CourseAttendance
     * @author @author h.francisco
     */
    @Override
    public Set<CourseAttendance> findCourseScheduleDetailById(Long id) {
        String sql = "SELECT CSCHEDDET.SCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME,"
                + "CSCHEDDET.DURATION, "
                + "C.NAME, "
                + "E.LAST_NAME, "
                + "E.FIRST_NAME,"
                + "V.NAME, CPART.COURSE_SCHEDULE_ID,"
                + "CPART.PARTICIPANT_ID, "
                + "CPART.REGISTRATION_DATE"

                + "FROM COURSE_SCHEDULE AS CSCHED"
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID"
                + "INNER JOIN COURSE AS C ON CSCHED.COURSE_ID = C.ID"
                + "INNER JOIN EMPLOYEE AS E ON CSCHED.INSTRUCTOR_ID = E.ID"
                + "INNER JOIN VENUE AS V ON CSCHED.VENUE_ID = V.ID"
                + "INNER JOIN COURSE_PARTICIPANT AS CPART ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID"
                + "WHERE CSCHED.ID = :id AND CSCHED.STATUS = 'A'";

        List<CourseAttendance> attendanceList = template.query(sql, new CourseAttendanceRowMapper());
        Set<CourseAttendance> setCourse = new HashSet<CourseAttendance>(attendanceList);
        return setCourse;
    }

}
