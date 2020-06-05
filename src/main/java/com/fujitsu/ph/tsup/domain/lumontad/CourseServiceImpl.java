package com.fujitsu.ph.tsup.domain.lumontad;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class CourseServiceImpl implements CourseService{
    
private CourseDao courseDao;

@Override
public Long save(Course courseid) {
    return courseDao.save(courseid);
}

@Override
public Course findById(Long id) {
    try {
        return courseDao.findById(id);
    } catch (DataAccessException ex) {
        throw new CourseException("Course not found", ex);
    }
}
}
