package com.fujitsu.ph.tsup.domain.macabugao;

import java.util.Set;

public interface CourseScheduleDao {
	
	void save(CourseSchedule courseSchedule);

	Set<CourseSchedule> findAll();

	CourseSchedule findById(Long id);
	
	Long generatedKey();

}
