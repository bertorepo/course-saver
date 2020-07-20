package com.fujitsu.ph.tsup.enrollment.web;

//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :SubmitCourseDeclineFormTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.02    | 07/10/2020 | WS) K.Freo            | New Creation
//=================================================================================================


import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.HashSet;

import java.util.Set;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fujitsu.ph.tsup.authz.config.WebConfig;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.model.CourseDeclineForm;
import com.fujitsu.ph.tsup.enrollment.service.EnrollmentService;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
class ShowCourseDeclineFormTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		EnrollmentController enrollmentController() {

		return new EnrollmentController();
		}

	}

    @Autowired
    private MockMvc mockMvc;

    
    @MockBean
    private EnrollmentService enrollmentService;


	private Long id;
    
	@WithMockCustomUser (id = 1L, username = "l.lorenzo")
    @Test
	void showCourseDeclineFormTest() throws Exception{
    
    	when(enrollmentService.findCourseParticipantById(1L)).thenReturn(courseParticipant());
		//doThrow(new DataRetrievalFailureException("error")).when(enrollmentService)
		//.declineCourse(any(CourseParticipant.class));

		mockMvc.perform(get("/enrollment/mySchedules/{courseParticipantId}/decline", 1L)) 
				.andDo(print())
				.andExpect(status().isOk()) 
				.andExpect(view().name("enrollment/myCourseDecline"))
				.andExpect(model().attributeExists("enrollment/myCourseDecline")) 
				.andExpect(model().attribute("courseDecline", allOf( 
								hasProperty("id"), 
								hasProperty("CourseName"), 
								hasProperty("InstructorName"),
								hasProperty("VenueName"), 
								hasProperty("ParticipantName"), 
								hasProperty("RegistrationDate"),
								hasProperty("Reason")))) 
				.andExpect(model().attributeDoesNotExist("errorMessages")).andReturn();
 
       verify(enrollmentService, times(1)).findCourseParticipantById(id); 
       verifyNoMoreInteractions(enrollmentService);
    
       CourseDeclineForm courseParticipant= new CourseDeclineForm();
       courseParticipant.setId(id);
    }
    
       private CourseParticipant courseParticipant() {
           return new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY", ZonedDateTime.now(), "DUMMY", ZonedDateTime.now()).build();
       }	


}
