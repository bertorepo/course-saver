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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.time.ZonedDateTime;
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
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

@ExtendWith(SpringExtension.class)
public class FindAllInstructorsTest {
    
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
     * testFindAllInstructors with Valid Values
     * Call scheduleDao.findAllInstructors and test if instructors is return
     * <pre>
     */
    @Test
    void testFindAllInstructors() {
        InstructorForm instructorForm = new InstructorForm();
        instructorForm.setId(1L);
        instructorForm.setName("TEST");
        
        Set<InstructorForm> instructors = new HashSet<>();
        instructors.add(instructorForm);
        
        when(scheduleDao.findAllInstructors()).thenReturn(instructors);
        Set<InstructorForm> service = scheduleService.findAllInstructors();
        assertNotNull(service);
        assertEquals(service.size(), instructors.size());
    }
    
    /**
     * <pre>
     * testFindAllInstructors with Error Message
     * Call scheduleDao.findAllInstructors and test if an error message is return
     * <pre>
     */
    @Test
    void testFindAllInstructors_Error() {
        when(scheduleDao.findAllInstructors()).thenThrow(new DataRetrievalFailureException("error"));
        
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () 
                -> scheduleService.findAllInstructors());
        
        String expectedMessage = "Can't access Instructors";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
        
    }
    
    /**
     * <pre>
     * testFindAllInstructors with Null Value
     * Call scheduleDao.findAllInstructors and test if an error message is return
     * <pre>
     */
    @Test
    void testFindAllInstructors_Null() {
        when(scheduleDao.findAllInstructors()).thenReturn(null);
        
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () 
                -> scheduleService.findAllInstructors());
        
        String expectedMessage = "Can't find Instructors";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage)); 
    }

}
