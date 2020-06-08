package com.fujitsu.ph.tsup.domain.francisco;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public void save(Course course) {
        try {
            courseDao.save(course);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

    @Override
    public Set<Course> findAll() {
        try {
            return courseDao.findAll();
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

    @Override
    public Course findById(Long id) {
        try {
            return courseDao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

}
