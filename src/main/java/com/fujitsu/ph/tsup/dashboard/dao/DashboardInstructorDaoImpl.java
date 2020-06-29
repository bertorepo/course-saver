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
        String sql = "SELECT c.name AS \"c.name\", " +
                "csd.scheduled_start_datetime AS \"csd.scheduled_start_datetime\", " +
                "csd.scheduled_end_datetime AS \"csd.scheduled_end_datetime\", " +
                "v.name  AS \"v.name\", "+
                "e.id AS \"e.id\", " +
                "cs.status  AS \"cs.status\" " +
                "FROM course_schedule cs " +
                "INNER JOIN course c " +
                "ON cs.course_id = c.id " +
                "INNER JOIN course_schedule_detail csd " +
                "ON cs.id = csd.course_schedule_id " +
                "INNER JOIN venue v " +
                "ON cs.venue_id = v.id " +
                "INNER JOIN employee e " +
                "ON cs.instructor_id = e.id " +
                "WHERE cs.status= :status  " +
                "AND e.id = :employeeId " +
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
        String sql = "SELECT COUNT(course.name) " + 
                "FROM course_schedule " + 
                "INNER JOIN employee ON course_schedule.instructor_id = employee.id " + 
                "INNER JOIN course ON course_schedule.course_id = course.id " + 
                "INNER JOIN course_schedule_detail ON course_schedule.id = course_schedule_detail.course_schedule_id " + 
                "WHERE DATE(course_schedule_detail.scheduled_start_datetime) = :startDateTime " + 
                "AND employee.id = :employeeId " + 
                "AND course_schedule.status = :status";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("startDateTime", LocalDate.now())
                .addValue("employeeId", employeeId)
                .addValue("status", "A");

        int result = template.queryForObject(sql, namedParameters, Integer.class);
        return result;
    }
    
}
