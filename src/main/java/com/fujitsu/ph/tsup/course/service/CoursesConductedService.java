package com.fujitsu.ph.tsup.course.service;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.course.model.CoursesConducted;



public interface CoursesConductedService {
	
	 /**
     * <pre>
     * Finds all courses conducted based on the given date range
     * <pre>
     * @param selectedStartDateTime
     * @param selectedEndDateTime
     * @return Set<ConductedCourse>
     */
	Set<CoursesConducted> findAllCoursesConducted(ZonedDateTime selectedStartDateTime, ZonedDateTime  selectedEndDateTime);
	
	 /**
     * <pre>
     * Finds all courses conducted based on the given date range
     * <pre>
     * @return Set<ConductedCourse>
     */
}
