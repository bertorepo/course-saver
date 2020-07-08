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
public class CreateCourseScheduleTest {
    
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
     * testCreateCourseSchedule with Null and Valid Value
     * Call scheduleDao.saveCourseSchedule and test if values are save and return error message if null values are save
     * <pre>
     */
    @Test
    void testCreateCourseSchedule() {
        doThrow(new DataRetrievalFailureException("error")).when(scheduleDao).saveCourseSchedule(null);
        
        CourseSchedule courseSchedule = createCourseSchedule();
        scheduleService.createCourseSchedule(courseSchedule);
        
        assertEquals(courseSchedule.getCourseId(), 1L);
        assertEquals(courseSchedule.getInstructorId(), 1L);
        assertEquals(courseSchedule.getVenueId(), 1L);
        assertEquals(courseSchedule.getMinRequired(), 1);
        assertEquals(courseSchedule.getCourseScheduleDetail().size(), 1);
        assertEquals(courseSchedule.getMaxAllowed(), 10);
    }
    
    /**
     * <pre>
     * testCreateCourseSchedule with Error Message
     * Call scheduleDao.saveCourseSchedule and test if error message is return
     * <pre>
     */
    @Test
    void testCreateCourseSchedule_Error() {
        doThrow(new DataRetrievalFailureException("error")).when(scheduleDao).saveCourseSchedule(any(CourseSchedule.class));
        
        CourseSchedule courseSchedule = createCourseSchedule();
        
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () 
                -> scheduleService.createCourseSchedule(courseSchedule));
        
        String expectedMessage = "Can't save course schedule";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    
    /**
     * <pre>
     * Build data for Create Course Schedule
     * <pre>
     */
    private CourseSchedule createCourseSchedule() {
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(1L, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5)).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);
        return new CourseSchedule.Builder(1L, 1L, 1L, 1, courseScheduleDetailSet).maxAllowed(10).build();
    }
}
