package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.fujitsu.ph.tsup.domain.angara.CourseDao;

public class CourseServiceImpl implements CourseService {
    
    @Autowired
    private CourseDao dao;
    
    // check if id matches any id from courses
    // (course.getId() != id) throw exception

    @Override
    public void save(Course course) {
        dao.save(course);
        if (course.getId() == null) {
            throw new IllegalArgumentException("Course not saved.");
        } 
    }

    @Override
    public Set<Course> findAll() {
        Set<Course> c = dao.findAll();
        if (c.isEmpty()) {
            throw new IllegalArgumentException("No course found.");
        }
        return c;
    }

    @Override
    public Course findById(Long id) {
       Course cd = dao.findById(id);
       if (cd.getId() == 0) {
           throw new IllegalArgumentException("No course found.");
       }
    return cd;
    }
}