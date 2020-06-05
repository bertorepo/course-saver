package com.fujitsu.ph.tsup.domain.deguzman;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class CourseScheduleServiceImpl implements CourseScheduleService{
private Logger logger = LoggerFactory.getLogger(CourseScheduleServiceImpl.class);
    
    @Autowired
    private CourseScheduleDao dao;
    
    @Override
    public void save(CourseSchedule courseSchedule){
        CourseSchedule c = new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 5, 100, "A").build();
        if(c.getStatus() == "C") {
            throw new IllegalArgumentException("Course Schedule is Closed");
        }
    }
    
    @Override
    public Set<CourseSchedule> findAll(){
        Set<CourseSchedule> c = dao.findAll();
        if (c.isEmpty() || c == null) {
            throw new IllegalArgumentException("No records found");
        }
        return c;
    }
    
    @Override
    public CourseSchedule findById(Long id){
        CourseSchedule cs = dao.findById(id);
        if (cs.getStatus() == "C") {
            throw new IllegalArgumentException("Course Schedule is closed");
        }
        return cs;
        
    }
  
}
