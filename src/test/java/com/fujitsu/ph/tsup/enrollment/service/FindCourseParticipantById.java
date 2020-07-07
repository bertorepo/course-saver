package com.fujitsu.ph.tsup.enrollment.service;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :FindCourseParticipantById.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) M. Lumontad       | New Creation
//0.01    | 07/07/2020 | WS) M. Lumontad       | Update
//==================================================================================================
/**
* <pre>
* FindCourseParticipantById.java is a method under EnrollmentService
* <pre>
* 
* @version 0.01
* @author m.lumontad                          
*/

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;

@ExtendWith(SpringExtension.class)
class FindCourseParticipantById {

    /**
     * Used to define Bean
     */
    @TestConfiguration
    static class CourseServiceImplestContextConfiguration {

        /**
         * Test Specific Bean
         */
        @Bean
        EnrollmentService enrollmentService() {
            return new EnrollmentServiceImpl();
        }
    }

    /**
     * Test Specific Bean
     */
    @Autowired
    private EnrollmentService service;

    /**
     * Allows to mock classes or interface,
     * to record or verify behaviors
     */
    @MockBean
    private EnrollmentDao dao;

    /**
     * JUnit Assertion without Errors in Mock Bean data and Test Specific Data 
     */
    @Test
    void findCourseParticipantById() {
        CourseParticipant result = findParticipantId();
        when(dao.findCourseParticipantById(10L)).thenReturn(result);

        CourseParticipant courseParticipant = service.findCourseParticipantById(10L);
        assertEquals(result.getParticipantId(), courseParticipant.getParticipantId());
    }


    /**
     * JUnit Assertion with Errors in Mock Bean data and Test Specific Data 
     */
    @Test
    void findCourseParticipantById_Error() {

        CourseParticipant result = findParticipantId();
        when(dao.findCourseParticipantById(20L)).thenReturn(result);

        CourseParticipant courseParticipant = service.findCourseParticipantById(10L);
        assertEquals(result.getParticipantId(), courseParticipant.getParticipantId());

    }

    /**
     * JUnit Assertion with Errors in Mock Bean data and Test Specific Data
     * the return value is null 
     */
    @Test
    void findCourseParticipantById_ReturnNull() {

        CourseParticipant result = findParticipantId();
        when(dao.findCourseParticipantById(10L)).thenReturn(null);

        CourseParticipant courseParticipant = service.findCourseParticipantById(10L);
        assertEquals(result.getParticipantId(), courseParticipant.getParticipantId());

    }

    
    /**
     * JUnit Assertion with Errors in Mock Bean data and Test Specific Data
     * the Mock Bean Value for Participant ID is null 
     */
 
    @Test
    void findCourseParticipantById_DaoNull() {

        CourseParticipant result = findParticipantId();
        when(dao.findCourseParticipantById(null)).thenReturn(result);

        CourseParticipant courseParticipant = service.findCourseParticipantById(10L);
        assertEquals(result.getParticipantId(), courseParticipant.getParticipantId());

    }

    private CourseParticipant findParticipantId() {
        return new CourseParticipant.Builder(10L, 10L).build();
    }
}