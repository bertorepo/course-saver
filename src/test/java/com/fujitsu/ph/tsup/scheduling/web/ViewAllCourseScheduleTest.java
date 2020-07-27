package com.fujitsu.ph.tsup.scheduling.web;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//Class Name   :ViewAllCourseSceduleTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 07/09/2020 | WS) J.Balanon         | New Creation
//==================================================================================================
/**
* <pre>
* ViewAllCourseScedule test for ViewAllCourseScedule method in controller
* 
* <pre>
* 
* @version 0.01
* @author J.Balanon
*/

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.service.ScheduleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScheduleController.class)
@AutoConfigureMockMvc
public class ViewAllCourseScheduleTest {

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
     * viewAllCourseScheduleTest. Method = GET
     * 
     * <pre>
     * 
     */
    @WithMockCustomUser(id = 1L, username = "l.lorenzo")
    @Test
    void viewAllCourseScheduleTest() throws Exception {

        when(scheduleService.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class)))
                .thenReturn(courseSchedules());

        mockMvc.perform(get("/schedules/view")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("fromDateTime", String.valueOf(ZonedDateTime.now()))
                .param("toDateTime", String.valueOf(ZonedDateTime.now().plusDays(3))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("scheduling/instructorCourseScheduleList"))
                .andExpect(model().attributeExists("scheduleView"))
                .andExpect(model().attribute("scheduleView", allOf(
                                hasProperty("id"),
                                hasProperty("courseId"),
                                hasProperty("instructorId"),
                                hasProperty("courses"),
                                hasProperty("instructors"))))
                .andExpect(model().attributeDoesNotExist("errorMessages")).
                 andReturn();

        verify(scheduleService, times(1)).findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class));
        verifyNoMoreInteractions(scheduleService);
    }
    

    /**
     * <pre>
     * 
     * 
     * Mock data for CourseScheduleView
     * 
     * <pre>
     * 
     */
    
    private Set<CourseSchedule> courseSchedules() {
        Set<CourseSchedule> courseSchedules = new HashSet<>();
        Set<CourseScheduleDetail> courseScheduleDetails = new HashSet<>();
        
        CourseScheduleDetail courseScheduleDetail1 = new CourseScheduleDetail.Builder(1L, ZonedDateTime.now(), 
                ZonedDateTime.now().plusHours(8)).build();
        CourseScheduleDetail courseScheduleDetail2 = new CourseScheduleDetail.Builder(1L, ZonedDateTime.now().plusDays(1), 
                ZonedDateTime.now().plusDays(1).plusHours(8)).build();
        
        courseScheduleDetails.add(courseScheduleDetail1);
        courseScheduleDetails.add(courseScheduleDetail2);
        
        CourseSchedule courseSchedule1 = 
                new CourseSchedule.Builder(1L, 1L, "name", 1L, "last", "first", 1L, "venue", 1, 10, 'A')
                .addDetail(courseScheduleDetails).build();
        CourseSchedule courseSchedule2 = 
                new CourseSchedule.Builder(2L, 2L, "name2", 2L, "last2", "first2", 2L, "venue2", 1, 10, 'A')
                .addDetail(courseScheduleDetails).build();
        courseSchedules.add(courseSchedule1);
        courseSchedules.add(courseSchedule2);
        return courseSchedules;
    }

}
