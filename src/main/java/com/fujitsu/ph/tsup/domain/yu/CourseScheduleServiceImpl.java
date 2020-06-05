package com.fujitsu.ph.tsup.domain.yu;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.domain.yu.CourseScheduleService;
import com.fujitsu.ph.tsup.domain.yu.CourseScheduleDao;
import com.fujitsu.ph.tsup.domain.yu.CourseSchedule;

public class CourseScheduleServiceImpl implements CourseScheduleService {
    private Logger logger = LoggerFactory
            .getLogger(CourseScheduleServiceImpl.class);

    @Autowired
    private CourseScheduleDao dao;

    @Override
    public void save(CourseSchedule id) {
        dao.save(id);
        if (id.getStatus() == "C") {
            throw new IllegalArgumentException("Save failed");
        }
    }

    @Override
    public Set<CourseSchedule> findAll() {
        Set<CourseSchedule> c = dao.findAll();
        if (c.isEmpty()) {
            throw new IllegalArgumentException("No schedule found");
        }
        return c;
    }

    @Override
    public CourseSchedule findById(Long id) {
        CourseSchedule c = dao.findById(id);
        if (c.getStatus() == "C") {
            throw new IllegalArgumentException("Course is cancelled");
        }
        return c;
    }
}