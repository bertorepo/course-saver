package com.fujitsu.ph.tsup.domain.macabugao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	public void save(Course course) {
		try {
			courseDao.save(course);
		} catch (DataAccessException ex) {
			throw new IllegalApplicationException("Course not found", ex);
		}

	}

	public Set<Course> findAll() {
		try {
			return courseDao.findAll();
		} catch (DataAccessException ex) {
			throw new IllegalApplicationException("Course not found", ex);
		}
	}

	public Course findById(Long id) {
		try {
			return courseDao.findById(id);
		} catch (DataAccessException ex) {
			throw new IllegalApplicationException("Id not found", ex);
		}
	}

}
