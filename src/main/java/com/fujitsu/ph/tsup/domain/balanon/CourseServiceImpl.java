package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fujitsu.ph.tsup.domain.balanon.CourseDao;
import com.fujitsu.ph.tsup.domain.balanon.Course;


@Service
public class CourseServiceImpl implements CourseService{
    
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    
    @Autowired
    private CourseDao dao;

    @Override
    public Set<Course> findAll() {
        
        Set<Course> e = dao.findAll();
        if (e.isEmpty()) {
            throw new IllegalArgumentException("Input to find");
        }
        return e;
     
    }

    @Override
    public Course findById(Long Id) {
        Course d = dao.findById(Id);
        if (d.getId() == 'D') {
            throw new IllegalArgumentException("Can't Find Course ID");
        }
        return d;
    }

    @Override
    public void save(Course Id) {
        // TODO Auto-generated method stub
        
    }
}
