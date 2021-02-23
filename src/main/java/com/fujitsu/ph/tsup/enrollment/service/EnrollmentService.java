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
//0.01    | 06/24/2020 | WS) T.Oviedo          | New Creation
//0.02    | 08/24/2020 | WS) J.Yu              | Update
//==================================================================================================
/**
* <pre>
* JavaBean for EnrollmentService
* <pre>
* 
* @version 0.01
* @author t.oviedo                    
*/

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.enrollment.model.SearchForm;
import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public interface EnrollmentService {

	/** Sends calendar invite to the successfully enrolled participant */
	void sendCalendarInvite(CourseParticipant courseParticipant);

	/** Finds all scheduled courses based on the given date range */
	Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime);

	/** Finds specific details on courses based on the given date range */
	Set<CourseSchedule> findAllMemberScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime);

	/** Finds the course schedule by Id */
	CourseSchedule findCourseScheduleById(Long id);

	/** Enrolls to a scheduled course */
	void enroll(CourseParticipant courseParticipant);

	/** Finds all my enrolled courses starting from this day onwards */
	Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId, ZonedDateTime fromDateTime,
			ZonedDateTime toDateTime);

	/** Finds course participant by id */
	CourseParticipant findCourseParticipantById(Long id);

	/** Decline the course which the participant was previously enrolled */
	void declineCourse(CourseParticipant courseParticipant);

	/** Cancels a scheduled course */
	void cancel(Long id);

	/** Finds the participant of course by Id */
//	List<Participant> findEnrolledMembersById(Long id);

	/** Add the participant of course by Id */
//	Integer addEnrolledMembersById(Participant participant);

	/**
	 * Cancel all course schedule below minimum participants
	 * 
	 * @param courseScheduleSet
	 */
	void cancelCourseSchedules(Set<CourseSchedule> courseScheduleSet);

	/**
	 * Find all Active Course schedule
	 * 
	 * @return
	 */
	Set<CourseSchedule> findAllActiveCourseSchedule();

	/**
	 * Find all course schedule by month/quarter
	 * 
	 * @param queryBy
	 * @return
	 */
	Set<CourseSchedule> findAllCouresScheduleByMonthOrQuarter(String queryBy);

	/**
	 * Reschedule Course schedule
	 * 
	 * @param courseScheduleDetail
	 */
	void rescheduleCourseScheduleById(CourseScheduleDetail courseScheduleDetail);

	/**
	 * Find all course schedule below minimum
	 * 
	 * @return
	 */
	Set<CourseSchedule> findAllCourseScheduleBelowMinimumParticipants();

	/**
	 * Find all participant in course schedule
	 * 
	 * @param courseScheduleId
	 * @return
	 */
	Set<CourseParticipant> findAllParticipantByCourseScheduleId(Long courseScheduleId);

	/**
	 * find all members not enrolled in course schedule
	 * 
	 * @param courseParticipant
	 * @return
	 */
	Set<CourseParticipant> findAllMemberNotEnrolledByCourseScheduleId(CourseParticipant courseParticipant);

	/**
	 * Search feature - find all participant not enrolled in course schedule and in
	 * search criteria
	 * 
	 * @param searchForm
	 * @return
	 */
	Set<CourseParticipant> findMemberNotEnrolledByCourseScheduleId(SearchForm searchForm);

	/**
	 * Find available course schedule by course id
	 * 
	 * @param courseId
	 * @return
	 */
	Set<CourseSchedule> findCourseScheduleByCourseId(CourseSchedule courseSchedule);

	/** Finds the top 10 learners */
	List<TopLearnerForm> findTopLearner(ZonedDateTime fromDateTime, ZonedDateTime toDateTime);

	void updateSchedule(CourseParticipant courseParticipant);
}
