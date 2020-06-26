package com.fujitsu.ph.tsup.dashboard.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmoForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardPmoDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/25/2020 |  WS) Jm.Deguzman   | New Creation
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
public class DashboardPmoDaoImpl implements DashboardPmoDao {

    /*
     * NamedParameterJdbcTemplate
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Finds the courses that should be cancelled from the database
     * 
     * @return Set<dashboardPmoForm>
     */
    @Override
    public Set<DashboardPmoForm> findCourses() {
        String sql = "SELECT c.name AS \"c.name\" , " +
                "CONCAT(e.last_name , ', ', e.first_name) AS full_name, " +
                "csd.scheduled_start_datetime  AS \"csd.scheduled_start_datetime\", " +
                "csd.scheduled_end_datetime AS \"csd.scheduled_end_datetime\", " +
                "cs.min_required AS \"cs.min_required\", " +
                "cs.max_allowed AS \"cs.max_allowed\", " +
                "COUNT(cp.id) AS enrolled, " +
                "cs.status AS \"cs.status\" " +
                "FROM course_schedule cs " +
                "FULL JOIN course c " +
                "ON cs.course_id = c.id " +
                "FULL JOIN employee e " +
                "ON cs.instructor_id = e.id " +
                "FULL JOIN course_schedule_detail csd " +
                "ON cs.id = csd.course_schedule_id " +
                "FULL JOIN course_participant cp " +
                "ON cs.id = cp.course_schedule_id " +
                "WHERE cs.status = :status " +
                "GROUP BY " +
                "c.name, " +
                "full_name, " +
                "csd.scheduled_start_datetime, " +
                "csd.scheduled_end_datetime, " +
                "cs.min_required, " +
                "cs.max_allowed, " +
                "cs.status "  +
                "having COUNT(cp.id) < cs.min_required " +
                "ORDER BY COUNT(cp.id) DESC " +
                "LIMIT 5";
 
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("status", "A");
        
        List<DashboardPmoForm> dashboardPmo = template.query(sql, namedParameters, new DashboardPmoRowMapper());
        Set<DashboardPmoForm> setDashboardPmo = new HashSet<DashboardPmoForm>(dashboardPmo);
        return setDashboardPmo;
    }
}
