package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

interface CourseDao {
	 void save(Course course);
	 Set<Course> findAll();
	 Course findById(Long id);
	 Long returnGeneratedKey();
}