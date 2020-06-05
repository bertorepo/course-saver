package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseScheduleServiceImpl implements CourseScheduleService {

    @Autowired
    private CourseScheduleDao dao;

    // check if id matches any id from courseSchedule
    // (CourseSchedule.getId() != id) throw exception

    @Override
    public void save(CourseSchedule courseSchedule) {
        dao.save(courseSchedule);
        if (courseSchedule.getId() == null) {
            throw new IllegalArgumentException("Schedule not saved.");
        }
    }

    @Override
    public Set<CourseSchedule> findAll() {
        Set<CourseSchedule> c = dao.findAll();
        if (c.isEmpty()) {
            throw new IllegalArgumentException("No records found.");
        }
        return c;
    }

    @Override
    public CourseSchedule findById(Long id) {
        CourseSchedule cs = dao.findById(id);
        if (cs.getId() == 0) {
            throw new IllegalArgumentException("No records found.");
        }
        return cs;
    }
}
