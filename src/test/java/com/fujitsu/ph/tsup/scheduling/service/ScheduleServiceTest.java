package com.fujitsu.ph.tsup.scheduling.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.scheduling.dao.ScheduleDao;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

@ExtendWith(SpringExtension.class)
public class ScheduleServiceTest {
    
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        
        ScheduleService scheduleService() {
            return new ScheduleServiceImpl();
        }
        
    }
    
    @Autowired
    ScheduleService scheduleService;
    
    @MockBean
    ScheduleDao scheduleDao;
    
    @Test
    void testFindAllScheduledCourses() {
        CourseSchedule courseSchedule = createViewCourseSchedule();
        Set<CourseSchedule> courseScheduleSet = new HashSet<>();
        courseScheduleSet.add(courseSchedule);
        when(scheduleDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class))).thenReturn(courseScheduleSet);
        Set<CourseSchedule> anotherCourseSchedule = scheduleDao.findAllScheduledCourses(ZonedDateTime.now(), ZonedDateTime.now().plusHours(5));
        assertEquals(courseScheduleSet.size(), anotherCourseSchedule.size());
        
    }
    
    @Test
    void testFindAllCourses() {
        CourseForm courseForm = new CourseForm();
        courseForm.setId(1L);
        courseForm.setName("TEST");
        
        Set<CourseForm> courses = new HashSet<>();
        courses.add(courseForm);
        
        when(scheduleDao.findAllCourses()).thenReturn(courses);
        assertEquals(scheduleDao.findAllCourses().size(), courses.size());
    }
    
    @Test
    void testFindAllInstructors() {
        InstructorForm instructorForm = new InstructorForm();
        instructorForm.setId(1L);
        instructorForm.setName("TEST");
        
        Set<InstructorForm> instructors = new HashSet<>();
        instructors.add(instructorForm);
        
        when(scheduleDao.findAllInstructors()).thenReturn(instructors);
        assertEquals(scheduleDao.findAllInstructors().size(), instructors.size());
    }
    
    @Test
    void testFindAllVenues() {
        VenueForm venueForm = new VenueForm();
        venueForm.setId(1L);
        venueForm.setName("TEST");
        
        Set<VenueForm> venues = new HashSet<>();
        venues.add(venueForm);
        
        when(scheduleDao.findAllVenues()).thenReturn(venues);
        assertEquals(scheduleDao.findAllVenues().size(), venues.size());
    }
    
    @Test
    void testCreateCourseSchedule() {
        doThrow(new DataRetrievalFailureException("error")).when(scheduleDao).saveCourseSchedule(null);
        
        CourseSchedule courseSchedule = createCourseSchedule();
        scheduleService.createCourseSchedule(courseSchedule);
        
        assertEquals(courseSchedule.getCourseId(), 1L);
        assertEquals(courseSchedule.getInstructorId(), 1L);
        assertEquals(courseSchedule.getVenueId(), 1L);
        assertEquals(courseSchedule.getMinRequired(), 1);
        assertEquals(courseSchedule.getCourseScheduleDetail().size(), 1);
        assertEquals(courseSchedule.getMaxAllowed(), 10);
    }
    
    private CourseSchedule createCourseSchedule() {
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(1L, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5)).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);
        return new CourseSchedule.Builder(1L, 1L, 1L, 1, courseScheduleDetailSet).maxAllowed(10).build();
    }
    
    private CourseSchedule createViewCourseSchedule() {
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(1L, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5)).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);
        
        return new CourseSchedule.Builder(1L, 1L, "Dummy", 1L, "Dummy", "Dummy", 1L, "Dummy", 1, 10, "A".charAt(0))
                    .addDetail(courseScheduleDetailSet).build();
    }

}
