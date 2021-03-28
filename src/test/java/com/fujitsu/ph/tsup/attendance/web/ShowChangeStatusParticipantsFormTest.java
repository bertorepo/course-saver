package com.fujitsu.ph.tsup.attendance.web;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.attendance.service.AttendanceService;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
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
//Class Name   :ShowChangeStatusParticipantsFormTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                       | Content
//--------+------------+--------------------------------------------------+-------------------------
//0.01    | 07/13/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | New Creation
//0.02    | 09/02/2020 |  WS) K.Abad, WS) J.Iwarat, WS) R.Ramos           | Update
//==================================================================================================
/**
* <pre>
* The change status Test for Controller.
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
public class ShowChangeStatusParticipantsFormTest {
    
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
         * show the changed status of the participants
         * 
         * </pre>
         * 
         */
        @Test
        @WithMockCustomUser(id = 1L, username = "l.lorenzo")
        void testAttendanceController() throws Exception {

            CourseAttendance courseAttendance = createCourseAttendance();

            Set<CourseAttendance> courseAttendanceSet = new HashSet<>();
            courseAttendanceSet.add(courseAttendance);
            
            CourseSchedule courseSchedule = createCourseSchedule();
            
            Set<CourseSchedule> courseScheduleSet = new HashSet<>();
            courseScheduleSet.add(courseSchedule);
            
            
            when(attendanceService.findAllScheduledCoursesByInstructor(any(ZonedDateTime.class), 
                    any(ZonedDateTime.class), any(Long.class))).thenReturn(courseScheduleSet);
            
            when(attendanceService.findCourseAttendanceByCourseScheduleDetailId(any(Long.class)))
                    .thenReturn(courseAttendanceSet);

            mockMvc.perform(get("/attendance/{courseScheduleDetailId}/participants", 1L))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("attendance/changeAttendanceStatus"))
            .andExpect(model().attributeExists("changeStatusForm"))
            .andExpect(model().attributeDoesNotExist("errorMessages"))
            .andExpect(model().attribute("changeStatusForm",hasProperty("participants",
                    containsInAnyOrder(hasProperty("name",is(courseAttendance.getParticipantName()))))))
            .andExpect(model().attribute("changeStatusForm",hasProperty("participants",
                    containsInAnyOrder(hasProperty("loginDateTime",is(courseAttendance.getLoginDateTime()))))))
            .andExpect(model().attribute("changeStatusForm",hasProperty("participants",
                    containsInAnyOrder(hasProperty("courseAttendanceId",is(courseAttendance.getId()))))))
            .andExpect(model().attribute("changeStatusForm",hasProperty("participants",
                    containsInAnyOrder(hasProperty("participantId",is(courseAttendance.getParticipantId()))))))
            .andExpect(model().attribute("changeStatusForm",hasProperty("participants",
                    containsInAnyOrder(hasProperty("status",is(courseAttendance.getStatus()))))))           
            .andReturn();            
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
            return new CourseAttendance.Builder(1L, 1L, "SpringBoot", "Lorenzo, Loyce", "TWO/Neo", 3L, "Abad, Kenneth",
                    ZonedDateTime.parse("2020-07-06T08:30:00.000+08:00"),
                    ZonedDateTime.parse("2020-07-06T08:30:00.000+08:00").plusHours(8), 3.0f,
                    ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"),
                    ZonedDateTime.parse("2019-08-08T11:15:24.983+08:00"), 'P', 
                    "Course Description", "k.abad@fujitsu.com", 1L, "G3CC", "TRN123456").build();
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
            CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L,
                    ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
                    ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00").plusHours(8)).build();
            Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
            courseScheduleDetailSet.add(courseScheduleDetail);
            
            CourseSchedule courseSchedule = new CourseSchedule.Builder(1L, 1L, "SpringBoot", 3L, "Lorenzo", "Loyce", 4L,
                    "TwoNeo", 30, 100, 80, "A".charAt(0)).addDetail(courseScheduleDetailSet).build();
            return courseSchedule;
        }
        

}
