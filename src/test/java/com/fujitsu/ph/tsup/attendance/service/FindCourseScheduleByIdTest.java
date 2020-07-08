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
//==================================================================================================
/**
* <pre>
* The Find Course Schedule By Id Test for Service.
* In this class, it test the method using mockito 
* 
* <pre>
* 
* @version 0.01
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
        Set<CourseParticipant> courseParticipant = new HashSet<CourseParticipant>();
        
        courseParticipant.add(new CourseParticipant.Builder(12345L, 1000L, "java", 
            "Lorenzo, Loyce", "WFH", 22L, "Abad, Kenneth", ZonedDateTime.now(), ZonedDateTime.now().plusDays(5), 2.0f, 
            ZonedDateTime.now(), "k.abad@fujitsu.com", "220054288").build());
        
        when(attendanceDao.findCourseScheduleById(any(Long.class))).thenReturn(courseParticipant);
        
        Set<CourseParticipant> course = attendanceService.findCourseScheduleById(any(Long.class));
        
        assertNotNull(course.size());
        assertEquals(1, course.size());
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
}
