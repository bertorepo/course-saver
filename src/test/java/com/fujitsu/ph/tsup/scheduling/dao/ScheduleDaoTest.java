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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.ZoneId;
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
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;


@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)

class ScheduleDaoTest {

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
	

	/**
     * <pre>
     * testFindAllCourses with valid values
     * scheduleDao.findAllCourses and test if the methods find all the courses
     * <pre>
     */
	@Test
    void testFindAllCourses() {
		Set<CourseForm> courseFormSet = scheduleDao.findAllCourses();
		
		for(CourseForm courses : courseFormSet) {
		    System.out.println("Course ID:" + courses.getId());
		    System.out.println("Course Name:" + courses.getName()+"\n");
		}
		System.out.println("CourseForm Size:"+ courseFormSet.size()+"\n");
		assertNotNull(courseFormSet.size());
	}
	
	/**
     * <pre>
     * testFindAllInstructors with valid values
     * scheduleDao.findAllInstructors and test if the methods find all the instrutors
     * <pre>
     */
	@Test
    void testFindAllInstructors() {
		Set<InstructorForm> instructorFormSet = scheduleDao.findAllInstructors();
		
		for(InstructorForm instructors : instructorFormSet) {
		    System.out.println("Instructor ID: "+ instructors.getId());
		    System.out.println("Instructor Name: "+ instructors.getName()+"\n");
		}
		System.out.println("InstructorForm Size:"+ instructorFormSet.size()+"\n");
		assertNotNull(instructorFormSet.size());
	}
	
	/**
     * <pre>
     * testFindAllVenues with valid values
     * scheduleDao.findAllVenues and test if the methods find all the venues
     * <pre>
     */
	@Test
	 void testFindAllVenues() {
		Set<VenueForm> venueFormSet = scheduleDao.findAllVenues();
		
		for(VenueForm venues : venueFormSet) {
		    System.out.println("Venue ID: "+ venues.getId());
		    System.out.println("Venue Name: "+ venues.getName()+"\n");
		}
		System.out.println("VenueForm Size:" + venueFormSet.size()+"\n");
		assertNotNull(venueFormSet.size());
		 
	 }
	
	/**
     * <pre>
     * Builded data for create course schedule
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
