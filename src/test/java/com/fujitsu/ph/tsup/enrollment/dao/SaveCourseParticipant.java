package com.fujitsu.ph.tsup.enrollment.dao;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :SaveCourseParticipant.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) M. Lumontad       | New Creation
//==================================================================================================
/**
* <pre>
* SaveCourseParticipant.java is a method under EnrollmentDao
* <pre>
* 
* @version 0.01
* @author m.lumontad                          
*/

import static org.junit.jupiter.api.Assertions.assertEquals;

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
class SaveCourseParticipant {

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
    void saveParticipant() {
       CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 10L).build();
       enrollmentDao.saveCourseParticipant(courseParticipant);
       
       assertEquals(1L, courseParticipant.getCourseScheduleId());
       assertEquals(1L, courseParticipant.getParticipantId());
               }

}
