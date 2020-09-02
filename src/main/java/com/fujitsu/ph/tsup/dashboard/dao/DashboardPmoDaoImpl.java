package com.fujitsu.ph.tsup.dashboard.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmo;
//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardPmoDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/25/2020 |  WS) Jm.Deguzman   | New Creation
//0.02 | 08/24/2020 |  WS) Jm.Deguzman   | Updated
//0.03 | 08/28/2020 |  WS) Jm.Deguzman   | Updated
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
    public Set<DashboardPmo> findCourses() {
        String sql = "SELECT C.NAME AS \"C.NAME\" , " +
                "CONCAT(E.LAST_NAME , ', ', E.FIRST_NAME) AS FULL_NAME, " +
                "CSD.SCHEDULED_START_DATETIME  AS \"CSD.SCHEDULED_START_DATETIME\", " +
                "CSD.SCHEDULED_END_DATETIME AS \"CSD.SCHEDULED_END_DATETIME\", " +
                "CS.MIN_REQUIRED AS \"CS.MIN_REQUIRED\", " +
                "CS.MAX_ALLOWED AS \"CS.MAX_ALLOWED\", " +
                "COUNT(CP.ID) AS ENROLLED, " +
                "CS.STATUS AS \"CS.STATUS\" " +
                "FROM COURSE_SCHEDULE CS " +
                "FULL JOIN COURSE C " +
                "ON CS.COURSE_ID = C.ID " +
                "FULL JOIN EMPLOYEE E " +
                "ON CS.INSTRUCTOR_ID = E.ID " +
                "FULL JOIN COURSE_SCHEDULE_DETAIL CSD " +
                "ON CS.ID = CSD.COURSE_SCHEDULE_ID " +
                "FULL JOIN COURSE_PARTICIPANT CP " +
                "ON CS.ID = CP.COURSE_SCHEDULE_ID " +
                "WHERE CS.STATUS = :status " +
                "GROUP BY " +
                "C.NAME, " +
                "FULL_NAME, " +
                "CSD.SCHEDULED_START_DATETIME, " +
                "CSD.SCHEDULED_END_DATETIME, " +
                "CS.MIN_REQUIRED, " +
                "CS.MAX_ALLOWED, " +
                "CS.STATUS "  +
                "HAVING COUNT(CP.ID) < CS.MIN_REQUIRED " +
                "ORDER BY CSD.SCHEDULED_START_DATETIME ASC " +
                "LIMIT 5";
 
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("status", "A");
        
        List<DashboardPmo> dashboardPmo = template.query(sql, namedParameters, new DashboardPmoRowMapper());
        Set<DashboardPmo> setDashboardPmo = new HashSet<DashboardPmo>(dashboardPmo);
        return setDashboardPmo;
    }
}
