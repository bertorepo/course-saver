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
public class FindAllVenuesTest {
    
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
     * testFindAllVenues with Valid Values
     * Call scheduleDao.findAllVenues and test if values is return
     * <pre>
     */
    @Test
    void testFindAllVenues() {
        VenueForm venueForm = new VenueForm();
        venueForm.setId(1L);
        venueForm.setName("TEST");
        
        Set<VenueForm> venues = new HashSet<>();
        venues.add(venueForm);
        
        when(scheduleDao.findAllVenues()).thenReturn(venues);
        
        Set<VenueForm> service = scheduleService.findAllVenues();
        assertNotNull(service);
        assertEquals(service.size(), venues.size());
    }
    
    /**
     * <pre>
     * testFindAllVenues with Error Message
     * Call scheduleDao.findAllVenues and test if an error message is return
     * <pre>
     */
    @Test
    void testFindAllVenues_Error() {
        when(scheduleDao.findAllVenues()).thenThrow(new DataRetrievalFailureException("error"));
        
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () 
                -> scheduleService.findAllVenues());
        
        String expectedMessage = "Can't access Venues";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
        
    }
    
    /**
     * <pre>
     * testFindAllVenues with Null Value
     * Call scheduleDao.findAllVenues and test if an error message is return
     * <pre>
     */
    @Test
    void testFindAllVenues_Null() {
        when(scheduleDao.findAllVenues()).thenReturn(null);
        
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () 
                -> scheduleService.findAllVenues());
        
        String expectedMessage = "Can't find Venues";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage)); 
    }
}
