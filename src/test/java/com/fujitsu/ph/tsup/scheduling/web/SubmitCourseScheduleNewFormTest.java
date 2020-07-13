package com.fujitsu.ph.tsup.scheduling.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fujitsu.ph.tsup.scheduling.dao.ScheduleDao;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleNewForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;
import com.fujitsu.ph.tsup.scheduling.service.ScheduleService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SubmitCourseScheduleNewFormTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ScheduleService scheduleService;
    
    @Test
    public void testSubmitCourseScheduleNewForm() throws Exception {
//        when(scheduleService.findAllCourses()).thenReturn(setCourses());
//        when(scheduleService.findAllInstructors()).thenReturn(setInstructors());
//        when(scheduleService.findAllVenues()).thenReturn(setVenues());
//        doThrow(new DataRetrievalFailureException("error")).when(scheduleService).createCourseSchedule(any(CourseSchedule.class));
        
        mockMvc.perform(post("/schedules/new"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(view().name("redirect:/schedules/new"))
               .andExpect(redirectedUrl("/schedules/new"))
               .andExpect(model().attributeDoesNotExist("scheduleNew"))
               .andExpect(flash().attribute("scheduleNew", is(CourseScheduleNewForm.class)));
        
    }
    
//    @Test
//    public void testSubmitCourseScheduleNewForm_Error() throws Exception {
//        when(scheduleService.findAllCourses()).thenReturn(setCourses());
//        when(scheduleService.findAllInstructors()).thenReturn(setInstructors());
//        when(scheduleService.findAllVenues()).thenReturn(setVenues());
//        doThrow(new DataRetrievalFailureException("error")).when(scheduleService).createCourseSchedule(any(CourseSchedule.class));
//        
//        MvcResult result = mockMvc.perform(post("/schedules/new"))
//                                  .andDo(print())
//                                  .andExpect(status().isOk())
//                                  .andExpect(view().name("redirect:/schedules/new"))
//                                  .andExpect(redirectedUrl("/schedules/new"))
//                                  .andExpect(model().attributeExists("scheduleNew"))
//                                  .andExpect(flash().attribute("scheduleNew", is(CourseScheduleNewForm.class)))
//                                  .andReturn();
//        
//    }
    
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
    
    private CourseScheduleNewForm form() {
        CourseScheduleNewForm newForm = new CourseScheduleNewForm();
        
        
        return newForm;
    }
}
