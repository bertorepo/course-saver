package com.fujitsu.ph.tsup.scheduling.service;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleService.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) JC. Jimenez | New Creation
//0.02    | 08/24/2002 | WS) J. Balanon  | New Creation
//
//=======================================================

/**
* <pre>
* The interface for schedule service
* <pre>
* @version 0.01
* @author jc.jimenez
*
*/

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleNewForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleUpdateForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.TopLearnersForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

public interface ScheduleService {
    
    /**
     * <pre>
     * Finds all scheduled courses based on the given date range
     * <pre>
     * @param fromDate
     * @param toDate
     * @return Set<CourseSchedule>
     */
    Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDate, ZonedDateTime toDate);
    
    /**
     * <pre>
     * Finds all Courses based on the given date range
     * <pre>
     * 
     * @return Set<CourseForm>
     */
    Set<CourseForm> findAllCourses();
    
    /**
     * <pre>
     * Finds all Instructors based on the given date range
     * <pre>
     * 
     * @return Set<InstructorForm>
     */
    Set<InstructorForm> findAllInstructors();
    
    /**
     * <pre>
     * Finds all Venues based on the given date range
     * <pre>
     * 
     * @return Set<VenueForm>
     */
    Set<VenueForm> findAllVenues();
    
    /**
     * Create a course schedule
     * @param courseSchedule
     */
    void createCourseSchedule(CourseSchedule courseSchedule);
    
    /**
     * Update a course schedule
     * @param courseSchedule
     */
    void updateCourseSchedule(CourseSchedule courseSchedule);
    
    /**
     * Delete a course schedule by Id
     * @param long id
     */
    void deleteCourseScheduleById(Long id);
    
    /**
     * Counts the Enrolled Courses by Instructor Id
     * @param long id
     * @return 
     */
    int countAllEnrolledCoursesByInstructorId(Long id);
    
    /**
     * Find Top Learners
     * @param long id
     */
    List<TopLearnersForm> findTopLearners(ZonedDateTime fromDateTime, ZonedDateTime toDateTime);
    
    
    /**
     * Find Course Schedule by Id
     * @param long id
     * @return 
     */
    CourseSchedule findCourseScheduleById(Long Id);
    
    /**
     * Find Course Schedule by Course Id
     * @param long id
     * 
     */
    Set<CourseSchedule> findCourseScheduleByCourseId(Long id);
 
    /**
     * Change Schedule Status
     * @param changeScheduleStatus
     */
    void changeScheduleStatus(Set<CourseSchedule> courseSchedules);
    
	/** Sends email to participants */
	void sendEmailtoParticipants(Long id, ZonedDateTime formStart, ZonedDateTime formEnd);
	
	/** Checks if date and time range entered conflicts with any existing schedule (New)
	 * @param courseSchedule **/
	boolean checkForScheduleConflict(@Valid CourseScheduleNewForm form, CourseSchedule courseSchedule, CourseScheduleDetail cSchedDet);
	
	/** Checks if date or time range entered conflicts with any existing schedule (Update)
	 * @param courseSchedule **/
	boolean checkForScheduleConflictUpdate(@Valid CourseScheduleUpdateForm form, CourseSchedule courseSchedule,
			CourseScheduleDetail cSchedDet);
}