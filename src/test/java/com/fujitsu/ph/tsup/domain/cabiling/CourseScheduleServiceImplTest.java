package com.fujitsu.ph.tsup.domain.cabiling;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
public class CourseScheduleServiceImplTest {
    @TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        CourseScheduleService CourseScheduleService() {
            /*
             * returns the implementation to be injected
             */
            return new CourseScheduleServiceImpl();
        }
    }

    @Autowired
    private CourseScheduleService CourseScheduleService;
  
    
    @MockBean
    private CourseScheduleDao CourseScheduleDao;
    
    
    @Test
    void testSave() {
    	
        doThrow(new DataRetrievalFailureException("error")).when(CourseScheduleDao).save(null);
        
        CourseSchedule CourseSchedule = createCourseSchedule();        
        CourseScheduleService.save(CourseSchedule);     
        
        assertEquals(CourseSchedule.getId(), 654321L);
        assertEquals(CourseSchedule.getCourseId(), 654321L);
        assertEquals(CourseSchedule.getInstructorId(), 654321L);
        assertEquals(CourseSchedule.getVenueId(), 654321L);
        assertEquals(CourseSchedule.getMinRequired(), 6);
        assertEquals(CourseSchedule.getMaxAllowed(), 7);
        assertEquals(CourseSchedule.getStatus(), 'A');
    }
    
    @Test
    void testSaveEx() {
    	
        doThrow(new DataRetrievalFailureException("error")).when(CourseScheduleDao).save(null);
        
        CourseSchedule crse = createErrCourseSchedule();
        
        Exception cException = assertThrows(CourseScheduleException.class, () -> {
            CourseScheduleService.save(crse);
        });
        
        String expectedMessage = "Course Schedule Id canno be equal to or less than zero.";
        String actualMessage = cException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
    }


    @Test
    void testFindById() {
        CourseSchedule createCrse = createCourseSchedule();
        when(CourseScheduleDao.findById(any(Long.class)))
            .thenReturn(createCrse);
        
        CourseSchedule CourseSchedule = CourseScheduleService.findById(123434L);
        
        assertEquals(createCrse.getId(), CourseSchedule.getId());
;
    }

    @Test
    void testFindById_NotFound() {
    	
        when(CourseScheduleDao.findById(any(Long.class)))
            .thenThrow(new DataRetrievalFailureException("error"));
        
        
        Exception cException = assertThrows(CourseScheduleException.class, () -> {
            CourseScheduleService.findById(1L);
        });
        
        String expectedMessage = "Course Schedule not found!";
        String actualMessage = cException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    
    
    @Test
    void testFindAll() {
        Set<CourseSchedule> crse = new HashSet<CourseSchedule>();
        crse.add(createCourseSchedule());
        when(CourseScheduleDao.findAll()).thenReturn(crse);
        assertEquals(CourseScheduleService.findAll().size(), crse.size()); 
    }
    
    @Test
    void testFindAll_NotFound() {
        Exception cException = assertThrows(CourseScheduleException.class, () -> {
            CourseScheduleService.findAll();
        });
        
        String expectedMessage = "Can't find Course Schedule Details";
        String actualMessage = cException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private CourseSchedule createCourseSchedule() {
        return new CourseSchedule.Builder(654321L, 654321L, 654321L, 654321L, 6, 7, 'A').builder(); 
    }
    
    private CourseSchedule createErrCourseSchedule() {
       return new CourseSchedule.Builder(0L, 1234L, 1234L, 1234L, 5, 5, 'C').builder();
    }
    
}
