package com.fujitsu.ph.tsup.dashboard.dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardMember;
//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardMemberDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By                                   | Content
//--------+------------+----------------------------------------------+-----------------------------
//0.01    | 06/23/2020 |  WS) Jm.Deguzman                             | New Creation
//0.02    | 08/24/2020 |  WS) Jm.Deguzman                             | Updated
//0.03    | 09/09/2020 |  WS) K.abad, WS) J.Iwarat, WS) R.Ramos       | Updated
//==================================================================================================
/**
* <pre>
* The data access implementation to get the data from the database
* <pre>
* 
* @version 0.03
* @author k.abad
* @author Jm.Deguzman
* @author j.iwarat
* @author r.ramos
*/
@Repository
public class DashboardMemberDaoImpl implements DashboardMemberDao {

    /*
     * NamedParameterJdbcTemplate
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Finds the courses that the member haven't enrolled in yet from the database
     * 
     * @param employeeId
     * @return Set<DashboardMemberForm>
     */
    @Override
    public Set<DashboardMember> findCourses(Long employeeId) {
        String sql = "SELECT C.NAME AS \"C.NAME\", " + 
                "   CONCAT(E.LAST_NAME , ', ', E.FIRST_NAME) AS FULL_NAME, " + 
                "   COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS \"CSD.SCHEDULED_START_DATETIME\", " +
                "   COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS \"CSD.SCHEDULED_END_DATETIME\", " +
                "    V.NAME AS \"V.NAME\", " + 
                "    E.ID AS \"E.ID\", " +
                "    CS.STATUS AS \"CS.STATUS\" " +
                " FROM COURSE_SCHEDULE CS " + 
                " LEFT JOIN EMPLOYEE E ON CS.INSTRUCTOR_ID = E.ID " + 
                " LEFT JOIN COURSE_SCHEDULE_DETAIL CSD ON CS.ID = CSD.COURSE_SCHEDULE_ID " + 
                " LEFT JOIN VENUE V ON CS.VENUE_ID=V.ID " + 
                " LEFT JOIN COURSE C ON CS.COURSE_ID = C.ID " + 
                " LEFT JOIN COURSE_PARTICIPANT CP ON CS.ID = CP.COURSE_SCHEDULE_ID " + 
                " WHERE C.NAME NOT IN (SELECT C.NAME " + 
                "                      FROM COURSE_SCHEDULE CS " + 
                "                      INNER JOIN COURSE_PARTICIPANT CP " + 
                "                      ON CS.ID =CP.COURSE_SCHEDULE_ID " + 
                "                      INNER JOIN COURSE C " + 
                "                      ON CS.COURSE_ID = C.ID " + 
                "                      WHERE CP.PARTICIPANT_ID = :employeeId AND " +
                "                      CS.STATUS IN ('D', 'C'))" + 
                "    GROUP BY" + 
                "    C.NAME, " + 
                "    FULL_NAME, " + 
                "    CSD.RESCHEDULED_START_DATETIME, " +
                "    CSD.RESCHEDULED_END_DATETIME, " +
                "    CSD.SCHEDULED_START_DATETIME, " + 
                "    CSD.SCHEDULED_END_DATETIME, " + 
                "    V.NAME, " + 
                "    E.ID, " +
                "    CS.STATUS " +
                "    ORDER BY CSD.SCHEDULED_START_DATETIME desc " +
                "    LIMIT 5 ";
 
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("employeeId", employeeId);
        
        List<DashboardMember> dashboardMember = template.query(sql, namedParameters, new DashboardMemberRowMapper());
        Set<DashboardMember> setDashboardMember = new HashSet<DashboardMember>(dashboardMember);
        
        
        return setDashboardMember;
    }
    
    /**
     * Gets the count from the database of the member's trainings for the day
     * 
     * @param employeeId
     * @return integer
     */
    @Override
    public int getTrainingsToday(Long employeeId) {
        String sql = "SELECT COUNT(COURSE.NAME) AS COUNT " + 
                "FROM COURSE_SCHEDULE " + 
                "INNER JOIN COURSE_PARTICIPANT ON COURSE_SCHEDULE.ID = COURSE_PARTICIPANT.COURSE_SCHEDULE_ID " + 
                "INNER JOIN COURSE ON COURSE_SCHEDULE.COURSE_ID = COURSE.ID " + 
                "INNER JOIN COURSE_SCHEDULE_DETAIL ON COURSE_SCHEDULE.ID = COURSE_SCHEDULE_DETAIL.COURSE_SCHEDULE_ID " + 
                "WHERE :startDateTime BETWEEN "+
                " DATE(COALESCE(COURSE_SCHEDULE_DETAIL.RESCHEDULED_START_DATETIME, COURSE_SCHEDULE_DETAIL.SCHEDULED_START_DATETIME)) AND "+ 
                " DATE(COALESCE(COURSE_SCHEDULE_DETAIL.RESCHEDULED_END_DATETIME, COURSE_SCHEDULE_DETAIL.SCHEDULED_END_DATETIME)) " + 
                "AND COURSE_PARTICIPANT.PARTICIPANT_ID = :employeeId " + 
                "AND COURSE_SCHEDULE.STATUS IN ('A', 'O')";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("startDateTime", LocalDate.now())
                .addValue("employeeId", employeeId);

        int result = template.queryForObject(sql, namedParameters, Integer.class);
        return result;
    }
    
    /**
     * Gets the count from the database of the number of courses that the member has enrolled to
     * 
     * @param employeeId
     * @return integer
     */
    @Override
    public int getActiveCourses(Long employeeId) {
        String sql = "SELECT COUNT(COURSE.NAME) AS COUNT " + 
                "FROM COURSE_SCHEDULE " + 
                "INNER JOIN COURSE_PARTICIPANT ON COURSE_SCHEDULE.ID = COURSE_PARTICIPANT.COURSE_SCHEDULE_ID " + 
                "INNER JOIN COURSE ON COURSE_SCHEDULE.COURSE_ID = COURSE.ID " + 
                "INNER JOIN COURSE_SCHEDULE_DETAIL ON COURSE_SCHEDULE.ID = COURSE_SCHEDULE_DETAIL.COURSE_SCHEDULE_ID " + 
                "WHERE COURSE_PARTICIPANT.PARTICIPANT_ID = :employeeId " + 
                "AND COURSE_SCHEDULE.STATUS = :status ";
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("employeeId", employeeId)
                .addValue("status", "A");

        int result = template.queryForObject(sql, namedParameters, Integer.class);
        return result;
    }
    

}
