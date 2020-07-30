package com.fujitsu.ph.tsup.attendance.web;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.service.AttendanceService;

import java.time.ZonedDateTime;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.dao.DataRetrievalFailureException;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :SubmitChangeStatusParticipantsFormTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                       | Content
//--------+------------+--------------------------------------------------+-------------------------
//0.01    | 07/13/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | New Creation
//==================================================================================================
/**
* <pre>
* The SubmitChangeStatusParticipantsForm Test for Controller.
* In this class, it test the method using mockito 
* 
* <pre>
* 
* @version 0.01
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/

@ExtendWith(SpringExtension.class)

/*
 * SpringBoot Test
 */
@SpringBootTest(classes = AttendanceController.class)

/*
 * AutoConfigureMockMvc
 */
@AutoConfigureMockMvc
class SubmitChangeStatusParticipantsFormTest {

    /*
     * MockMvc as dependency
     */
    @Autowired
    private MockMvc mockMvc;

    /*
     * add mock objects to the Spring application context
     */
    @MockBean
    private AttendanceService attendanceService;
    
    /**
     * <pre>
     * submit the changed status of the participants
     * 
     * </pre>
     * 
     */
    @Test
    @WithMockCustomUser(id = 1L, username = "l.lorenzo")
    void testPost() throws Exception {
        
        CourseSchedule courseSchedule = createCourseSchedule();

        Set<CourseSchedule> courseScheduleSet = new HashSet<>();
        courseScheduleSet.add(courseSchedule);

        CourseAttendance courseAttendance = createCourseAttendance();
        
        Set<CourseAttendance> courseAttendanceSet = new HashSet<>();
        courseAttendanceSet.add(courseAttendance);
        
        when(attendanceService.findAllScheduledCoursesByInstructor(any(ZonedDateTime.class), 
                any(ZonedDateTime.class), any(Long.class))).thenReturn(courseScheduleSet);
        when(attendanceService.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                .thenReturn(courseAttendanceSet);
        Mockito.doThrow(new DataRetrievalFailureException("error")).when(attendanceService)
                .changeStatus(null);
        
        mockMvc.perform(post("/attendance/{courseScheduleDetailId}/participants",1L)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf()))
       .andDo(print())
       .andExpect(status().isFound())  
       .andReturn();
    }

    /**
     * <pre>
     * Creates an instance of the CourseSchedule using the given builder class.
     * 
     * <pre>
     * 
     * @return courseSchedule
     */
    private CourseSchedule createCourseSchedule() {
        return new CourseSchedule.Builder(1L, 1L, "SpringBoot", 3L, "Lorenzo", "Loyce", 4L,
                  "TwoNeo", 30, 100, 80, "A".charAt(0)).build();
    }
    
    /**
     * <pre>
     * Creates an instance of the CourseAttendance using the given builder class.
     * 
     * <pre>
     * 
     * @return courseAttendance
     */
    private CourseAttendance createCourseAttendance() {
        return new CourseAttendance.Builder(1L, 1L, "SpringBoot", "Lorenzo, Loyce", "TwoNeo", 1L, 
                "De Guzman, Genevieve", ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 2.0f, 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 'A')
                .absent().present(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00")).build();
    }  
}
