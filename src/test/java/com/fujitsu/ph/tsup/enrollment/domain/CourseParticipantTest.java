package com.fujitsu.ph.tsup.enrollment.domain;
//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CourseParticipantTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 07/07/2020 | WS) J.Macabugao       | New Creation
//==================================================================================================
/**
* <pre>
* CourseParticipantTest for CourseParticipant Builder
* 
* <pre>
* 
* @version 0.01
* @author J.Macabugao
*/
import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CourseParticipantTest {

	/**
	 * Test case for when Id is null
	 */
	@Test
	void testId_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(null, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Id should not be empty"));
	}

	/**
	 * Test case for when Id is zero
	 */
	@Test
	void testdId_isZero() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(0L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Id should not be empty"));
	}

	/**
	 * Test case for when Id is valid
	 */
	@Test
	void testdId_isValid() {

		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getId());
		assertEquals(1L, courseParticipant.getId());
	}

	/**
	 * Test case for when course schedule id is null
	 */
	@Test
	void testCourseScheduleId_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, null, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Course Schedule Id should not be empty"));
	}

	/**
	 * Test case for when course schedule id is zero
	 */
	@Test
	void testCourseScheduleId_isZero() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 0L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Course Schedule Id should not be empty"));
	}
	
	/**
	 * Test case for when course schedule id is valid
	 */
	@Test
	void testCourseScheduleId_isValid() {
		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getCourseScheduleId());
		assertEquals(1L, courseParticipant.getCourseScheduleId());
	}

	/**
	 * Test case for when course name is null
	 */
	@Test
	void testCourseName_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, null, "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Course name should not be empty"));
	}

	/**
	 * Test case for when course name is empty
	 */
	@Test
	void testCourseName_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Course name should not be empty"));
	}
	
	/**
	 * Test case for when course name is valid
	 */
	@Test
	void testCourseName_isValid() {

		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getCourseName());
		assertEquals("DUMMY", courseParticipant.getCourseName());
	}

	/**
	 * Test case for when instructor name is null
	 */
	@Test
	void testInstructorName_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", null, "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
	}

	/**
	 * Test case for when instructor name is empty
	 */
	@Test
	void testInstructorName_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
	}
	
	/**
	 * Test case for when instructor name is valid
	 */
	@Test
	void testInstructorName_isValid() {

		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getInstructorName());
		assertEquals("DUMMY", courseParticipant.getInstructorName());
	}


	/**
	 * Test case for when venue name is null
	 */
	@Test
	void testVenueName_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", null, 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Venue name should not be empty"));
	}

	/**
	 * Test case for when venue name is empty
	 */
	@Test
	void testVenueName_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Venue name should not be empty"));
	}
	
	/**
	 * Test case for when venue name is valid
	 */
	@Test
	void testVenueName_isValid() {
		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getVenueName());
		assertEquals("DUMMY", courseParticipant.getVenueName());
	}


	/**
	 * Test case for when participant id is null
	 */
	@Test
	void testParticipantId_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", null, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Participant Id should not be empty"));
	}

	
	/**
	 * Test case for when participant id is zero
	 */
	@Test
	void testParticipantId_isZero() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 0L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Participant Id should not be empty"));
	}

	
	/**
	 * Test case for when participant id is valid
	 */
	@Test
	void testParticipantId_isValid() {
		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getParticipantId());
		assertEquals(1L, courseParticipant.getParticipantId());
	
	}
	
	/**
	 * Test case for when participant name is null
	 */
	@Test
	void testParticipantName_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, null,
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Participant Name should not be empty"));
	}

	/**
	 * Test case for when participant name is empty
	 */
	@Test
	void testParticipantName_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Participant Name should not be empty"));
	}
	
	/**
	 * Test case for when participant name is valid
	 */
	@Test
	void testParticipantName_isValid() {
		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getParticipantName());
		assertEquals("DUMMY", courseParticipant.getParticipantName());
	}

	/**
	 * Test case for when registration date is null
	 */
	@Test
	void testRegistrationDate_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY", null, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
		});
		assertTrue(error.getMessage().equals("Registration Date should not be empty"));
	}
	
	/**
	 * Test case for when registration date is valid
	 */
	@Test
	void testRegistrationDate_isValid() {

		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getRegistrationDate());
		assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), courseParticipant.getRegistrationDate());
	}

	/**
	 * Test case for when reason is null
	 */
	@Test
	void testReason_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.decline(null);
		});
		assertTrue(error.getMessage().equals("Reason should not be empty"));
	}

	/**
	 * Test case for when reason is empty
	 */
	@Test
	void testReason_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.decline("");
		});
		assertTrue(error.getMessage().equals("Reason should not be empty"));
	}
	
	/**
	 * Test case for when reason is valid
	 */
	@Test
	void testReason_isValid() {
		
		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
			ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getReason());
		assertEquals("DUMMY", courseParticipant.getReason());
	}
	

	/**
	 * Test case for when course schedule detail is null
	 */
	@Test
	void testInvalidCourseScheduleDetail_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.addDetail(null).build();
		});

		System.out.println(error.getMessage());
		assertTrue(error.getMessage().equals("Course Schedule Detail should have atleast 1 record"));
	}
	
	/**
	 * Test case for when course schedule detail is valid
	 */
	@Test
	void testCourseScheduleDetail_isValid() {

		CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L,
				ZonedDateTime.parse("2016-11-16T17:21:00Z"), ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
		courseScheduleDetailSet.add(courseScheduleDetail);

		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).addDetail(courseScheduleDetailSet).build();

		assertNotNull(courseParticipant.getCourseScheduleDetail());
		assertEquals(courseScheduleDetailSet, courseParticipant.getCourseScheduleDetail());
	}

}
