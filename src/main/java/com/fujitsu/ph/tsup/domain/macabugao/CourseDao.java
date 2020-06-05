package com.fujitsu.ph.tsup.domain.macabugao;

import java.util.Set;

public interface CourseDao {

	void save(Course course);

	Set<Course> findAll();

	Course findById(Long id);

}
