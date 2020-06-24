package com.fujitsu.ph.tsup.scheduling.service;

import java.time.ZonedDateTime;
import java.util.Set;

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

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

public interface ScheduleService {
    
    /**
     * Finds all scheduled courses based on the given date range
     * @param fromDate
     * @param toDate
     */
    //Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDate, ZonedDateTime toDate);
    
    //Set<CourseForm> findAllCourses();
    
    //Set<InstructorForm> findAllInstructors();
    
    //Set<VenueForm> findAllVenues();
    /**
     * Create a course schedule
     * @param courseSchedule
     */
    CourseSchedule createCourseSchedule(CourseSchedule courseSchedule);
    
    
}
