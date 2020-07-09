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
	  void testDeclineCourse() {
		
		CourseParticipant courseParticipantSet = courseParticipant();
			when(dao.findCourseParticipantById(any(Long.class))).thenReturn(courseParticipantSet);
			doThrow(new DataRetrievalFailureException("error")).when(dao).saveCourseNonParticipant(null);
			doThrow(new DataRetrievalFailureException("error")).when(dao).deleteCourseParticipantById(null); 
			
			enrollmentService.declineCourse(courseParticipantSet);
			assertEquals(courseParticipantSet.getId(), 10L);
	  }
	  
	  @Test
	  void testDeclineCoursewithError(){
		  when(dao.findCourseParticipantById(any(Long.class))).thenReturn(courseParticipant());
		  doThrow(new DataRetrievalFailureException("error")).when(dao).saveCourseNonParticipant(any(CourseParticipant.class));
		  doThrow(new DataRetrievalFailureException("error")).when(dao).deleteCourseParticipantById(any(Long.class)); 
		  
		  Exception exception = assertThrows(IllegalArgumentException.class,() 
				  -> enrollmentService.declineCourse(courseParticipant()));
				
		  assertTrue(exception.getMessage().equals(" Can't decline Course."));
	  }
	
	  private CourseParticipant courseParticipant() {
			return new CourseParticipant.Builder(10L).decline("DUMMY").build();
		}  
}
