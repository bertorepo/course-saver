package com.fujitsu.ph.tsup.domain.iwarat;

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

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", course.getName());
        template.update(sql, namedParameters, generatedKeyHolder);
        GeneratedKeyHolderId();
    }
    
    @Override
    public Long GeneratedKeyHolderId() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Set<Course> findAll() {
        String query = "SELECT id, name FROM course";
        List<Course> listCourse = template.query(query, new CourseRowMapper());
        Set<Course> setCourse = new HashSet<Course>(listCourse);

        return setCourse;
    }

    @Override
    public Course findById(Long id) {
        String sql = "SELECT id, name FROM COURSE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new CourseRowMapper());
    }

}
