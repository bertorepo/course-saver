package com.fujitsu.ph.tsup.attendance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :FindCourseAttendanceByCourseScheduleDetailIdTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                    | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 07/08/2020 |   WS) K.Abad, WS) J.Iwarat, WS) R.Ramos       | New Creation
//==================================================================================================
/**
* <pre>
* It is the test of method of service class
* In this class, test the FindCourseAttendanceByCourseScheduleDetailId of service using mockito
* </pre>
* 
* @version 0.01
* @author k.abad
* @author j.iwarat
* @author r.ramos
*/

@ExtendWith(SpringExtension.class)
public class FindCourseAttendanceByCourseScheduleDetailIdTest {
    
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
     * Mock bean of AttendanceDao
     */
    @MockBean
    private AttendanceDao attendanceDao;
    
    /**
     * <pre>
     * FindCourseAttendanceByCourseScheduleDetailId with valid values Call
     * attendanceDao.findCourseAttendanceByCourseScheduleDetailId for data and call
     * attendanceService.findCourseAttendanceByCourseScheduleDetailId to test the service.
     * 
     * <pre>
     */
    @Test
    void testFindCourseAttendanceByCourseScheduleDetailId() {
        CourseAttendance courseAttendance1 = createCourseAttendance1();
        CourseAttendance courseAttendance2 = createCourseAttendance2();

        Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>();
        courseAttendanceSet.add(courseAttendance1);
        courseAttendanceSet.add(courseAttendance2);

        when(attendanceDao.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                .thenReturn(courseAttendanceSet);

        Set<CourseAttendance> coursesAttendance = attendanceService.findCourseAttendanceByCourseScheduleDetailId(7L);

        assertNotNull(coursesAttendance.size());
        assertNotEquals('P', courseAttendance2.getStatus());
        assertNotSame('A', courseAttendance1.getStatus());
        assertEquals(1, coursesAttendance.size());
        assertEquals("De Leon, John Carlo", courseAttendance1.getInstructorName());
        assertEquals("SS", courseAttendance2.getCourseName());
    }

    /**
     * <pre>
     * FindCourseAttendanceByCourseScheduleDetailId with Error values Call
     * attendanceDao.findCourseAttendanceByCourseScheduleDetailId for data and call
     * attendanceService.findCourseAttendanceByCourseScheduleDetailId to test the error
     * catch.
     * 
     * <pre>
     */
    @Test
    void testFindCourseAttendanceByCourseScheduleDetailId_Error() {
        when(attendanceDao.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                .thenThrow(new DataRetrievalFailureException("error"));

        Exception attendanceException = assertThrows(IllegalArgumentException.class,
                () -> attendanceService.findCourseAttendanceByCourseScheduleDetailId(1L));

        String expectedMessage = "No record found.";
        String actualMessage = attendanceException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    /**
     * <pre>
     * CourseAttendance Builder for the data test
     * 
     * <pre>
     */
    private CourseAttendance createCourseAttendance1() {
        CourseAttendance courseAttendance1 = new CourseAttendance.Builder(4L, 2L, "SS", "De Leon, John Carlo", "TwoNeo",
                3L, "Abad, Kenneth", ZonedDateTime.now(), ZonedDateTime.now().plusDays(5), 2.0f,
                ZonedDateTime.now(), "P".charAt(0)).build();
        return courseAttendance1;
    }

    /**
     * <pre>
     * CourseAttendance Builder for the data test
     * 
     * <pre>
     */
    private CourseAttendance createCourseAttendance2() {
        CourseAttendance courseAttendance2 = new CourseAttendance.Builder(4L, 2L, "SS", "De Leon, John Carlo", "TwoNeo",
                3L, "Ramon, Ramos", ZonedDateTime.now(), ZonedDateTime.now().plusDays(5), 2.0f,
                ZonedDateTime.now(), "A".charAt(0)).build();
        return courseAttendance2;
    }
}
