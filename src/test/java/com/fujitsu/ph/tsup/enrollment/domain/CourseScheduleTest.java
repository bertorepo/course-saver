package com.fujitsu.ph.tsup.enrollment.domain;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CourseScheduleTest {
	
	@Test
	void testValid() {
		CourseSchedule courseSchedule = createValidTest();
		assertTrue(courseSchedule.getId()>0);
		assertTrue(courseSchedule.getCourseId()>0);
		assertNotNull(courseSchedule.getCourseName());
		assertTrue(courseSchedule.getInstructorId()>0);
		assertNotEquals(courseSchedule.getInstructorFirstName(), "");
		assertNotNull(courseSchedule.getInstructorFirstName());
		assertNotEquals(courseSchedule.getInstructorLastName(), " ");
		assertNotNull(courseSchedule.getInstructorLastName());
		assertTrue(courseSchedule.getVenueId()>0);
		assertNotEquals(courseSchedule.getVenueName(), " ");
		assertNotNull(courseSchedule.getVenueName());
		assertTrue(courseSchedule.getMinRequired() > 0);
		assertTrue(courseSchedule.getMaxAllowed() > courseSchedule.getMinRequired());

	} 
	
	private CourseSchedule createValidTest() {
		Set<CourseScheduleDetail> courseScheduleDetail = new HashSet<CourseScheduleDetail>();
		CourseScheduleDetail csd1 = new CourseScheduleDetail.Builder(1L, 1L, ZonedDateTime.now(), ZonedDateTime.now().plusDays(5)).build();
		CourseScheduleDetail csd2 = new CourseScheduleDetail.Builder(2L, 2L, ZonedDateTime.now(), ZonedDateTime.now().plusDays(5)).build();
		courseScheduleDetail.add(csd1);
		courseScheduleDetail.add(csd2);
		return new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').addDetail(csd1).build();
	}
	
	@Test
	void testIdZero() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(0L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
        assertTrue(expected.getMessage().contains("Id should not be empty"));
	}
	
	@Test
	void testIdNull() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(null, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Id should not be empty"));
	}
	
	@Test
	void testCourseIdZero() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 0L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
        assertTrue(expected.getMessage().contains("Course should not be empty"));
	}
	
	@Test
	void testCourseIdNull() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, null, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Course should not be empty"));
	}
	
	@Test
	void testCourseNameEmpty() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, " ", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Course name should not be empty"));
	}
	
	@Test
	void testCourseNameNull() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, null, 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Course name should not be empty"));
	}
	
	@Test
	void testInstructorIdZero() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 0L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
        assertTrue(expected.getMessage().contains("Instructor should not be empty"));
	}
	
	@Test
	void testInstructorIdNull() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", null, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Instructor should not be empty"));
	}
	
	@Test
	void instructorFirstNameEmpty() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", " ", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Instructor Name should not be empty"));
	}
	
	@Test
	void testInstructorFirstNameNull() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", null, 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Instructor Name should not be empty"));
	}
	
	@Test
	void testInstructorLastNameEmpty() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "", "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Instructor Name should not be empty"));
	}
	
	@Test
	void testInstructorLastNameNull() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, null, "Gene", 10L, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Instructor Name should not be empty"));
	}
	
	@Test
	void testVenueIdZero() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 0L, "DeGuzman", "Gene", 0L, "EcoTower", 10, 20, 15, 'A').build();
        });
        assertTrue(expected.getMessage().contains("Venue should not be empty"));
	}
	
	@Test
	void testVenueIdNull() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", null, "DeGuzman", "Gene", null, "EcoTower", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Venue should not be empty"));
	}
	
	@Test
	void testVenueNameEmpty() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman ", "Gene", 10L, " ", 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Venue should not be empty"));
	}
	
	@Test
	void testVenueNameNull() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, null, 10, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Venue should not be empty"));
	}
	
	@Test
	void testMinRequiredZero() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 0, 20, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Mininum No. of Participants should be greater than 0"));
	}
	
	@Test
	void testMaxAllowedZero() {
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 0, 15, 'A').build();
        });
		assertTrue(expected.getMessage().contains("Maximum No. of Participants should not be less than 0"));
	}
	
	@Test
	void testInvalidateCourseScheduleDetail() {
//		Set<CourseScheduleDetail> courseScheduleDetail = new HashSet<CourseScheduleDetail>();
		CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(0L).build();
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 0, 'A').addDetail(courseScheduleDetail);
        });
		assertTrue(expected.getMessage().contains("The schedule should have at least 1 record"));
	}
	
	@Test
	void testStatusEmpty() { //No validation on CourseSchedule
		Exception expected = assertThrows(IllegalArgumentException.class, () -> {
            new CourseSchedule.Builder(10L, 10L, "PeerReview", 10L, "DeGuzman", "Gene", 10L, "EcoTower", 10, 20, 0, ' ').build();
        });
		assertTrue(expected.getMessage().contains("status should not be empty"));
	}
	
	

}
