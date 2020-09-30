package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusParticipant;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.context.SecurityContextHolder;
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
//0.07    | 07/30/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//0.08    | 08/26/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//0.09    | 09/02/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//0.10    | 09/03/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//0.11    | 09/08/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//0.12    | 09/18/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//0.13    | 09/30/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos                     | Update
//==================================================================================================
/**
 * <pre>
 * The data access class for attendance related database access
 * </pre>
 * 
 * @version 0.13
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
                FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
                String sql = "SELECT "
                + "CSCHED.ID AS ID, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_DETAIL_ID, "
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
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME "
                + "FROM COURSE_SCHEDULE AS CSCHED "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE AS C "
                + "ON CSCHED.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E "
                + "ON CSCHED.INSTRUCTOR_ID = E.ID "
                + "INNER JOIN VENUE AS V "
                + "ON CSCHED.VENUE_ID = V.ID ";
               
               
       if(!user.getRoles().contains("Instructor") || user.getRoles().contains("PMO")) {
            sql +=  "WHERE COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                    + "CSCHEDDET.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
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
        } else {
           
            sql +=  "WHERE COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                    + "CSCHEDDET.SCHEDULED_START_DATETIME) BETWEEN :fromDateTime AND :toDateTime "
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
                + "D.ID AS DEPARTMENT_ID, "
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
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "CPART.REGISTRATION_DATE AS REGISTRATION_DATE, "
                +"("
                + "    SELECT EMAIL_ADDRESS "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS EMAIL, "
                +"("
                + "    SELECT NUMBER "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS EMPLOYEE_NUMBER, "
                + "("
                + "    SELECT DEPT.DEPARTMENT_NAME "
                + "    FROM DEPARTMENT AS DEPT "
                + "    INNER JOIN EMPLOYEE AS EMP "
                + "    ON EMP.DEPARTMENT_ID = DEPT.ID "
                + "    WHERE EMP.ID = CPART.PARTICIPANT_ID"
                + ") AS DEPARTMENT_NAME "
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
                + "INNER JOIN DEPARTMENT AS D  "
                + "ON E.DEPARTMENT_ID = D.ID " 
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
                + "CATTEN.ID AS ID, "
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, "
                + "D.ID AS DEPARTMENT_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, "
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "V.NAME AS VENUE_NAME, "
                + "CATTEN.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "("
                + "SELECT LAST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS PARTICIPANT_LAST_NAME, "
                + "("
                + "    SELECT FIRST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS PARTICIPANT_FIRST_NAME, "
                +"("                                      
                + "SELECT NUMBER "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS EMPLOYEE_NUMBER, "
                + "("
                + "     SELECT DEPT.DEPARTMENT_NAME "
                + "     FROM DEPARTMENT AS DEPT "
                + "     INNER JOIN EMPLOYEE AS EMP "
                + "     ON EMP.DEPARTMENT_ID = DEPT.ID "
                + "     WHERE EMP.ID = CATTEN.PARTICIPANT_ID"
                + ") AS DEPARTMENT_NAME, "
                + "("
                + "    SELECT EMAIL_ADDRESS "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID "
                + ") AS EMAIL, "
                + "C.DETAIL AS DETAIL, "
                + "CATTEN.STATUS AS STATUS, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CATTEN.LOG_IN_DATETIME AS LOG_IN_DATETIME, "
                + "CATTEN.LOG_OUT_DATETIME AS LOG_OUT_DATETIME "
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
                + "ON CSCHEDDET.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID "
                + "INNER JOIN DEPARTMENT AS D  "
                + "ON E.DEPARTMENT_ID = D.ID "
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
                + "CATTEN.ID AS ID, "
                + "D.ID AS DEPARTMENT_ID, "
                + "C.NAME AS COURSE_NAME, "  
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, " 
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, " 
                + "V.NAME AS VENUE_NAME, " 
                + "CATTEN.PARTICIPANT_ID AS PARTICIPANT_ID, " 
                + "("
                + "    SELECT LAST_NAME "
                + "    FROM EMPLOYEE  "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS PARTICIPANT_LAST_NAME, " 
                + "("
                + "     SELECT FIRST_NAME "
                + "    FROM EMPLOYEE " 
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS PARTICIPANT_FIRST_NAME, " 
                + "("
                + "    SELECT NUMBER "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS EMPLOYEE_NUMBER, "
                + "("
                + "     SELECT DEPT.DEPARTMENT_NAME "
                + "     FROM DEPARTMENT AS DEPT "
                + "     INNER JOIN EMPLOYEE AS EMP "
                + "     ON EMP.DEPARTMENT_ID = DEPT.ID "
                + "     WHERE EMP.ID = CATTEN.PARTICIPANT_ID"
                + ") AS DEPARTMENT_NAME, "
                + "("
                + "    SELECT EMAIL_ADDRESS "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID "
                + ") AS EMAIL, "
                + "C.DETAIL AS DETAIL, "
                + "CSCHEDDET.DURATION AS DURATION, " 
                + "CATTEN.STATUS AS STATUS, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CATTEN.LOG_IN_DATETIME AS LOG_IN_DATETIME, "
                + "CATTEN.LOG_OUT_DATETIME AS LOG_OUT_DATETIME " 
                + "FROM COURSE_SCHEDULE AS CSCHED  "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID  " 
                + "INNER JOIN COURSE AS C " 
                + "ON CSCHED.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E "
                + "ON CSCHED.INSTRUCTOR_ID = E.ID " 
                + "INNER JOIN VENUE AS V " 
                + "ON CSCHED.VENUE_ID = V.ID " 
                + "INNER JOIN COURSE_ATTENDANCE AS CATTEN " 
                + "ON CSCHEDDET.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID "
                + "INNER JOIN DEPARTMENT AS D  "
                + "ON E.DEPARTMENT_ID = D.ID "
                + "WHERE CSCHEDDET.ID = :id " 
                + "AND CSCHED.STATUS = 'A'";
        
        try {
        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("id", id);
        
        List<CourseAttendance> listCourseAttendance = template.query(sql, courseScheduleParameters, 
                new CourseAttendanceRowMapper());
        
        Set<CourseAttendance> setCourseAttendance = new HashSet<CourseAttendance>(listCourseAttendance);
        return setCourseAttendance;
        }catch(EmptyResultDataAccessException e) {
            return null;
        }

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
        String sql = "UPDATE COURSE_ATTENDANCE " 
                + "SET status= 'P', log_in_datetime= 'now()' "
                + "WHERE participant_id = :participantId AND "
                + "course_schedule_detail_id= :courseScheduleDetailId ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseScheduleDetailId", courseAttendance.getCourseScheduleDetailId())
                .addValue("participantId", courseAttendance.getParticipantId())
                .addValue("loginDateTime", courseAttendance.getLoginDateTime().withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());

        template.update(sql, namedParameters);
    }
     
    @Override
    public void updateLogout(CourseAttendance courseAttendance) {
        String sql = "UPDATE COURSE_ATTENDANCE SET LOG_OUT_DATETIME = :logoutDateTime "
                + "WHERE participant_id = :participantId AND "
                + "COURSE_SCHEDULE_DETAIL_ID = :courseScheduleDetailId ";
    
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("participantId", courseAttendance.getParticipantId())
                .addValue("courseScheduleDetailId", courseAttendance.getCourseScheduleDetailId())
                .addValue("logoutDateTime", courseAttendance.getLogoutDateTime().withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
        template.update(sql, namedParameters);      
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
                + "participant_id = :participantId, "
                + "status = :status, "
                + "log_in_datetime = :loginDateTime, "
                + "log_out_datetime = :logoutDateTime, "
                + "email = :email "
                + "WHERE participant_id = :participantId AND course_schedule_detail_id = :courseScheduleDetailId";

        List<ChangeStatusParticipant> participants = courseAttendance.getParticipants();
        
        for(ChangeStatusParticipant changeStatusParticipant : participants) {
           
            if(changeStatusParticipant.getStatus() == 'P') {
                SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("id", courseAttendance.getId()) 
                    
                    .addValue("courseScheduleDetailId", changeStatusParticipant.getCourseAttendanceId())
                    .addValue("participantId", changeStatusParticipant.getParticipantId())                   
                    .addValue("status", String.valueOf(changeStatusParticipant.getStatus()))
                    .addValue("loginDateTime", changeStatusParticipant.getLoginDateTime().withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                    .addValue("logoutDateTime", changeStatusParticipant.getLogoutDateTime().withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                    .addValue("email", changeStatusParticipant.getEmail());
                     template.update(sql, namedParameters);  
            } else {
                    SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("id", courseAttendance.getId()) 
                                
                    .addValue("courseScheduleDetailId", changeStatusParticipant.getCourseAttendanceId())
                    .addValue("participantId", changeStatusParticipant.getParticipantId())                   
                    .addValue("status", String.valueOf(changeStatusParticipant.getStatus()))
                    .addValue("loginDateTime", null)
                    .addValue("logoutDateTime", null)
                    .addValue("email", changeStatusParticipant.getEmail());
                    template.update(sql, namedParameters);   
             }           
        }                 
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
                + "CATTEN.ID AS ID, "
                + "CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, "
                + "D.ID AS DEPARTMENT_ID, "
                + "C.NAME AS COURSE_NAME, "
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, "
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "V.NAME AS VENUE_NAME, "
                + "CATTEN.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "("
                + "SELECT LAST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS PARTICIPANT_LAST_NAME, "
                + "("
                + "    SELECT FIRST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS PARTICIPANT_FIRST_NAME, "
                + "("
                + "    SELECT EMAIL_ADDRESS "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID "
                + ") AS EMAIL, "
                + "("                         
                + "    SELECT NUMBER "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CATTEN.PARTICIPANT_ID"
                + ") AS EMPLOYEE_NUMBER, "
                + "("
                + "    SELECT DEPT.DEPARTMENT_NAME "
                + "    FROM DEPARTMENT AS DEPT "
                + "    INNER JOIN EMPLOYEE AS EMP "
                + "    ON EMP.DEPARTMENT_ID = DEPT.ID "
                + "    WHERE EMP.ID = CATTEN.PARTICIPANT_ID "
                + ") AS DEPARTMENT_NAME, "
                + "C.DETAIL AS DETAIL, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "CATTEN.STATUS AS STATUS, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME, "
                + "CATTEN.LOG_IN_DATETIME AS LOG_IN_DATETIME, "
                + "CATTEN.LOG_OUT_DATETIME AS LOG_OUT_DATETIME "
                
                + "FROM COURSE_SCHEDULE AS CSCHED "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "
                + "INNER JOIN COURSE AS C "
                + "ON CSCHED.COURSE_ID = C.ID "
                + "INNER JOIN EMPLOYEE AS E "
                + "ON CSCHED.INSTRUCTOR_ID = E.ID "
                + "INNER JOIN VENUE AS V "
                + "ON CSCHED.VENUE_ID = V.ID "
                + "INNER JOIN COURSE_ATTENDANCE AS CATTEN "
                + "ON CSCHEDDET.ID = CATTEN.COURSE_SCHEDULE_DETAIL_ID "
                + "INNER JOIN DEPARTMENT AS D  "
                + "ON E.DEPARTMENT_ID = D.ID "
                + "WHERE CSCHEDDET.ID = :id AND CSCHED.STATUS = 'A' AND "
                + "CATTEN.PARTICIPANT_ID = :PARTICIPANT_ID";
        
        try {
            FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long participantId = user.getId(); 
            System.out.println("ASDASDASD" +participantId);
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id)
                    .addValue("PARTICIPANT_ID", participantId);
            
            List<CourseAttendance> attendanceList = template.query(sql, namedParameters,
                    new CourseAttendanceRowMapper());

            Set<CourseAttendance> setCourse = new HashSet<CourseAttendance>(attendanceList);
            return setCourse;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * <pre>
     * Finds all the scheduled courses by participants
     * 
     * <pre>
     * 
     * @param id
     * @return CourseParticipant
     * @author r.ramos
     */
    @Override
    public Set<CourseParticipant> findAllScheduledCoursesByParticipant(ZonedDateTime fromDateTime, ZonedDateTime toDateTime,
            Long participantId) {

        String query = "SELECT "
                + "CPART.ID AS ID, "
                + "D.ID AS DEPARTMENT_ID, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_ID, "
                + "CSCHED.ID AS COURSE_SCHEDULE_ID, "
                + "C.NAME AS COURSE_NAME, " 
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "V.NAME AS VENUE_NAME, " 
                + "CPART.PARTICIPANT_ID AS PARTICIPANT_ID, "
                + "("
                + "   SELECT LAST_NAME "
                + "   FROM EMPLOYEE "
                + "   WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS PARTICIPANT_LAST_NAME, "
                + "("
                + "    SELECT FIRST_NAME "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS PARTICIPANT_FIRST_NAME, " 
                + "("
                + "    SELECT EMAIL_ADDRESS "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID "
                + ") AS EMAIL, "
                + "("
                + "    SELECT NUMBER "
                + "    FROM EMPLOYEE "
                + "    WHERE ID = CPART.PARTICIPANT_ID"
                + ") AS EMPLOYEE_NUMBER, "
                + "("
                + "    SELECT DEPT.DEPARTMENT_NAME "
                + "    FROM DEPARTMENT AS DEPT "
                + "    INNER JOIN EMPLOYEE AS EMP "
                + "    ON EMP.DEPARTMENT_ID = DEPT.ID "
                + "    WHERE EMP.ID = CPART.PARTICIPANT_ID "
                + ") AS DEPARTMENT_NAME, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "CPART.REGISTRATION_DATE AS REGISTRATION_DATE, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_START_DATETIME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME) AS SCHEDULED_START_DATETIME, "
                + "COALESCE(CSCHEDDET.RESCHEDULED_END_DATETIME, "
                + "CSCHEDDET.SCHEDULED_END_DATETIME) AS SCHEDULED_END_DATETIME "
                + "FROM COURSE_SCHEDULE AS CSCHED "
                + "INNER JOIN COURSE_SCHEDULE_DETAIL AS CSCHEDDET "
                + "ON CSCHEDDET.COURSE_SCHEDULE_ID = CSCHED.ID "
                + "INNER JOIN tsup.COURSE_PARTICIPANT AS CPART " 
                + "ON CPART.COURSE_SCHEDULE_ID = CSCHED.ID " 
                + "INNER JOIN tsup.COURSE AS C "
                + "ON C.ID = CSCHED.COURSE_ID "
                + "INNER JOIN EMPLOYEE AS E " 
                + "ON E.ID = CSCHED.INSTRUCTOR_ID " 
                + "INNER JOIN tsup.VENUE AS V "
                + "ON V.ID = CSCHED.VENUE_ID \r\n"
                + "INNER JOIN DEPARTMENT AS D  "
                + "ON E.DEPARTMENT_ID = D.ID "
                + "WHERE CSCHEDDET.SCHEDULED_START_DATETIME BETWEEN :fromDateTime AND :toDateTime "
                + "AND CPART.PARTICIPANT_ID = :participantId "
               + "AND CSCHED.STATUS = 'A'";
        
        SqlParameterSource courseEnrolledParameters = new MapSqlParameterSource()
                .addValue("fromDateTime",  fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                .addValue("participantId", participantId);
        List<CourseParticipant> listCourseEnrolled = template.query(query, courseEnrolledParameters,
                new CourseParticipantRowMapper());
        Set<CourseParticipant> setCourseParticipant = new HashSet<>(listCourseEnrolled);
        return setCourseParticipant;

    }

}