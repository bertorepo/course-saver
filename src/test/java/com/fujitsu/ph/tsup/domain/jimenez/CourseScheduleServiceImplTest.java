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
        //This function will return a DataRetrievalFailureException when save() of CourseScheduleDao is null.
        doThrow(new DataRetrievalFailureException("error")).when(CourseScheduleDao).save(null);
        
        CourseSchedule CourseSchedule = createCourseSchedule();        
        CourseScheduleService.save(CourseSchedule);     
        
        assertEquals(CourseSchedule.getId(), 123434L);
        assertEquals(CourseSchedule.getCourseId(), 1234L);
        assertEquals(CourseSchedule.getInstructorId(), 1234L);
        assertEquals(CourseSchedule.getVenueId(), 1234L);
        assertEquals(CourseSchedule.getMinRequired(), 5);
        assertEquals(CourseSchedule.getMaxAllowed(), 5);
        assertEquals(CourseSchedule.getStatus(), 'C');
    }
    
    @Test
    void testSaveEx() {
        //This function will return a DataRetrievalFailureException when save() of CourseScheduleDao is null.
        doThrow(new DataRetrievalFailureException("error")).when(CourseScheduleDao).save(null);
        
        CourseSchedule crse = createErrCourseSchedule();
        
        Exception cException = assertThrows(CourseScheduleException.class, () -> {
            CourseScheduleService.save(crse);
        });
        
        String expectedMessage = "Course Schedule Id should not be zero or less than zero.";
        String actualMessage = cException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
    }


    @Test
    void testFindById() {
        CourseSchedule createCrse = createCourseSchedule();
        //This function will return createCourseSchedule when a data has been passed to findbyId.
        when(CourseScheduleDao.findById(any(Long.class)))
            .thenReturn(createCrse);
        
        CourseSchedule CourseSchedule = CourseScheduleService.findById(123434L);
        
        assertEquals(createCrse.getId(), CourseSchedule.getId());
;
    }

    @Test
    void testFindById_NotFound() {
        //This function will return an error when a data has been passed to findbyId.
        //To test this method properly, no parameters should be passed.
        when(CourseScheduleDao.findById(any(Long.class)))
            .thenThrow(new DataRetrievalFailureException("error"));
        
        //This will get the exception thrown by inserting just a 1L
        Exception cException = assertThrows(CourseScheduleException.class, () -> {
            CourseScheduleService.findById(1L);
        });
        
        String expectedMessage = "Course Schedule not found!";
        String actualMessage = cException.getMessage();
        //This will assert True if the exception message matches the expectedMessage
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
        return new CourseSchedule.Builder(123434L, 1234L, 1234L, 1234L, 5, 5, 'C').builder(); 
    }
    
    private CourseSchedule createErrCourseSchedule() {
       return new CourseSchedule.Builder(0L, 1234L, 1234L, 1234L, 5, 5, 'C').builder();
    }
    
}


