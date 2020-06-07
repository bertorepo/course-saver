package com.fujitsu.ph.tsup.domain.freo;

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
public class CourseDaoImp implements CourseDao {

	@Autowired
	private NamedParameterJdbcTemplate template;
	KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
	
	
	@Override
	public void save(Course course) {
		String sql = "INSERT INTO course_name" +"VALUES(:course_name)";
		SqlParameterSource namedParams = new MapSqlParameterSource()
				.addValue("coursename",course.getCourseName());
		template.update(sql, namedParams, generatedKeyHolder);
        returnGeneratedKey();
		
	}

	@Override
	public Set<Course> findAll() {
		String query = "SELECT id, courseName FROM course";
        List<Course> cls = template.query(query, new CourseRowMapper());
        Set<Course> Course= new HashSet<Course>(cls);
		return Course;
	}

	@Override
	public Course findById(Long id) {
		 String query = "SELECT id, course_name FROM course WHERE id = :id";
	        SqlParameterSource namedParams = new MapSqlParameterSource().addValue("id", id);
	        return template.queryForObject(query,namedParams, new CourseRowMapper ());
	}

	@Override
	public Long returnGeneratedKey() {
		return (Long) generatedKeyHolder.getKeys().get("Id");
	}
	
	
	
}
