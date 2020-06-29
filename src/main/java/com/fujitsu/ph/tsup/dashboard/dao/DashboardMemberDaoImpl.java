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

import com.fujitsu.ph.tsup.dashboard.domain.DashboardMemberForm;
//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardMemberDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) Jm.Deguzman   | New Creation
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
    public Set<DashboardMemberForm> findCourses(Long employeeId) {
        String sql = "SELECT c.name AS \"c.name\"," + 
                "   CONCAT(e.last_name , ', ', e.first_name) AS \"full_name\", " + 
                "    csd.scheduled_start_datetime AS \"csd.scheduled_start_datetime\"," + 
                "    csd.scheduled_end_datetime AS \"csd.scheduled_end_datetime\", " + 
                "    v.name AS \"v.name\", " + 
                "     e.id AS \"e.id\", " +
                "    cs.status AS \"cs.status\"" +
                " FROM course_schedule cs" + 
                " LEFT JOIN employee e ON cs.instructor_id = e.id" + 
                " LEFT JOIN course_schedule_detail csd ON cs.id = csd.course_schedule_id" + 
                " LEFT JOIN venue v ON cs.venue_id=v.id" + 
                " LEFT JOIN course c ON cs.course_id = c.id" + 
                " LEFT JOIN course_participant cp ON cs.id = cp.course_schedule_id" + 
                " WHERE c.name NOT IN (SELECT c.name " + 
                "                      FROM course_schedule cs" + 
                "                      INNER JOIN course_participant cp" + 
                "                      ON cs.id =cp.course_schedule_id" + 
                "                      INNER JOIN course c" + 
                "                      ON cs.course_id = c.id" + 
                "                      WHERE cp.participant_id = :employeeId AND " +
                "                      cs.status = :status)" + 
                "    GROUP BY" + 
                "    c.name, " + 
                "    full_name, " + 
                "    csd.scheduled_start_datetime, " + 
                "    csd.scheduled_end_datetime, " + 
                "    v.name," + 
                "    e.id, " +
                "    cs.status" +
                "    ORDER BY csd.scheduled_start_datetime desc" +
                "    LIMIT 5 ";
 
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("employeeId", employeeId)
                .addValue("status", "A");
        
        List<DashboardMemberForm> dashboardMember = template.query(sql, namedParameters, new DashboardMemberRowMapper());
        Set<DashboardMemberForm> setDashboardMember = new HashSet<DashboardMemberForm>(dashboardMember);
        
        
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
        String sql = "SELECT COUNT(course.name) AS count " + 
                "FROM course_schedule " + 
                "INNER JOIN course_participant ON course_schedule.id = course_participant.course_schedule_id " + 
                "INNER JOIN course ON course_schedule.course_id = course.id " + 
                "INNER JOIN course_schedule_detail ON course_schedule.id = course_schedule_detail.course_schedule_id " + 
                "WHERE DATE(course_schedule_detail.scheduled_start_datetime) = :startDateTime " + 
                "AND course_participant.participant_id = :employeeId " + 
                "AND course_schedule.status = :status";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("startDateTime", LocalDate.now())
                .addValue("employeeId", employeeId)
                .addValue("status", "A");

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
        String sql = "SELECT COUNT(course.name) AS count " + 
                "FROM course_schedule " + 
                "INNER JOIN course_participant ON course_schedule.id = course_participant.course_schedule_id " + 
                "INNER JOIN course ON course_schedule.course_id = course.id " + 
                "INNER JOIN course_schedule_detail ON course_schedule.id = course_schedule_detail.course_schedule_id " + 
                "WHERE course_participant.participant_id = :employeeId " + 
                "AND course_schedule.status = :status ";
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("employeeId", employeeId)
                .addValue("status", "A");

        int result = template.queryForObject(sql, namedParameters, Integer.class);
        return result;
    }
    

}
