package com.fujitsu.ph.tsup.scheduling.service;

import java.time.ZonedDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.fujitsu.ph.tsup.domain.jimenez.CourseScheduleException;
import com.fujitsu.ph.tsup.scheduling.dao.ScheduleDao;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/23/2020 | WS) J.Macabugao | New Creation
//
//
//=======================================================
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

/**
* <pre>
* The implementation of schedule service
* <pre>
* @version 0.01
* @author j.macabugao
* @author jc.jimenez
*/

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleNewForm.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/24/2020 | WS) JC. Jimenez | New Creation
//        |            | WS) J. Macabugao| 
//
//
//
//=======================================================

public class ScheduleServiceImpl implements ScheduleService{
	
	/**
     * Schedule dao
     */
	@Autowired
	private ScheduleDao scheduleDao;

	/**
	 * <pre>
	 * Finds all scheduled courses based on the given date range
	 * Call scheduleDao.findAllScheduledCourses using the given fromDateTime, toDateTime and return the result
	 * <pre>
	 * 
	 * @param fromDate
	 * @param toDate
	 */
    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDate,
            ZonedDateTime toDate) {
        try {
            return scheduleDao.findAllScheduledCourses(fromDate, toDate);
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Can't access fromDate and toDate.");
        }
    }
    
    /**
     * <pre>
     * Finds all courses.
     * Call scheduleDao.findAllCourses and return the result.
     * <pre>
     */
    @Override
    public Set<CourseForm> findAllCourses() {
        Set<CourseForm> CourseFormList = scheduleDao.findAllCourses();
        try {
            if(CourseFormList == null || CourseFormList.isEmpty()) {
                throw new IllegalArgumentException("Can't find Courses");
            } else {
                return CourseFormList;
            }    
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Can't access Courses");
        }
    }

	/**
     * <pre>
     * Finds all instructors
     * Call scheduleDao.findAllInstructors and return the result
     * <pre>
     */
    @Override
    public Set<InstructorForm> findAllInstructors() {
    	 Set<InstructorForm> InstructorFormList = scheduleDao.findAllInstructors();
         try {
             if(InstructorFormList == null || InstructorFormList.isEmpty()) {
                 throw new IllegalArgumentException("Can't find Instructors");
             } else {
                 return InstructorFormList;
             }    
         } catch (DataAccessException ex) {
             throw new IllegalArgumentException("Can't access Instructors");
         }
         
    }
    
	/**
     * <pre>
     * Finds all venues
     * Call scheduleDao.findAllVenues and return the result
     * <pre>
     */
    @Override
    public Set<VenueForm> findAllVenues() {
    	 Set<VenueForm> VenueFormList = scheduleDao.findAllVenues();
         try {
             if(VenueFormList == null || VenueFormList.isEmpty()) {
                 throw new IllegalArgumentException("Can't find Venues");
             } else {
                 return VenueFormList;
             }    
         } catch (DataAccessException ex) {
             throw new IllegalArgumentException("Can't access Venues");
         }
         
    }
    
	/**
     * <pre>
     * Create a course schedule
     * Call the scheduleDao.saveCourseSchedule using the given courseSchedule
     * <pre>
     * 
     * @param courseSchedule
     */
	@Override
	public CourseSchedule createCourseSchedule(CourseSchedule courseSchedule) {
		
		 try {
	            return scheduleDao.saveCourseSchedule(courseSchedule);
	        } catch (DataAccessException ex) {
	        	 throw new IllegalArgumentException("No records found"); 
	        }
	    }
}
	

