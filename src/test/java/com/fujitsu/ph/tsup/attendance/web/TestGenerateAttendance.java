package com.fujitsu.ph.tsup.attendance.web;


import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.domain.CourseScheduleDetail;
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
* The Generate Attendance Sheet Test for controller.
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
public class TestGenerateAttendance {
    
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
    void testAttendanceController() throws Exception {

        CourseAttendance courseAttendance1 = createCourseAttendance1();

        Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>();
        courseAttendanceSet.add(courseAttendance1);
        
        CourseSchedule courseSchedule = createCourseSchedule();

        Set<CourseSchedule> courseScheduleSet = new HashSet<>();
        courseScheduleSet.add(courseSchedule);
 
        when(attendanceService.findAllScheduledCoursesByInstructor(any(ZonedDateTime.class), any(ZonedDateTime.class),
                any(Long.class))).thenReturn(courseScheduleSet);
        when(attendanceService.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                .thenReturn(courseAttendanceSet);
        mockMvc.perform(get("/attendance/generate/{courseScheduleDetailId}/present", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("attendance/generateAttendanceSheet"))
                .andExpect(model().attributeExists("generateAttendance"))
                .andExpect(model().attributeDoesNotExist("errorMessages"))
                .andExpect(model().attribute("generateAttendance",
                        hasProperty("courseName", is(courseSchedule.getCourseName()))))
                .andExpect(model().attribute("generateAttendance",
                        hasProperty("instructorName",is(courseAttendance1.getInstructorName()))))
                .andExpect(model().attribute("generateAttendance",
                        hasProperty("venueName", is(courseSchedule.getVenueName()))))
                .andExpect(model().attribute("generateAttendance",
                        hasProperty("scheduledEndDateTime", is(courseAttendance1.getScheduleEndDateTime()))))
                .andExpect(model().attribute("generateAttendance",
                        hasProperty("scheduledStartDateTime", is(courseAttendance1.getScheduleStartDateTime()))))
                .andExpect(model().attribute("generateAttendance",
                        hasProperty("duration", is(courseAttendance1.getDuration()))))
                .andExpect(model().attribute("generateAttendance",hasProperty("participants",
                        containsInAnyOrder(hasProperty("name",is(courseAttendance1.getParticipantName()))))))
                .andExpect(model().attribute("generateAttendance",hasProperty("participants",
                        containsInAnyOrder(hasProperty("status",is(courseAttendance1.getStatus()))))))
                .andExpect(model().attribute("generateAttendance",hasProperty("participants",
                        containsInAnyOrder(hasProperty("loginDateTime",is(courseAttendance1.getLoginDateTime()))))))
                
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

    private CourseAttendance createCourseAttendance1() {
        CourseAttendance courseAttendance1 = new CourseAttendance.Builder(1L, 1L, "SpringBoot", "Lorenzo, Loyce",
                "TwoNeo", 3L, "Abad, Kenneth", ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 3.0f,
                ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00").plusHours(8), 'A').build();
        return courseAttendance1;
    }

}
