package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService {

	@Autowired
	private CourseScheduleDao dao;

	@Override
	public void save(CourseSchedule courseSchedule) {
		try {
			dao.save(courseSchedule);
		} catch (IllegalArgumentException ex) {
			throw new NewException("Schedule not saved", ex);
		}
	}

	@Override
	public Set<CourseSchedule> findAll() {
		try {
			return dao.findAll();
		} catch (IllegalArgumentException ex) {
			throw new NewException("No schedule found", ex);
		}
	}

	@Override
	public CourseSchedule findById(Long id) {
		try {
			return dao.findById(id);
		} catch (IllegalArgumentException ex) {
			throw new NewException("No schedule found for that id", ex);
		}
	}

}
