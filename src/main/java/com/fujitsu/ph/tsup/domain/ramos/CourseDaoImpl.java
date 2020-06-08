package com.fujitsu.ph.tsup.domain.ramos;

import java.util.HashSet;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


@Repository
public class CourseDaoImpl implements CourseDao{

    @Autowired
    private NamedParameterJdbcTemplate template;
    
	@Override
	public Long save(Course course) {
        String sql = "INSERT INTO COURSE(course_name)"
                + "VALUES(:courseName)";
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseName",course.getCourseName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        return (Long) generatedKeyHolder.getKeys().get("id");
		
	}

	@Override
	public Set<Course> findAll() {
        String sql = "SELECT id, course_name FROM COURSE";
        List<Course> course = template.query(sql, new CourseRowMapper());
        Set<Course> c = new HashSet<Course>(course);
        return c;
	}

	@Override
	public Course findById(Long id) {
        String sql = "SELECT id, name FROM COURSE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new CourseRowMapper());
	}

	@Override
	public Long saveLong(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

}
