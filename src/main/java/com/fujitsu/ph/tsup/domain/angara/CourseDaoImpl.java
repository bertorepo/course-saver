package com.fujitsu.ph.tsup.domain.angara;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private JdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void saveLong(Course course) {
        String sql = "INSERT INTO COURSE(course_name)" + "VALUES(:courseName)";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("course_name", course.getCourseName());
        template.update(sql, namedParameters, generatedKeyHolder);
        return;
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
        String sql = "SELECT id, course_name FROM COURSE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return (Course) template.queryForMap(sql, namedParameters, new CourseRowMapper());
    }

    @Override
    public Long saveCourse() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Long save(Course course) {
        // TODO Auto-generated method stub
        return null;
    }


        
    }
