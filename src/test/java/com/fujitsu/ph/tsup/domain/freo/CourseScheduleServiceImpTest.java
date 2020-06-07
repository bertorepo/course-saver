package com.fujitsu.ph.tsup.domain.freo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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
public class CourseScheduleServiceImpTest {

	  @TestConfiguration
	    static class TestContextConfiguration {
	        
	        @Bean
	        CourseScheduleService CourseScheduleService() {
	           
	            return new CourseScheduleServiceImp();
	        }
	    }
	  
	    @Autowired
	    private CourseScheduleService CsService;
	  
	    
	    @MockBean
	    private CourseScheduleDao CourseScheduleDao;
	    
	    
	    @Test
	    void testSave() {
	       
	        doThrow(new DataRetrievalFailureException("error")).when(CourseScheduleDao).save(null);
	        
	        CourseSchedule CourseSchedule = createCourseSchedule();        
	        CsService.save(CourseSchedule);
	        
	        assertEquals(CourseSchedule.getId(), 20202L);
	        assertEquals(CourseSchedule.getCourseId(), 0000L);
	        assertEquals(CourseSchedule.getInstructorId(), 5555L);
	        assertEquals(CourseSchedule.getVenueId(), 8888L);
	        assertEquals(CourseSchedule.getMinRequired(), 1);
	        assertEquals(CourseSchedule.getMaxAllowed(), 2);
	        assertEquals(CourseSchedule.getStatus(), "OP");
	    }
	    
	    @Test
	    void testSaveEx() {
	       
	        doThrow(new DataRetrievalFailureException("error")).when(CourseScheduleDao).save(null);
	        
	        CourseSchedule sch = createschCourseSchedule();
	        
	        Exception eException = assertThrows(CourseScheduleException.class, () -> {
	            CsService.save(sch);
	        });
	        
	        String expectedMessage = "Course Schedule Id should not be zero or less than zero.";
	        String actualMessage = eException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage)); 
	    }


	    @Test
	    void testFindById() {
	        CourseSchedule createCrse = createCourseSchedule();
	       
	        when(CourseScheduleDao.findById(any(Long.class)))
	            .thenReturn(createCrse);
	        
	        CourseSchedule CourseSchedule = CsService.findById(20202L);
	        
	        assertEquals(createCrse.getId(), CourseSchedule.getId());

	    }

	    @Test
	    void testFindById_NotFound() {
	       
	        when(CourseScheduleDao.findById(any(Long.class)))
	            .thenThrow(new DataRetrievalFailureException("error"));
	        
	       
	        Exception eException = assertThrows(CourseScheduleException.class, () -> {
	            CsService.findById(20202L);
	        });
	        
	        String expectedMessage = "Course Schedule not found!";
	        String actualMessage = eException.getMessage();
	       
	        assertTrue(actualMessage.contains(expectedMessage));
	    }
	    
	    
	    
	    @Test
	    void testFindAll() {
	        Set<CourseSchedule> csh = new HashSet<CourseSchedule>();
	        csh.add(createschCourseSchedule());
	        when(CourseScheduleDao.findAll()).thenReturn(csh);
	        assertEquals(CsService.findAll().size(), csh.size()); 
	    }
	    
	    @Test
	    void testFindAll_NotFound() {
	        Exception eException = assertThrows(CourseScheduleException.class, () -> {
	            CsService.findAll();
	        });
	        
	        String expectedMessage = "Can't find Course Schedule Details";
	        String actualMessage = eException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	    }
	    
	    private CourseSchedule createCourseSchedule() {
	        return new CourseScheduleService.builder(20202L, 0000L, 5555L, 8888L, 1, 2, "OP").builder(); 
	    }
	    
	    private CourseSchedule createschCourseSchedule() {
	       return new CourseScheduleService.builder(20202L, 0000L, 5555L, 8888L, 1, 2, "OP").builder();
	    }
	   
}
