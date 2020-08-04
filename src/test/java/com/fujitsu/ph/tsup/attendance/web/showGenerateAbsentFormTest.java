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
//Class Name   :showGenerateAbsentFormTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                                       | Content
//--------+------------+--------------------------------------------------+-------------------------
//0.01    | 07/13/2020 |  WS) K.Abad                                      | New Creation
//==================================================================================================
/**
* <pre>
* The show generate absent form Test for Controller.
* In this class, it test the method using mockito 
* 
* <pre>
* 
* @version 0.01
* @author k.abad
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
public class showGenerateAbsentFormTest  {
    
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
     * show generate absent form test
     * 
     * </pre>
     * 
     */
    @Test
    @WithMockCustomUser(id = 1L, username = "l.lorenzo")
     void testAttendanceController() throws Exception{
                     
      CourseSchedule courseSchedule = createCourseSchedule();
      
      Set<CourseSchedule> courseScheduleSet = new HashSet<>();
      courseScheduleSet.add(courseSchedule);  
      
      CourseAttendance attendanceOne = courseAbsentOne();
      
      Set<CourseAttendance> courseAttendanceSet = new HashSet<CourseAttendance>(); 
      courseAttendanceSet.add(attendanceOne);
      
      when(attendanceService.findAllScheduledCoursesByInstructor(any(ZonedDateTime.class), 
              any(ZonedDateTime.class), any(Long.class)))
              .thenReturn(courseScheduleSet);
      
      when(attendanceService.findCourseAbsentParticipantByCourseScheduleDetailId(any(Long.class)))
              .thenReturn(courseAttendanceSet);

      mockMvc.perform(get("/attendance/generate/{courseScheduleDetailId}/absent",1L))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(view().name("attendance/attendanceForAbsent"))
      .andExpect(model().attributeExists("attendanceForAbsent"))
      .andExpect(model().attributeDoesNotExist("errorMessages"))
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("courseName", 
              is(courseSchedule.getCourseName()))))     
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("courses", 
              containsInAnyOrder(hasProperty("courseName",is(attendanceOne.getCourseName() 
                      + " | " + attendanceOne.getScheduleStartDateTime() + " --- " 
                      + attendanceOne.getScheduleEndDateTime()))))))     
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("instructorName", 
              is(attendanceOne.getInstructorName()))))
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("venueName", 
              is(attendanceOne.getVenueName()))))
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("scheduledStartDateTime", 
              is(attendanceOne.getScheduleStartDateTime()))))
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("scheduledEndDateTime", 
              is(attendanceOne.getScheduleEndDateTime()))))      
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("duration", 
              is(attendanceOne.getDuration()))))
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("participants",
              containsInAnyOrder(hasProperty("name",is(attendanceOne.getParticipantName()))))))
      .andExpect(model().attribute("attendanceForAbsent", hasProperty("participants",
              containsInAnyOrder(hasProperty("status",is(attendanceOne.getStatus()))))))
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
      CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L, ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
              ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00").plusHours(8)).build();
      Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
      courseScheduleDetailSet.add(courseScheduleDetail);
      
      CourseSchedule courseSchedule = new CourseSchedule.Builder(1L, 1L, "java", 3L, "Lorenzo", "Loyce", 4L,
              "TwoNeo", 30, 100, 80, "A".charAt(0)).addDetail(courseScheduleDetailSet).build();
      return courseSchedule;
  }

    /**
     * <pre>
     * Creates an instance of the CourseAttendance using the given builder class.
     * 
     * <pre>
     * 
     * @return courseAttendance
     */
    private CourseAttendance courseAbsentOne() {
        CourseAttendance courseAttendance1 = new CourseAttendance.Builder(1L, 1L, "java", 
                "Lorenzo, Loyce", "WFH", 22L, "Abad, Kenneth",  
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), 
                ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00").plusHours(8), 
                2.0f, ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 'A').absent().build();
        return courseAttendance1;
  }
} 
