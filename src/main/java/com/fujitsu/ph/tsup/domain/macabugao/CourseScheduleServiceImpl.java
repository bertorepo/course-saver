package com.fujitsu.ph.tsup.domain.macabugao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleServiceImpl implements CourseScheduleService{
	
	@Autowired
	CourseScheduleDao courseScheduleDao;
	
	public void save(CourseSchedule courseSchedule) {
		try {
			courseScheduleDao.save(courseSchedule);
		} catch (DataAccessException ex) {
			throw new IllegalApplicationException("Course Schedule not found", ex);
		}
		
	}

	public Set<CourseSchedule> findAll() {
		try {
			return courseScheduleDao.findAll();
		} catch (DataAccessException ex) {
			throw new IllegalApplicationException("Cannot find Course Schedule", ex);
		}
	}

	public CourseSchedule findById(Long id) {
		try {
			return courseScheduleDao.findById(id);
		} catch (DataAccessException ex) {
			
			throw new IllegalApplicationException("Id not found", ex);
			
		}
	}


}
