package com.fujitsu.ph.tsup.scheduling.dao;
/**
* <pre>
* The Unit Testing of schedule dao
* <pre>
* @version 0.01
* @author jc.jimenez
* @author j.macabugao
*/

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleServiceTest.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+-----------------+---------------
//0.01    | 07/02/2020 | WS) J. Macabugao | New Creation
//0.01    | 07/03/2020 | WS) JC. Jimenez  | Update
//
//
//=======================================================
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;

@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)

class SaveCourseScheduleTest {

	/**
     * ScheduleDao as dependency
     */
	@Autowired
	private ScheduleDao scheduleDao;
	
	/**
     * Test Configuration
     */
	@TestConfiguration
    static class TestContextConfiguration {
        
		/**
    	 * ScheduleDao
    	 * @return ScheduleDaoImpl
    	 */
        @Bean
        public ScheduleDao scheduleDao() {
        	return new ScheduleDaoImpl();
        }
    }
	
	
    /**
     * <pre>
     * testSaveCourseSchedule with valid values
     * Call scheduleDao.saveCourseSchedule and test if course schedule is save in the database
     * <pre>
     */
	@Test
	 void testSaveCourseSchedule() {
	    
		CourseSchedule courseSchedule = createCourseSchedule();

		scheduleDao.saveCourseSchedule(courseSchedule);
		
		assertEquals(1L, courseSchedule.getCourseId());
		assertEquals(1L, courseSchedule.getInstructorId());
		assertEquals(1L, courseSchedule.getVenueId());
		assertEquals(1, courseSchedule.getMinRequired());
		assertEquals(10, courseSchedule.getMaxAllowed());
		assertEquals('A', courseSchedule.getStatus());
	 }
	
	
	/**
     * <pre>
     * Builded data for create course schedule
     * <pre>
     */
	private CourseSchedule createCourseSchedule() {
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(1L, ZonedDateTime.now(), ZonedDateTime.now().plusHours(5), 5f).build();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        courseScheduleDetailSet.add(courseScheduleDetail);
        return new CourseSchedule.Builder(1L, 1L, 1L, 1, courseScheduleDetailSet).maxAllowed(10).build();
    }
}