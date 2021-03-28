package com.fujitsu.ph.tsup.attendance.web;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusCourse;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusForm;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusParticipant;
import com.fujitsu.ph.tsup.attendance.service.AttendanceService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
//0.02    | 09/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | Update
//==================================================================================================
/**
* <pre>
* The SubmitChangeStatusParticipantsForm Test for Controller.
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
    void testAttendanceController() throws Exception {
        ChangeStatusForm newForm = newForm();
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
                .with(csrf())
                .flashAttr("changeStatusForm", newForm))
       .andDo(print())
       .andExpect(status().isFound()) 
       .andExpect(view().name("redirect:/attendance/0/participants"))
       .andExpect(model().attributeDoesNotExist("changeStatusForm"))
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
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 
                ZonedDateTime.parse("2019-08-08T11:15:24.983+08:00"), 'A',
                "Course Description", "k.abad@fujitsu.com", 1L, "G3CC", "TRN123456")
                .present(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00")).build();
    }
    
    /**
     * <pre>
     * Mock data for ChangeStatusCourse
     * <pre>
     * 
     */
    private Set<ChangeStatusCourse> setCourses() {
        ChangeStatusCourse detailForm = new ChangeStatusCourse();
        detailForm.setId(10L);
        detailForm.setCourseName("UNDERSTANDING UI");
        Set<ChangeStatusCourse> detailFormSet = new HashSet<>();
        detailFormSet.add(detailForm);       
        return detailFormSet;
        
    }
    
    /**
     * <pre>
     * Mock data for ChangeStatusParticipant
     * <pre>
     * 
     */
    private List<ChangeStatusParticipant> setParticipants() {
        ChangeStatusParticipant detailForm = new ChangeStatusParticipant();
        detailForm.setCourseAttendanceId(1L);
        detailForm.setEmail("k.abad@fujitsu.com");
        detailForm.setLoginDateTime(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"));
        detailForm.setLogoutDateTime(ZonedDateTime.parse("2020-07-06T10:30:47.946+08:00"));
        detailForm.setName("LORENZO, LOYCE");
        detailForm.setParticipantId(1L);
        detailForm.setStatus('P');
        List<ChangeStatusParticipant> detailFormSet = new ArrayList<ChangeStatusParticipant>();
        detailFormSet.add(detailForm);        
        return detailFormSet;
        
    }
    
    /**
     * <pre>
     * Mock data for ChangeStatusForm
     * <pre>
     * 
     */
    private ChangeStatusForm newForm() {
        ChangeStatusForm newForm = new ChangeStatusForm();       
        newForm.setId(1L);
        newForm.setCourses(setCourses());
        newForm.setParticipants(setParticipants());       
        return newForm;
    }
}
