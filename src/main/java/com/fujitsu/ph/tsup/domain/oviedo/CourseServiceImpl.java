package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
	private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	private Set<Course> courses;

	@Autowired
	private CourseDao dao;

	@Override
	public Course findById(Long id) {
		// TODO Auto-generated method stub

//		try {
//			return dao.findById(id);
//		} catch (IllegalArgumentException ex) {
//			throw new ApplicationException("Course not found", ex);
//		}
		Course c = dao.findById(id);
		if(c.getId() == 0 || c.getId() == null) {
			throw new ApplicationException("ID is invalid");
		}
		return c;
	}

	@Override
	public void save(Course course) {
		// TODO Auto-generated method stub
		dao.save(course);
	}

	@Override
	public Set<Course> findAll() {
		// TODO Auto-generated method stub
		Course c = (Course) dao.findAll();
		return c.cns;
	}
}
