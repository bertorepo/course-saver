package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;

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

//===============================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : FindAllScheduledCoursesByParticipantTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/08/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//0.02    | 09/02/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | Update
//===============================================================================
/**
* <pre>
* The test case for AttendanceDao SaveAttendance
* </pre>
* 
* @version 0.02
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/
@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FindAllScheduledCoursesByParticipantTest {
    /**
     * <pre>
     * Autowired annotation is used on properties, setters, and constructors.
     * 
     * <pre>
     * 
     */
    @Autowired
    private AttendanceDao attendanceDao;

    /**
     * <pre>
     * TestConfiguration annotation is used to define/override beans for unit tests
     * 
     * <pre>
     * 
     */
    @TestConfiguration
    static class TestContextConfiguration {

        /**
         * <pre>
         * Bean annotation tells that a method produces a bean to be managed by the
         * Spring container
         * 
         * <pre>
         * 
         */
        @Bean
        public AttendanceDao attendanceDao() {
            return new AttendanceDaoImpl();
        }
    }
    
    /**
     * <pre>
     * FindAllScheduledCoursesByParticipantTest with valid values
     * Call attendanceDao.findAllScheduledCoursesByParticipant and test if course schedule is retrieving data from the database
     * <pre>
     */
    @Test
    void testfindAllScheduledCoursesByParticipant() {
        ZonedDateTime fromDateTime = ZonedDateTime.ofInstant(Timestamp.valueOf("2020-01-01 08:30:00").toInstant(),
                ZoneId.of("UTC"));
        ZonedDateTime toDateTime = ZonedDateTime.ofInstant(Timestamp.valueOf("2020-12-31 17:30:00").toInstant(),
                ZoneId.of("UTC"));
        
        Set<CourseParticipant> courseParticipantSet = attendanceDao.findAllScheduledCoursesByParticipant(fromDateTime, toDateTime, 4L);
        
        for (CourseParticipant courseParticipant : courseParticipantSet) {
            System.out.println("Id: " + courseParticipant.getId());
            System.out.println("Course Schedule Id: " + courseParticipant.getCourseScheduleId());
            System.out.println("Instructor Name: " + courseParticipant.getInstructorName());
            System.out.println("Course Name: " + courseParticipant.getCourseName());
            System.out.println("Venue Name: " + courseParticipant.getVenueName());
            System.out.println("Participant Id: " + courseParticipant.getParticipantId());
            System.out.println(courseParticipant.getParticipantName());
            
            assertEquals(1L, courseParticipant.getId());
            assertEquals(1L, courseParticipant.getCourseScheduleId());
            assertEquals("Understanding UI", courseParticipant.getCourseName());
            assertEquals("LORENZO, LOYCE", courseParticipant.getInstructorName());
            assertEquals("EcoTower", courseParticipant.getVenueName());
            assertEquals(4L, courseParticipant.getParticipantId());
            assertEquals("RAMOS, RAMON", courseParticipant.getParticipantName());
        }
    }

}
