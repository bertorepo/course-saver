package com.fujitsu.ph.tsup.domain.rivera;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
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
        Course course = service.findById(1000L);
        assertEquals(course.getCourseId(), new Long(1000));
    }
    
    
    private Course createCourseId() {
        return new Course.Builder(new Long(1000), "Course1").build();
    }
       
    @Test
    public void testSave() {
        Course course = new Course.Builder(1000L, "Course1").build();
        service.save(course);
        assertEquals(course.getId(), new Long(1000));
        assertEquals(course.getCourseName(), "Course1");    
    }
    
        
    @Test
    public void testFindAll() {
        Set<Course> course = new HashSet<Course>();
        course.add(new Course.Builder(1000L, "Course1").build());
        when(courseDao.findAll()).thenReturn(course);
        assertEquals(service.findAll().size(), course.size());
    }
    

}