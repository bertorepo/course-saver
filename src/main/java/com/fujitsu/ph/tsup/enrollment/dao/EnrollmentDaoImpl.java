package com.fujitsu.ph.tsup.enrollment.dao;

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
//0.01    | 06/29/2020 | WS) G.Cabiling        | Updated
//0.02    | 06/30/2020 | WS) K.Freo            | Updated
//0.03    | 07/07/2020 | WS) J.Yu              | Updated
//0.03    | 07/14/2020 | WS) T.Oviedo          | Updated
//0.03    | 07/23/2020 | WS) M.Lumontad        | Updated
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
import com.fujitsu.ph.tsup.enrollment.domain.Participant;
import com.fujitsu.ph.tsup.enrollment.model.MemberSchedule;

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
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {
        String query = "SELECT C.NAME AS COURSE_NAME, "
                + "CS.ID AS ID, "
                + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "//Added
                + "CS.COURSE_ID AS COURSE_ID, "
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
                + "CSD.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
                + "CSD.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME, "
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
                + "WHERE CSD.SCHEDULED_START_DATETIME BETWEEN :fromDateTime AND :toDateTime "
                + "AND CS.STATUS = 'A' "
                +" ORDER BY C.NAME, CSD.SCHEDULED_START_DATETIME";
        SqlParameterSource courseScheduleParameters = new MapSqlParameterSource()
                .addValue("fromDateTime", fromDateTime.withZoneSameInstant(ZoneId.of("UTC"))
                        .toOffsetDateTime())
                .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
        List<CourseSchedule> courseScheduleList = template.query(query, courseScheduleParameters, 
                new EnrollmentRowMapperCourseSchedule());
        Set<CourseSchedule> courseScheduleSet = new HashSet<CourseSchedule>(courseScheduleList);
        return courseScheduleSet;
    }

    /**
     * Method to Sort the Table Course Schedule by ID and STATUS
     **/
    @Override
    public CourseSchedule findCourseScheduleById(Long id) {
//        String findCourseScheduleByIdSql = "SELECT * FROM COURSE_SCHEDULE" + "WHERE ID = :id" + 
//                "AND STATUS = 'A'";
        
        String query = "SELECT C.NAME AS COURSE_NAME, "
                + "CS.ID AS ID, "
                + "CSD.ID AS COURSE_SCHEDULE_DETAIL_ID, "//Added
                + "CS.COURSE_ID AS COURSE_ID, "
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
                + "CSD.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
                + "CSD.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME, "
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
                + "WHERE CS.ID = :id AND CS.STATUS = 'A'"
                +" ORDER BY C.NAME, CSD.SCHEDULED_START_DATETIME";
        
        SqlParameterSource findCourseScheduleByIdParameter = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, findCourseScheduleByIdParameter,
                new EnrollmentRowMapperCourseSchedule());
    }

    /** 
     * Method to Sort the Table Course Participant by COURSE SCHEDULE ID and PARTICIPANT ID 
     **/
    @Override
    public CourseParticipant findCourseParticipantByCourseScheduleIdAndParticipantId(Long courseScheduleId,
            Long participantId) {
//        String findCourseParticipantByCourseScheduleIdAndParticipantIdSql = "SELECT *" + 
//                "FROM COURSE_PARTICIPANT "
//                + "WHERE COURSE_SCHEDULE_ID = :courseScheduleId " + "AND PARTICIPANT_ID = :participantId )";
        
        String query = "SELECT CP.ID AS ID, CP.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, CP.PARTICIPANT_ID AS PARTICIPANT_ID,"
                + " CP.REGISTRATION_DATE AS REGISTRATION_DATE_TIME FROM COURSE_PARTICIPANT AS CP"
                + " where CP.COURSE_SCHEDULE_ID = :courseScheduleId AND CP.PARTICIPANT_ID = :participantId";

        SqlParameterSource NamedParameters = new MapSqlParameterSource()
                .addValue("courseScheduleId", courseScheduleId)
                .addValue("participantId", participantId);
        return template.queryForObject(query, NamedParameters,
                new EnrollmentRowMapperCourseParticipantByCourseScheduleIdAndParticipantId());
    }
    /** 
     * Method to Save Data to Table COURSE PARTICIPANT 
     **/
    @Override
    public void saveCourseParticipant(CourseParticipant courseParticipant) {
         String saveCourseParticipantSql = "INSERT INTO COURSE_PARTICIPANT"
                 + "(ID, COURSE_SCHEDULE_ID, COURSE_NAME, INSTRUCTOR_NAME, VENUE_NAME, PARTICIPANT_ID, "
                 + "PARTICIPANT_NAME, COURSE_SCHEDULE_DETAILS, REGISTRATION_DATE, REASON, DECLINE_DATE)"
                 + "VALUES (:id, :courseScheduleId, :courseName, :instructorName, :venueName, :participantId,"
                 + ":participantName, :courseScheduleDetails, :registrationDate, :reason, :declineDate)";

         SqlParameterSource saveCourseParticipantParameters = new MapSqlParameterSource()
                 .addValue(" id", courseParticipant.getId())
                 .addValue(" courseScheduleId", courseParticipant.getCourseScheduleId())
                 .addValue(" courseName", courseParticipant.getCourseName())
                 .addValue(" instructorName", courseParticipant.getInstructorName())
                 .addValue(" venueName", courseParticipant.getVenueName())
                 .addValue(" participantId", courseParticipant.getParticipantId())
                 .addValue(" participantName", courseParticipant.getParticipantName())
                 .addValue(" courseScheduleDetail", courseParticipant.getCourseScheduleDetail())
                 .addValue(" registrationDate", courseParticipant.getRegistrationDate())
                 .addValue(" reason", courseParticipant.getReason())
                 .addValue(" declineDate", courseParticipant.getDeclineDate());
         template.update(saveCourseParticipantSql, saveCourseParticipantParameters);
    }

    
    /**
     * Finds the scheduled courses starting from today onwards
     * 
     * @param participantId
     * @param fromDateTime
     * @param toDateTime
     * 
     * @author g.cabiling
     */
    
    @Override
    public Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId, 
            ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {
        String query = "SELECT " 
                + "CSCHED.ID AS COURSE_SCHEDULE_ID, "
                + "CSCHEDDET.ID AS COURSE_SCHEDULE_DETAIL_ID, "
                + "CSCHEDDET.DURATION AS DURATION, "
                + "CSCHED.COURSE_ID AS COURSE_ID, "
                + "C.NAME AS COURSE_NAME, " 
                + "E.LAST_NAME AS INSTRUCTOR_LAST_NAME, " 
                + "E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, "
                + "V.NAME AS VENUE_NAME, "
                + "CPART.ID AS COURSE_PARTICIPANT_ID, "
                + "CPART.REGISTRATION_DATE AS REGISTRATION_DATE, "
                + "CNONPART.REASON AS REASON, "
                + "CNONPART.DECLINE_DATE AS DECLINE_DATE, "
                + "E.ID AS PARTICIPANT_ID, "
                + "E.FIRST_NAME AS PARTICIPANT_FIRST_NAME, "
                + "E.LAST_NAME AS PARTICIPANT_LAST_NAME, "
                + "CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, "
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
                + "INNER JOIN COURSE_PARTICIPANT AS CPART "
                + "ON E.ID = CPART.PARTICIPANT_ID "
                + "INNER JOIN COURSE_NON_PARTICIPANT AS CNONPART "
                + "ON CSCHED.ID = CNONPART.COURSE_SCHEDULE_ID " //Added this line
                + "WHERE CSCHEDDET.SCHEDULED_START_DATETIME BETWEEN :fromDateTime AND :toDateTime "
                + "AND CPART.PARTICIPANT_ID = :participantId "
                + "AND CSCHED.STATUS = 'A' ";
        SqlParameterSource courseEnrolledParameters = new MapSqlParameterSource()
                .addValue("participantId", participantId)
                .addValue("fromDateTime",  fromDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime())
                .addValue("toDateTime", toDateTime.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
        
        List<CourseParticipant> courseEnrolledList = template.query(query, courseEnrolledParameters,
                new EnrollmentRowMapperCourseParticipant());
        Set<CourseParticipant> courseEnrolled = new HashSet<>(courseEnrolledList);
        
        return courseEnrolled;
    }

    
    /**
     * <pre>
     *
     *FindCourseParticipantById
     *
     *@author k.freo
     * <pre>
     */
    
    @Override
    public CourseParticipant findCourseParticipantById(Long id) {
        String sql = "SELECT " 
                +"CSCHED.ID AS ID,  "
                +"CSCHEDDET.COURSE_SCHEDULE_ID AS COURSE_SCHEDULE_ID, " 
                +"C.NAME AS COURSE_NAME,  " 
                +"E.LAST_NAME AS INSTRUCTOR_LAST_NAME, "
                +"E.FIRST_NAME AS INSTRUCTOR_FIRST_NAME, " 
                +"V.NAME AS VENUE_NAME, " 
                +"CPART.REGISTRATION_DATE AS REGISTRATION_DATE, "
                  +"(SELECT LAST_NAME  " 
                  +"FROM tsup.EMPLOYEE  "
                  +"WHERE ID = CPART.PARTICIPANT_ID) AS PARTICIPANT_LAST_NAME, " 
                  +"(SELECT FIRST_NAME  "
                  +"FROM tsup.EMPLOYEE " 
                  +"WHERE ID = CPART.PARTICIPANT_ID) AS PARTICIPANT_FIRST_NAME,"
                +"(SELECT REASON FROM tsup.COURSE_NON_PARTICIPANT " 
                +" WHERE ID = CPART.PARTICIPANT_ID) AS REASON, " 
                +"(SELECT DECLINE_DATE FROM tsup.COURSE_NON_PARTICIPANT " 
                +" WHERE ID = CPART.PARTICIPANT_ID) AS DECLINE_DATE, "  
                +"CPART.PARTICIPANT_ID AS PARTICIPANT_ID, "
                +"CSCHEDDET.SCHEDULED_START_DATETIME AS SCHEDULED_START_DATETIME, " 
                +"CSCHEDDET.SCHEDULED_END_DATETIME AS SCHEDULED_END_DATETIME "
                +"FROM tsup.COURSE_SCHEDULE AS CSCHED " 
                +"INNER JOIN tsup.COURSE_SCHEDULE_DETAIL AS CSCHEDDET " 
                +"ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID " 
                +"INNER JOIN tsup.COURSE AS C " 
                +"ON CSCHED.COURSE_ID = C.ID " 
                +"INNER JOIN tsup.EMPLOYEE AS E "
                +"ON CSCHED.INSTRUCTOR_ID = E.ID  " 
                +"INNER JOIN tsup.VENUE AS V  " 
                +"ON CSCHED.VENUE_ID = V.ID " 
                +"INNER JOIN tsup.COURSE_PARTICIPANT AS CPART  "
                +"ON CSCHED.ID = CPART.COURSE_SCHEDULE_ID " 
                +"WHERE CPART.PARTICIPANT_ID = :id " 
                +"AND STATUS = 'A'";
        
        SqlParameterSource  NamedParameters = new MapSqlParameterSource()
                          .addValue("id", id);
       return template.queryForObject(sql, NamedParameters, new EnrollmentRowMapperCourseParticipant());
             
      }
    
    /**
     * <pre>
     *
     *deleteCourseParticipantById
     *
     *@author k.freo
     * <pre>
     */
    @Override
    public void deleteCourseParticipantById(Long id) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "DELETE FROM COURSE_PARTICIPANT WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, namedParameters, generatedKeyHolder);
        
           Long key = (Long) generatedKeyHolder.getKeys().get("id");
           System.out.println("\nCourse Participant ID to be deleted: "+ key +"\n");
    }

    
    /**
     * <pre>
     *
     *saveCourseNonParticipant
     *
     *@author k.freo
     * <pre>
     */
    @Override
    public void saveCourseNonParticipant(CourseParticipant courseParticipant) {

        String courseParticipantSql = "INSERT INTO COURSE_NON_PARTICIPANT\r\n" + 
            "( COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, REASON, DECLINE_DATE)\r\n" + 
            "SELECT  COURSE_SCHEDULE_ID, PARTICIPANT_ID, REGISTRATION_DATE, :reason, :declineDate\r\n" + 
            "FROM COURSE_PARTICIPANT\r\n" + 
            "WHERE ID = :id;";
    
            SqlParameterSource coursenonpartParameters = new MapSqlParameterSource()
                    .addValue("id", courseParticipant.getId())
                    .addValue("reason", courseParticipant.getReason())
                    .addValue("declineDate", courseParticipant.getDeclineDate().withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime() ); 
            template.update(courseParticipantSql, coursenonpartParameters);
            
           
            System.out.println("\nCourse Participant ID who decline: "+ courseParticipant.getId() +"\n"); 
    }

    
    @Override
    public void changeCourseScheduleStatus(CourseSchedule courseSchedule) {
        String sql = "UPDATE COURSE_SCHEDULE SET status = :status WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", courseSchedule
                .getStatus()).addValue("id", courseSchedule.getId());
        template.update(sql, namedParameters);
    }
    
    /**
     * <pre>
     *
     *viewEnrolledMembers
     *
     *@author c.delapena
     * <pre>
     */
    @Override
    public List<Participant> viewEnrolledMembers(Long id) {
        String sql = "SELECT C.ID, "
                + "E.NUMBER AS EMPLOYEE_NUMBER, "
                + "E.LAST_NAME, "
                + "E.FIRST_NAME, "
                + "E.EMAIL_ADDRESS "
                + "FROM tsup.COURSE_PARTICIPANT AS CP "
                + "INNER JOIN tsup.EMPLOYEE AS E "
                + "ON CP.PARTICIPANT_ID = E.ID "
                + "INNER JOIN tsup.COURSE_SCHEDULE AS CS "
                + "ON CS.ID = CP.COURSE_SCHEDULE_ID "
                + "INNER JOIN tsup.COURSE AS C "
                + "ON C.ID = CS.COURSE_ID " 
                + "WHERE C.ID = :ID "
                + "ORDER BY C.NAME ";
        
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ID", id);
        
        List<Participant> participantList = template.query(sql, namedParameters, 
                new EnrollmentRowMapperParticipant());
        
        return participantList;
    }
    
    /**
     * <pre>
     *
     *addEnrolledMembers
     *
     *@author c.delapena
     * <pre>
     */
    @Override
    public Integer addEnrolledMembersById(Participant participant)  {
        String sql = "WITH participant AS ( "
        		+ "INSERT INTO tsup.employee ( number, last_name, first_name, email_address, username) "
        		+ "VALUES (:EMPLOYEENUMBER, :LASTNAME, :FIRSTNAME, :EMAILADDRESS, null) RETURNING ID ) "
                + "INSERT INTO tsup.course_participant( course_schedule_id, participant_id, registration_date) "
        		+ "VALUES (:ID, (SELECT ID from participant), Now()); ";
        
        Integer row = template.update(sql, new MapSqlParameterSource("ID", participant.getId()).addValue("EMPLOYEENUMBER", participant.getEmployeeNumber())
        		.addValue("LASTNAME", participant.getLastName()).addValue("FIRSTNAME", participant.getFirstName())
        		.addValue("EMAILADDRESS", participant.getEmailAddress()));
        
        return row;
    }
}