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
class FindCourseParticipantById {
    @TestConfiguration
    static class CourseServiceImplestContextConfiguration {
        @Bean
        EnrollmentService enrollmentService() {
            return new EnrollmentServiceImpl();
        }
    }

    @Autowired
    private EnrollmentService service;
    @MockBean
    private EnrollmentDao dao;
  //Test
    @Test
    void findCourseParticipantById() {
        CourseParticipant result = findParticipantId();
        when(dao.findCourseParticipantById(any(Long.class))).thenReturn(result);

        CourseParticipant courseParticipant = service.findCourseParticipantById(1L);
        assertEquals(result.getParticipantId(), courseParticipant.getParticipantId());
    }

    private CourseParticipant findParticipantId() {
        return new CourseParticipant.Builder(10L,1L).build();
    }
}