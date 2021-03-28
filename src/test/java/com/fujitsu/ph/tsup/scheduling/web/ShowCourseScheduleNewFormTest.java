package com.fujitsu.ph.tsup.scheduling.web;
//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//Class Name   :ShowCourseSceduleNewFormTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 07/10/2020 | WS) J.Macabugao       | New Creation
//==================================================================================================
/**
* <pre>
* ShowCourseSceduleNewForm test for ShowCourseSceduleNewForm method in controller
* 
* <pre>
* 
* @version 0.01
* @author J.Macabugao
*/

import java.util.HashSet;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;

import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;
import com.fujitsu.ph.tsup.scheduling.service.ScheduleService;

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
@WebAppConfiguration
@AutoConfigureMockMvc
public class ShowCourseScheduleNewFormTest {
	
	
	/**
     * Test Configuration
     */
	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		ScheduleController scheduleController() {

			return new ScheduleController();
		}

	}

	/**
     * MockMvc for dependency
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Schedule Service for mock bean
     */
    @MockBean
    private ScheduleService scheduleService;
    
    

    /**
     * <pre>
     * showCourseScheduleNewFormTest. Method = GET
     * <pre>
     * 
     */
    @Test
    @WithMockCustomUser(id = 1L, username = "l.lorenzo")
	void showCourseScheduleNewFormTest() throws Exception{
   
    	
		when(scheduleService.findAllCourses()).thenReturn(setCourses());
		when(scheduleService.findAllInstructors()).thenReturn(setInstructors());
		when(scheduleService.findAllVenues()).thenReturn(setVenues());
		doThrow(new DataRetrievalFailureException("error")).when(scheduleService)
				.createCourseSchedule(any(CourseSchedule.class));

		mockMvc.perform(get("/schedules/new"))
		        .andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("scheduling/createSched")) 
				.andExpect(model().attributeExists("scheduleNew"))
				.andExpect(model().attributeDoesNotExist("errorMessages"))
				.andExpect(model().attribute("scheduleNew", allOf( 
						hasProperty("id"), 
						hasProperty("courseId"), 
						hasProperty("instructorId"),
						hasProperty("venueId"), 
						hasProperty("minRequired"), 
						hasProperty("maxAllowed"),
						hasProperty("courses"), 
						hasProperty("instructors"), 
						hasProperty("venues"))))
		.andExpect(model().attributeDoesNotExist("errorMessages"))
		.andReturn();
        
       verify(scheduleService, times(1)).findAllCourses(); 
       verify(scheduleService, times(1)).findAllInstructors();
       verify(scheduleService, times(1)).findAllVenues();
       verifyNoMoreInteractions(scheduleService);
	}

    /**
     * <pre>
     * Mock data for CourseForm
     * <pre>
     * 
     */
	private Set<CourseForm>setCourses() {
		Set<CourseForm> courses = new HashSet<>();
		CourseForm courseForm = new CourseForm();
		courseForm.setId(1L);
		courseForm.setName("Springboot");
		courses.add(courseForm);
		return courses;
	}
	
	/**
     * <pre>
     * Mock data for InstructorForm
     * <pre>
     * 
     */
	private Set<InstructorForm> setInstructors() {
		Set<InstructorForm> instructors = new HashSet<>();
		InstructorForm instructorForm = new InstructorForm();
		instructorForm.setId(1L);
		instructorForm.setName("JC. De Leon");
		instructors.add(instructorForm);
		return instructors;
	}
	
	 /**
     * <pre>
     * Mock data for VenueForm
     * <pre>
     * 
     */
	private Set<VenueForm> setVenues() {
		Set<VenueForm> venues = new HashSet<>();
		VenueForm venueForm = new VenueForm();
		venueForm.setId(1L);
		venueForm.setName("Hall");
		venues.add(venueForm);
		return venues;
	}

}
