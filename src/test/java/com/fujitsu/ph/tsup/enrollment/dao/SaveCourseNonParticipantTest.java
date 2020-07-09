package com.fujitsu.ph.tsup.enrollment.dao;


//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :SaveCourseNonParticipantTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.02    | 07/07/2020 | WS) K.Freo            | New Creation
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
* SaveCourseNonParticipantTest.java
* <pre>
* 
* @version 0.01
* @author k.freo                    
*/
@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SaveCourseNonParticipantTest {

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
 		void testsaveCourseNonParticipant() {
 	   
    	CourseParticipant courseParticipant = createCourseParticipant();
	    	
	    	dao.saveCourseNonParticipant(courseParticipant);
	    	assertEquals(10L, courseParticipant.getId());
	}

	    private CourseParticipant createCourseParticipant() {		
		return  new CourseParticipant.Builder(10L).decline("TEST").build();
	    }
	}
