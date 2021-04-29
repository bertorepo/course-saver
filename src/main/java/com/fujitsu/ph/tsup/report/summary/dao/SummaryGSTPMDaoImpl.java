//Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
package com.fujitsu.ph.tsup.report.summary.dao;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for PM
//Class Name   : SummaryGSTPMDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/04/20 | WS) d.escala          | Initial Version
//0.02    | 2021/04/23 | WS) d.escala          | Updated
//0.03    | 2021/04/27 | WS) d.escala          | Updated
//0.04    | 2021/04/28 | WS) d.escala          | Updated
//==================================================================================================
/**
* <pre>
* The controller for the SummaryGSTPMDaoImpl
* <pre>
* 
* @version 0.04
* @author d.escala
*/


@Repository
public class SummaryGSTPMDaoImpl implements SummaryGSTPMDao{

	 @Autowired
	 private NamedParameterJdbcTemplate template;
	
	
	@Override
	public int getDeptId() {
		String query = "SELECT id FROM tsup.DEPARTMENT WHERE department_name =:department_name";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("department_name", "FDC-G3CC");
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}
	
	@Override
	public List<Integer> getEmployeeRoleId() {
		String query = "SELECT id FROM tsup.MEMBER_ROLE WHERE role_type =:rolePM or role_type = :roleTL";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
												.addValue("rolePM", "PM")
												.addValue("roleTL", "TL");
		return template.queryForList(query,sqlParameterSource,Integer.class);
	}

	@Override
	public int getCatId() {
		String query = "SELECT id FROM tsup.COURSE_CATEGORY WHERE category = :category";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category", "JDU Standardization Training");
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

	@Override
	public List<Integer> gstCourses(int catId) {
		String query = "SELECT id FROM tsup.COURSE WHERE course_category_id = :category_id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category_id", catId);
		List<Integer> CourseList = template.queryForList(query, sqlParameterSource,Integer.class);
		return CourseList;
	}
	
	@Override
	public int countTotalNumberOfJDUPM(int deptId ,List<Integer> roleID) {
		String query = "SELECT Count (*) FROM tsup.EMPLOYEE WHERE DEPARTMENT_ID = :deptId"
						+"and e.member_role_id in (:roleId)";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("deptId", deptId)
																			.addValue("roleId", roleID);
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

	@Override
	public int countTotalNumberJDUPMFinished(List<Integer> gstCourses,int deptId, List<Integer> roleID, ZonedDateTime EndDate) {
		String query = "SELECT Count (e.id)" 
						+" From tsup.employee as e"
						+" inner join tsup.course_attendance as ca"
						+" on e.id = ca.participant_id"
						+" inner join tsup.course_schedule_detail as csd"
						+" on ca.course_schedule_detail_id = csd.id"
						+" inner join tsup.course_schedule as cs"
						+" on csd.course_schedule_id = cs.id"
						+" inner join tsup.course as c"
						+" on cs.course_id = c.id"
						+" where c.id in (:courses)"
						+" and e.department_id = :deptId"
						+" and e.member_role_id in (:roleId)"
						+" and cs.status ='D'"
						+ "and ca.log_out_dateTime < :EndDate;";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
													.addValue("deptId", deptId)
													.addValue("courses", gstCourses)
													.addValue("roleId", roleID)
													.addValue("EndDate", EndDate.toOffsetDateTime());
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

	@Override
	public int countTotalNumberJDUPMFinishedLW(ZonedDateTime startDate, ZonedDateTime EndDate,List<Integer> gstCourses,int deptId,List<Integer> roleId) {
		String query = "SELECT Count (e.id)" 
				+" From tsup.employee as e"
				+" inner join tsup.course_attendance as ca"
				+" on e.id = ca.participant_id"
				+" inner join tsup.course_schedule_detail as csd"
				+" on ca.course_schedule_detail_id = csd.id"
				+" inner join tsup.course_schedule as cs"
				+" on csd.course_schedule_id = cs.id"
				+" inner join tsup.course as c"
				+" on cs.course_id = c.id"
				+" where c.id in (:courses)"
				+" and e.department_id = :deptId"
				+" and e.member_role_id in (:roleId)"
				+" and cs.status ='D'"
				+" and ((ca.log_out_dateTime >= date_trunc('week', :startDate - interval '1 week')"																					
				+" and ca.log_out_dateTime < date_trunc('week', :EndDate)));";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
													.addValue("deptId", deptId)
													.addValue("courses", gstCourses)
													.addValue("roleId", roleId)
													.addValue("EndDate",EndDate.toOffsetDateTime())
													.addValue("startDate", startDate.toOffsetDateTime());
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

}
