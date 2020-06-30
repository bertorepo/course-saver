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

import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructorForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardInstructorDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/24/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The data access implementation to get the data from the database
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
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
    public Set<DashboardInstructorForm> findCourses(Long employeeId) {
        String sql = "SELECT C.NAME AS \"C.NAME\", " +
                "CSD.SCHEDULED_START_DATETIME AS \"CSD.SCHEDULED_START_DATETIME\", " +
                "CSD.SCHEDULED_END_DATETIME AS \"CSD.SCHEDULED_END_DATETIME\", " +
                "V.NAME AS \"V.NAME\", "+
                "E.ID AS \"E.ID AS\", " +
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
                "WHERE CS.STATUS= :status  " +
                "AND E.ID = :employeeId " +
                "LIMIT 5";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("employeeId", employeeId)
                .addValue("status", "A");
        
        List<DashboardInstructorForm> dashboardInstructor = template.query(sql, namedParameters, new DashboardInstructorRowMapper());
        Set<DashboardInstructorForm> setDashboardInstructor = new HashSet<DashboardInstructorForm>(dashboardInstructor);
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
                "WHERE DATE(COURSE_SCHEDULE_DETAIL.SCHEDULED_START_DATETIME) = :startDateTime " + 
                "AND EMPLOYEE.ID = :employeeId " + 
                "AND COURSE_SCHEDULE.STATUS = :status";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("startDateTime", LocalDate.now())
                .addValue("employeeId", employeeId)
                .addValue("status", "A");

        int result = template.queryForObject(sql, namedParameters, Integer.class);
        return result;
    }
    
}
