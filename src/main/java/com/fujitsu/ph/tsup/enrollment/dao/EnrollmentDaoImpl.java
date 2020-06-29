package com.fujitsu.ph.tsup.enrollment.dao;

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
//=================================================================================================
/**
* <pre>
* EnrollmentDaoImpl.java is data access class for enrollment related database access
* <pre>
* 
* @version 0.01
* @author m.lumontad                      
*/
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
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

public class EnrollmentDaoImpl implements EnrollmentDao {
    @Autowired
    private NamedParameterJdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
    
    /**
     * Finds the scheduled courses by the given fromDateTime and toDateTime
     * 
     * @param fromDateTime
     * @param toDateTime
     * @return Course Schedule Set
     * @author J.yu
     **/
    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {
        String query = "SELECT C.NAME, E.FIRST_NAME, E.LAST_NAME, CSD.SCHEDULED_START_DATETIME, CSD.SCHEDULED_END_DATETIME, CSD.DURATION"
                + "FROM COURSE_SCHEDULE_DETAIL AS CSD"
                + "INNER JOIN COURSE_SCHEDULE AS CS"
                + "ON CSD.COURSE_SCHEDULE_ID = C.ID"
                + "INNER JOIN COURSE AS C"
                + "ON CS.COURSE_ID = C.ID"
                + "INNER JOIN EMPLOYEE AS E"
                + "ON CS.INSTRUCTOR_ID = E.ID"
                + "WHERE SCHEDULED_START_DATETIME = :fromDate AND SCHEDULE_END_DATETIME = :toDate";
        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("fromDateTime", fromDateTime)
                .addValue("toDateTime", toDateTime);
        List<CourseSchedule> courseScheduleList = template.query(query, courseScheduleParameters, new EnrollmentRowMapperCourseSchedule());
        Set<CourseSchedule> courseScheduleSet = new HashSet<CourseSchedule>(courseScheduleList);
        return courseScheduleSet;
    }

    /**
     * Method to Sort the Table Course Schedule by ID and STATUS
     **/
    @Override
    public CourseSchedule findCourseScheduleById(Long id) {
        String findCourseScheduleByIdSql = "SELECT * FROM COURSE_SCHEDULE" + "WHERE ID = :id" + "AND STATUS = 'A'";
        SqlParameterSource findCourseScheduleByIdParameter = new MapSqlParameterSource().addValue("id  ", id);
        return template.queryForObject(findCourseScheduleByIdSql, findCourseScheduleByIdParameter,
                new EnrollmentRowMapperCourseSchedule());
    }

    /** 
     * Method to Sort the Table Course Participant by COURSE SCHEDULE ID and PARTICIPANT ID 
     **/
    @Override
    public CourseParticipant findCourseParticipantByCourseScheduleIdAndParticipantId(Long courseScheduleId,
            Long participantId) {
        String findCourseParticipantByCourseScheduleIdAndParticipantIdSql = "SELECT *" + "FROM COURSE_PARTICIPANT "
                + "WHERE COURSE_SCHEDULE_ID = :courseScheduleId " + "AND PARTICIPANT_ID = :participantId )";

        SqlParameterSource NamedParameters = new MapSqlParameterSource()
                .addValue("courseScheduleId  ", courseScheduleId).addValue("participantId", participantId);
        return template.queryForObject(findCourseParticipantByCourseScheduleIdAndParticipantIdSql, NamedParameters,
                new EnrollmentRowMapperCourseParticipant());
    }
    /** 
     * Method to Save Data to Table COURSE PARTICIPANT 
     **/
    @Override
    public void saveCourseParticipant(CourseParticipant courseParticipant) {
        String saveCourseParticipantSql = "INSERT INTO COURSE_PARTICIPANT"
                + "(ID, COURSE_SCHEDULE_ID, COURSE_NAME, INSTRUCTOR_NAME, VENUE_NAME, PARTICIPANT_ID, PARTICIPANT_NAME,"
                + "COURSE_SCHEDULE_DETAILS, REGISTRATION_DATE, REASON, DECLINE_DATE)"
                + "VALUES (:id, :courseScheduleId, :courseName, :instructorName, :venueName, :participantId, :participantName,"
                + ":courseScheduleDetails, :registrationDate, :reason, :declineDate)";

        SqlParameterSource saveCourseParticipantParameters = new MapSqlParameterSource()
                .addValue(" id", courseParticipant.getId())
                .addValue(" courseScheduleId", courseParticipant.getCourseScheduleId())
                .addValue(" courseName", courseParticipant.getCourseName())
                .addValue(" instructorName", courseParticipant.getInstructorName())
                .addValue(" venueName", courseParticipant.getVenueName())
                .addValue(" participantId", courseParticipant.getParticipantId())
                .addValue(" participantName", courseParticipant.getParticipantName())
                .addValue(" courseScheduleDetails", courseParticipant.getCourseScheduleDetails())
                .addValue(" registrationDate", courseParticipant.getRegistrationDate())
                .addValue(" reason", courseParticipant.getReason())
                .addValue(" declineDate", courseParticipant.getDeclineDate());
        template.update(saveCourseParticipantSql, saveCourseParticipantParameters);

    }

    @Override
    public Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId, ZonedDateTime fromDateTime,
            ZonedDateTime toDateTime) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CourseParticipant findCourseParticipantById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCourseParticipantById(Long id) {
        // TODO Auto-generated method stub
    	String sql = "DELETE FROM COURSE_PARTICIPANT WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, namedParameters);
    }

    @Override
    public void saveCourseNonParticipant(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeCourseScheduleStatus(CourseSchedule courseSchedule) {
        // TODO Auto-generated method stub
    	String sql = "UPDATE COURSE_SCHEDULE SET status = :status WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", courseSchedule.getStatus()).addValue("id", courseSchedule.getId());
		template.update(sql, namedParameters);
    }

}
