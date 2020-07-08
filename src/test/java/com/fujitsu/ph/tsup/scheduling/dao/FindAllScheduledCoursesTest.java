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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

class FindAllScheduledCoursesTest {

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
     * testFindAllScheduledCourses with valid values
     * Call scheduleDao.findAllScheduledCourses and test if the methods find all the scheduled courses
     * <pre>
     */
	@Test
	void testFindAllScheduledCourses() {
		
	    ZonedDateTime fromDateTime =  ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-01 10:00:00").toInstant(),
                ZoneId.of("UTC"));
		
		ZonedDateTime toDateTime = ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-06 10:00:00").toInstant(),
                ZoneId.of("UTC"));
		
		Set<CourseSchedule> courseScheduleSet = scheduleDao.findAllScheduledCourses
				(fromDateTime, toDateTime);
		
		for(CourseSchedule courseSched : courseScheduleSet) {
		    System.out.println("Course Schedule ID: "+ courseSched.getId());
		    System.out.println("Course ID: "+ courseSched.getCourseId());
		    System.out.println("Course Name: "+ courseSched.getCourseName());
		    System.out.println("Instructor ID: "+ courseSched.getInstructorId());
		    System.out.println("Instructor Name: "+courseSched.getInstructorFirstName()+" "+courseSched.getInstructorLastName());
		    System.out.println("Venue ID: "+courseSched.getVenueId());
		    System.out.println("Venue Name: "+courseSched.getVenueName());
		    System.out.println("Minimum Required: "+courseSched.getMinRequired());
		    System.out.println("Maximum Allowed: "+courseSched.getMaxAllowed());
		    System.out.println("Status: "+courseSched.getStatus()+"\n");
		    
		    for(CourseScheduleDetail courseSchedDet : courseSched.getCourseScheduleDetail()) {
		        /**
		         * Checks if Scheduled Start DateTime is in between fromDateTime and toDateTime
		         */
		        assertTrue((!courseSchedDet.getScheduledStartDateTime().isBefore(fromDateTime)) 
		                && courseSchedDet.getScheduledStartDateTime().isBefore(toDateTime));
		        System.out.println("Course Schedule ID: "+courseSchedDet.getCourseScheduleId());
		        System.out.println("Scheduled Start Date Time: "+courseSchedDet.getScheduledStartDateTime());
		        System.out.println("Scheduled End Date Time: "+courseSchedDet.getScheduledEndDateTime());
		        System.out.println("Duration: "+courseSchedDet.getDuration()+"\n");
		        
		    }
		}
		System.out.println("Course Schedule Size:" + courseScheduleSet.size()+"\n");
		assertNotNull(courseScheduleSet.size());
	}
}
	