package com.fujitsu.ph.tsup.attendance.dao;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.attendance.model.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.model.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.model.CourseSchedule;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :AttendanceDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) J. Iwarat   | New Creation
//==================================================================================================
/**
 * <pre>
 * The data access interface for attendance related database access
 * <pre>
 * 
 * @version 0.01
 * @author j.iwarat
 */
public interface AttendanceDao {
    
    /**
     * Finds the scheduled courses starting from today onwards
     * @param fromDateTime
     * @param toDateTime
     * @param instructorId
     * @return
     */
    Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime,
            Long instructorId);

    /**
     * Finds the course schedule by id
     * @param id
     * @return
     */
    Set<CourseParticipant> findCourseScheduleById(Long id);

    /**
     * Finds the course participants by course schedule detail id
     * @param id
     * @return
     */
    Set<CourseAttendance> findCourseScheduleDetailParticipantsById(Long id);
    
    /**
     * Finds the course schedule by id
     * @param id
     * @return
     */
    Set<CourseAttendance> findCourseAttendanceByCourseScheduleDetailById(Long id);

    /**
     * Creates the course attendance
     * @param courseAttendance
     * @return
     */
    Set<CourseAttendance> saveAttendance(CourseAttendance courseAttendance);

    /**
     * Updates the course attendance
     * @param courseAttendance
     * @return
     */
    Set<CourseAttendance> updateAttendance(CourseAttendance courseAttendance);

    /**
     * Finds the course participants by course schedule detail id
     * @param id
     * @return
     */
    Set<CourseAttendance> findCourseScheduleDetailById(Long id);

}
