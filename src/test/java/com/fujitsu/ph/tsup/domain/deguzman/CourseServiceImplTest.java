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
        Course c = new Course.Builder((long) 1, "ISPROG1").build();
        service.save(c);
        assertEquals(c.getId(), new Long (1));
        assertEquals(c.getCourseName(), "ISPROG1");
    }


    @Test
    public void testSaveErr() {
        Course c = new Course.Builder((long) 0, "ISPROG").build();
        service.save(c);
        assertEquals(c.getId(), new Long (0));
        assertEquals(c.getCourseName(), "ISPROG");
    }
    
    @Test
    public void testFindAll(){
        Set<Course> c = new HashSet<Course>();
        c.add(new Course.Builder((long) 1, "ISPROG1").build());
        when(courseDao.findAll()).thenReturn(c);
        assertEquals(service.findAll().size(), c.size());
    }

    @Test
    public void testFindAllErr() {
        Set<Course> c = new HashSet<Course>();
        c.add(new Course.Builder((long) 0, "ISPROG").build());
        assertEquals(service.findAll().size(), c.size());
    }
    
    @Test
    public void testFindById(){
        when(courseDao.findById(anyLong()))
        .thenReturn(createCourseFindById());
        Course c = service.findById((long) 1);
        assertEquals(c.getId(), new Long(1));
    }

    @Test
    public void testFindByIdErr() {
        when(courseDao.findById(anyLong()))
        .thenReturn(createCourseFindByIdErr());
        Course c = service.findById((long) 0);
        assertEquals(c.getId(), new Long(0));
    }
    
    private Course createCourseFindById() {
        return new Course.Builder(new Long(1), "ISPROG1").build();
    }
    private Course createCourseFindByIdErr() {
        return new Course.Builder(new Long(0), "ISPROG").build();
    }

}
