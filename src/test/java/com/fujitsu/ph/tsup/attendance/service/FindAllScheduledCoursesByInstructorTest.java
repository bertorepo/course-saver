package com.fujitsu.ph.tsup.attendance.service;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.domain.CourseScheduleDetail;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :findAllScheduledCoursesByInstructorTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                    | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 07/08/2020 |   WS) K.Abad, WS) J.Iwarat, WS) R.Ramos       | New Creation
//0.02    | 09/02/2020 |   WS) K.Abad, WS) J.Iwarat, WS) R.Ramos       | Update
//==================================================================================================
/**
 * <pre>
* It is the test of method of service class
* In this class, test the findAllScheduledCoursesByInstructor of service using mockito
 * </pre>
 * 
 * @version 0.02
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 */

@ExtendWith(SpringExtension.class)
public class FindAllScheduledCoursesByInstructorTest {

    @TestConfiguration
    static class TestContextConfiguration {

        /*
         * AttendanceService
         * 
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
     * FindAllScheduledCoursesByInstructor with valid values Call
     * attendanceDao.findAllScheduledCourses for data and call
     * attendanceService.findAllScheduledCoursesByInstructor to test the service.
     * 
     * <pre>
     */
    @Test
    void testFindAllScheduledCoursesByInstructor() {
        CourseSchedule courseSchedule = createCourseSchedule();
        CourseSchedule courseSchedule1 = createCourseSchedule1();
        Set<CourseSchedule> courseScheduleSet = new HashSet<>();
        courseScheduleSet.add(courseSchedule);
        courseScheduleSet.add(courseSchedule1);
        when(attendanceDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class), any(Long.class)))
                .thenReturn(courseScheduleSet);
        Set<CourseSchedule> courses = attendanceService
                .findAllScheduledCoursesByInstructor(ZonedDateTime.now().plusDays(5), ZonedDateTime.now(), 1L);

        assertNotNull(courses.size());
        assertEquals(2, courses.size());
        assertEquals(2L, courseSchedule.getCourseId());
        assertEquals("Lorenzo", courseSchedule.getInstructorLastName());
        assertEquals("UI", courseSchedule1.getCourseName());
        assertNotEquals("SpringBoot", courseSchedule1.getCourseName());

    }

    /**
     * <pre>
     * FindAllScheduledCoursesByInstructor with Error values Call
     * attendanceDao.findAllScheduledCourses for data and call
     * attendanceService.findAllScheduledCoursesByInstructor to test the error
     * catch.
     * 
     * <pre>
     */
    @Test
    void testFindAllScheduledCoursesByInstructor_Error() {
        when(attendanceDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class), any(Long.class)))
                .thenThrow(new DataRetrievalFailureException("error"));

        Exception attendanceException = assertThrows(IllegalArgumentException.class, () -> attendanceService
                .findAllScheduledCoursesByInstructor(ZonedDateTime.now().plusDays(5), ZonedDateTime.now(), 1L));

        String expectedMessage = "Can't find from date time, to date time and instructor Id.";
        String actualMessage = attendanceException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * <pre>
     * CourseSchedule Builder for the data test
     * 
     * <pre>
     */
    private CourseSchedule createCourseSchedule() {
        CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(5)).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);
        CourseSchedule courseSchedule = new CourseSchedule.Builder(1L, 2L, "SpringBoot", 3L, "Lorenzo", "Loyce", 4L,
                "TwoNeo", 30, 100, 80, "A".charAt(0)).addDetail(courseScheduleDetailSet).build();
        return courseSchedule;
    }

    /**
     * <pre>
     * CourseSchedule Builder for the data test
     * 
     * <pre>
     */
    private CourseSchedule createCourseSchedule1() {
        CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(2L, ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(5)).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);
        CourseSchedule courseSchedule1 = new CourseSchedule.Builder(5L, 6L, "UI", 7L, "De Guzman", "Genevieve", 8L,
                "TwoNeo", 30, 100, 80, "A".charAt(0)).addDetail(courseScheduleDetailSet).build();
        return courseSchedule1;
    }

}
