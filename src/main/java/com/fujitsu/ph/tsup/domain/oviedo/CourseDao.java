package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

public interface CourseDao {
	 void save(Course course);
	 Long saveCourse(Course course);
	 Set<Course> findAll();
	 Course findById(Long id);
}
