package com.fujitsu.ph.tsup.domain.iwarat;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao dao;

    @Override
    public void save(Course course) {
        dao.save(course);
    }

    @Override
    public Set<Course> findAll() {
        try {
            return dao.findAll();
        } catch (IllegalArgumentException e) {
            throw new MyException("Error not found", e);
        }
    }

    @Override
    public Course findById(Long id) {
        try {
            return dao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new MyException("Error not found", e);
        }
    }

}