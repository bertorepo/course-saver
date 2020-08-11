package com.fujitsu.ph.tsup.enrollment.dao;

//====================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Process
//Class Name   :FindAllScheduledCoursesTest.java
//
//<<Modification History>>
//Version | Date       | Updated By | Content
//--------+------------+-----------------------+------
//0.01    | 07/07/2020 |  WS) J.Yu  | New Creation
//====================================================

import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for findAllScheduledCourses method
 * 
 * @author J.Yu
 */
@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FindAllScheduledCoursesTest {
    
    @Autowired
    private EnrollmentDao enrollmentDao;
    
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public EnrollmentDao enrollmentDao() {
            return new EnrollmentDaoImpl();
        }
    }
    
    /**
     * Test case for findAllScheduledCourses method
     * 
     * @author J.Yu
     */
    @Test
    void testFindAllScheduledCourses() {
        ZonedDateTime fromDateTime =  ZonedDateTime.ofInstant
                (Timestamp.valueOf("2020-06-01 08:30:00").toInstant(),ZoneId.of("UTC"));
        ZonedDateTime toDateTime = ZonedDateTime.ofInstant
                (Timestamp.valueOf("2020-12-31 17:30:00").toInstant(),ZoneId.of("UTC"));
        Set<CourseSchedule> courseScheduleSet = enrollmentDao.findAllScheduledCourses
                (fromDateTime, toDateTime);
        
        for(CourseSchedule courseSchedule : courseScheduleSet) {
            System.out.println("Course Name: "+ courseSchedule.getCourseName());
            System.out.println("Instructor Name: "+courseSchedule.getInstructorFirstName()+" "+
                    courseSchedule.getInstructorLastName());           
            for(CourseScheduleDetail courseScheduleDetail : courseSchedule.getCourseScheduleDetail()) {
                assertTrue((!courseScheduleDetail.getScheduledStartDateTime().isBefore(fromDateTime)) 
                        && courseScheduleDetail.getScheduledStartDateTime().isBefore(toDateTime));
                System.out.println("Scheduled Start Date and Time: "+courseScheduleDetail
                        .getScheduledStartDateTime());
                System.out.println("Scheduled End Date and Time: "+courseScheduleDetail
                        .getScheduledEndDateTime());
                System.out.println("Duration: "+courseScheduleDetail.getDuration()+"\n");           
            }
        }
        System.out.println("Course Schedule Size:" + courseScheduleSet.size()+"\n");
        assertNotNull(courseScheduleSet.size());
    }
}