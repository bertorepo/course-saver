package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseScheduleServiceImpl implements CourseScheduleService {
	private Logger logger = LoggerFactory.getLogger(CourseScheduleServiceImpl.class);

	@Autowired
	CourseScheduleDao dao;
	@Override
	public void save(CourseSchedule cs) {
		// TODO Auto-generated method stub
		dao.save(cs);
	}

	@Override
	public Set<CourseSchedule> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public CourseSchedule findById(Long id) {
		// TODO Auto-generated method stub
		try {
			return dao.findById(id);
		}catch(IllegalArgumentException ex) {
			throw new ApplicationException("CourseSchedule not found",ex);
		}

	}
}
