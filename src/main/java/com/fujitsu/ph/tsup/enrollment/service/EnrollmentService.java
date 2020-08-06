package com.fujitsu.ph.tsup.enrollment.service;
//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Course
//Class Name   :EnrollmentService.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/24/2020  | WS) T.Oviedo          | New Creation
//==================================================================================================
/**
* <pre>
* JavaBean for EnrollmentService
* <pre>
* 
* @version 0.01
* @author t.oviedo                    
*/
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.Participant;

public interface EnrollmentService {
	
	/** Finds all scheduled courses based on the given date range */
	Set<CourseSchedule>findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime);
	
	/** Finds specific details on courses based on the given date range */
	Set<CourseSchedule>findAllMemberScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime);
	
	/** Finds the course schedule by Id */
	CourseSchedule findCourseScheduleById(Long id);
	
	/** Enrolls to a scheduled course */
	void enroll(CourseParticipant courseParticipant);
	
	/** Finds all my enrolled courses starting from this day onwards */
	Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId, ZonedDateTime fromDateTime, ZonedDateTime toDateTime);
	
	/** Finds course participant by id */
	CourseParticipant findCourseParticipantById(Long id);
	
	/** Decline the course which the participant was previously enrolled */
	void declineCourse(CourseParticipant courseParticipant);

	/** Cancels a scheduled course */
	void cancel(Long id);

	/** Finds the participant of course by Id */
	List<Participant> findEnrolledMembersById(Long id);

	/** Add the participant of course by Id */
	Integer addEnrolledMembersById(Participant participant);
}
