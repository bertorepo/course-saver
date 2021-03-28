package com.fujitsu.ph.tsup.enrollment.dao;

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

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;

@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FindAllEnrolledCoursesByParticipantId {

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
            void findAllEnrolledCourseByParticipantId() {
                ZonedDateTime fromDateTime =  ZonedDateTime.ofInstant
                        (Timestamp.valueOf("2020-06-01 08:30:00").toInstant(),ZoneId.of("UTC"));
                ZonedDateTime toDateTime = ZonedDateTime.ofInstant
                        (Timestamp.valueOf("2020-12-31 17:30:00").toInstant(),ZoneId.of("UTC"));
                Set<CourseParticipant> courseParticipantSet = enrollmentDao.findAllEnrolledCoursesByParticipantId(4L, fromDateTime, toDateTime);
                for(CourseParticipant courseParticipant : courseParticipantSet) {
                    System.out.println("Course Name: "+ courseParticipant.getCourseName());
                    System.out.println("Instructor Name: "+ courseParticipant.getInstructorName());
                    System.out.println("Participant Name: "+ courseParticipant.getParticipantName());
                    System.out.println("Registration Date: "+ courseParticipant.getRegistrationDate());
                }
                
            }  
        //Enrolled Courses Test


}
