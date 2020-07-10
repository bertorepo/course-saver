package com.fujitsu.ph.tsup.scheduling.web;


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

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class ShowCourseScheduleNewFormTest {
	
	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		ScheduleController scheduleController() {

			return new ScheduleController();
		}

	}

    @Autowired
    private MockMvc mockMvc;

    
    @MockBean
    private ScheduleService scheduleService;

    
	@Test
	void showCourseScheduleNewFormTest() throws Exception{
		
		// Configure the used mock object to return the created test data when its
		// findAll() method is called.
		when(scheduleService.findAllCourses()).thenReturn(setCourses());
		when(scheduleService.findAllInstructors()).thenReturn(setInstructors());
		when(scheduleService.findAllVenues()).thenReturn(setVenues());
		doThrow(new DataRetrievalFailureException("error")).when(scheduleService)
				.createCourseSchedule(any(CourseSchedule.class));

		mockMvc.perform(get("/schedules/new")) // Execute a GET request to url ‘/schedules/new’.
				.andExpect(status().isOk()) // Ensure that the HTTP status code 200 is returned.
				.andExpect(view().name("scheduling/createSched")) // Ensure that the name of the returned view is
				.andExpect(model().attributeExists("scheduleNew")) // Ensure that model attribute is existing.
				.andExpect(model().attribute("scheduleNew", allOf( //Ensure that the model contains correct items
								hasProperty("id"), 
								hasProperty("courseId"), 
								hasProperty("instructorId"),
								hasProperty("venueId"), 
								hasProperty("minRequired"), 
								hasProperty("maxAllowed"),
								hasProperty("courses"), 
								hasProperty("instructors"), 
								hasProperty("venues"))))
				.andExpect(model().attributeDoesNotExist("errorMessages")).andReturn();
        
	
       //Verify that the findAll() method of our mock object was called only once.
       verify(scheduleService, times(1)).findAllCourses(); 
       verify(scheduleService, times(1)).findAllInstructors();
       verify(scheduleService, times(1)).findAllVenues();
       //Ensure that other methods of the mock object were not called during the test.
       verifyNoMoreInteractions(scheduleService);
       
   	
	}
	
	
	// Create the test data which is returned when our service method is called
	private Set<CourseForm> setCourses() {
		Set<CourseForm> courses = new HashSet<>();
		CourseForm courseForm = new CourseForm();
		courseForm.setId(1L);
		courseForm.setName("TEST");
		return courses;
	}
	
	private Set<InstructorForm> setInstructors() {
		Set<InstructorForm> instructors = new HashSet<>();
		InstructorForm instructorForm = new InstructorForm();
		instructorForm.setId(1L);
		instructorForm.setName("TEST");
		return instructors;
	}
	
	private Set<VenueForm> setVenues() {
		Set<VenueForm> venues = new HashSet<>();
		VenueForm venueForm = new VenueForm();
		venueForm.setId(1L);
		venueForm.setName("TEST");
		return venues;
	}

}
