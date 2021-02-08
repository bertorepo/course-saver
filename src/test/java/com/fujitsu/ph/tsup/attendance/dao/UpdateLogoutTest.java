package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

import java.time.ZonedDateTime;

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
//Class Name   : UpdateLogoutTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/08/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//0.02    | 09/02/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | Update
//===============================================================================
/**
* <pre>
* The test case for AttendanceDao UpdateAttendanceLogout
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
class UpdateLogoutTest {

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
     * UpdateAttendanceLogoutTest with valid values
     * Call attendanceDao.updateLogout and test if course attendance is retrieving data from the database
     * <pre>
     */
    @Test
    void testUpdateLogout() {
        CourseAttendance courseAttendance = createCourseAttendance();

        attendanceDao.updateLogout(courseAttendance);
        
        assertEquals(1L, courseAttendance.getId());
        assertEquals(1L, courseAttendance.getCourseScheduleDetailId());
        assertEquals(1L, courseAttendance.getParticipantId());
        assertEquals(ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"), courseAttendance.getLogoutDateTime());
    }
    
    private CourseAttendance createCourseAttendance() {
        return new CourseAttendance.Builder(1L, 1L, 1L).logout(ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00")).build();
    }
}
