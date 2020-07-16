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
//==================================================================================================
/**
* <pre>
* The Find Course Absent Participant By Course Schedule Detail Id Test for Service.
* In this class, it test the method using mockito 
* 
* <pre>
* 
* @version 0.01
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
        CourseAttendance signUpOne = coursePresentOne();
        CourseAttendance signUpTwo = coursePresentTwo();
        
        CourseAttendance attendanceOne = coursePresentOne();
        CourseAttendance attendanceTwo = coursePresentTwo();
        CourseAttendance attendanceThree = courseAbsentOne();
        CourseAttendance attendanceFour = courseAbsentTwo();
         
        Set<CourseAttendance> signUpSet = new HashSet<>();
        signUpSet.add(signUpOne);
        signUpSet.add(signUpTwo);
        
        Set<CourseAttendance> courseAttendanceSet = new HashSet<>(); 
        courseAttendanceSet.add(attendanceOne);
        courseAttendanceSet.add(attendanceTwo);
        courseAttendanceSet.add(attendanceThree);
        courseAttendanceSet.add(attendanceFour);
        
        when(attendanceDao.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                .thenReturn(signUpSet);
        
        when(attendanceDao.findCourseScheduleDetailParticipantsById(any(Long.class)))
                .thenReturn(courseAttendanceSet);     
        
        Set<CourseAttendance> course = attendanceService
                .findCourseAbsentParticipantByCourseScheduleDetailId(1L);

        assertNotNull(course.size());
        assertNotEquals('A', attendanceOne.getStatus());
        assertNotSame(ZonedDateTime.now(), attendanceFour.getLoginDateTime());
        assertEquals(2, course.size());    
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
        assertEquals(0, courseAttendance.size());
        assertEquals("Juan", presentOne.getParticipantName());
        assertEquals(11L, presentTwo.getParticipantId());
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
        CourseAttendance courseSchedule = new CourseAttendance.Builder(12345L, 1000L, "java", 
                "Lorenzo, Loyce", "WFH", 22L, "Abad, Kenneth", ZonedDateTime.now(), ZonedDateTime.now()
                .plusDays(5), 2.0f, ZonedDateTime.now(), 'A').absent().build();
        return courseSchedule;
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
        CourseAttendance courseSchedule = new CourseAttendance.Builder(123456L, 1000L, "java", 
                "Lorenzo, Loyce", "WFH", 20L, "Velasco, Monica", ZonedDateTime.now(), ZonedDateTime.now()
                .plusDays(5), 2.0f, ZonedDateTime.now(), 'A').absent().build();
        return courseSchedule;
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
        CourseAttendance courseSchedule = new CourseAttendance.Builder(12345L, 1000L, "java", 
                "Lorenzo, Loyce", "WFH", 10L, "Juan", ZonedDateTime.now(), ZonedDateTime.now()
                .plusDays(5), 2.0f, ZonedDateTime.now(), 'P')
                .present(ZonedDateTime.now()).build();
        return courseSchedule;
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
        CourseAttendance courseSchedule = new CourseAttendance.Builder(12345L, 1000L, "java", 
                "Lorenzo, Loyce", "WFH", 11L, "Pedro", ZonedDateTime.now(), ZonedDateTime.now()
                .plusDays(5), 2.0f, ZonedDateTime.now(), 'P')
                .present(ZonedDateTime.now()).build();
        return courseSchedule;
    }
}
