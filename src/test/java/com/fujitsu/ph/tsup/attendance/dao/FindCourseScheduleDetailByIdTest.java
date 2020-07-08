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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :FindCourseScheduleDetailByIdTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 07/06/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | New Creation
//==================================================================================================
/**
* <pre>
* The Find Course Schedule Detail By Id Test for Dao.
* In this class, it test the method that will get data from database 
* 
* <pre>
* 
* @version 0.01
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/

@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class FindCourseScheduleDetailByIdTest {
    
    /*
     * AttendanceDao as dependency
     */
    @Autowired
    private AttendanceDao attendanceDao;
    
    /*
     * Test Configuration
     */
    @TestConfiguration
    static class TestContextConfiguration{
        
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
     * Finds the course schedule detail by id test
     * Call attendanceDao.findCourseScheduleDetailById and test if 
     * course schedule detail is retrieving data from the database
     * 
     * </pre>
     */   
    @Test
    void testFindCourseScheduleDetailById() {
        Set<CourseAttendance> courseAttendanceSet = attendanceDao.findCourseScheduleDetailById(2L);
        
        for(CourseAttendance courseAttendance : courseAttendanceSet) {
            System.out.println("ID: " + courseAttendance.getId());
            System.out.println("Course Schedule Detail ID: " + courseAttendance.getCourseScheduleDetailId());
            System.out.println("Course Name: " + courseAttendance.getCourseName());
            System.out.println("Instructor Name: " + courseAttendance.getInstructorName());
            System.out.println("Venue Name: " + courseAttendance.getVenueName());
            System.out.println("Participant ID: " + courseAttendance.getParticipantId());
            System.out.println("Participant Name: " + courseAttendance.getParticipantName());
            System.out.println("Scheduled Start Date Time: "+courseAttendance.getScheduleStartDateTime());
            System.out.println("Scheduled End Date Time: "+courseAttendance.getScheduleEndDateTime());
            System.out.println("Duration: "+courseAttendance.getDuration());
            System.out.println("Login Date Time: " + courseAttendance.getLoginDateTime());
            System.out.println("Status: " + courseAttendance.getStatus()); 
            
            assertTrue(!courseAttendance.getScheduleEndDateTime()
                    .isBefore(courseAttendance.getScheduleStartDateTime()));
            assertNotNull(courseAttendanceSet.size());
            assertEquals("DE LEON, JC", courseAttendance.getInstructorName());
            assertNotEquals("ONLINE", courseAttendance.getVenueName());
            assertNotSame("LORENZO, LOYCE", courseAttendance.getParticipantName());
        }
    }
}
