package com.fujitsu.ph.tsup.domain.yu;

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

public class CourseDaoImpl implements CourseDao {

    @Autowired
    private NamedParameterJdbcTemplate template;
    
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
    
    @Override
    public void save(Course id) {
        String query = "INSERT INTO COURSE(name)"+"VALUES(:name)";   
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", id.getName());
        template.update(query, namedParameters, generatedKeyHolder);
        generatedKey(); 
    }

    public Long generatedKey() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }


    @Override
    public Set<Course> findAll() {
        String query = "SELECT id, name FROM COURSE";
        List<Course> courseList = template.query(query, new CourseRowMapper());
        Set<Course> courseSet = new HashSet<Course>(courseList);
        return courseSet;
    }

    @Override
    public Course findById(Long id) {
        String query = "SELECT id, name FROM COURSE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, namedParameters, new CourseRowMapper());
    }

}
