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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fujitsu.ph.tsup.attendance.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleViewForm;
import com.fujitsu.ph.tsup.scheduling.service.ScheduleService;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@AutoConfigureMockMvc

public class ViewAllCourseScheduleTest {
    
    public Long id;
    public Long courseId;
    public String courseName;
    public Long instructorId;
    public String instructorLastName;
    public String instructorFirstName;
    public Set<CourseScheduleDetail> courseScheduleDetail;
    
    public void CourseSchedule(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.courseName = builder.courseName;
        this.instructorId = builder.instructorId;
        this.instructorLastName = builder.instructorLastName;
        this.instructorFirstName = builder.instructorFirstName;
        
    }

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
     * viewAllCourseScheduleTest. Method = GET
     * 
     * <pre>
     * 
     */
    @WithMockCustomUser(id = 1L, username = "l.lorenzo")
    @Test
    void viewAllCourseScheduleTest() throws Exception {

        when(scheduleService.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class)))
                .thenReturn(setCourseSchedule());
        doThrow(new DataRetrievalFailureException("error")).when(scheduleService)
                .createCourseSchedule(any(CourseSchedule.class));

        mockMvc.perform(get("/schedules/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("scheduling/view"))
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

        CourseScheduleViewForm courseSchedule = new CourseScheduleViewForm();

        courseSchedule.setCourseSchedule(courseSchedule);

        
        System.out.println(setCourseSchedule());

        System.out.println(courseSchedule);

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
    private Set<CourseSchedule> setCourseSchedule() {
        Set<CourseSchedule> courseSchedule = new HashSet<>();
        CourseSchedule courseSched = new CourseSchedule(null);
        courseSched.setCourseId(1L);
        courseSched.setCourseId(1L);
        courseSched.setName("Programming");
        courseSchedule.add(courseSched);
        return courseSchedule;
    }

}

   