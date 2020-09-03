package com.fujitsu.ph.tsup.attendance.service;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;
import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;

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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :FindAllScheduledCoursesByParticipantTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                       | Content
//--------+------------+--------------------------------------------------+-------------------------
//0.01    | 07/08/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | New Creation
//0.02    | 09/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | Update
//==================================================================================================
/**
* <pre>
* The Find All Scheduled Courses By Participant Test for Service.
* In this class, it test the method using mockito 
* 
* <pre>
* 
* @version 0.02
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/
@ExtendWith(SpringExtension.class)
class FindAllScheduledCoursesByParticipantTest {

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
     * FindAllScheduledCoursesByParticipant with valid values Call
     * attendanceDao.findAllScheduledCoursesByParticipant for data and call
     * attendanceService.findAllScheduledCoursesByParticipant to test the service.
     * 
     * <pre>
     */
    @Test
    void testFindAllScheduledCoursesByParticipant() {
        CourseParticipant courseParticipant = createCourseParticipant();
        Set<CourseParticipant> courseParticipantSet = new HashSet<>();
        courseParticipantSet.add(courseParticipant);

        when(attendanceDao.findAllScheduledCoursesByParticipant(any(ZonedDateTime.class), 
                any(ZonedDateTime.class), any(Long.class))).thenReturn(courseParticipantSet);
        
        Set<CourseParticipant> participants = attendanceService.findAllScheduledCoursesByParticipant(ZonedDateTime.now(), ZonedDateTime.now().plusDays(5),  1L);

        assertNotNull(participants);
        assertEquals(1, participants.size());
        assertEquals(1L, courseParticipant.getId());
        assertEquals(1L, courseParticipant.getCourseScheduleId());
        assertEquals("CourseName", courseParticipant.getCourseName());
        assertEquals("InstructorName", courseParticipant.getInstructorName());

    }
    
    /**
     * <pre>
     * FindAllScheduledCoursesByParticipant with Error values Call
     * attendanceDao.findAllScheduledCoursesByParticipant for data and call
     * attendanceService.findAllScheduledCoursesByParticipant to test the error
     * catch.
     * 
     * <pre>
     */
    @Test
    void testFindAllScheduledCoursesByParticipant_Error() {
        when(attendanceDao.findAllScheduledCoursesByParticipant(any(ZonedDateTime.class), 
                any(ZonedDateTime.class), any(Long.class))).thenThrow(new DataRetrievalFailureException("Error"));

        Exception attendanceException = assertThrows(IllegalArgumentException.class, () -> 
            attendanceService.findAllScheduledCoursesByParticipant(ZonedDateTime.now(), 
                    ZonedDateTime.now().plusDays(5),  1L));

        String expectedMessage = "Can't find from date time, to date time and participant Id.";
        String actualMessage = attendanceException.getMessage();
        assertEquals(expectedMessage, actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    private CourseParticipant createCourseParticipant() {
        return new CourseParticipant.Builder(1L, 1L, "CourseName", "InstructorName", "VenueName", 1L, "ParticipantName", 
                ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"), ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"), 
                1.0f, ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"), "Email", "EmployeeNumber123", 1L, "DepartmentName").build();
    }

}
