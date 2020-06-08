package com.fujitsu.ph.tsup.domain.ramos;

import java.util.Set;

public interface CourseDao {
	Long save(Course course);

	Set<Course> findAll();
	
	Course findById(Long id);

	Long saveLong(Course course);

}
