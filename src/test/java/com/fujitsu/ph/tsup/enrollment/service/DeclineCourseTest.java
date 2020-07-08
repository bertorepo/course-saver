package com.fujitsu.ph.tsup.enrollment.service;


//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Course
//Class Name   :DeclineCourseTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 07/08/2020 | WS) K.Freo            | Update
//==================================================================================================
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import static org.mockito.Mockito.when;

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

import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;




@ExtendWith(SpringExtension.class)
class DeclineCourseTest {
	
	
	  @TestConfiguration
	    static class TestContextConfiguration {
	        
	        @Bean
	        
	        EnrollmentService enrollmentService() {
	            return new EnrollmentServiceImpl();
	        }
	    }
	  
	  @Autowired
	  EnrollmentService enrollmentService;

	  @MockBean
		private EnrollmentDao dao;


	@Test
	  void testsaveCourseNonParticipant() {
	        
		 CourseParticipant courseParticipant = courseParticipant();
	        enrollmentService.declineCourse(courseParticipant);
	        
	        assertEquals(courseParticipant.getId(),10L);
	   
	  }
	  
	  @Test
	  void testsaveCourseNonParticipantwithError(){
		  doThrow(new DataRetrievalFailureException("error")).when(dao).saveCourseParticipant(any(CourseParticipant.class));
	        
		  CourseParticipant courseParticipant = courseParticipant();
	        
	        Exception  courseParticipantException = assertThrows(IllegalArgumentException.class, () 
	                -> enrollmentService.declineCourse(courseParticipant));
	        
	        String expectedMessage = "Can't save Course non-participant";
	        String actualMessage = courseParticipantException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	  }
	  
	     @Test
	  void saveCourseNonParticipant_Null(){
		  doThrow(new DataRetrievalFailureException("error")).when(dao).saveCourseNonParticipant(any(CourseParticipant.class)); 
		  
		  Exception courseParticipantException = assertThrows(IllegalArgumentException.class, () 
          -> enrollmentService.declineCourse(courseParticipant()));
  
		  String expectedMessage = "Can't find Course Non-Participant";
		  String actualMessage = courseParticipantException.getMessage();
		  assertTrue(actualMessage.contains(expectedMessage)); 
	  }
	  

	private  CourseParticipant courseParticipant() {
            new  CourseParticipant.Builder(10L).build();
            
		return  new CourseParticipant.Builder(10L).build();
		
	}
}
