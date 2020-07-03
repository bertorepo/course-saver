package com.fujitsu.ph.tsup.scheduling.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneOffset;
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
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;


@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)

class ScheduleDaoTest {

	@Autowired
	private ScheduleDao scheduleDao;
	
	@TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public ScheduleDao scheduleDao() {
        	return new ScheduleDaoImpl();
        }
    }
	
	
	@Test
	 void testCreateCourseSchedule() {
		
		CourseSchedule courseSchedule = new CourseSchedule.Builder(1L, 1L, "aaaa", 1L, "aaaa", "aaaa", 1L, "aaaa", 
				10, 10,'A').build();

		scheduleDao.saveCourseSchedule(courseSchedule);
	 }
	
	@Test
	void testFindAllScheduledCourses() {
		
		ZonedDateTime scheduledStartDateTime = ZonedDateTime.now(ZoneOffset.UTC);
		
		ZonedDateTime scheduledEndDateTime = ZonedDateTime.now(ZoneOffset.UTC);
		
		
		//setObject(scheduledEndDateTime, Types.TIMESTAMP_WITH_TIMEZONE);
		
		Set<CourseSchedule> courseScheduleSet = scheduleDao.findAllScheduledCourses
				(scheduledStartDateTime, scheduledEndDateTime);
		
		assertNotNull(courseScheduleSet.size());
	}
	

	@Test
    void testFindAllCourses() {
	
		Set<CourseForm> courseFormSet = scheduleDao.findAllCourses();
		assertNotNull(courseFormSet.size());
	}
	
	@Test
    void testFindAllInstructors() {

		Set<InstructorForm> instructorFormSet = scheduleDao.findAllInstructors();
		assertNotNull(instructorFormSet.size());
	}
	
	
	@Test
	 void testFindAllVenues() {
		
		Set<VenueForm> venueFormSet = scheduleDao.findAllVenues();
		assertNotNull(venueFormSet.size());
		 
	 }
	 

}
