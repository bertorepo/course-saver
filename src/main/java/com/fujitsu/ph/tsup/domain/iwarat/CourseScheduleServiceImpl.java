package com.fujitsu.ph.tsup.domain.iwarat;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService {

    @Autowired
    private CourseScheduleDao cdao;

    @Override
    public void save(CourseSchedule courseSchedule) {
        try {
            cdao.save(courseSchedule);
        } catch (IllegalArgumentException e) {
            throw new MyException("Error found", e);
        }
    }

    @Override
    public Set<CourseSchedule> findAll() {
        try {
            return cdao.findAll();
        } catch (IllegalArgumentException e) {
            throw new MyException("Error found", e);
        }
    }

    @Override
    public CourseSchedule findById(Long id) {
        try {
            return cdao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new MyException("Error found", e);
        }
    }
}
