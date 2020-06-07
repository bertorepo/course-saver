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
    
    @Override
    public void save(Course id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Set<Course> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Course findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
