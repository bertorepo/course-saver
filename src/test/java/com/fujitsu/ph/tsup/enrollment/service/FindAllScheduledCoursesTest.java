package com.fujitsu.ph.tsup.enrollment.service;

//====================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Process
//Class Name   :FindAllScheduledCoursesTest.java
//
//<<Modification History>>
//Version | Date       | Updated By | Content
//--------+------------+-----------------------+------
//0.01    | 07/06/2020 |  WS) J.Yu  | New Creation
//====================================================

import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * JUnit test class for findAllScheduledCourses method
 * 
 * @author J.Yu
 */
@ExtendWith(SpringExtension.class)
public class FindAllScheduledCoursesTest {

    @TestConfiguration
    static class EnrollmentServiceImplTestContextConfiguration {

        @Bean
        EnrollmentService attendanceService() {
            return new EnrollmentServiceImpl();
        }
    }

    @Autowired
    private EnrollmentService enrollmentService;

    @MockBean
    private EnrollmentDao enrollmentDao;
    
    /**
     * Test case for findAllScheduledCourses method
     * 
     * @author J.Yu
     */
    @Test
    void testFindAllScheduledCourses() {
        CourseSchedule courseSchedule = createCourseSchedule();
        Set<CourseSchedule> courseScheduleSet = new HashSet<>();
        courseScheduleSet.add(courseSchedule);
        when(enrollmentDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class)))
                .thenReturn(courseScheduleSet);
        Set<CourseSchedule> courseSchedSet = enrollmentService.findAllScheduledCourses
                (ZonedDateTime.now(), ZonedDateTime.now().plusDays(5));
        assertEquals(courseScheduleSet.size(), courseSchedSet.size());
    }
    
    /**
     * Test case for when findAllScheduledCourses method have error
     * 
     * @author J.Yu
     */
    @Test
    void testFindAllScheduledCoursesError() {
        when(enrollmentDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class)))
                .thenThrow(new DataRetrievalFailureException("No schedules found"));
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () -> 
        enrollmentService.findAllScheduledCourses(ZonedDateTime.now(), ZonedDateTime.now().plusDays(5))); 
        String expectedMessage = "Can't Access From Datetime and To Datetime";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    private CourseSchedule createCourseSchedule() {
        CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, ZonedDateTime.now(), 
                ZonedDateTime.now().plusDays(5)).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);
        
        return new CourseSchedule.Builder(1L, 1L, "SpringMVC", 1L, "Lorenzo", "Loyce", 1L, "TwoNeo", 1, 
                100, 50, "A".charAt(0)).addDetail(courseScheduleDetailSet).build();
    }
} 