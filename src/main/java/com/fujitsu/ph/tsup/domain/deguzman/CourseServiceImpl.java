package com.fujitsu.ph.tsup.domain.deguzman;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl implements CourseService {
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseDao dao;

    @Override
    public void save(Course course) {
        dao.save(course);
        if (course.getId() == 0) {
            throw new IllegalArgumentException("Course not saved");
        }
    }

    @Override
    public Set<Course> findAll() {
        Set<Course> c = dao.findAll();
        if (c.isEmpty() || c == null) {
            throw new IllegalArgumentException("No records found");
        }
        return c;
    }

    @Override
    public Course findById(Long id) {
        Course c = dao.findById(id);
        if (c.getId() == 0) {
            throw new IllegalArgumentException("Invalid course");
        }
        return c;

    }

}
