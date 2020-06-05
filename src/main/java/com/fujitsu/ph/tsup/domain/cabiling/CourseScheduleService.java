package com.fujitsu.ph.tsup.domain.cabiling;

import java.util.Set;

public interface CourseScheduleService {
	void save(CourseSchedule courseSchedule);
    Set<CourseSchedule> findAll();
    CourseSchedule findById(Long id);

}
