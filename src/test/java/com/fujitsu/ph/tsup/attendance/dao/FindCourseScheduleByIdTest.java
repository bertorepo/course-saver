package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;

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
//Class Name   :FindCourseScheduleByIdTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 07/06/2020 |  WS) K.Abad , WS) J.Iwarat, WS) R.Ramos         | New Creation
//0.02    | 09/02/2020 |  WS) K.Abad , WS) J.Iwarat, WS) R.Ramos         | Update
//==================================================================================================
/**
* <pre>
* The Find Course Schedule By Id Test for Dao.
* In this class, it test the method that will get data from database 
* 
* <pre>
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

class FindCourseScheduleByIdTest {
    
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
     * Finds the course schedule by id test
     * call attendanceDao.findCourseScheduleById and test if 
     * course schedule is retrieving data from the database
     * 
     * </pre>
     */    
    @Test
    void testFindCourseScheduleById() {    
        Set<CourseParticipant> courseParticipantSet = attendanceDao.findCourseScheduleById(2L);
    
        for(CourseParticipant courseParticipant : courseParticipantSet) {
            System.out.println("Course ID: " + courseParticipant.getId());
            System.out.println("Course Schedule ID: " + courseParticipant.getCourseScheduleId());
            System.out.println("Course Name: " + courseParticipant.getCourseName());
            System.out.println("Instructor Name: " + courseParticipant.getInstructorName());
            System.out.println("Venue Name: " + courseParticipant.getVenueName());
            System.out.println("Participant ID: " + courseParticipant.getParticipantId());
            System.out.println("Participant Name: " + courseParticipant.getParticipantName());
            System.out.println("Scheduled Start Date Time: "+courseParticipant.getScheduledStartDateTime());
            System.out.println("Scheduled End Date Time: "+courseParticipant.getScheduledEndDateTime());
            System.out.println("Duration: "+courseParticipant.getDuration());
            System.out.println("Registration Date: " + courseParticipant.getRegistrationDate());
            System.out.println("Email: " + courseParticipant.getEmail());
            System.out.println("Employee Number: " + courseParticipant.getEmployeeNumber());
        
            assertTrue(courseParticipant.getScheduledEndDateTime()
                    .isAfter(courseParticipant.getScheduledStartDateTime()));
            assertNotNull(courseParticipantSet.size());
            assertEquals("LORENZO, LOYCE", courseParticipant.getInstructorName());
            assertNotEquals("ONLINE", courseParticipant.getVenueName());
            assertNotSame("Understanding SS", courseParticipant.getCourseName());
        }      
    }
}