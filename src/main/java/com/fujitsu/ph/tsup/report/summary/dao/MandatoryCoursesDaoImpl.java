	//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : MandatoryCoursesDaoImpl                                                                                                                                                               
// Class Name   : MandatoryCoursesDaoImpl.java                                                                                                                                                                          
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/04/21 | WS)J.Barbadillo       | New Creation      
// 1.0.1   | 2021/05/05 | WS)J.Barbadillo       | Updated
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.dao;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.report.summary.model.*;

/**
 * <pre>
 * The Dao Implmentation for the MandatoryCourses
 * </pre>
 * 
 * @author j.barbadillo
 * @version 1.0.1
 */

@Repository
public class MandatoryCoursesDaoImpl implements MandatoryCoursesDao{
    
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
    public Set<MandatoryCourses> findMandatoryCourses(LocalDateTime selectedStartDateTime,
            LocalDateTime selectedEndDateTime) {
            
        String query = "SELECT "
                    + "     DISTINCT C.ID AS ID, "
                    + "     C.NAME AS COURSE_NAME "                                                                                             
                    + "FROM TSUP.COURSE_SCHEDULE AS CSCHED "                                                                                            
                    + "INNER JOIN TSUP.COURSE_SCHEDULE_DETAIL AS CSD "                                                                                          
                    + "     ON CSCHED.ID = CSD.COURSE_SCHEDULE_ID "                                                                                                                                                                    
                    + "INNER JOIN TSUP.COURSE AS C "                                                                                                
                    + "     ON CSCHED.COURSE_ID = C.ID "                                                                                            
                    + "WHERE COALESCE(CSD.RESCHEDULED_START_DATETIME, CSD.SCHEDULED_START_DATETIME) "                                                                                               
                    + "BETWEEN :scheduledStartDateTime "
                    + "AND :scheduledEndDateTime "                                                                                  
                    + "AND C.MANDATORY = 'Yes'" ;                                                                                       
        
        SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
                .addValue("scheduledStartDateTime", selectedStartDateTime)
                .addValue("scheduledEndDateTime", selectedEndDateTime);
        
        List<MandatoryCourses> mandatoryCoursesList = template.query(query, mandatoryCoursesParameters,
                new MandatoryCoursesRowMapper());
        
        Set<MandatoryCourses> mandatoryCourses = new HashSet<>(mandatoryCoursesList);

        logger.debug("Result: {}", mandatoryCoursesList);

        return mandatoryCourses;

    }

    /**
     *  Count the total number of JDU
     * @return int
     */
    public int findTotalNumberOfJdu() {
            
        String query = "SELECT "
                    + "   COUNT(*) AS TOTAL_NUMBER_OF_JDU "                                                                                             
                    + "FROM TSUP.EMPLOYEE AS E "                                                                                            
                    + "INNER JOIN TSUP.DEPARTMENT D "
                    + "   ON D.ID = E.DEPARTMENT_ID "
                    + "WHERE  D.DEPARTMENT_NAME = :DEPARTMENT_NAME;";  
        
         SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
                 .addValue("DEPARTMENT_NAME", "FDC-G3CC");
         return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
         
    }
    
    /**
     *  Find the total number of  JDU who finished training  based on the courses  
     * @param mandatoryCourses
     * @return int
     */
    public int findTotalNumberOfJduWhoFinishedTraining(String mandatoryCourses) {
            
        String query =  "SELECT "                                                                                  
                + "     COUNT(DISTINCT CUPLOAD.EMPLOYEE_ID) AS TOTAL_NUMBER_OF_JDU_WHO_FINISHED_TRAINING "                                                                            
                + "FROM TSUP.COURSE_SCHEDULE AS CSCHED "                                                                                   
                + "INNER JOIN TSUP.COURSE_SCHEDULE_DETAIL AS CSCHEDDET "                                                                                   
                + "     ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID "                                                                              
                + "INNER JOIN TSUP.COURSE_ATTENDANCE AS CA "                                                                                   
                + "     ON CA.ID = CSCHEDDET.ID "                                                                              
                + "INNER JOIN tsup.COURSE AS C "                                                                               
                + "     ON CSCHED.COURSE_ID = C.ID "
                + "INNER JOIN TSUP.CERTIFICATE_UPLOAD AS CUPLOAD "                                                                         
                + "     ON CUPLOAD.COURSE_ID = C.ID "
                + "WHERE CUPLOAD.CERTIFICATE IS NOT NULL "
                + "AND C.NAME = :name "
                + "AND C.MANDATORY = 'Yes';";
        
        SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
                .addValue("name", mandatoryCourses);
        
         return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
    }
    
    /**
     *  Find the total number of  JDU who finished training lastweek  based on course
     * @param mandatoryCourses
     * @return int
     */
    public int findTotalNumberOfJduWhoFinishedTrainingLastWeek(String mandatoryCourses) {
            
        String query =  "SELECT "
                + "     COUNT(DISTINCT CUPLOAD.EMPLOYEE_ID) AS TOTAL_NUMBER_OF_JDU_WHO_FINISHED_TRAINING_LASTWEEK  "
                + "FROM TSUP.COURSE_SCHEDULE AS CSCHED "
                + "INNER JOIN TSUP.COURSE_SCHEDULE_DETAIL AS CSCHEDDET  "
                + "     ON CSCHED.ID = CSCHEDDET.COURSE_SCHEDULE_ID  "
                + "INNER JOIN TSUP.COURSE_ATTENDANCE AS CA  "
                + "     ON CA.ID = CSCHEDDET.ID  "
                + "INNER JOIN tsup.COURSE AS C  "
                + "     ON CSCHED.COURSE_ID = C.ID  "
                + "INNER JOIN TSUP.CERTIFICATE_UPLOAD AS CUPLOAD  "
                + "     ON CUPLOAD.COURSE_ID = C.ID  "
                + "WHERE C.NAME = :name  "
                + "AND C.MANDATORY = 'Yes'  "
                + "AND "
                + "     CASE CUPLOAD.UPLOAD_DATE  "
                + "WHEN NULL THEN  "
                + "     CA.LOG_OUT_DATETIME <= (NOW()::DATE-EXTRACT(DOW from NOW())::INTEGER)  "
                + "ELSE  "
                + "     CUPLOAD.UPLOAD_DATE <= (NOW()::DATE-EXTRACT(DOW from NOW())::INTEGER) "
                + "END;";  
          

        SqlParameterSource mandatoryCoursesParameters = new MapSqlParameterSource()
                .addValue("name", mandatoryCourses);

         return template.queryForObject(query, mandatoryCoursesParameters, Integer.class);
    }
    
    
    
}
