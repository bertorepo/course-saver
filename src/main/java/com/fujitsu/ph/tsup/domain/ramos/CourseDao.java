package com.fujitsu.ph.tsup.domain.ramos;

import java.util.Set;

public interface CourseDao {
	void save(Course courseId);

	Set<Course> findAll();

	Course findById(Long courseId);;
	
	Course getCourseId(Long courseId);
}
