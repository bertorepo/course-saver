package com.fujitsu.ph.tsup.attendance.web;

import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.attendance.model.AttendanceParticipantDetail;
import com.fujitsu.ph.tsup.attendance.model.CourseScheduleDetailForm;
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
//Class Name   :CourseParticipantTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                      | Content
//--------+------------+-------------------------------------------------+--------------------------
//0.01    | 07/10/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos          | New Creation
//==================================================================================================
/**
* <pre>
* The View Instructor Course Participant Test for controller.
* In this class, It will test the method of this user story
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
@SpringBootTest(classes = AttendanceController.class)
@AutoConfigureMockMvc
public class TestViewInstructorCourseParticipant {

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
     *  View Instructor Course Participant Test with valid values
     * Call attendanceService.findAllScheduledCoursesByInstructor and attendanceService.findCourseScheduleById 
     * test if the controller it retrieving the data that was implemented
     * <pre>
     */
    @Test
    @WithMockCustomUser(id = 1L, username = "l.lorenzo")
    void testViewInstructorCourseParticipant() throws Exception {

        CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        courseScheduleDetailForm.setScheduledEndDateTime(ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"));
        courseScheduleDetailForm.setScheduledStartDateTime(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"));
        AttendanceParticipantDetail attendanceParticipantDetail = new AttendanceParticipantDetail();
        attendanceParticipantDetail.setEmployeeNumber("220054288"); 
        attendanceParticipantDetail.setEmail("k.abad@fujitsu.com");
        attendanceParticipantDetail.setName("Abad, Kenneth");

        CourseParticipant courseParticipant = createCourseParticipant();

        Set<CourseParticipant> courseParticipantSet = new HashSet<CourseParticipant>();
        courseParticipantSet.add(courseParticipant);

        CourseSchedule courseSchedule = createCourseSchedule();

        Set<CourseSchedule> courseScheduleSet = new HashSet<>();
        courseScheduleSet.add(courseSchedule);
        when(attendanceService.findAllScheduledCoursesByInstructor(any(ZonedDateTime.class), any(ZonedDateTime.class),
                any(Long.class))).thenReturn(courseScheduleSet);
        when(attendanceService.findCourseScheduleById(any(Long.class))).thenReturn(courseParticipantSet);
        mockMvc.perform(get("/attendance/schedules/{courseScheduleId}/participants", 2L)).andDo(print())
                .andExpect(status().isOk()).andExpect(view().name("attendance/viewInstructorCourseParticipants"))
                .andExpect(model().attributeExists("courseParticipant"))
                .andExpect(model().attributeDoesNotExist("errorMessages"))
                .andExpect(model().attribute("duration", is(courseParticipant.getDuration())))
                .andExpect(model().attribute("courseParticipant", hasProperty("courseSchedules",
                        containsInAnyOrder(
                                hasProperty("courseId", is(courseSchedule.getCourseId()))))))
                .andExpect(model().attribute("courseParticipant",
                        hasProperty("courseName", is(courseParticipant.getCourseName()))))
                .andExpect(model().attribute("courseParticipant",
                        hasProperty("instructorName", is(courseParticipant.getInstructorName()))))
                .andExpect(model().attribute("courseParticipant",
                        hasProperty("courseScheduleDetails",
                                containsInAnyOrder(hasProperty("scheduledStartDateTime",
                                        is(courseScheduleDetailForm.getScheduledStartDateTime()))))))
                .andExpect(model().attribute("courseParticipant",
                        hasProperty("courseScheduleDetails",
                                containsInAnyOrder(hasProperty("scheduledEndDateTime",
                                        is(courseScheduleDetailForm.getScheduledEndDateTime()))))))
                .andExpect(model().attribute("courseParticipant",
                        hasProperty("participants",
                                containsInAnyOrder(hasProperty("name", is(attendanceParticipantDetail.getName()))))))
                .andExpect(model().attribute("courseParticipant",
                        hasProperty("participants",
                                containsInAnyOrder(hasProperty("email", is(attendanceParticipantDetail.getEmail()))))))
                .andExpect(model().attribute("courseParticipant", hasProperty("participants",
                        containsInAnyOrder(
                                hasProperty("employeeNumber", is(attendanceParticipantDetail.getEmployeeNumber()))))))
                .andReturn();
    }

    private CourseSchedule createCourseSchedule() {
        CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L,
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00").plusDays(5)).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);

        CourseSchedule courseSchedule = new CourseSchedule.Builder(1L, 1L, "SpringBoot", 1L, "Lorenzo", "Loyce", 2L,
                "TwoNeo", 30, 100, 80, 'A').addDetail(courseScheduleDetailSet).build();
        return courseSchedule;
    }

    private CourseParticipant createCourseParticipant() {
        return new CourseParticipant.Builder(1L, 1L, "Java", "Lorenzo, Loyce", "TwoNeo", 1L, "Abad, Kenneth",
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 2.0f,
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), "k.abad@fujitsu.com", "220054288").build();
    }

}
