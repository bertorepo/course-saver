package com.fujitsu.ph.tsup.domain.rivera;

public interface CourseService {
	Long save(Course course);
	
	Course findById(Long courseId);

}
