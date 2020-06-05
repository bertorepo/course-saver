package com.fujitsu.ph.tsup.domain.lumontad;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao {

    
    
//    @Autowired
//    private NamedParameterJdbcTemplate template;
    
    @Override
    public void save(Course course) {
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
