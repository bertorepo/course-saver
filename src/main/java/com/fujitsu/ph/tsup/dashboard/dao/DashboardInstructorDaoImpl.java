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

import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructor;
//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardInstructorDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By                                   | Content
//--------+------------+----------------------------------------------+-----------------------------
//0.01    | 06/24/2020 |  WS) Jm.Deguzman                             | New Creation
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
public class DashboardInstructorDaoImpl implements DashboardInstructorDao {

    /*
     * NamedParameterJdbcTemplate
     */
    @Autowired
    private NamedParameterJdbcTemplate template;
    
    /**
     * Finds the courses of the instructor in the database
     * 
     * @param employeeId
     * @return Set<DashboardInstructorForm>
     */
    @Override
    public Set<DashboardInstructor> findCourses(Long employeeId) {
        String sql = "SELECT C.NAME AS \"C.NAME\", " +
                "COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) AS \"CSD.SCHEDULED_START_DATETIME\", " +
                "COALESCE(CSD.RESCHEDULED_END_DATETIME, CSD.SCHEDULED_END_DATETIME) AS \"CSD.SCHEDULED_END_DATETIME\", " +
                "V.NAME AS \"V.NAME\", "+
                "E.ID AS \"E.ID\", " +
                "CS.STATUS  AS \"CS.STATUS\" " +
                "FROM COURSE_SCHEDULE CS " +
                "INNER JOIN COURSE C " +
                "ON CS.COURSE_ID = C.ID " +
                "INNER JOIN COURSE_SCHEDULE_DETAIL CSD " +
                "ON CS.ID = CSD.COURSE_SCHEDULE_ID " +
                "INNER JOIN VENUE V " +
                "ON CS.VENUE_ID = V.ID " +
                "INNER JOIN EMPLOYEE E " +
                "ON CS.INSTRUCTOR_ID = E.ID " +
                "WHERE CS.STATUS IN ('A', 'O') " +
                "AND E.ID = :employeeId " +
                "LIMIT 5";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("employeeId", employeeId);
        
        List<DashboardInstructor> dashboardInstructor = template.query(sql, namedParameters, new DashboardInstructorRowMapper());
        Set<DashboardInstructor> setDashboardInstructor = new HashSet<DashboardInstructor>(dashboardInstructor);
        return setDashboardInstructor;
    }

    /**
     * Gets the count from the database of courses of the instructor for the day
     * 
     * @param employeeId
     * @return integer
     */
    @Override
    public int getCoursesToday(Long employeeId) {
        String sql = "SELECT COUNT(COURSE.NAME) " + 
                "FROM COURSE_SCHEDULE " + 
                "INNER JOIN EMPLOYEE ON COURSE_SCHEDULE.INSTRUCTOR_ID = EMPLOYEE.ID " + 
                "INNER JOIN COURSE ON COURSE_SCHEDULE.COURSE_ID = COURSE.ID " + 
                "INNER JOIN COURSE_SCHEDULE_DETAIL ON COURSE_SCHEDULE.ID = COURSE_SCHEDULE_DETAIL.COURSE_SCHEDULE_ID " + 
                "WHERE :startDateTime BETWEEN " + 
                " DATE(COALESCE(COURSE_SCHEDULE_DETAIL.RESCHEDULED_START_DATETIME, COURSE_SCHEDULE_DETAIL.SCHEDULED_START_DATETIME)) AND " +
                " DATE(COALESCE(COURSE_SCHEDULE_DETAIL.RESCHEDULED_END_DATETIME, COURSE_SCHEDULE_DETAIL.SCHEDULED_END_DATETIME)) "+
                "AND EMPLOYEE.ID = :employeeId " + 
                "AND COURSE_SCHEDULE.STATUS IN ('O', 'A')";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("startDateTime", LocalDate.now())
                .addValue("employeeId", employeeId);

        int result = template.queryForObject(sql, namedParameters, Integer.class);
        return result;
    }
    
}
