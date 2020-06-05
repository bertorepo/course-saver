package com.fujitsu.ph.tsup.domain.deguzman;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

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
    public void testSave(){
        when(courseDao.findById(anyLong())).thenReturn(createCourse());
        Course c = service.findById((long) 123);
        service.save(c);
        assertEquals(c.getCourseName(), "ISPROG1");
    }


    @Test
    public void testSaveErr() {
        when(courseDao.findById(anyLong())).thenReturn(createCourseErr());
        Course c = service.findById((long) 123);
        service.save(c);
        assertEquals(c.getCourseName(), "ISPROG");
    }
    
    private Course createCourse() {
        return new Course.Builder("ISPROG1").build();
    }
    private Course createCourseErr() {
        return new Course.Builder("ISPROG").build();
    }

    @Test
    public void testFindAll(){
        Set<Course> c = new HashSet<Course>();
        c.add(new Course.Builder("ISPROG3").build());
        when(courseDao.findAll()).thenReturn(c);
        assertEquals(courseDao.findAll().size(), c.size());
    }

    @Test
    public void testFindAllErr() {
        Set<Course> c = new HashSet<Course>();
        c.add(new Course.Builder("").build());
        when(courseDao.findAll()).thenReturn(c);
        assertEquals(courseDao.findAll().size(), c.size());
    }
    
    @Test
    public void testFindById(){
        when(courseDao.findById(anyLong()))
        .thenReturn(createCourseFindById());
        Course c = service.findById((long) 1);
        assertEquals(c.getCourseName(), "ISPROG1");
    }

    @Test
    public void testFindByIdErr() {
        when(courseDao.findById(anyLong()))
        .thenReturn(createCourseFindByIdErr());
        Course c = service.findById((long) 123);
        assertEquals(c.getCourseName(), "ISPROG");
    }
    
    private Course createCourseFindById() {
        return new Course.Builder("ISPROG1").build();
    }
    private Course createCourseFindByIdErr() {
        return new Course.Builder("ISPROG").build();
    }

}
