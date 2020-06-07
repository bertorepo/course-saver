package com.fujitsu.ph.tsup.domain.macabugao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
	KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

	@Override
	public void save(Course course) {
		String sql = "INSERT INTO COURSE(name)" + "VALUES(:name)";

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("name", course.getCourseName());
		template.update(sql, namedParameters, generatedKeyHolder);

	}

	@Override
	public Set<Course> findAll() {
		String query = "SELECT id, name FROM course";
		List<Course> courseList = template.query(query, new CourseRowMapper());
		Set<Course> Course = new HashSet<Course>(courseList);

		return Course;
	}

	@Override
	public Course findById(Long id) {
		String sql = "SELECT id, name FROM COURSE WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, namedParameters, new CourseRowMapper());
	}

	public Long GeneratedKey() {
		return (Long) generatedKeyHolder.getKeys().get("id");

	}

}
