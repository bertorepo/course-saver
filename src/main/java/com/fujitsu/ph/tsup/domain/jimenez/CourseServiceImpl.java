package com.fujitsu.ph.tsup.domain.jimenez;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseDao courseDao;

    @Override
    public Course findById(Long id) {
        try {
            return courseDao.findById(id);
        } catch (DataAccessException ex) {
            throw new CourseException("Course not found!", ex);
        }
    }
    
    @Override
    public Set<Course> findAll() {
        Set<Course> CourseList = courseDao.findAll();
        try {
            if(CourseList.isEmpty() || CourseList == null) {
                throw new CourseException("Can't find Course Details");
            } else {
                return CourseList;
            }    
        } catch (DataAccessException ex) {
            throw new CourseException("Can't access Course Details.");
        }
        
    }


    @Override
    public void save(Course Course) {
        if (Course.getId() != 0) {
            courseDao.save(Course);
        } else if (Course.getId() <= 0) {
            throw new CourseException("Course Id should not be zero or less than zero.");
        }
        
     }

}
