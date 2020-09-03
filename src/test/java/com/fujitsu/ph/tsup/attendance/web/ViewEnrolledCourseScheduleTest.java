package com.fujitsu.ph.tsup.attendance.web;

import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.attendance.model.CourseScheduleForm;
import com.fujitsu.ph.tsup.attendance.service.AttendanceService;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :ViewEnrolledCourseScheduleTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 08/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | New Creation
//0.02    | 09/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | Update
//==================================================================================================

/**
* <pre>
* The View Enrolled Course Schedule Test for controller.
* In this class, It will test the method of this user story
* 
* <pre>
* 
* @version 0.02
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AttendanceController.class)
@AutoConfigureMockMvc
public class ViewEnrolledCourseScheduleTest {
    
    /*
     * MockMvc for mockito
     */
    @Autowired
    private MockMvc mockMvc;
    
    /*
     * MockBean of Attendance Service
     */
    @MockBean
    private AttendanceService attendanceService;
    
    /**
     * <pre>
     *  Generate Attendance Sheet Test with valid values
     * Call attendanceService.findAllScheduledCoursesByParticipant
     * test if the controller it retrieving the data that was implemented
     * <pre>
     */
    @Test
    @WithMockCustomUser(id = 1L, username = "l.lorenzo")
    void testAttendanceController() throws Exception {
        CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
        courseScheduleForm.setId(1L);
        courseScheduleForm.setCourseName("UNDERSTANDING UI");
        courseScheduleForm.setInstructorName("LORENZO, LOYCE");
        
        CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        courseScheduleDetailForm.setId(1L);
        courseScheduleDetailForm.setScheduledStartDateTime(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"));
        courseScheduleDetailForm.setScheduledEndDateTime(ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"));
        courseScheduleDetailForm.setDuration(2.0f);
        
        CourseParticipant courseParticipant = createCourseParticipant();
        
        Set<CourseParticipant> courseParticipantSet = new HashSet<CourseParticipant>();
        courseParticipantSet.add(courseParticipant);
        
        when(attendanceService.findAllScheduledCoursesByParticipant(any(ZonedDateTime.class), any(ZonedDateTime.class),
                any(Long.class))).thenReturn(courseParticipantSet);
        
        mockMvc.perform(get("/attendance/signin"))
        .andDo(print())
        .andExpect(status().isOk()).andExpect(view().name("attendance/attendCourseList"))
        .andExpect(model().attributeExists("attendCourseList"))
        .andExpect(model().attributeDoesNotExist("errorMessages"))
        .andExpect(model().attribute("attendCourseList",
                hasProperty("courseSchedules", 
                        containsInAnyOrder(hasProperty("id",
                                is(courseScheduleForm.getId()))))))   
        .andExpect(model().attribute("attendCourseList",
                hasProperty("courseSchedules", 
                        containsInAnyOrder(hasProperty("courseName",
                                is(courseScheduleForm.getCourseName())))))) 
        .andExpect(model().attribute("attendCourseList",
                hasProperty("courseSchedules", 
                        containsInAnyOrder(hasProperty("instructorName",
                                is(courseScheduleForm.getInstructorName()))))))
        .andExpect(model().attribute("attendCourseList",
                hasProperty("courseSchedules",
                        containsInAnyOrder(hasProperty("courseScheduleDetails",
                                containsInAnyOrder(hasProperty("id",
                                is(courseScheduleDetailForm.getId()))))))))  
        .andExpect(model().attribute("attendCourseList",
                hasProperty("courseSchedules",
                        containsInAnyOrder(hasProperty("courseScheduleDetails",
                                containsInAnyOrder(hasProperty("scheduledStartDateTime",
                                is(courseScheduleDetailForm.getScheduledStartDateTime()))))))))
        .andExpect(model().attribute("attendCourseList",
                hasProperty("courseSchedules",
                        containsInAnyOrder(hasProperty("courseScheduleDetails",
                                containsInAnyOrder(hasProperty("scheduledEndDateTime",
                                is(courseScheduleDetailForm.getScheduledEndDateTime())))))))) 
        .andExpect(model().attribute("attendCourseList",
                hasProperty("courseSchedules",
                        containsInAnyOrder(hasProperty("courseScheduleDetails",
                                containsInAnyOrder(hasProperty("duration",
                                is(courseScheduleDetailForm.getDuration()))))))))  
        .andReturn();
    }
    
    /**
     * <pre>
     * Creates an instance of the CourseParticipant using the given builder class.
     * 
     * <pre>
     * 
     * @return courseParticipant
     */
    private CourseParticipant createCourseParticipant() {
        return new CourseParticipant.Builder(1L, 1L, "UNDERSTANDING UI", "LORENZO, LOYCE", "TwoNeo", 1L, "Abad, Kenneth",
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 2.0f,
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), "k.abad@fujitsu.com", "220054288",
                1L, "G3CC").build();
    }
}
