package com.fujitsu.ph.tsup.scheduling.dao;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleDao.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/23/2020 | WS) J.Macabugao | New Creation
//
//
//=======================================================

/**
* <pre>
* The data access interface for schedule related database access
* <pre>
* @version 0.01
* @author j.macabugao
*
*/

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.TopLearnersForm;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

public interface ScheduleDao {

    /**
     * Finds the scheduled courses starting from today onwards
     * @param scheduledStartDate
     * @param scheduledEndDateTime 
     */
	Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime scheduledStartDateTime, 
			ZonedDateTime scheduledEndDateTime );
	
	int countAllEnrolledCoursesByInstructorId(long id);
	
	List<TopLearnersForm> findMonthlyTopLearners();
	
	List<TopLearnersForm> findQuarterlyTopLearners();
	 /**
     * Finds all courses
     * 
     */
	Set<CourseForm> findAllCourses();
	
	 /**
     * Finds all instructors
     * 
     */
	Set<InstructorForm> findAllInstructors();
	
	 /**
     * Finds all venues
     * 
     */
	Set<VenueForm> findAllVenues();
	
	CourseSchedule findCourseScheduleById(long id);
	
	 /**
     * Saves the CourseSchedule and CourseScheduleDetail object
     * @param courseSchedule
     */
	void saveCourseSchedule(CourseSchedule courseSchedule);
	
	 /**
     * Update a course schedule
     * @param long course schedule id
     */
	void updateCourseSchedule(Long id);
	
	
	 /**
     * Deletes the CourseSchedule and CourseScheduleDetail by ID
     * @param long course schedule id
     */
	void deleteCourseScheduleById(Long id);
	
}
