package com.fujitsu.ph.tsup.enrollment.dao;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;

@JdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ExtendWith(SpringExtension.class)
class ChangeCourseScheduleStatusTest {

	@Autowired
	private EnrollmentDao enrollmentDao;
	
	@TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public EnrollmentDao enrollmentDao() {
        	return new EnrollmentDaoImpl();
        }
    }
	
	@Test
	void testChangeCourseScheduleStatus() {
		CourseSchedule courseSchedule = new CourseSchedule.Builder(1L, 1L, "Understanding UI", 1L, 
				"LORENZO", "LOYCE", 1L, "Eco Tower", 10,
				20, 15, 'D').build();
		//CourseSchedule courseSchedule = new CourseSchedule.Builder(1L).build();
		enrollmentDao.changeCourseScheduleStatus(courseSchedule);
		
		CourseSchedule courseSched = enrollmentDao.findCourseScheduleById(courseSchedule.getId());//returns null
		System.out.println("Course Name: "+ courseSched.getCourseName());
		System.out.println("Instructor Name: "+ courseSched.getInstructorFirstName() + " " + courseSched.getInstructorLastName());
		System.out.println("Venue Name: "+ courseSched.getVenueName());
		System.out.println("Minimum Required: "+ courseSched.getMinRequired());
		System.out.println("Maximum Allowed"+ courseSched.getMaxAllowed());
		System.out.println("Status: "+ courseSched.getStatus());
		CourseScheduleDetail  courseSchedDet = courseSched.getCourseScheduleDetail();
		System.out.println("CourseSchedule ID: "+ courseSchedDet.getCourseScheduleId());
		System.out.println("StartDateTime: "+ courseSchedDet.getScheduledStartDateTime());
		System.out.println("EndDateTime"+ courseSchedDet.getScheduledEndDateTime() + "\n");
		assertNull(courseSched);
		//assertNotEquals(courseSched, courseSchedule);
		//assertEquals(courseSched.getStatus(), 'D');
	}

}
