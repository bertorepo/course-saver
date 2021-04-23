package com.fujitsu.ph.tsup.report.summary.dao;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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
	public int getEmployeeRoleId() {
		String query = "SELECT id FROM tsup.MEMBER_ROLE WHERE role_name =:rolePM or role_name = :roleTL";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
												.addValue("rolePM", "PM")
												.addValue("roleTL", "TL");
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

	@Override
	public int getCatId() {
		String query = "SELECT id FROM tsup.COURSE_CATEGORY WHERE category = :category";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category", "G3CC Standardization Training");
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

	@Override
	public List<Integer> gstCourses(int catId) {
		String query = "SELECT id FROM tsup.COURSE WHERE category_id = :category_id";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category_id", catId);
		List<Integer> CourseList = template.queryForList(query, sqlParameterSource,Integer.class);
		return CourseList;
	}
	
	@Override
	public int countTotalNumberOfJDUPM(int deptId) {
		String query = "SELECT Count (*) FROM tsup.EMPLOYEE WHERE DEPARTMENT_ID = :deptId";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("deptId", deptId);
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

	@Override
	public int countTotalNumberJDUPMFinished(List<Integer> gstCourses,int deptId, int roleID) {
		String query = "SELECT Count (e.id)" 
						+"From tsup.employee as e"
						+"inner join tsup.course_attendance as ca"
						+"on e.id = ca.participant_id"
						+"inner join tsup.course_schedule_detail as csd"
						+"on ca.course_schedule_detail_id = csd.id"
						+"inner join tsup.course_schedule as cs"
						+"on csd.course_schedule_id = cs.id"
						+"inner join tsup.course as c"
						+"on cs.course_id = c.id"
						+"where c.id in (:courses)"
						+"and e.department_id = :deptId"
						+"and e.role_id = :roleID"
						+"and cs.status ='D';";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
													.addValue("deptId", deptId)
													.addValue("courses", gstCourses);
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

	@Override
	public int countTotalNumberJDUPMFinishedLW(ZonedDateTime startDate, ZonedDateTime EndDate,List<Integer> gstCourses,int deptId,int roleId) {
		String query = "SELECT Count (e.id)" 
				+"From tsup.employee as e"
				+"inner join tsup.course_attendance as ca"
				+"on e.id = ca.participant_id"
				+"inner join tsup.course_schedule_detail as csd"
				+"on ca.course_schedule_detail_id = csd.id"
				+"inner join tsup.course_schedule as cs"
				+"on csd.course_schedule_id = cs.id"
				+"inner join tsup.course as c"
				+"on cs.course_id = c.id"
				+"where c.id in (:courses)"
				+"and e.department_id = :deptId"
				+"and e.role_id = :roleID"
				+"and cs.status ='D'"
				+ "((ca.log_out_dateTime >= date_trunc('week', CURRENT_TIMESTAMP - interval '1 week')"																					
				+ "and ca.log_out_dateTime < date_trunc('week', CURRENT_TIMESTAMP)));";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
													.addValue("deptId", deptId)
													.addValue("courses", gstCourses)
													.addValue("roleId", roleId);
		return template.queryForObject(query,sqlParameterSource,Integer.class);
	}

}
