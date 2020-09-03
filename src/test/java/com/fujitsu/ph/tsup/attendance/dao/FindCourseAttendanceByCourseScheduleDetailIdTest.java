package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

import java.util.Set;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
//0.02    | 09/02/2020 |   WS) K.Abad, WS) J.Iwarat, WS) R.Ramos       | Update
//==================================================================================================
/**
* <pre>
* It is the test of method of service class
* In this class, test the findCourseAttendanceByCourseScheduleDetailId if retrieving data from the database
* </pre>
* 
* @version 0.02
* @author k.abad
* @author j.iwarat
* @author r.ramos
*/
@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FindCourseAttendanceByCourseScheduleDetailIdTest {

    /*
     * ScheduleDao as dependency
     */
    @Autowired
    private AttendanceDao attendanceDao;

    /*
     * Test Configuration
     */
    @TestConfiguration
    static class TestContextConfiguration {

        /*
         * AttendanceDao
         * @return AttendanceDaoImpl
         */
        @Bean
        public AttendanceDao attendanceDao() {
            return new AttendanceDaoImpl();
        }
    }

    /**
     * <pre>
     * findCourseAttendanceByCourseScheduleDetailId with valid values
     * Call attendanceDao.findCourseAttendanceByCourseScheduleDetailId and test if course schedule is retrieving data from the database
     * <pre>
     */
    @Test
    void testfindCourseAttendanceByCourseScheduleDetailId() {
        Set<CourseAttendance> courseAttendanceSet = attendanceDao.findCourseAttendanceByCourseScheduleDetailId(1L);
        
        for (CourseAttendance courseAttendance : courseAttendanceSet) {
            System.out.println("\nId:" + courseAttendance.getId());
            System.out.println("Course Schedule Detail Id:" + courseAttendance.getCourseScheduleDetailId());
            System.out.println("Course Name:" + courseAttendance.getCourseName());
            System.out.println("Instructor Name:" + courseAttendance.getInstructorName());
            System.out.println("Venue Name:" + courseAttendance.getVenueName());
            System.out.println("Participant Id:" + courseAttendance.getParticipantId());
            System.out.println("Participant Name:" + courseAttendance.getParticipantName());
            System.out.println("ScheduleStart DateTime:" + courseAttendance.getScheduleStartDateTime());
            System.out.println("ScheduleStart DateTime:" + courseAttendance.getScheduleStartDateTime());
            System.out.println("Duration:" + courseAttendance.getDuration());
            System.out.println("Login Date Time:" + courseAttendance.getLoginDateTime());
            System.out.println("Status:" + courseAttendance.getStatus());
            
            assertEquals("DE LEON, JC", courseAttendance.getInstructorName());
            assertEquals("DE GUZMAN, GENEVIEVE", courseAttendance.getParticipantName());
            assertEquals("Understanding SS",courseAttendance.getCourseName());
            assertEquals(2L, courseAttendance.getCourseScheduleDetailId());
            assertEquals("Two/Neo", courseAttendance.getVenueName());
        }        
        assertNotNull(courseAttendanceSet.size());
    }
}
