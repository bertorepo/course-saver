package com.fujitsu.ph.tsup.enrollment.dao;

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
//==================================================================================================
/**
* <pre>
* FindAllEnrolledCoursesByParticipantId.java is a method under EnrollmentDao
* <pre>
* 
* @version 0.01
* @author m.lumontad                          
*/

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void findAllEnrolledCoursesByParticipantId() {
        Long courseScheduleId = 1L;
        Long courseParticipantId = 0020L;
        CourseParticipant courseIdandParticipantId = enrollmentDao.findCourseParticipantByCourseScheduleIdAndParticipantId(courseScheduleId, courseParticipantId);
        assertNotNull(courseIdandParticipantId);
    }

}
