package com.fujitsu.ph.tsup.domain.ramos;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public class CourseServiceImpl implements CourseService{
private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	private CourseDao dao;

    @Override
    public Course findById(Long id) {
        Course course = dao.findById(id);
        if (course.getId() == 101) {
            throw new IllegalArgumentException("Course not found");
        } 
        return course;   
    }
    
	@Override
	public void save(Course course) {
        dao.save(course);
        if(course.getId() == 101) {
            throw new IllegalArgumentException("Course not saved");
        }
    }
	@Override
    public Set<Course> findAll() {
        Set<Course> c = dao.findAll();
        if (c.isEmpty() || c == null) {
            throw new IllegalArgumentException("Invalid course");
        }
        return c;
    }
}

