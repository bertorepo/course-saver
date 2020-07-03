package com.fujitsu.ph.tsup.enrollment.service;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :FindCourseScheduleById.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) M. Lumontad       | New Creation
//==================================================================================================
/**
* <pre>
* FindCourseScheduleById.java is a method under EnrollmentService
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
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
@ExtendWith(SpringExtension.class)
class FindCourseScheduleById {

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

    @Test
    void findCourseScheduleById() {
        CourseSchedule result = findCourseScheduleId();
        when(dao.findCourseScheduleById(any(Long.class))).thenReturn(result);
        
        CourseSchedule courseSchedule = service.findCourseScheduleById(1L);
        assertEquals(result.getCourseId() , courseSchedule.getCourseId());
    }

    private CourseSchedule findCourseScheduleId() {
        return new CourseSchedule.Builder(1L).build();
    }

}
