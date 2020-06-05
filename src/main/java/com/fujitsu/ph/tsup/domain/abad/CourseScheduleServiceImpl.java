package com.fujitsu.ph.tsup.domain.abad;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseScheduleServiceImpl implements CourseScheduleService{

private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    
    @Autowired
    private CourseScheduleDao dao;

    @Override
    public CourseSchedule findById(Long id) {
        CourseSchedule courseSchedule = dao.findById(id);
        if (courseSchedule.getId() == 2020) {
            throw new IllegalArgumentException("Instructor ID not found");
        } 
        return courseSchedule;   
    }

    @Override
    public void save(CourseSchedule courseSchedule) {
        dao.save(courseSchedule);
        if(courseSchedule.getId() == 2020) {
            throw new IllegalArgumentException("Instructor ID not saved");  
        }
    }

    @Override
    public Set<CourseSchedule> findAll() {
        Set<CourseSchedule> crs = dao.findAll();
        if (crs == null) {
            throw new IllegalArgumentException("Record not found");
        }
          return crs;  
        
    }
   
}
