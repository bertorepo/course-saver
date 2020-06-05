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

    @Override
    public Set<Course> findAll() {
        Set<Course> c = dao.findAll();
        if (c.isEmpty()) {
            throw new IllegalArgumentException("No course found");
        }
        return c;
    }

    public Course findById(Long id) {

        Course c = dao.findById(id);
        if (c.getId() == 2) {
            throw new IllegalArgumentException("Course not found");
        }

        return c;
    }

}
