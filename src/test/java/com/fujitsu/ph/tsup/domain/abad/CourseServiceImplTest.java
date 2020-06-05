package com.fujitsu.ph.tsup.domain.abad;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
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
        Course course = service.findById(1000L);
        assertEquals(course.getId(), new Long(1000));
    }
    
    @Test
    public void testFindById_Unmatched() {
        when(courseDao.findById(any(Long.class)))
            .thenThrow(new ServiceException("Course not found"));
    
        Exception exception = assertThrows(ServiceException.class, () -> {
        service.findById(1000L);
        });
        
        String expectedMessage = "Course not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    private Course createCourseId() {
        return new Course.Builder(new Long(1000), "DIFFCALC").build();
    }
       
    @Test
    public void testSave() {
        Course course = new Course.Builder(1000L, "DIFFCALC").build();
        service.save(course);
        assertEquals(course.getId(), new Long(1000));
        assertEquals(course.getCourseName(), "DIFFCALC");    
    }
    
    @Test
    public void testSaveUnmatched() {
        Course course = createCourseId();
        doThrow(new ServiceException("Course not saved")).
            when(courseDao).save(any(Course.class));
            
        Exception exception = assertThrows(ServiceException.class, () -> {
            service.save(course);
                
        });

        String expectedMessage = "Course not saved";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
        
    @Test
    public void testFindAll() {
        Set<Course> course = new HashSet<Course>();
        course.add(new Course.Builder(1000L, "DIFFCALC").build());
        when(courseDao.findAll()).thenReturn(course);
        assertEquals(service.findAll().size(), course.size());
    }
    
    @Test
    public void testFindAllUnmatched() {
        doThrow(new ServiceException("Record not found")).
        when(courseDao).findAll();
    
        Exception exception = assertThrows(ServiceException.class, () -> {
        service.findAll();    
        });

        String expectedMessage = "Record not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage)); 
    }
    
}