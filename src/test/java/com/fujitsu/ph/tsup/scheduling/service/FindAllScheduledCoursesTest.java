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
public class FindAllScheduledCoursesTest {
    
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
     * testFindAllScheduledCourses with Valid Values
     * Call scheduleDao.findAllSchedledCourses and test if scheduled courses is return
     * <pre>
     */
    @Test
    void testFindAllScheduledCourses() {
        CourseSchedule courseSchedule = createViewCourseSchedule();
        Set<CourseSchedule> courseScheduleSet = new HashSet<>();
        courseScheduleSet.add(courseSchedule);
        when(scheduleDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class)))
                .thenReturn(courseScheduleSet);
        Set<CourseSchedule> anotherCourseSchedule = 
                scheduleDao.findAllScheduledCourses(ZonedDateTime.now(), ZonedDateTime.now().plusHours(5));
        assertEquals(courseScheduleSet.size(), anotherCourseSchedule.size());
    }
    
    
    /**
     * <pre>
     * testFindAllScheduledCourses with Error Message
     * Call scheduleDao.findAllSchedledCourses and test if an error message is return
     * <pre>
     */
    @Test
    void testFindAllScheduledCourses_Error() {
        when(scheduleDao.findAllScheduledCourses(any(ZonedDateTime.class), any(ZonedDateTime.class)))
                .thenThrow(new DataRetrievalFailureException("error"));
        
        Exception courseScheduleException = assertThrows(IllegalArgumentException.class, () 
                -> scheduleService.findAllScheduledCourses(ZonedDateTime.now(), ZonedDateTime.now().plusHours(5)));
        
        String expectedMessage = "Can't access fromDate and toDate.";
        String actualMessage = courseScheduleException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    /**
     * <pre>
     * Builded data for Create View Course Schedule
     * <pre>
     */
    private CourseSchedule createViewCourseSchedule() {
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(1L, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5), 1f).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);
        
        return new CourseSchedule.Builder(1L, 1L, "Dummy", 1L, "Dummy", "Dummy", 1L, "Dummy", 1, 10, "A".charAt(0))
                    .addDetail(courseScheduleDetailSet).build();
    }
}
