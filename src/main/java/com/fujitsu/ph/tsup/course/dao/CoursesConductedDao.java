package com.fujitsu.ph.tsup.course.dao;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.course.model.CoursesConducted;


public interface CoursesConductedDao {
	
	/**
     * Finds the courses conducted starting from today onwards
     * @param selectedStartDateTime
     * @param selectedEndDateTime 
     */
	Set<CoursesConducted> findAllCoursesConducted(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime);
	
	/**
     * Finds the courses conducted starting from today onwards
     */

}
