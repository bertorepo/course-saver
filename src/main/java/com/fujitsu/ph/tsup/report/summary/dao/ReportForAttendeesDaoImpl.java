//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Reports                                                                                                                                                          
// Class Name   : ReportForNonAttendeesDaoImpl.java                                                                                                                                                                          
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.1   | 2021/06/25 | WS)L.Celoso           | New Creation   
//==================================================================================================     
package com.fujitsu.ph.tsup.report.summary.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.report.summary.model.Attendee;

@Repository
public class ReportForAttendeesDaoImpl implements ReportForAttendeesDao {
	
	/**
     * Logger Factory
     */
    private static Logger logger = LoggerFactory.getLogger(MandatoryCoursesDaoImpl .class);
    
    /**
     * JDBC Template for Named Parameters
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     *  Find the total number of  JDU based on the given date range
     * @param selectedStartDateTime
     * @param selectedEndDateTime 
     * @return mandatoryCourses
     */
    @Override
    public Set<Attendee> findAllNonAttendees(String mandatoryType, Long jduId) {
            
        String query = "SELECT * FROM tsup.GET_NON_ATTENDEES(:mandatory_type, :jdu_id)";                                                                                    
        
        SqlParameterSource nonAttendeesParameter = new MapSqlParameterSource()
                .addValue("mandatory_type", mandatoryType)
                .addValue("jdu_id", jduId);
        
        List<Attendee> nonAttendeeList = template.query(query, nonAttendeesParameter, new ReportForAttendeesRowMapper());
        Set<Attendee> nonAttendees = new LinkedHashSet<>(nonAttendeeList);

        logger.debug("Result: {}", nonAttendees);
        return nonAttendees;

    }

    @Override
    public Set<Attendee> findMandatoryAllCourses(String mandatoryType, Long jduId) {
        
        String query = "SELECT DISTINCT '0' AS EMPLOYEE_ID, '' AS EMPLOYEE_NAME, C.ID AS COURSE_ID, C.NAME AS COURSE_NAME "
        		+ "	FROM   tsup.course C "
        		+ " INNER JOIN tsup.department D "
        		+ "	     	   ON D.id = C.department_id "
        		+ "	WHERE  C.mandatory = 'Yes' "
        		+ "		   AND C.mandatory_type = :mandatory_type "
        		+ "		   AND D.jdu_id = :jdu_id"
        		+ " ORDER BY COURSE_NAME";                                                                                    
        
        SqlParameterSource nonAttendeesParameter = new MapSqlParameterSource()
                .addValue("mandatory_type", mandatoryType)
                .addValue("jdu_id", jduId);
        
        List<Attendee> mandatoryCourses = template.query(query, nonAttendeesParameter, new ReportForAttendeesRowMapper());
        Set<Attendee> mandatoryCoursesSet = new LinkedHashSet<>(mandatoryCourses);
        
        logger.debug("Result: {}", mandatoryCourses);
        return mandatoryCoursesSet;

    }

    @Override
    public Set<Attendee> findEmployees(String mandatoryType, Long jduId) {
        
        String query = "SELECT DISTINCT '0' AS COURSE_ID, '' AS COURSE_NAME, E.ID AS EMPLOYEE_ID "
        		+ " , CONCAT(E.last_name,', ', E.first_name) As EMPLOYEE_NAME "
        		+ "	FROM   EMPLOYEE E "
        		+ " INNER JOIN DEPARTMENT D "
        		+ "   ON D.id = E.department_id "
        		+ "	WHERE D.jdu_id = :jdu_id"
        		+ " ORDER BY EMPLOYEE_NAME ";                                                                                    
        
        SqlParameterSource nonAttendeesParameter = new MapSqlParameterSource()
                .addValue("mandatory_type", mandatoryType)
                .addValue("jdu_id", jduId);
        
        List<Attendee> employeeList = template.query(query, nonAttendeesParameter, new ReportForAttendeesRowMapper());
        Set<Attendee> employeeSet = new LinkedHashSet<>(employeeList);
        
        
        logger.debug("Result: {}", employeeList);
        return employeeSet;

    }
    
    @Override
    public Long getJDUType() {
        
    	FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String query = "SELECT JT.id "
        		+ " FROM tsup.JDU_TYPE JT "
        		+ " INNER JOIN tsup.department D ON D.JDU_ID = JT.id"
        		+ " INNER JOIN tsup.employee E ON E.DEPARTMENT_ID = D.id"
        		+ " WHERE E.ID = :employee_id";                                                                                    
        
        SqlParameterSource nonAttendeesParameter = new MapSqlParameterSource()
                .addValue("employee_id", user.getId());
        
        Long jduType = template.queryForObject(query, nonAttendeesParameter, Long.class);
        
        
        logger.debug("Result: {}", jduType);
        return jduType;

    }
 
}
