package com.fujitsu.ph.tsup.attendance.web;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
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
//Class Name   :ShowAttendanceFormTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 08/26/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | New Creation
//0.02    | 09/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | Update
//==================================================================================================
/**
* <pre>
* The Show Attendance Form Test for controller.
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
public class ShowAttendanceFormTest {

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
     *  Show Attendance Form Test with valid values
     * Call attendanceService.findCourseScheduleDetailById
     * test if the controller it retrieving the data that was implemented
     * <pre>
     */
    @Test
    @WithMockCustomUser(id=1L, username = "l.lorenzo")
    void testAttendanceController() throws Exception {
    
        CourseAttendance courseAttendance = createCourseAttendance();
        
        Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>();
        courseAttendanceSet.add(courseAttendance);
        
        when(attendanceService.findCourseScheduleDetailById(any(Long.class)))
                .thenReturn(courseAttendanceSet);
        mockMvc.perform(get("/attendance/signin/{courseScheduleDetailId}", 1L))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("attendance/attend"))
        .andExpect(model().attributeExists("courseAttendance"))
        .andExpect(model().attributeDoesNotExist("errorMessages"))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("id",is(courseAttendance.getId()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("courseScheduleDetailId",is(courseAttendance.getCourseScheduleDetailId()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("courseName",is(courseAttendance.getCourseName()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("instructorName",is(courseAttendance.getInstructorName()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("venueName",is(courseAttendance.getVenueName()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("participantId",is(courseAttendance.getParticipantId()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("scheduledStartDateTime",is(courseAttendance.getScheduleStartDateTime()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("scheduledEndDateTime",is(courseAttendance.getScheduleEndDateTime()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("loginDateTime",is(courseAttendance.getLoginDateTime()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("logoutDateTime",is(courseAttendance.getLogoutDateTime()))))
        .andExpect(model().attribute("courseAttendance",
                hasProperty("courseDescription",is(courseAttendance.getCourseDescription()))))
        .andReturn();
    }
    
    private CourseAttendance createCourseAttendance() {
        CourseAttendance courseAttendance = new CourseAttendance.Builder(1L, 1L, "SpringBoot", "Lorenzo, Loyce",
                "TwoNeo", 3L, "Ramos, Ramon", ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 3.0f,
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 
                ZonedDateTime.parse("2019-08-08T11:15:24.983+08:00"),'P',
                "Course Description", "r.ramos@fujitsu.com", 1L, "G3CC", "TRN123456").build();
        return courseAttendance;
    }
}
