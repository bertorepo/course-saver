package com.fujitsu.ph.tsup.domain.yu;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.domain.yu.CourseService;
import com.fujitsu.ph.tsup.domain.yu.CourseDao;
import com.fujitsu.ph.tsup.domain.yu.Course;

public class CourseServiceImpl implements CourseService {
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseDao dao;

    @Override
    public void save(Course id) {
        dao.save(id);
        if (id.getId() == 2) {
            throw new IllegalArgumentException("Save failed");
        }
    }

    public Course findById(Long id) {

        Course c = dao.findById(id);
        if (c.getId() == 2) {
            throw new IllegalArgumentException("Course not found");
        }

        return c;
    }

    @Override
    public Set<Course> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
