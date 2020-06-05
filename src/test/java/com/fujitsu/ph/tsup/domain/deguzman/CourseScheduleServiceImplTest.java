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
    public void testSave(){
        when(courseScheduleDao.findById(anyLong())).thenReturn(createCourseSchedule());
        CourseSchedule c = service.findById((long) 123);
        service.save(c);
        assertEquals(c.getStatus(), "A");
    }


    @Test
    public void testSaveErr() {
        when(courseScheduleDao.findById(anyLong())).thenReturn(createCourseScheduleErr());
        CourseSchedule c = service.findById((long) 123);
        service.save(c);
        assertEquals(c.getStatus(), "C");
    }
    
    private CourseSchedule createCourseSchedule() {
        return new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 5, 100, "A").build();
    }
    private CourseSchedule createCourseScheduleErr() {
        return new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 5, 100, "C").build();
    }

    @Test
    public void testFindAll(){
        Set<CourseSchedule> c = new HashSet<CourseSchedule>();
        c.add(new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 5, 100, "A").build());
        when(courseScheduleDao.findAll()).thenReturn(c);
        assertEquals(courseScheduleDao.findAll().size(), c.size());
    }

    @Test
    public void testFindAllErr() {
        Set<CourseSchedule> c = new HashSet<CourseSchedule>();
        c.add(new CourseSchedule.Builder(null, null, null, 0,0, "C").build());
        when(courseScheduleDao.findAll()).thenReturn(c);
        assertEquals(courseScheduleDao.findAll().size(), c.size());
    }
    
    @Test
    public void testFindById(){
        when(courseScheduleDao.findById(anyLong()))
        .thenReturn(createCourseScheduleFindById());
        CourseSchedule c = service.findById((long) 1);
        assertEquals(c.getStatus(), "A");
    }

    @Test
    public void testFindByIdErr() {
        when(courseScheduleDao.findById(anyLong()))
        .thenReturn(createCourseScheduleFindByIdErr());
        CourseSchedule c = service.findById((long) 1);
        assertEquals(c.getStatus(), "C");
    }
    
    private CourseSchedule createCourseScheduleFindById() {
        return new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 5, 100, "A").build();
    }
    private CourseSchedule createCourseScheduleFindByIdErr() {
        return new CourseSchedule.Builder((long) 1, (long) 1, (long) 1, 5, 100, "C").build();
    }

}
