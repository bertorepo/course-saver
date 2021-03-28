package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.domain.CourseScheduleDetail;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :FindAllScheduledCoursesTest.java
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
* In this class, test the findAllScheduledCourses if retrieving data from the database
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
class FindAllScheduledCoursesTest {
    
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
     * testfindAllScheduledCourses with valid values
     * Call attendanceDao.findAllScheduledCourses and test if course schedule is retrieving data from the database
     * <pre>
     */
    @Test
    void testfindAllScheduledCourses() {
        ZonedDateTime fromDateTime = ZonedDateTime.ofInstant(Timestamp.valueOf("2020-08-02 08:00:00").toInstant(),
                ZoneId.of("UTC"));
        ZonedDateTime toDateTime = ZonedDateTime.ofInstant(Timestamp.valueOf("2020-08-02 12:00:00").toInstant(),
                ZoneId.of("UTC"));

        Set<CourseSchedule> courseScheduleSet = attendanceDao.findAllScheduledCourses(fromDateTime, toDateTime, 4L);

        for (CourseSchedule courseSchedule : courseScheduleSet) {
            System.out.println("Course Name:" + courseSchedule.getCourseName());
            System.out.println("Instructor First Name:" + courseSchedule.getInstructorFirstName());
            System.out.println("Instructor Last Name:" + courseSchedule.getInstructorLastName());
            System.out.println("Max Allowed:" + courseSchedule.getMaxAllowed());
            System.out.println("Min Required:" + courseSchedule.getMinRequired());
            System.out.println("Status:" + courseSchedule.getStatus());
            System.out.println("Total Participant:" + courseSchedule.getTotalParticipants());
            System.out.println("Venue Name:" + courseSchedule.getVenueName());
            System.out.println("Course Id:" + courseSchedule.getCourseId());
            System.out.println("Id:" + courseSchedule.getId());
            System.out.println("Instructor Id:" + courseSchedule.getInstructorId());
            System.out.println("Venue Id:" + courseSchedule.getVenueId());

            assertEquals("GENEVIEVE", courseSchedule.getInstructorFirstName());
            assertNotEquals("LORENZO", courseSchedule.getInstructorLastName());
            assertEquals("Goal Setting",courseSchedule.getCourseName());
            assertEquals(3L, courseSchedule.getInstructorId());
            assertEquals("Online", courseSchedule.getVenueName());

            for (CourseScheduleDetail courseSchedDet : courseSchedule.getCourseScheduleDetail()) {
                assertTrue((!courseSchedDet.getScheduledStartDateTime().isBefore(fromDateTime))
                        && courseSchedDet.getScheduledStartDateTime().isBefore(toDateTime));
                System.out.println("Course Schedule ID: " + courseSchedDet.getCourseScheduleId());
                System.out.println("Scheduled Start Date Time: " + courseSchedDet.getScheduledStartDateTime());
                System.out.println("Scheduled End Date Time: " + courseSchedDet.getScheduledEndDateTime());
            }
        }
            assertNotNull(courseScheduleSet.size());
    }
    
}
