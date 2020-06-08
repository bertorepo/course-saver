package com.fujitsu.ph.tsup.domain.francisco;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService {

    @Autowired
    private CourseScheduleDao courseDao;

    @Override
    public void save(CourseSchedule courseSchedule) {
        try {
            courseDao.save(courseSchedule);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

    @Override
    public Set<CourseSchedule> findAll() {
        try {
            return courseDao.findAll();
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

    @Override
    public CourseSchedule findById(Long id) {
        try {
            return courseDao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }
}
