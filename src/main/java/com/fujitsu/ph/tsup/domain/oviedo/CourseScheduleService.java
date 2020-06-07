package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

public interface CourseScheduleService {
	void save(CourseSchedule cs);
    Set<CourseSchedule> findAll();
    CourseSchedule findById(Long id);
}
