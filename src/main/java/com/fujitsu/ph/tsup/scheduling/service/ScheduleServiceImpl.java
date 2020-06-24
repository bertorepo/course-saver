package com.fujitsu.ph.tsup.scheduling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

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
	
	

