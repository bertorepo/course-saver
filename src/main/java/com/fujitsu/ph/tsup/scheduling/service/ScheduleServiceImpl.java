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
*
*/
public class ScheduleServiceImpl implements ScheduleService{
	
	/**
     * Schedule dao
     */
	@Autowired
	private ScheduleDao scheduleDao;


    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDate,
            ZonedDateTime toDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<CourseForm> findAllCourses() {
        // TODO Auto-generated method stub
        return null;
    }

	/**
     * <pre>
     * Finds all instructors
     * <pre>
     * 
     * @return
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
     * <pre>
     * 
     * @return
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
	

