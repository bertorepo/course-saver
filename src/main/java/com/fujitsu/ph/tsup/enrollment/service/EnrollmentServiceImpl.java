package com.fujitsu.ph.tsup.enrollment.service;
//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Course
//Class Name   :EnrollmentServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/25/2020 | WS) T.Oviedo          | New Creation
//==================================================================================================
/**
* <pre>
* JavaBean for EnrollmentServiceImpl
* <pre>
* 
* @version 0.01
* @author t.oviedo                    
*/
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import java.time.ZonedDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentDao enrollmentDao;
	
	/** Finds the scheduled courses starting from today onwards */
	@Override
	public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {	
		Set<CourseSchedule> courseScheduleSet = enrollmentDao.findAllScheduledCourses(fromDateTime, toDateTime);
		 if (courseScheduleSet.isEmpty() || courseScheduleSet == null) {
	            throw new IllegalArgumentException("No schedules found");
	        }
		return courseScheduleSet;
	}

	/** Finds the course schedule by id */
	@Override
	public CourseSchedule findCourseScheduleById(Long id) {
		return enrollmentDao.findCourseScheduleById(id);
	}

	/** enroll using the courseParticipant */
	@Override
	public void enroll(CourseParticipant courseParticipant) {
		

		try {
			CourseSchedule courseRecord = enrollmentDao.findCourseScheduleById(courseParticipant.getId());
			if (courseRecord == null){
				throw new IllegalArgumentException("This course " +courseParticipant.getCourseName()+ " is not existing");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("This course " +courseParticipant.getCourseName()+ " is not existing");
		}
		
		try {
			CourseParticipant participantRecord = enrollmentDao.findCourseParticipantByCourseScheduleIdAndParticipantId(courseParticipant.getCourseScheduleId(), courseParticipant.getParticipantId());
			if(participantRecord != null){
				throw new IllegalArgumentException("You are already enrolled to the course: " +courseParticipant.getCourseName()+ ".");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("You are already enrolled to the course: " +courseParticipant.getCourseName()+ ".");
		}

		enrollmentDao.saveCourseParticipant(courseParticipant);

	}

	/** Finds the scheduled courses starting from today onwards */
	@Override
	public Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId, ZonedDateTime fromDateTime,
			ZonedDateTime toDateTime) {
		Set<CourseParticipant> courseParticipant = enrollmentDao.findAllEnrolledCoursesByParticipantId(participantId, fromDateTime, toDateTime);
		return courseParticipant;
	}

	/** Finds the participant enrolled by id */
	@Override
	public CourseParticipant findCourseParticipantById(Long id) {
		CourseParticipant courseParticipant = enrollmentDao.findCourseParticipantById(id);
		  try {
	             if(courseParticipant == null ) {
	                 throw new IllegalArgumentException("Participant not Found");
	             } else {
	                 return courseParticipant;
	             }    
	         } catch (DataAccessException ex) {
	             throw new IllegalArgumentException(" Participant not Found ");
	         }	
	}

	@Override
	public void declineCourse(CourseParticipant courseParticipant) {
	
		enrollmentDao.deleteCourseParticipantById(courseParticipant.getId());
		enrollmentDao.saveCourseNonParticipant(courseParticipant);
	} 

	@Override
	public void cancel(Long id) {
		CourseSchedule courseSchedule = enrollmentDao.findCourseScheduleById(id);
		
		if(courseSchedule.getId() == null) {
			throw new IllegalArgumentException("This course{"+id+"} is not existing");
		}
		CourseSchedule courseScheduleInstance = new CourseSchedule.Builder(id).cancel();	
		enrollmentDao.changeCourseScheduleStatus(courseScheduleInstance);
	}

}
