package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

public interface CourseScheduleDao {
	 void save(CourseSchedule cs);
	    Long saveCourseSchedule(CourseSchedule courseSchedule);
	    Set<CourseSchedule> findAll();
	    CourseSchedule findById(Long id);
}
