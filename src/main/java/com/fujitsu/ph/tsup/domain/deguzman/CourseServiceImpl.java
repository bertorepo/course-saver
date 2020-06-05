package com.fujitsu.ph.tsup.domain.deguzman;

import java.time.LocalDate;
import java.util.Set;

import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class CourseServiceImpl implements CourseService{
private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    
    @Autowired
    private CourseDao dao;
    
    @Override
    public void save(Course course){
        Course c = new Course.Builder("ISPROG1").build();
        if(c.getCourseName() == "ISPROG") {
            throw new IllegalArgumentException("Course is invalid");
        }
    }
    
    @Override
    public Set<Course> findAll(){
        Set<Course> c = dao.findAll();
        if (c.isEmpty() || c == null) {
            throw new IllegalArgumentException("No records found");
        }
        return c;
    }
    
    @Override
    public Course findById(Long id){
        Course c = dao.findById(id);
        if (c.getCourseName() == "ISPROG") {
            throw new IllegalArgumentException("Invalid course");
        }
        return c;
        
    }

  
}
