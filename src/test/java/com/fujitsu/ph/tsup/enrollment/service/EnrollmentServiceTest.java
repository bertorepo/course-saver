package com.fujitsu.ph.tsup.enrollment.service;


import static org.junit.Assert.assertThrows;

//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :EnrollmentDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.01    | 07/02/2020 | WS) M.Lumontad        | New Creation
//0.01    | 07/02/2020 | WS) K.Freo        	   | New Creation
//=================================================================================================
/**
* <pre>
* EnrollmentService.java under Test Package is used for JUnit Testing
* <pre>
* 
* @version 0.01
* @author m.lumontad 
*  @author k.freo
*                      
*/

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;


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
public class EnrollmentServiceTest {

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
		private EnrollmentDao EnrollmenteDao;
	  
	  /*  @Test
	  void findCourseParticipantById() {
		  
		  doThrow(new DataRetrievalFailureException("error")).when(EnrollmenteDao).saveCourseNonParticipant(null);
	        
			 CourseParticipant courseParticipant = courseParticipant();
		        enrollmentService.declineCourse(courseParticipant);
		        
		        assertEquals(courseParticipant.getId(), 1L);
		        
				return;  
		  
		  
	  }
	  
	  @Test 
	  void findCourseParticipantById_Error() {
		  when(EnrollmenteDao.findCourseParticipantById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));
		 
	        Exception Exception = assertThrows(IllegalArgumentException.class, () 
	                ->{ enrollmentService.findCourseParticipantById(1L);
	                });
	        String expectedMessage = "Can't find  find Course Participant By Id";
	        String actualMessage = Exception.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
		  
	   
	  }
	  
	  
	  
	  
	  @Test 
	   void findCourseParticipantById_Null() {
		  CourseParticipant courseParticipant = courseParticipant();
		  
			 when(EnrollmentDao.findCourseParticipantById(any(Long.class).thenReturn(Long.class);
	      
	      Exception courseParticipantException = assertThrows(IllegalArgumentException.class, () 
	              -> enrollmentService.declineCourse(courseParticipant()));
	      
	      String expectedMessage = "Can't find CourseParticipant By Id";
	      String actualMessage = courseParticipantException.getMessage();
	      assertTrue(actualMessage.contains(expectedMessage));   
		 
	  }*/
	  
	 

	@Test
	  CourseParticipant saveCourseNonParticipant() {
		 doThrow(new DataRetrievalFailureException("error")).when(EnrollmenteDao).saveCourseNonParticipant(null);
	        
		 CourseParticipant courseParticipant = courseParticipant();
	        enrollmentService.declineCourse(courseParticipant);
	        
	        assertEquals(courseParticipant.getId(), 1L);
	        assertEquals(courseParticipant.getCourseName(), 1);
	        assertEquals(courseParticipant.getParticipantName(), 1);
	        assertEquals(courseParticipant.getCourseScheduleDetail(), 1);
	        assertEquals(courseParticipant.getInstructorName(), 1);
	        assertEquals(courseParticipant.getParticipantId(), 1L);
	        assertEquals(courseParticipant.getCourseScheduleId(), 1L);
	        assertEquals(courseParticipant.getVenueName(), 1);
	        assertEquals(courseParticipant.getRegistrationDate(),1 );
	        assertEquals(courseParticipant.getDeclineDate(),1 );
	        assertEquals(courseParticipant.getReason(), 1);
			return courseParticipant;  
	  }
	  
	  @Test
	  void saveCourseNonParticipantwithError(){
		  doThrow(new DataRetrievalFailureException("error")).when(EnrollmenteDao).saveCourseParticipant(any(CourseParticipant.class));
	        
		  CourseParticipant CourseParticipant = courseParticipant();
	        
	        Exception  courseParticipantException = assertThrows(IllegalArgumentException.class, () 
	                -> enrollmentService.declineCourse(CourseParticipant));
	        
	        String expectedMessage = "Can't save Course non-participant";
	        String actualMessage = courseParticipantException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	  }
	  
	  /*   @Test
	  void saveCourseNonParticipant_Null(){
		  when(EnrollmenteDao.saveCourseNonParticipant(courseParticipant())).thenReturn(null);
      
      Exception courseParticipantException = assertThrows(IllegalArgumentException.class, () 
              -> enrollmentService.declineCourse(courseParticipant()));
      
      String expectedMessage = "Can't find Course Non-Participant";
      String actualMessage = courseParticipantException.getMessage();
      assertTrue(actualMessage.contains(expectedMessage)); 
	  
	  }*/
	  
	




	private  CourseParticipant courseParticipant() {
		return null;
		
		
	}
	
}
