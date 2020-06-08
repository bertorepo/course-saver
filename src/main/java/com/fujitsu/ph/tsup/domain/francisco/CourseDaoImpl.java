package com.fujitsu.ph.tsup.domain.francisco;

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
public class CourseDaoImpl implements CourseDao{
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO COURSE(course_name)" + "VALUES(:courseName)";

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", course.getCourseName());
        
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Set<Course> findAll() {
        String query = "SELECT id, course_name FROM course";
        List<Course> courses = template.query(query, new CourseRowMapper());
        Set<Course> setCourse = new HashSet<Course>(courses);
        return setCourse;
    }

    @Override
    public Course findById(Long id) {
        String sql = "SELECT id, course_name FROM COURSE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new CourseRowMapper());
    }

}
