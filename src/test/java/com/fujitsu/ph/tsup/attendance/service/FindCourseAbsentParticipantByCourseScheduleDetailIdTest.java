package com.fujitsu.ph.tsup.attendance.service;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :FindCourseAbsentParticipantByCourseScheduleDetailIdTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                       | Content
//--------+------------+--------------------------------------------------+-------------------------
//0.01    | 07/08/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | New Creation
//0.02    | 09/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | Update
//==================================================================================================
/**
* <pre>
* The Find Course Absent Participant By Course Schedule Detail Id Test for Service.
* In this class, it test the method using mockito 
* 
* <pre>
* 
* @version 0.02
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/

@ExtendWith(SpringExtension.class)
public class FindCourseAbsentParticipantByCourseScheduleDetailIdTest {
    
    /*
     * Test Configuration
     */
    @TestConfiguration
    static class TestContextConfiguration {
        
        /*
         * AttendanceService
         * @return AttendanceServiceImpl
         */
        @Bean
        AttendanceService attendanceService() {           
            return new AttendanceServiceImpl();
        }
    }
    
    /*
     * AttendanceService as dependency
     */
    @Autowired
    private AttendanceService attendanceService;
    
    /*
     * add mock objects to the Spring application context
     */
    @MockBean
    private AttendanceDao attendanceDao;

    /**
     * <pre>
     * Finds the course participants who signed up but didn't attend using the course schedule detail Id
     * 
     * </pre>
     * 
     */
    @Test
    void testFindCourseAbsentParticipantByCourseScheduleDetailId_SignedUpNotAttended() {
        CourseAttendance attendanceOne = coursePresentOne();
        CourseAttendance attendanceTwo = coursePresentTwo();
        CourseAttendance attendanceThree = courseAbsentOne();
        CourseAttendance attendanceFour = courseAbsentTwo();
         
        Set<CourseAttendance> signUpSet = new HashSet<>();
        signUpSet.add(attendanceOne);
        signUpSet.add(attendanceTwo);
        signUpSet.add(attendanceThree);
        signUpSet.add(attendanceFour);
        
        Set<CourseAttendance> courseAttendanceSet = new HashSet<>(); 
        courseAttendanceSet.add(attendanceOne);
        courseAttendanceSet.add(attendanceTwo);
         
        when(attendanceDao.findCourseScheduleDetailParticipantsById(any(Long.class)))
        .thenReturn(signUpSet);
        
        when(attendanceDao.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                .thenReturn(courseAttendanceSet);

        Set<CourseAttendance> course = attendanceService
                .findCourseAbsentParticipantByCourseScheduleDetailId(1L);

        assertNotNull(course.size());
        assertNotEquals('A', attendanceOne.getStatus());
        assertNotEquals('A', attendanceTwo.getStatus());
        assertNotSame(ZonedDateTime.now(), attendanceFour.getLoginDateTime());
        assertEquals(2, course.size());
        assertEquals('A', attendanceThree.getStatus());
        assertEquals('A', attendanceFour.getStatus());
        assertEquals('P', attendanceTwo.getStatus());
        assertEquals("Abad, Kenneth", attendanceThree.getParticipantName());
        assertEquals(20L, attendanceFour.getParticipantId());
    }
    
    /**
     * <pre>
     * Finds the course participants who signed up and attend using the course schedule detail Id test
     * 
     * </pre>
     * 
     */
    @Test
    void testFindCourseAbsentParticipantByCourseScheduleDetailId_SignedUpAttended() {
        CourseAttendance presentOne = coursePresentOne();
        CourseAttendance presentTwo = coursePresentTwo();
        
        CourseAttendance loggedInOne = coursePresentOne();
        CourseAttendance loggedInTwo = coursePresentTwo();
        
        Set<CourseAttendance> presentSet = new HashSet<>();
        presentSet.add(presentOne);
        presentSet.add(presentTwo);
        
        Set<CourseAttendance> loggedInSet = new HashSet<>();
        loggedInSet.add(loggedInOne);
        loggedInSet.add(loggedInTwo);
        
        when(attendanceDao.findCourseScheduleDetailParticipantsById(any(Long.class)))
                .thenReturn(presentSet);
        
        when(attendanceDao.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                .thenReturn(loggedInSet);
        
        Set<CourseAttendance> courseAttendance = attendanceService
                .findCourseAbsentParticipantByCourseScheduleDetailId(1L);
        
        assertNotNull(courseAttendance.size());
        assertNotEquals('A', presentOne.getStatus());
        assertNotSame('A', presentTwo.getLoginDateTime());
        assertEquals(1, courseAttendance.size());
        assertEquals("Juan", presentOne.getParticipantName());
        assertEquals(20L, presentTwo.getParticipantId());
    }
    
    /**
     * <pre>
     * Finds the course participants error using the course schedule detail Id test
     * 
     * </pre>
     * 
     */
    @Test
    void testFindCourseAbsentParticipantByCourseScheduleDetailId_Error() {
        when(attendanceDao.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                .thenThrow(new DataRetrievalFailureException("error"));
        
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                attendanceService.findCourseAbsentParticipantByCourseScheduleDetailId(1L));
        
        String expectedMessage = "No record found.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * <pre>
     * Creates an instance of the CourseAttendance using the given builder class.
     * 
     * <pre>
     * 
     * @return courseSchedule
     */
    private CourseAttendance courseAbsentOne() {
        CourseAttendance courseAttendance = new CourseAttendance.Builder(12345L, 1L, "java", 
                "Lorenzo, Loyce", "WFH", 22L, "Abad, Kenneth",ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 2.0f, 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 'A',
                "Course Description", "k.abad@fujitsu.com", 1L, "G3CC", "TRN123456").absent().build();
        return courseAttendance;
    }

    /**
     * <pre>
     * Creates an instance of the CourseAttendance using the given builder class.
     * 
     * <pre>
     * 
     * @return courseSchedule
     */
    private CourseAttendance courseAbsentTwo() {
        CourseAttendance courseAttendance = new CourseAttendance.Builder(12345L, 1L, "java", 
                "Lorenzo, Loyce", "WFH", 20L, "Velasco, Monica", ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 2.0f, 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 'A',
                "Course Description", "k.abad@fujitsu.com", 1L, "G3CC", "TRN123456").absent().build();
        return courseAttendance;
    }
    
    /**
     * <pre>
     * Creates an instance of the CourseAttendance using the given builder class.
     * 
     * <pre>
     * 
     * @return courseSchedule
     */
    private CourseAttendance coursePresentOne() {
        CourseAttendance courseAttendance = new CourseAttendance.Builder(12345L, 1L, "java", 
                "Lorenzo, Loyce", "WFH", 20L, "Juan", ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 2.0f, 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 'A',
                "Course Description", "k.abad@fujitsu.com", 1L, "G3CC", "TRN123456")
                .present(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00")).build();
        return courseAttendance;
    }
    
    /**
     * <pre>
     * Creates an instance of the CourseAttendance using the given builder class.
     * 
     * <pre>
     * 
     * @return courseSchedule
     */
    private CourseAttendance coursePresentTwo() {
        CourseAttendance courseAttendance = new CourseAttendance.Builder(12345L, 1L, "java", 
                "Lorenzo, Loyce", "WFH", 20L, "Pedro", ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 2.0f, 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 'A',
                "Course Description", "k.abad@fujitsu.com", 1L, "G3CC", "TRN123456")
                .present(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00")).build();
        return courseAttendance;
    }
}
