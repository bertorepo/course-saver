package com.fujitsu.ph.tsup.scheduling.service;
/**
* <pre>
* The Unit Testing of schedule service
* <pre>
* @version 0.01
* @author jc.jimenez
*/

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleServiceTest.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 07/02/2020 | WS) JC. Jimenez | New Creation
//
//
//=======================================================
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.fujitsu.ph.tsup.scheduling.dao.ScheduleDao;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;

@ExtendWith(SpringExtension.class)
public class FindAllCoursesTest {
    
    @TestConfiguration
    static class TestContextConfiguration {
        
    	/**
    	 * ScheduleService
    	 * @return ScheduleServiceImpl
    	 */
        @Bean
        public ScheduleService scheduleService() {
            return new ScheduleServiceImpl();
        }
        
    }
	
	/**
     * ScheduleService as dependency
     */
    @Autowired
    private ScheduleService scheduleService;
    
	
	/**
     * ScheduleDao as mock object
     */
    @MockBean
    private ScheduleDao scheduleDao;
    
    /**
     * <pre>
     * testFindAllCourses with Valid Values
     * Call scheduleDao.findAllCourses and test if courses is return
     * <pre>
     */
    @Test
    void testFindAllCourses() {
        CourseForm courseForm = new CourseForm();
        courseForm.setId(1L);
        courseForm.setName("TEST");
        
        Set<CourseForm> courses = new HashSet<>();
        courses.add(courseForm);
        
        when(scheduleDao.findAllCourses()).thenReturn(courses);
        Set<CourseForm> service = scheduleService.findAllCourses();
        assertNotNull(service);
        assertEquals(service.size(), courses.size());
    }
    
    
    /**
     * <pre>
     * testFindAllCourses with Error Message 
     * Call scheduleDao.findAllCourses and test if an error message is return
     * <pre>
     */
    @Test
    void testFindAllCourses_Error() {
        when(scheduleDao.findAllCourses()).thenThrow(new DataRetrievalFailureException("error"));
        
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () 
                -> scheduleService.findAllCourses());
        
        String expectedMessage = "Can't access Courses";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));   
    }
    
    
    /**
     * <pre>
     * testFindAllCourses with Null Value
     * Call scheduleDao.findAllCourses and test if an error message is retur
     * <pre>
     */
    @Test
    void testFindAllCourses_Null() {
        when(scheduleDao.findAllCourses()).thenReturn(null);
        
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () 
                -> scheduleService.findAllCourses());
        
        String expectedMessage = "Can't find Courses";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage)); 
    }

}
