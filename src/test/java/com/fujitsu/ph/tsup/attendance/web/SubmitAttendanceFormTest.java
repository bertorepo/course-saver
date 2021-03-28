package com.fujitsu.ph.tsup.attendance.web;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.model.CourseAttendanceForm;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :SubmitAttendanceFormTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                       | Content
//--------+------------+--------------------------------------------------+-------------------------
//0.01    | 07/13/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | New Creation
//0.02    | 09/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | Update
//==================================================================================================
/**
* <pre>
* The show generate absent form Test for Controller.
* In this class, it test the method using mockito 
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
class SubmitAttendanceFormTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttendanceService attendanceService;
    
    @Test
    @WithMockCustomUser(id = 1L, username = "l.lorenzo")
    void testAttendanceController() throws Exception {
        CourseAttendanceForm newForm = newForm();
        CourseAttendance courseAttendance = createCourseAttendance();
        Set<CourseAttendance> courseAttendanceSet = new HashSet<>();
        courseAttendanceSet.add(courseAttendance);
        attendanceService.attendLogin(courseAttendance);
        
        when(attendanceService.findCourseScheduleDetailById(any(Long.class))).thenReturn(courseAttendanceSet);
        
        Mockito.doThrow(new DataRetrievalFailureException("error")).when(attendanceService).attendLogin(null);
        
        mockMvc.perform(post("/attendance/signin/{courseScheduleDetailId}", 1L)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(csrf())
                .flashAttr("courseAttendance", newForm))
         .andDo(print())        
         .andExpect(status().isFound())
         .andExpect(view().name("redirect:/attendance/signin/{courseScheduleDetailId}"))
         .andExpect(model().attributeDoesNotExist("courseAttendance"))
         .andReturn();
    }

    private CourseAttendance createCourseAttendance() {
        return new CourseAttendance.Builder(1L, 2L, "JAVA", "Lorenzo, Loyce", "TWO/Neo Bldg.", 3L, "Abad, Kenneth",
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00").plusHours(8), 3.0f,
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 
                ZonedDateTime.parse("2019-08-08T11:15:24.983+08:00"),'P',
                "Course Description", "k.abad@fujitsu.com", 1L, "G3CC", "TRN123456").present(ZonedDateTime.now()).build();
    }
    
    private CourseAttendanceForm newForm() {
        CourseAttendanceForm newForm = new CourseAttendanceForm();
        
        newForm.setCourseDescription("Course Description");
        newForm.setCourseName("UNDERSTANDING UI");
        newForm.setCourseScheduleDetailId(1L);
        newForm.setDuration(2.0f);
        newForm.setEmail("k.abad@fujitsu.com");
        newForm.setId(1L);
        newForm.setInstructorName("LORENZO, LOYCE");
        newForm.setLoginDateTime(ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"));
        newForm.setLogoutDateTime(ZonedDateTime.parse("2019-08-08T11:15:24.983+08:00"));
        newForm.setParticipantId(1L);
        newForm.setScheduledStartDateTime(ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"));
        newForm.setScheduledEndDateTime(ZonedDateTime.parse("2019-08-08T11:15:24.983+08:00"));
        newForm.setVenueName("ONLINE");
        return newForm;
    }
}
