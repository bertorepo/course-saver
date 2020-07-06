package com.fujitsu.ph.tsup.enrollment.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;

@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SaveCourseNonParticipant {

	

	    @Autowired
	    private EnrollmentDao dao;

	    @TestConfiguration
	    static class TestContextConfiguration {

	        @Bean
	        public EnrollmentDao enrollmentDao() {
	            return new EnrollmentDaoImpl();
	        }
	    }

	    @Test
		void saveCourseNonParticipanttest() {
	   
	       CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 10L).build();
	       dao.saveCourseNonParticipant(courseParticipant);
	       
	       assertEquals(1L, courseParticipant.getCourseScheduleId());
	       assertEquals(1L, courseParticipant.getParticipantId());
	    
	}
}
