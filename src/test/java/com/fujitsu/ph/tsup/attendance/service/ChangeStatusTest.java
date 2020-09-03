package com.fujitsu.ph.tsup.attendance.service;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

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
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :ChangeStatusTest.java
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
* In this class, test the findAllScheduledCourses of service using mockito
 * </pre>
 * 
 * @version 0.02
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 */

@ExtendWith(SpringExtension.class)
class ChangeStatusTest {

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
     * ChangeStatus with valid values Call attendanceDao.saveAttendance and test if
     * it will a retrieve data from the database
     * 
     * <pre>
     */
    @Test
    void testChangeStatusSaveAttendance() {
        doThrow(new DataRetrievalFailureException("error")).when(attendanceDao).saveAttendance(null);

        CourseAttendance courseAttendance1 = createCourseAttendance1();
        CourseAttendance courseAttendance2 = createCourseAttendance2();

        Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>();
        courseAttendanceSet.add(courseAttendance1);
        courseAttendanceSet.add(courseAttendance2);

        attendanceService.changeStatus(courseAttendanceSet);

        assertNotNull(courseAttendanceSet.size());
        assertNotEquals('P', courseAttendance2.getStatus());
        assertNotSame('A', courseAttendance1.getStatus());
        assertEquals(2, courseAttendanceSet.size());
        assertEquals("De Leon, JC", courseAttendance2.getInstructorName());
        assertEquals("SS", courseAttendance2.getCourseName());
    }

    /**
     * <pre>
     * ChangeStatus with valid values Call attendanceDao.updateAttendance and test
     * if it will a retrieve data from the database
     * 
     * <pre>
     */
    @Test
    void testChangeStatusUpdateAttendance() {
        doThrow(new DataRetrievalFailureException("error")).when(attendanceDao).updateAttendance(null);

        CourseAttendance courseAttendance1 = createCourseAttendance1();
        CourseAttendance courseAttendance2 = createCourseAttendance2();

        Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>();
        courseAttendanceSet.add(courseAttendance1);
        courseAttendanceSet.add(courseAttendance2);

        attendanceService.changeStatus(courseAttendanceSet);

        assertNotNull(courseAttendanceSet.size());
        assertNotEquals('P', courseAttendance2.getStatus());
        assertNotSame('A', courseAttendance1.getStatus());
        assertEquals(2, courseAttendanceSet.size());
        assertEquals("Lorenzo, Loyce", courseAttendance1.getInstructorName());
        assertEquals("SS", courseAttendance2.getCourseName());
    }

    /**
     * <pre>
     * ChangeStatus with Error values Call attendanceDao.updateAttendance to test
     * the error catch.
     * 
     * <pre>
     */
    @Test
    void testChangeStatusUpdateAttendance_Error() {
        doThrow(new DataRetrievalFailureException("error")).when(attendanceDao)
                .updateAttendance(any(CourseAttendance.class));

        CourseAttendance courseAttendance1 = createCourseAttendance1();
        CourseAttendance courseAttendance2 = createCourseAttendance2();

        Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>();
        courseAttendanceSet.add(courseAttendance1);
        courseAttendanceSet.add(courseAttendance2);

        Exception courseAttendanceException = assertThrows(IllegalArgumentException.class,
                () -> attendanceService.changeStatus(courseAttendanceSet));

        String expectedMessage = "Cannot insert/update data";
        String actualMessage = courseAttendanceException.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    /**
     * <pre>
     * ChangeStatus with Error values Call attendanceDao.saveAttendance to test
     * the error catch.
     * 
     * <pre>
     */
    @Test
    void testChangeStatusSaveAttendance_Error() {
        doThrow(new DataRetrievalFailureException("error")).when(attendanceDao)
                .saveAttendance(any(CourseAttendance.class));

        CourseAttendance courseAttendance1 = createCourseAttendance1();
        CourseAttendance courseAttendance2 = createCourseAttendance2();

        Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>();
        courseAttendanceSet.add(courseAttendance1);
        courseAttendanceSet.add(courseAttendance2);

        Exception courseAttendanceException = assertThrows(NullPointerException.class,
                () -> attendanceService.changeStatus(null));

        String expectedMessage = null;
        String actualMessage = courseAttendanceException.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * <pre>
     * CourseAttendance Builder for the data test
     * 
     * <pre>
     */
    private CourseAttendance createCourseAttendance1() {
        CourseAttendance courseAttendance1 = new CourseAttendance.Builder(1L, 2L, "JAVA", "Lorenzo, Loyce", "TWO/Neo Bldg.", 3L, "Abad, Kenneth",
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 3.0f,
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 'P',
                "Course Description", "k.abad@fujitsu.com", 1L, "G3CC", "TRN123456").build();
        return courseAttendance1;
    }

    /**
     * <pre>
     * CourseAttendance Builder for the data test
     * 
     * <pre>
     */
    private CourseAttendance createCourseAttendance2() {
        CourseAttendance courseAttendance2 = new CourseAttendance.Builder(1L, 2L, "SS", "De Leon, JC", "TWO/Neo Bldg.", 3L, "Ramos, Ramon",
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 3.0f,
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 'A',
                "Course Description", "r.ramos@fujitsu.com", 1L, "G3CC", "TRN123456").build();
        return courseAttendance2;
    }

}
