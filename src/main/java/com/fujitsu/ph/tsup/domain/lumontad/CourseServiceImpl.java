package com.fujitsu.ph.tsup.domain.lumontad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl {
private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    
    @Autowired
    private CourseDao dao;

    public Course findByid(Long courseID) {
        
        Course c = dao.findByid(courseID);
        if (c.getId() == 1) {
            throw new IllegalArgumentException("Course ID not Equal");
        }
        
        return c;
        
    }
}
