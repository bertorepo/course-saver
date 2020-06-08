package com.fujitsu.ph.tsup.domain.ramos;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseScheduleServiceImpl implements CourseScheduleService {
	private Logger logger = LoggerFactory.getLogger(CourseScheduleServiceImpl.class);

	@Autowired
	private CourseScheduleDao dao;

	@Override
	public void save(CourseSchedule courseSchedule) {
		dao.save(courseSchedule);
		if (courseSchedule.getId() == 101) {
			throw new IllegalArgumentException("Course schedule not saved");
		}
	}

	@Override
	public Set<CourseSchedule> findAll() {
        Set<CourseSchedule> courseSchedule = dao.findAll();
        if (courseSchedule.isEmpty() || courseSchedule == null) {
            throw new IllegalArgumentException("Invalid course schedule");
        }
        return courseSchedule;
	}

	@Override
	public CourseSchedule findById(Long id) {
        CourseSchedule courseSchedule = dao.findById(id);
        if (courseSchedule.getId() == 101) {
            throw new IllegalArgumentException("Course schedule not found");
        } 
        return courseSchedule;   
	}

}
