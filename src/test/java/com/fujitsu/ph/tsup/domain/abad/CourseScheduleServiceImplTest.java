package com.fujitsu.ph.tsup.domain.abad;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CourseScheduleServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @TestConfiguration
    static class CourseScheduleServiceImplestContextConfiguration {
        
        @Bean
        CourseScheduleService courseScheduleService() {
            return new CourseScheduleServiceImpl();
        }
    }
    
    @Autowired
    private CourseScheduleService service;
    
    @MockBean
    private CourseScheduleDao courseScheduleDao;
    
    @Test
    public void testFindById() {
        when(courseScheduleDao.findById(anyLong()))
            .thenReturn(createCourseScheduleId());
        CourseSchedule course = service.findById(1000L);
        assertEquals(course.getId(), new Long(1000));
    }
    
    @Test
    public void testFindById_Unmatched() {
        when(courseScheduleDao.findById(anyLong()))
            .thenReturn(createCourseScheduleIdUnmatched());
        CourseSchedule course = service.findById(2020L);
        assertEquals(course.getId(), new Long(2020));
    }
    
    private CourseSchedule createCourseScheduleId() {
        return new CourseSchedule.Builder(1000L, 2020L, 2030L, 2040L, 10, 1000, "X").build();
    }
    
    private CourseSchedule createCourseScheduleIdUnmatched() {
        return new CourseSchedule.Builder(2020L, 2020L, 2030L, 2040L, 10, 1000, "A").build();
    }
    
    @Test
    public void testSave() {
        CourseSchedule course = new CourseSchedule.Builder(1000L, 2020L, 2030L, 2040L, 10, 1000, "X").build();
        service.save(course);
        assertEquals(course.getId(), new Long(1000));
        assertEquals(course.getCourseId(), new Long(2020)); 
        assertEquals(course.getInstructorId(), new Long(2030));
        assertEquals(course.getVenueId(), new Long(2040));
        assertEquals(course.getMinRequired(), 10);
        assertEquals(course.getMaxAllowed(), 1000);
        assertEquals(course.getStatus(), "X");
    }
    
    @Test
    public void testSaveUnmatched() {
        CourseSchedule course = new CourseSchedule.Builder(2020L, 2020L, 2030L, 2040L, 10, 1000, "A").build();
        service.save(course);
        assertEquals(course.getId(), new Long(2020));
        assertEquals(course.getCourseId(), new Long(2020));
        assertEquals(course.getInstructorId(), new Long(2030));
        assertEquals(course.getVenueId(), new Long(2040));
        assertEquals(course.getMinRequired(), 10);
        assertEquals(course.getMaxAllowed(), 1000);
        assertEquals(course.getStatus(), "A");
    }
    
    @Test
    public void testFindAll() {
        Set<CourseSchedule> course = new HashSet<CourseSchedule>();
        course.add(new CourseSchedule.Builder(1000L, 2020L, 2030L, 2040L, 10, 1000, "X").build());
        when(courseScheduleDao.findAll()).thenReturn(course);
        assertEquals(service.findAll().size(), course.size());
    }
    
    @Test
    public void testFindAllUnmatched() {
        Set<CourseSchedule> course = new HashSet<CourseSchedule>();
        course.add(new CourseSchedule.Builder(0L, 0L, 0L, 0L, 10, 1000, "").build());
        assertEquals(service.findAll().size(), course.size());
    }
    
}