package com.fujitsu.ph.tsup.domain.jimenez;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

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

@ExtendWith(SpringExtension.class)
public class CourseServiceImplTest {
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        CourseService CourseService() {
            /*
             * returns the implementation to be injected
             */
            return new CourseServiceImpl();
        }
    }

    @Autowired
    private CourseService courseService;
  
    
    @MockBean
    private CourseDao courseDao;
    
    
    @Test
    void testSave() {
        doThrow(new DataRetrievalFailureException("error")).when(courseDao).save(null);
        
        Course Course = createCourse();        
        courseService.save(Course);     
        
        assertEquals(Course.getId(), 123434L);
        assertEquals(Course.getName(), "SpringBoot");
    }
    
    @Test
    void testSaveEx() {
        doThrow(new DataRetrievalFailureException("error")).when(courseDao).save(null);
        
        Course crse = createErrCourse();
        
        Exception cException = assertThrows(CourseException.class, () -> {
            courseService.save(crse);
        });
        
        String expectedMessage = "Course Id should not be zero or less than zero.";
        String actualMessage = cException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
    }



    @Test
    void testFindById() {
        Course createCrse = createCourse();
        when(courseDao.findById(any(Long.class)))
            .thenReturn(createCrse);
        
        Course Course = courseService.findById(123434L);
        
        assertEquals(createCrse.getId(), Course.getId());
;
    }

    @Test
    void testFindById_NotFound() {
        when(courseDao.findById(any(Long.class)))
            .thenThrow(new DataRetrievalFailureException("error"));
        
        Exception cException = assertThrows(CourseException.class, () -> {
            courseService.findById(1L);
        });
        
        String expectedMessage = "Course not found!";
        String actualMessage = cException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    
    
    @Test
    void testFindAll() {
        Set<Course> crse = new HashSet<Course>();
        crse.add(createCourse());
        when(courseDao.findAll()).thenReturn(crse);
        assertEquals(courseService.findAll().size(), crse.size()); 
    }
    
    @Test
    void testFindAll_NotFound() {
        Exception cException = assertThrows(CourseException.class, () -> {
            courseService.findAll();
        });
        
        String expectedMessage = "Can't find Course Details";
        String actualMessage = cException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private Course createCourse() {
        return new Course.Builder(123434L, "SpringBoot").builder(); 
    }
    
    private Course createErrCourse() {
       return new Course.Builder(0L, "name").builder();
    }
    
}

