package com.fujitsu.ph.tsup.scheduling.service;

import java.time.ZonedDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


import com.fujitsu.ph.tsup.scheduling.dao.ScheduleDao;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
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
//0.01    | 06/24/2020 | WS) J. Macabugao| New Creattion
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
        Set<CourseForm> courseFormList = scheduleDao.findAllCourses();
        try {
            if(courseFormList == null || courseFormList.isEmpty()) {
                throw new IllegalArgumentException("Can't find Courses");
            } else {
                return courseFormList;
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
    	 Set<InstructorForm> instructorFormList = scheduleDao.findAllInstructors();
         try {
             if(instructorFormList == null || instructorFormList.isEmpty()) {
                 throw new IllegalArgumentException("Can't find Instructors");
             } else {
                 return instructorFormList;
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
    	 Set<VenueForm> venueFormList = scheduleDao.findAllVenues();
         try {
             if(venueFormList == null || venueFormList.isEmpty()) {
                 throw new IllegalArgumentException("Can't find Venues");
             } else {
                 return venueFormList;
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
	public void createCourseSchedule(CourseSchedule courseSchedule) {
		
		 try {
			   scheduleDao.saveCourseSchedule(courseSchedule);
	        } catch (DataAccessException ex) {
	        	 throw new IllegalArgumentException("Can't save course schedule"); 
	        }
	    }
}
	

