package com.fujitsu.ph.tsup.enrollment.dao;


//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :DeleteCourseParticipantByIdTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.02    | 06/30/2020 | WS) K.Freo            | New Creation
//=================================================================================================

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


/**
* <pre>
* DeleteCourseParticipantByIdTest.java
* <pre>
* 
* @version 0.01
* @author k.freo                    
*/

@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DeleteCourseParticipantByIdTest {

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
	void testDeleteCourseParticipantById() {
  	
		
		CourseParticipant courseParticipant = new CourseParticipant.Builder(10L).build();
		System.out.println("Id:" + courseParticipant.getId());
			
	    	assertNotNull(courseParticipant.getId());
	    	assertEquals(10L, courseParticipant.getId());
	}
}
