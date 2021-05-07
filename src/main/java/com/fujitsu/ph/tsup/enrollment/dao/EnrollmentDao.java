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
//0.01    | 06/24/2020 |  WS) J.Yu		| New Creation
//0.02    | 09/14/2020 |  WS) J.Yu		| Update
//0.03	  | 04/19/2021 |  WS) M.Atayde  | Update
//====================================================

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.enrollment.model.SearchForm;
import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
import com.fujitsu.ph.tsup.enrollment.model.Certificate;
//import com.fujitsu.ph.tsup.enrollment.domain.Participant;
import java.time.ZonedDateTime;
import java.util.List;
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

    /**
     * Find All Active Course Schedule
     * @return
     */
    Set<CourseSchedule> findAllActiveCourseSchedule();
    
    /**
     * Cancel Course Schedule By Id
     * @param courseScheduleSet
     */
    void cancelCourseSchedulesById(Set<CourseSchedule> courseScheduleSet);

    /**
     * Find Course Schedule that are below Minimum Participants
     * @return
     */
    Set<CourseSchedule> findAllCourseScheduleBelowMinimumParticipants();
    
    /** Finds the top 10 learners of the month */
    List<TopLearnerForm> findTopLearnerByMonth();
    
    /** Finds the top 10 learners of the quarter */
    List<TopLearnerForm> findTopLearnerByQuarter();
    
    /**
     * Find CourseSchedule By Month
     * @return
     */
    Set<CourseSchedule> findAllCourseScheduleByMonth();
    
    /**
     * Find CourseSchedule By Quarter
     * @return
     */
    Set<CourseSchedule> findAllCourseScheduleByQuarter();
    
    /**
     * Reschedule Course Schedule Detail
     * @param courseScheduleDetail
     */
    void reschedule(CourseScheduleDetail courseScheduleDetail);

    /**
     * Find All Enrolled Participant in Course Schedule
     * @param courseParticipant
     * @return
     */
    Set<CourseParticipant> findAllParticipantByCourseScheduleId(Long courseParticipant);

    /**
     * Find all Member that are not yet enrolled in Course Schedule
     * @param courseSchedule
     * @return
     */
    Set<CourseParticipant> findAllMemberNotEnrolledByCourseScheduleId(CourseParticipant courseSchedule);
    
    /**
     * Search Feature / Find All Member that are not yet enrolled and in criteria of SearchForm
     * @param searchForm
     * @return
     */
    Set<CourseParticipant> findMemberNotEnrolledByCourseScheduleId(SearchForm searchForm);
    
    /**
     * Find available course schedule by course id
     * @param courseId
     * @return
     */
    Set<CourseSchedule> findCourseScheduleByCourseId(CourseSchedule courseSchedule);
    
    
    void updateCourseParticipant(CourseParticipant courseParticipant);
	/**
	 * <pre>
	 *
	 *viewEnrolledMembers
	 *
	 *@author c.delapena
	 * <pre>
	 */
//	List<Participant> viewEnrolledMembers(Long id);


	/**
	 * <pre>
	 *
	 *addEnrolledMembers
	 *
	 *@author c.delapena
	 * <pre>
	 */
//	Integer addEnrolledMembersById(Participant participant);
    
    /**
	 * <pre>
	 *
	 *uploads the certificate to the database
	 *@param certificate
	 *@author m.atayde
	 *
	 * <pre>
	 */
    void uploadCertificate(Certificate certificate);

    /**
	 * <pre>
	 *
	 *finds and only enables the upload certificate button for mandatory courses from the database
	 *@param userId
	 *@param courseId
	 *@author m.atayde
	 *
	 * <pre>
	 */
    public List<String> findCourseScheduleIfMandatory();
		String findCertificateName(long userId, long courseId);
   
}
