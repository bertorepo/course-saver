/*package com.fujitsu.ph.tsup.domain.balanon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public class CourseServiceImpl implements CourseService{
    
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    
    @Autowired
    private CourseDao dao;
    
    public Course getCourseId(Long CourseId) {
        
        Course d = dao.findById(CourseId);
        if (d.getCourse() == 'D') {
            throw new IllegalArgumentException("Can't Find Course ID");
        }
        return d;
    }
}*/
