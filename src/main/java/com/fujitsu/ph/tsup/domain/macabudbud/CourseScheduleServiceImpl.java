package com.fujitsu.ph.tsup.domain.macabudbud;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService {
	@Autowired
	private CourseScheduleDao dao;

	public CourseSchedule getCourseById(Long id) {
		CourseSchedule courseSchedule = dao.findById(id);
		if (courseSchedule.getId() != id) {
			throw new IllegalArgumentException("Course schedule not found.");
		}
		return courseSchedule;
	}

	@Override
	public void save(CourseSchedule courseSchedule) {
		dao.save(courseSchedule);
		if(courseSchedule == null) {
			throw new IllegalArgumentException("Unable to save course schedule.");
		}
	}

	@Override
	public Set<CourseSchedule> findAll() {
		try {
			return dao.findAll();
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to find course schedule.");
		}
	}

	@Override
	public CourseSchedule findById(Long id) {
		try {
			return dao.findById(id);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to find course schedule by the the given id.");
		}
	}

}
