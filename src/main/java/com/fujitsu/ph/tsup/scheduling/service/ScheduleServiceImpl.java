package com.fujitsu.ph.tsup.scheduling.service;

import java.time.ZonedDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.scheduling.dao.ScheduleDao;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.TopLearnersForm;
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
//0.02    | 06/25/2020 | WS) J. Balanon  | New Creattion
//
//
//=======================================================
@Service
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
        
        try {
            Set<CourseForm> courseFormList = scheduleDao.findAllCourses();
            if(courseFormList == null || courseFormList.isEmpty()) {
                throw new IllegalArgumentException("Can't find Courses");
            }
            
            return courseFormList;
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
    	 
         try {
             Set<InstructorForm> instructorFormList = scheduleDao.findAllInstructors();
             if(instructorFormList == null || instructorFormList.isEmpty()) {
                 throw new IllegalArgumentException("Can't find Instructors");
             }
             
             return instructorFormList;
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
    	 
         try {
             Set<VenueForm> venueFormList = scheduleDao.findAllVenues();
             if(venueFormList == null || venueFormList.isEmpty()) {
                 throw new IllegalArgumentException("Can't find Venues");
             }
             
             return venueFormList;
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
	
	/**
     * <pre>
     * Update a course schedule
     * Call ScheduleDao.updateCourseSchedule by using Course Schedule Object.
     * <pre>
     * 
     * @param courseSchedule
     */
	@Override
	public void updateCourseSchedule(CourseSchedule courseSchedule) {
		
		long id = courseSchedule.getId();

		try {
			scheduleDao.updateCourseSchedule(id);
		} catch (DataAccessException ex) {
			throw new IllegalArgumentException("Can't update course schedule");
		}
	}
		
	/**
     * <pre>
     * Delete a course schedule
     * Call ScheduleDao.deleteCourseScheduleById by using Course ID
     * <pre>
     * 
     * @param courseSchedule
     */
	@Override
	public void deleteCourseScheduleById(Long id) {
		
		try {
			   scheduleDao.deleteCourseScheduleById(id);
	        } catch (DataAccessException ex) {
	        	 throw new IllegalArgumentException("Can't delete course schedule"); 
	        }
	    }

	@Override
	public void countAllEnrolledCoursesTodayByInstructorId(Long Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findMonthlyTopLearners(TopLearnersForm topLearnersForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findQuarterlyTopLearners(TopLearnersForm topLearnersForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CourseSchedule findCourseScheduleById(Long Id) {
		return null;
		// TODO Auto-generated method stub
		
	}
		
	}

	

