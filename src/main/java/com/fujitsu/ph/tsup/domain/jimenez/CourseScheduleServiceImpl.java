package com.fujitsu.ph.tsup.domain.jimenez;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService{
    @Autowired
    private CourseScheduleDao courseScheduleDao;

    @Override
    public CourseSchedule findById(Long id) {
        try {
            return courseScheduleDao.findById(id);
        } catch (DataAccessException ex) {
            throw new CourseScheduleException("Course Schedule not found!", ex);
        }
    }
    
    @Override
    public Set<CourseSchedule> findAll() {
        Set<CourseSchedule> CourseScheduleList = courseScheduleDao.findAll();
        try {
            if(CourseScheduleList.isEmpty() || CourseScheduleList == null) {
                throw new CourseScheduleException("Can't find Course Schedule Details");
            } else {
                return CourseScheduleList;
            }    
        } catch (DataAccessException ex) {
            throw new CourseScheduleException("Can't access Course Schedule Details.");
        }
        
    }

    @Override
    public void save(CourseSchedule courseSchedule) {
        if (courseSchedule.getId() != 0) {
            courseScheduleDao.save(courseSchedule);
        } else if (courseSchedule.getId() <= 0) {
            throw new CourseScheduleException("Course Schedule Id should not be zero or less than zero.");
        }
        
     }
    
    

}

