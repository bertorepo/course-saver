package com.fujitsu.ph.tsup.enrollment.service;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CancelTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 07/6/2020 | WS) T.Oviedo          | New Creation
//==================================================================================================
/**
* <pre>
* Cancels a scheduled course
* <pre>
* 
* @version 0.01
* @author t.oviedo                          
*/
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;

@ExtendWith(SpringExtension.class)
class CancelTest {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@MockBean
	private EnrollmentDao enrollmentDao;
	
	@TestConfiguration
	static class EnrollmentServiceImplestContextConfiguration {
		@Bean
		EnrollmentService enrollmentService() {
			return new EnrollmentServiceImpl();
			
		}
	}

	@Test
    void CancelTests() {
    	CourseSchedule courseSched = createCourseSchedule();
		when(enrollmentDao.findCourseScheduleById(any(Long.class))).thenReturn(courseSched);
		enrollmentService.cancel(courseSched.getId());
		CourseSchedule courseSchedule = enrollmentService.findCourseScheduleById(courseSched.getId());
	   	//assertNull(courseSched.getStatus());
	    assertEquals("Test Cancel Status 'D':", 'D',courseSchedule.getStatus());//Didn't Cancel
	    //assertEquals("PeerReview", courseSchedule.getCourseName());
		//assertEquals("Test Course Name: ",courseSchedule.getCourseName() ,"PeerReview");
    }

	@Test
	void testInvalidStatus() {
		Exception error = assertThrows(IllegalArgumentException.class, () -> {
	            new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "Two/Neo", 1, 1, 'C').build();
	        });
		 assertTrue(error.getMessage().equals("Status must only be A or D"));
	}
	
	
	private CourseSchedule createCourseSchedule() {
		//return new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
		Set<CourseScheduleDetail> courseSchedDetailSet = new HashSet<CourseScheduleDetail>();
		CourseScheduleDetail csd1 = new CourseScheduleDetail.Builder(1L, 1L, ZonedDateTime.now(), ZonedDateTime.now().plusDays(5)).build();
		CourseScheduleDetail csd2 = new CourseScheduleDetail.Builder(2L, 2L, ZonedDateTime.now(), ZonedDateTime.now().plusDays(5)).build();
		courseSchedDetailSet.add(csd1);
		courseSchedDetailSet.add(csd2);
		return new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').addDetail(csd1).build();
		
	}
	

}
