package com.fujitsu.ph.tsup.enrollment.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;


import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDaoImpl;

@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class test {
    @Autowired
    private EnrollmentDao enrollmentDao;

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public EnrollmentDao enrollmentDao() {
            return new EnrollmentDaoImpl();
        }
    }
    
    
    @Test
    void findCourseParticipantById() {
        ZonedDateTime fromDateTime = ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-01 10:00:00").toInstant(),
                ZoneId.of("UTC"));
        ZonedDateTime toDateTime = ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-06 10:00:00").toInstant(),
                ZoneId.of("UTC"));
        
        CourseParticipant courseParticipant = enrollmentDao.findCourseParticipantById(1L);
        
        System.out.println("\nId:" + courseParticipant.getId());
        System.out.println("Instructor Name:" + courseParticipant.getInstructorName());
        System.out.println("Participant Name:" + courseParticipant.getParticipantName());
        System.out.println("Reason:" + courseParticipant.getReason());
        System.out.println("Course Name:" + courseParticipant.getCourseName());
        System.out.println("Venue Name:" + courseParticipant.getVenueName());
        System.out.println("Participant Id:" + courseParticipant.getParticipantId());
        System.out.println("ScheduleStart DateTime:" + courseParticipant.getDeclineDate());
        System.out.println("Duration:" + courseParticipant.getRegistrationDate());
        System.out.println("Registration Date:" + courseParticipant.getRegistrationDate());

        CourseScheduleDetail courseSchedDet = courseParticipant.getCourseScheduleDetail();
        assertTrue((!courseSchedDet.getScheduledStartDateTime().isBefore(fromDateTime))
                && courseSchedDet.getScheduledStartDateTime().isBefore(toDateTime));
        System.out.println("Course Schedule ID: " + courseSchedDet.getCourseScheduleId());
        System.out.println("Scheduled Start Date Time: " + courseSchedDet.getScheduledStartDateTime());
        System.out.println("Scheduled End Date Time: " + courseSchedDet.getScheduledEndDateTime());
        System.out.println("Duration: " + courseSchedDet.getDuration());
      
        assertEquals("DE LEON, JC", courseParticipant.getInstructorName());
        assertNotEquals("DE GUZMAN, GENEVIEVE", courseParticipant.getParticipantName());
        assertEquals("Understanding SS", courseParticipant.getCourseName());
        assertEquals("Two/Neo", courseParticipant.getVenueName());
        assertNotNull(courseParticipant.getId());
    }

}
