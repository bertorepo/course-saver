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
import java.util.Set;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
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
    
}
