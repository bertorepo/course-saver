package com.fujitsu.ph.tsup.enrollment.service;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :FindAllEnrolledCoursesByParticipantId.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) M. Lumontad       | New Creation
//0.01    | 07/08/2020 | WS) M. Lumontad       | Update
//==================================================================================================
/**
* <pre>
* FindAllEnrolledCoursesByParticipantId.java is a method under EnrollmentService
* <pre>
* 
* @version 0.01
* @author m.lumontad                          
*/

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
class FindAllEnrolledCoursesByParticipantIdTest {

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean

        EnrollmentService enrollmentService() {
            return new EnrollmentServiceImpl();
        }

    }

    @Autowired
    EnrollmentService enrollmentService;

    @MockBean
    EnrollmentDao enrollmentDao;

    @Test
    void findCourseEnrolledParticipantById() {
        CourseParticipant result = findEnrolledCourseByParticipantId();
        when(enrollmentDao.findCourseParticipantById(any(Long.class))).thenReturn(result);

        CourseParticipant courseParticipant = enrollmentService.findCourseParticipantById(1L);
        assertEquals(result.getParticipantId(), courseParticipant.getParticipantId());
    }

    @Test
    void findCourseEnrolledParticipantById_DaoNull() {
        CourseParticipant result = findEnrolledCourseByParticipantId();
        when(enrollmentDao.findCourseParticipantById(null)).thenReturn(result);

        CourseParticipant courseParticipant = enrollmentService.findCourseParticipantById(1L);
        assertEquals(result.getParticipantId(), courseParticipant.getParticipantId());
    }

    @Test
    void findCourseEnrolledParticipantById_Error() {
        CourseParticipant result = findEnrolledCourseByParticipantId();
        when(enrollmentDao.findCourseParticipantById(10L)).thenReturn(result);

        CourseParticipant courseParticipant = enrollmentService.findCourseParticipantById(1L);
        assertEquals(result.getParticipantId(), courseParticipant.getParticipantId());
    }

    private CourseParticipant findEnrolledCourseByParticipantId() {
        return new CourseParticipant.Builder(1L).build();
    }
}
