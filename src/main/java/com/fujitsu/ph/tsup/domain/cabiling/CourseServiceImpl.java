package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao dao;

	@Override
	public void save(Course Course) {

		try {
			dao.save(Course);
		} catch (DataAccessException ex) {
			throw new CourseException("Schedule not saved", ex);
		}
	}

	@Override
	public Set<Course> findAll() {
		try {
			return dao.findAll();
		} catch (DataAccessException ex) {
			throw new CourseException("No course record found", ex);
		}
	}

	@Override
	public Course findById(Long id) {
		try {
			return dao.findById(id);
		} catch (DataAccessException ex) {
			throw new CourseException("No course found by that id", ex);
		}
	}

}