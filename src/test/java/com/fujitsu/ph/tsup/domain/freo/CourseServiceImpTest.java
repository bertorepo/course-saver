package com.fujitsu.ph.tsup.domain.freo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;



@ExtendWith(SpringExtension.class)
 public class CourseServiceImpTest {
	 @TestConfiguration
	    static class TestContextConfiguration {
	        
	        @Bean
	        CourseService CourseService() {
	          
	            return new CourseServiceImp();
	        }
	    }

	    @Autowired
	    private CourseService courseService;
	  
	    
	    @MockBean
	    private CourseDao cdao;
	    
	    
	    @Test
	    void testSave() {
	        doThrow(new DataRetrievalFailureException("Error Occurs")).when(cdao).save(null);
	        
	        Course cs = createCourse();        
	        courseService.save(cs);     
	        
	        assertEquals(cs.getId(), 2020202L);
	        assertEquals(cs.getCourseName(), "SDEM1");
	    }
	    
	    @Test
	    void testSaveEx() {
	        doThrow(new DataRetrievalFailureException("Error Occurs")).when(cdao).save(null);
	        
	        Course cs = createCrsCourse();
	        
	        Exception cseException = assertThrows(CourseException.class, () -> {
	            courseService.save(cs);
	        });
	        
	        String expectedMessage = "Course Id should not be zero or less than zero.";
	        String actualMessage = cseException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	        
	    }


	    @Test
	    void testFindById() {
	        Course createCs = createCourse();
	        when(cdao.findById(any(Long.class)))
	            .thenReturn(createCs);
	        
	        Course Course = courseService.findById(123434L);
	        
	        assertEquals(createCs.getId(), Course.getId());
	    }

	    @Test
	    void testFindById_NotFound() {
	        when(cdao.findById(any(Long.class)))
	            .thenThrow(new DataRetrievalFailureException("Error Occurs"));
	        
	        Exception cseException = assertThrows(CourseException.class, () -> {
	            courseService.findById(1L);
	        });
	        
	        String expectedMessage = "Course not found!";
	        String actualMessage = cseException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	    }
	    
	      
	    @Test
	    void testFindAll() {
	        Set<Course> cse = new HashSet<Course>();
	        cse.add(createCourse());
	        when(cdao.findAll()).thenReturn(cse);
	        assertEquals(courseService.findAll().size(), cse.size()); 
	    }
	    
	    @Test
	    void testFindAll_NotFound() {
	        Exception cseException = assertThrows(CourseException.class, () -> {
	            courseService.findAll();
	        });
	        
	        String expectedMessage = "Can't find Course Details";
	        String actualMessage = cseException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	    }

	    private Course createCourse() {
	        return new Course.Builder(123434L, "SDEM1").builder(); 
	    }
	    
	    private Course createCrsCourse() {
	       return new Course.Builder(0L, "CourseName").builder();
	    }
	    

}
