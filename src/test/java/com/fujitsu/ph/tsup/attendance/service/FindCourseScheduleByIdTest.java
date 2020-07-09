package com.fujitsu.ph.tsup.attendance.service;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;

import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;

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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :FindCourseScheduleByIdTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 07/08/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | New Creation
//0.02    | 07/08/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | Update
//==================================================================================================
/**
* <pre>
* The Find Course Schedule By Id Test for Service.
* In this class, it test the method using mockito 
* 
* <pre>
* 
* @version 0.02
* @author k.abad
* @author j.iwarat
* @author r.ramos
*/

@ExtendWith(SpringExtension.class)

public class FindCourseScheduleByIdTest {

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
     * Finds the course schedule and the participants using the course schedule Id Test
     * 
     * </pre>
     * 
     */
    @Test
    void testFindCourseScheduleById() {
        CourseParticipant courseParticipant = createCourseParticipant();
        
        Set<CourseParticipant> courseParticipantSet = new HashSet<CourseParticipant>();
        courseParticipantSet.add(courseParticipant);
        
        when(attendanceDao.findCourseScheduleById(any(Long.class)))
                .thenReturn(courseParticipantSet);
        
        assertNotNull(courseParticipantSet.size());
        assertEquals(1, courseParticipantSet.size());
        assertEquals(1L, courseParticipant.getId());
        assertEquals(2L, courseParticipant.getCourseScheduleId());
        assertEquals("Java", courseParticipant.getCourseName());
        assertEquals("Lorenzo, Loyce", courseParticipant.getInstructorName());
        assertEquals("WFH", courseParticipant.getVenueName());
        assertEquals(22L, courseParticipant.getParticipantId());
        assertEquals("Abad, Kenneth", courseParticipant.getParticipantName());
        assertEquals(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 
                courseParticipant.getScheduledStartDateTime());
        assertEquals(ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 
                courseParticipant.getScheduledEndDateTime());
        assertEquals(2.0f, courseParticipant.getDuration());
        assertEquals(ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 
                courseParticipant.getRegistrationDate());
        assertEquals("k.abad@fujitsu.com", courseParticipant.getEmail());
        assertEquals("220054288", courseParticipant.getEmployeeNumber());   
    }
    
    /**
     * <pre>
     * Finds the course schedule and the participants error using the course schedule Id Test
     * 
     * </pre>
     * 
     */
    @Test
    void testFindCourseScheduleById_Error() {
        when(attendanceDao.findCourseScheduleById(any(Long.class)))
                .thenThrow(new DataRetrievalFailureException("error"));
    
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                attendanceService.findCourseScheduleById(1L));
    
        String expectedMessage = "Course Schedule not found.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    /**
     * <pre>
     * Creates a builder method instance for the CourseParticipant
     * 
     * <pre>
     * 
     */
    private CourseParticipant createCourseParticipant() {
        return new CourseParticipant.Builder(1L, 2L, "Java", "Lorenzo, Loyce", "WFH", 22L, "Abad, Kenneth", 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 
                2.0f, ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), "k.abad@fujitsu.com", "220054288").build();
    }
}
