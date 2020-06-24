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
import java.util.Set;

import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
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
     * Saves the CourseSchedule and CourseScheduleDetail object
     * @param courseSchedule
     */
	CourseSchedule saveCourseSchedule(CourseSchedule courseSchedule);
	
}
