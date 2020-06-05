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
public class CourseServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @TestConfiguration
    static class CourseServiceImplestContextConfiguration {
        
        @Bean
        CourseService courseService() {
            return new CourseServiceImpl();
        }
    }
    
    @Autowired
    private CourseService service;
    
    @MockBean
    private CourseDao courseDao;
    
    @Test
    public void testFindById() {
        when(courseDao.findById(anyLong()))
            .thenReturn(createCourseId());
        Course course = service.findById((long) 1000);
        assertEquals(course.getId(), new Long(1000));
    }
    
    @Test
    public void testFindById_Unmatched() {
        when(courseDao.findById(anyLong()))
            .thenReturn(createCourseIdUnmatched());
        Course course = service.findById((long) 2020);
        assertEquals(course.getId(), new Long(2020));
    }
    
    private Course createCourseId() {
        return new Course.Builder(new Long(1000), "DIFFCALC").build();
    }
    
    private Course createCourseIdUnmatched() {
        return new Course.Builder(new Long(2020), "INTEGCALC").build();
    }
    
    @Test
    public void testSave() {
        Course course = new Course.Builder((long) 1000, "DIFFCALC").build();
        service.save(course);
        assertEquals(course.getId(), new Long(1000));
        assertEquals(course.getCourseName(), "DIFFCALC");    
    }
    
    @Test
    public void testSaveUnmatched() {
        Course course = new Course.Builder((long) 2020, "INTEGCALC").build();
        service.save(course);
        assertEquals(course.getId(), new Long(2020));
        assertEquals(course.getCourseName(), "INTEGCALC");
    }
        
    @Test
    public void testFindAll() {
        Set<Course> course = new HashSet<Course>();
        course.add(new Course.Builder((long) 1000, "DIFFCALC").build());
        when(courseDao.findAll()).thenReturn(course);
        assertEquals(service.findAll().size(), course.size());
    }
    
    @Test
    public void testFindAllUnmatched() {
        Set<Course> course = new HashSet<Course>();
        course.add(new Course.Builder((long) 2020, "INTEGCALC").build());
        assertEquals(service.findAll().size(), course.size());
        
      
    }
    
}