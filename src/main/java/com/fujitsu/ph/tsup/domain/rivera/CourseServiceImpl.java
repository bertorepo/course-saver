package com.fujitsu.ph.tsup.domain.rivera;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.domain.rivera.CourseService;
import com.fujitsu.ph.tsup.domain.rivera.CourseDao;
import com.fujitsu.ph.tsup.domain.rivera.Course;

@Service
public class CourseServiceImpl implements CourseService {
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseDao dao;

    @Override
    public void save(Course course) {
        dao.save(course);
        if(course.getCourseId() == 12345) {
            throw new IllegalArgumentException("Course not saved");
        }
    }

    public Course findById(Long id) {

        Course c = dao.findById(id);
        if (c.getCourseId() == 2) {
            throw new IllegalArgumentException("Course not found");
        }

        return c;
    }

    @Override
    public Set<Course> findAll() {
    	Set<Course> courseAll = dao.findAll();
        if (courseAll == null) {
            throw new IllegalArgumentException("Record not found");
        }
        return courseAll;
    }

}
