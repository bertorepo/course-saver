package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

public interface CourseService {

	void save(Course Course);
	Set<Course> findAll();
	Course findById(Long id);

}
