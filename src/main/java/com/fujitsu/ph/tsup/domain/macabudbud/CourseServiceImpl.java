package com.fujitsu.ph.tsup.domain.macabudbud;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao dao;

	public Course getCourseById(Long id) {
		Course course = dao.findById(id);
		if (course.getId() != id) {
			throw new IllegalArgumentException("Course not found.");
		}
		return course;
	}

	@Override
	public void save(Course course) {
		dao.save(course);
		if (course == null) {
			throw new IllegalArgumentException("Unable to save course");
		}
	}

	@Override
	public Set<Course> findAll() {
		Set<Course> course = dao.findAll();
		if (course == null || course.isEmpty() ) {
			throw new IllegalArgumentException("No course found");
		}
		return course;
	}

	@Override
	public Course findById(Long courseId) {
		Course course = dao.findById(courseId);
		if (course == null) {
			throw new IllegalArgumentException("No course found");
		}
		return course;
	}

}
