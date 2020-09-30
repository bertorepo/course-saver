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
	/**
     * Count all enrolled courses by Instructor Id
     * @param long id
     * 
     */
	int countAllEnrolledCoursesByInstructorId(long id);
	
	 /**
     * Finds monthly top learners
     * 
     */
	List<TopLearnersForm> findMonthlyTopLearners();
	
	 /**
     * Finds all quarterly top learners
     * 
     */
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
	
	 /**
     * Finds all course schedule by Id
     * @param long id
     * 
     */
	CourseSchedule findCourseScheduleById(Long id);
	
	 /**
     * Saves the CourseSchedule and CourseScheduleDetail object
     * @param courseSchedule
     */
	void saveCourseSchedule(CourseSchedule courseSchedule);
	
	 /**
     * Update a course schedule
     * @param courseSchedule
     */
	void updateCourseSchedule(CourseSchedule courseSchedule);
	
	
	 /**
     * Deletes the CourseSchedule and CourseScheduleDetail by ID
     * @param id
     */
	void deleteCourseScheduleById(Long id);
	
	 /**
     * Finds course schedule by course Id
     * @param long id
     * 
     */
	Set<CourseSchedule> findCourseScheduleByCourseId(Long id);
	
	 /**
     * Update course schedule status
     * @param courseSchedules
     * 
     */
	void updateCourseScheduleStatus(Set<CourseSchedule> courseSchedules);
	
}
