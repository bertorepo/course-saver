package com.fujitsu.ph.tsup.attendance.service;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;

//==================================================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : AttendanceService.java
//
//<<Modification History>>
//Version | Date       | Updated By                                       | Content
//--------+------------+--------------------------------------------------+-------------------------
//0.01    | 06/23/2020 | WS) K.Abad                                       | New Creation
//0.02    | 07/30/2020 | WS) R.Ramos                                      | Update
//0.03    | 08/26/2020 | WS) K.abad, WS) J.Iwarat, WS) R.Ramos            | Update
//==================================================================================================
/**
 * <pre>
 * It is the interface of attendance service
 * In this interface, it consists of the method required for the initial setting of the database
 * </pre>
 * 
 * @version 0.03
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 *
 */

public interface AttendanceService {
    
    /**
     * <pre>
     * Finds all scheduled courses based on the given date range
     * </pre>
     * 
     * @param fromDateTime
     * @param toDateTime
     * @param instructorId
     * @return Course Schedule Set
     */
    Set<CourseSchedule> findAllScheduledCoursesByInstructor(ZonedDateTime fromDateTime, ZonedDateTime toDateTime,
            Long instructorId);

    /**
     * <pre>
     * Finds the course schedule and the participants using the course schedule Id
     * </pre>
     * 
     * @param id
     * @return Course Participant Set
     */
    Set<CourseParticipant> findCourseScheduleById(Long id);

    /**
     * <pre>
     * Finds the course schedule and the participants and those who are attending using the course schedule Id
     * </pre>
     * 
     * @param id
     * @return Course Attendance Set
     */
    Set<CourseAttendance> findCourseAttendanceByCourseScheduleDetailId(Long id);

    /**
     * <pre>
     * Finds the course schedule and the participants and those who are attending using the course schedule Id
     * </pre>
     * 
     * @param courseAttendanceSet
     */
    void changeStatus(Set<CourseAttendance> courseAttendanceSet);

    /**
     * <pre>
     * Finds the course schedule and those participants who didn't attend using the course schedule Id
     * </pre>
     * 
     * @param id
     * @return Course Attendance Set
     */
    Set<CourseAttendance> findCourseAbsentParticipantByCourseScheduleDetailId(Long id);

    /**
     * <pre>
     * Finds the course schedule and the participants and those who are attending using the course schedule detail Id
     * </pre>
     * 
     * @param id
     * @return Course Attendance
     */
    Set<CourseAttendance> findCourseScheduleDetailById(Long id);

    /**
     * <pre>
     * log in for attend
     * </pre>
     * 
     * @param courseAttendance
     */
    void attendLogin(CourseAttendance courseAttendance);
    
    /**
     * <pre>
     * log out for attend
     * </pre>
     * 
     * @param courseAttendance
     */
    void attendLogout(CourseAttendance courseAttendance);
    
    /**
     * <pre>
     * Finds all scheduled courses based on the given date range
     * </pre>
     * 
     * @param fromDateTime
     * @param toDateTime
     * @param participantId
     * @return Course Participant Set
     */
    Set<CourseParticipant> findAllScheduledCoursesByParticipant(ZonedDateTime fromDateTime, ZonedDateTime toDateTime,
            Long participantId);
}
