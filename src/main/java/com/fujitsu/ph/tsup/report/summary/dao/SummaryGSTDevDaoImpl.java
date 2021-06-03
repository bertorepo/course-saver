//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Summary of G3CC Standardization Training for Dev                                                                                                                                                             
// Class Name   : SummaryGSTDevDaoImpl.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.01   | 2021/04/21 | WS)R.Rivero            | New Creation
// 0.02   | 2021/04/29 | WS)G.Cabiling          | Update 
//==================================================================================================

package com.fujitsu.ph.tsup.report.summary.dao;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * The implementation of G3CC standardization training for dev dao
 * </pre>
 * 
 * @version 0.01
 * @author r.rivero
 * @author g.cabiling
 *
 */

@Repository
public class SummaryGSTDevDaoImpl implements SummaryGSTDevDao {

    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Set<Long> findAllCoursesByCategoryId() {
        String query = "SELECT C.id FROM COURSE C LEFT JOIN COURSE_CATEGORY CC ON CC.ID = C.COURSE_CATEGORY_ID WHERE CC.CATEGORY = :category";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category",
                "JDU Standardization Training");
        List<Long> coursesList = template.queryForList(query, sqlParameterSource, Long.class);
        Set<Long> courses = new LinkedHashSet<>(coursesList);
        return courses;
    }

    @Override
    public Set<Long> findAllJDUDev() {
        String query = "SELECT E.id FROM EMPLOYEE E LEFT JOIN DEPARTMENT D ON E.DEPARTMENT_ID = D.ID WHERE D.DEPARTMENT_NAME = :dept_name";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("dept_name", "FDC-G3CC");
        List<Long> employeeList = template.queryForList(query, sqlParameterSource, Long.class);
        Set<Long> employee = new LinkedHashSet<>(employeeList);
        return employee;
    }

    @Override
    public int findAllJDUDevLastWeek() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int findAllJDUExisitingMembers() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int findAllJDUNewMembers() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int findTotalCoursePerEmployee(Set<Long> course_id, Long participant_id, ZonedDateTime EndDate) {
        String query = "SELECT COUNT(DISTINCT course_id)" 
                +" FROM tsup.course_attendance CA" 
                +" LEFT Join tsup.course_schedule_detail CSD ON" 
                +" CSD.id = CA.course_schedule_detail_id" 
                +" LEFT Join tsup.course_schedule CS" 
                +" ON CS.id=CSD.course_schedule_id"
                +" WHERE CS.status = 'D'"
                +" AND CA.status = 'P'"
                +" AND CS.course_id IN (:courses)"
                +" AND CA.participant_id = :participant_id"
        		+" AND ca.log_out_dateTime < :EndDate;";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("courses", course_id)
                .addValue("participant_id", participant_id)
        		.addValue("EndDate", EndDate.toOffsetDateTime());
        int no_of_course = template.queryForObject(query, sqlParameterSource, Integer.class);

        return no_of_course;
    }

    @Override
    public int findTotalCoursePerEmployeeLastWeek(ZonedDateTime startDate, ZonedDateTime EndDate, Set<Long> course_id, Long participant_id) {
        String query = "SELECT COUNT(DISTINCT course_id)"  
                +" FROM  tsup.course_attendance CA"  
                +" LEFT Join tsup.course_schedule_detail CSD"  
                +" ON CSD.id = CA.course_schedule_detail_id" 
                +" LEFT Join tsup.course_schedule CS" 
                +" ON CS.id=CSD.course_schedule_id"
                +" WHERE CS.status = 'D'" 
                +" AND CA.status = 'P'" 
                +" AND CS.course_id IN (:courses)" 
                +" AND CA.participant_id = :participant_id"
				/*
				 * +" AND CA.log_out_datetime <= (NOW()::DATE-EXTRACT(DOW from NOW())::INTEGER)"
				 */
		        +" AND ((ca.log_out_dateTime >= date_trunc('week', :startDate - interval '1 week')"																					
				+" AND ca.log_out_dateTime < date_trunc('week', :EndDate)));";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("courses", course_id)
                .addValue("participant_id", participant_id)
		        .addValue("EndDate",EndDate.toOffsetDateTime())
				.addValue("startDate", startDate.toOffsetDateTime());
		
		        int no_of_course = template.queryForObject(query, sqlParameterSource, Integer.class);

        return no_of_course;
    }

}
