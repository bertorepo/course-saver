package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties.Template;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public void save(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long saveCourse(Course course) {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO COURSE(id,CouseName)"
				+ "VALUES(:id, :cName)";

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id",course.getId())
				.addValue("cName",course.getCourse());

		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		template.update(sql, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKeys().get("id");
	}

	@Override
	public Set<Course> findAll() {
		// TODO Auto-generated method stub
		 String sql = "SELECT id, name FROM COURSE";
	     List<Course> course = template.query(sql, new CourseRowMapper());
	     Set<Course> result = new HashSet<Course>(course);
	     return result;
	}

	@Override
	public Course findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT id, CourseName FROM COURSE WHERE id = :id";
	    SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
	    return template.queryForObject(sql, namedParameters, new CourseRowMapper());
	}

}
