package com.fujitsu.ph.tsup.scheduling.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.Http2;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf; 
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleNewForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;
import com.fujitsu.ph.tsup.scheduling.service.ScheduleService;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = ScheduleController.class)
@AutoConfigureMockMvc
class SubmitCourseScheduleNewFormTest {  
    
    /**
     * Test Configuration
     */
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ScheduleService scheduleService;
    
    @Test
    @WithMockCustomUser (id = 1L, username = "l.lorenzo")
    public void testSubmitCourseScheduleNewForm() throws Exception {
        doThrow(new DataRetrievalFailureException("error")).when(scheduleService).createCourseSchedule(null);
        
        CourseScheduleNewForm newForm = newForm();

        mockMvc.perform(post("/schedules/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(csrf())
                        .flashAttr("scheduleNew", newForm))
                       .andDo(print())
                       .andExpect(status().isFound())
                       .andExpect(view().name("redirect:/schedules/new"))
                       .andExpect(redirectedUrl("/schedules/new"))
                       .andExpect(model().attributeDoesNotExist("scheduleNew"))
                       .andExpect(flash().attribute("scheduleNew", is(newForm)))
                       .andReturn();
        
    }
    
    @Test
    @WithMockCustomUser (id = 1L, username = "l.lorenzo")
    public void testSubmitCourseScheduleNewForm_Error() throws Exception {
        when(scheduleService.findAllCourses()).thenReturn(setCourses());
        when(scheduleService.findAllInstructors()).thenReturn(setInstructors());
        when(scheduleService.findAllVenues()).thenReturn(setVenues());
        doNothing().when(scheduleService).createCourseSchedule(any(CourseSchedule.class));
        
        CourseScheduleNewForm newForm = newForm();
        
        MvcResult result = mockMvc.perform(post("/schedules/new")
                                           .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                           .with(csrf())
                                           .param("courseId", "null")
                                           .param("instructorId", "null")
                                           .param("venueId", "null")
                                           .param("minRequired", "0")
                                           .param("maxAllowed", "0")
                                           .flashAttr("scheduleNew", new CourseScheduleNewForm()))
                                  .andDo(print())
                                  .andExpect(status().isOk())
                                  .andExpect(view().name("scheduling/createSched"))
                                  .andExpect(forwardedUrl("scheduling/createSched"))
                                  .andExpect(model().attributeExists("scheduleNew"))
                                  .andExpect(model().attributeHasErrors("scheduleNew"))
                                  .andExpect(model().attribute("scheduleNew", allOf(
                                          hasProperty("venues" , is(newForm.getVenues())),
                                          hasProperty("instructors", is(newForm.getInstructors())),
                                          hasProperty("courses", is(newForm.getCourses()))
                                          )))
                                  .andReturn();
    }
    
    private Set<CourseForm> setCourses() {
        Set<CourseForm> courses = new HashSet<>();
        CourseForm courseForm = new CourseForm();
        courseForm.setId(1L);
        courseForm.setName("Dummy");
        return courses;
    }
    
    private Set<InstructorForm> setInstructors() {
        Set<InstructorForm> instructors = new HashSet<>();
        InstructorForm instructorForm = new InstructorForm();
        instructorForm.setId(1L);
        instructorForm.setName("Dummy");
        return instructors;
    }
    
    private Set<VenueForm> setVenues() {
        Set<VenueForm> venues = new HashSet<>();
        VenueForm venueForm = new VenueForm();
        venueForm.setId(1L);
        venueForm.setName("Dummy");
        return venues;
    }
    
    private Set<CourseScheduleDetailForm> setCourseScheduleDetails() {
        CourseScheduleDetailForm detailForm = new CourseScheduleDetailForm();
        detailForm.setId(10L);
        detailForm.setScheduledStartDateTime(ZonedDateTime.now());
        detailForm.setScheduledEndDateTime(ZonedDateTime.now().plusHours(8));
        Set<CourseScheduleDetailForm> detailFormSet = new HashSet<>();
        detailFormSet.add(detailForm);
        
        return detailFormSet;
        
    }
    
    private CourseScheduleNewForm newForm() {
        CourseScheduleNewForm newForm = new CourseScheduleNewForm();
        
        newForm.setCourses(setCourses());
        newForm.setCourseId(1L);
        
        newForm.setInstructors(setInstructors());
        newForm.setInstructorId(1L);
        
        newForm.setVenues(setVenues());
        newForm.setVenueId(1L);
        
        newForm.setMinRequired(10);
        newForm.setMaxAllowed(50);
        
        newForm.setCourseScheduleDetails(setCourseScheduleDetails());
        return newForm;
    }

}
