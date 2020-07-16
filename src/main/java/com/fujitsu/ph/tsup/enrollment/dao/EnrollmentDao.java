package com.fujitsu.ph.tsup.enrollment.dao; 

//====================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Process
//Class Name   :EnrollmentDao.java
//
//<<Modification History>>
//Version | Date       | Updated By | Content
//--------+------------+-----------------------+------
//0.01    | 06/24/2020 |  WS) J.Yu  | New Creation
//====================================================

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * <pre>
 * The data access interface for enrollment related database access
 * <pre>
 * 
 * @version 0.01
 * @author j.yu
 */
public interface EnrollmentDao {
    
    /**
     * Finds the scheduled courses starting from today onwards
     * @param fromDateTime
     * @param toDateTime
     */
    Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime);
    
    /**
     * Finds the course schedule by id
     * @param id
     */
    CourseSchedule findCourseScheduleById(Long id);
    
    /**
     * Finds the course schedule enrollment by course schedule id and participant id
     * @param courseScheduleId
     * @param participantId
     */
    CourseParticipant findCourseParticipantByCourseScheduleIdAndParticipantId(Long courseScheduleId, 
            Long participantId);
    
    /**
     * Saves the CourseParticipant object
     * @param courseParticipant
     */
    void saveCourseParticipant(CourseParticipant courseParticipant);
    
    /**
     * Finds the scheduled courses starting from today onwards
     * @param participantId
     * @param fromDateTime
     * @param toDateTime
     */
    Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId, 
            ZonedDateTime fromDateTime, ZonedDateTime toDateTime);
    
    /**
     * Finds the participant enrolled by id
     * @param id
     */
    CourseParticipant findCourseParticipantById(Long id);
    
    /**
     * Deletes the course participant by id
     * @param id
     */
    void deleteCourseParticipantById(Long id);
    
    /**
     * Save the course non participant
     * @param id
     */
	void saveCourseNonParticipant(CourseParticipant courseParticipant); 
 
    /**
     * Save the course schedule
     * @param courseSchedule
     */
    void changeCourseScheduleStatus(CourseSchedule courseSchedule);
   
  
}
