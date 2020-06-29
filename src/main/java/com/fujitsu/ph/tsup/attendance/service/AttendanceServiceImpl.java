package com.fujitsu.ph.tsup.attendance.service;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;

import java.time.ZonedDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

//==================================================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : AttendanceServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By                                              | Content
//--------+------------+---------------------------------------------------------+-----------------
//0.01    | 06/23/2020 | WS) K.Abad, WS) M.Angara, WS) J.Iwarat, WS) R.Ramos     | New Creation
//==================================================================================================
/**
 * <pre>
 * It is the implementation of attendance service
 * In this class, it implements the AttendanceService class for the initial setting of the database
 * </pre>
 * 
 * @version 0.01
 * @author k.abad
 * @author m.angara
 * @author j.iwarat
 * @author r.ramos
 * 
 */

@Service
public class AttendanceServiceImpl implements AttendanceService {

    /*
     * AttendanceDao
     */
    @Autowired
    private AttendanceDao attendanceDao;

    /**
     * <pre>
     * Finds all scheduled courses based on the given date range Call
     * attendanceDao.findAllScheduledCourses using the given fromDateTime,
     * toDateTime, instructor id and return the result
     * 
     * <pre>
     * 
     * @param fromDateTime
     * @param toDateTime
     * @param instructorId
     * @return CourseScheduleSet
     */

    @Override
    public Set<CourseSchedule> findAllScheduledCoursesByInstructor(ZonedDateTime fromDateTime, ZonedDateTime toDateTime,
            Long instructorId) {
        try {
            return attendanceDao.findAllScheduledCourses(fromDateTime, toDateTime, instructorId);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Can't find from date time, to date time and instructor Id.", e);
        }
        
    }

    /**
     * <pre>
     * Finds course schedule based on the given course id Call
     * attendanceDao.findCourseScheduleById using the given course id and return the
     * result
     * 
     * <pre>
     * 
     * @param id
     * @return CourseParticipant Set
     */
    @Override
    public Set<CourseParticipant> findCourseScheduleById(Long id) {
        try {
            return attendanceDao.findCourseScheduleById(id);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Course Schedule not found.", e);
        }
    }

    /**
     * <pre>
     * Finds the course schedule and the participants and those who are attending
     * using the course schedule Id Get those you signed up Call the
     * attendanceDao.findCourseScheduleDetailParticipantsById using the given id and
     * return the result Get those you logged-in Call the
     * attendanceDao.findCourseAttendanceByCourseScheduleDetailId using the given id
     * and return the result Merge the the results of the previous steps. Ensure
     * that there are no duplicate participants. If there are duplicate
     * participants, retain the record from Step 2.1 Return the result of the merge
     * course attendance set.
     * 
     * <pre>
     * 
     * @param id
     * @return CourseAttendance Set
     */

    @Override
    public Set<CourseAttendance> findCourseAttendanceByCourseScheduleDetailId(Long id) {
        Set<CourseAttendance> signedUp = attendanceDao.findCourseScheduleDetailParticipantsById(id);
        Set<CourseAttendance> signedUpTwo = attendanceDao.findCourseScheduleDetailParticipantsById(id);
        Set<CourseAttendance> loggedIn = attendanceDao.findCourseAttendanceByCourseScheduleDetailId(id);
        try {
            if (signedUp == null || signedUp.isEmpty()) {
                if (loggedIn == null || loggedIn.isEmpty()) {
                    throw new IllegalArgumentException("Employee not found.");
                }
            }
            if (signedUp.equals(signedUpTwo)) {
                throw new IllegalArgumentException("Duplicate value found.");
            }
            return loggedIn;

        } catch (DataAccessException e) {
            throw new IllegalArgumentException("No record found.", e);
        }
    }

    /**
     * <pre>
     * Finds the course schedule and the participants and those who are attending
     * using the course schedule Id For each courseAttendance in the Set if
     * courseAttendance.id = null, Call attendanceDao.saveAttendance using the
     * courseAttendance otherwise, Call attendanceDao.updateAttendance using the
     * courseAttendance
     * 
     * <pre>
     * 
     * @param courseAttendanceSet
     */
    @Override
    public void changeStatus(Set<CourseAttendance> courseAttendanceSet) {
        try {
            if (((CourseAttendance) courseAttendanceSet).getId() == null) {
                throw new IllegalArgumentException("Id not found.");
            }
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("No record found.", e);
        }
    }

    /**
     * <pre>
     * Finds Course absent participant Call
     * attendanceDao.findCourseScheduleDetailParticipantsById and return the result
     * Call attendanceDao.findCourseAttendanceByCourseScheduleDetailId and return
     * the result Merged the results and get only the participants that have
     * signed-up but didn't logged-in
     * 
     * <pre>
     * 
     * @param id
     * @return CourseAttendance Set
     */
    @Override
    public Set<CourseAttendance> findCourseAbsentParticipantByCourseScheduleDetailId(Long id) {
        try {
            Set<CourseAttendance> signedUp = attendanceDao.findCourseScheduleDetailParticipantsById(id);
            Set<CourseAttendance> loggedIn = attendanceDao.findCourseAttendanceByCourseScheduleDetailId(id);

            if (signedUp.isEmpty() || signedUp == null || loggedIn.isEmpty() || loggedIn == null) {
                throw new IllegalArgumentException("Employee not found.");
            }
            signedUp.removeAll(loggedIn);
            return signedUp;
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("No record found.", e);
        }
    }

    /**
     * <pre>
     * Finds the course schedule and the participants and those who are attending
     * using the course schedule detail Id Call the
     * attendanceDao.findCourseScheduleDetailById using the given id and return the
     * result
     * 
     * <pre>
     * 
     * @param id
     * @return courseAttendance
     */
    @Override
    public Set<CourseAttendance> findCourseScheduleDetailById(Long id) {
        Set<CourseAttendance> courseAttendance = attendanceDao.findCourseScheduleDetailById(id);
        try {
            if (((CourseAttendance) courseAttendance).getId() == 0);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("No records found.", e);
        }

        return courseAttendance;
    }

    /**
     * <pre>
     * Finds the course schedule and the participants and those who are attending
     * using the course schedule detail Id Call the attendanceDao.saveAttendance
     * using the courseAttendance
     * 
     * <pre>
     * 
     * @param courseAttendance
     */

    @Override
    public void attend(CourseAttendance courseAttendance) {
        attendanceDao.saveAttendance(courseAttendance);
        try {
            if (courseAttendance.getId() == null);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("No records found.", e);
        }
    }
}