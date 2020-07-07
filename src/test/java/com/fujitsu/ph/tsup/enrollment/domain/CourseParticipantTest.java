package com.fujitsu.ph.tsup.enrollment.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CourseParticipantTest {

	@Test
	void testValid_CourseParticipant() {
		CourseParticipant courseParticipant = new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L,
				"DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY",
				ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();

		assertNotNull(courseParticipant.getId());
		assertEquals(1L, courseParticipant.getId());

		assertNotNull(courseParticipant.getCourseScheduleId());
		assertEquals(1L, courseParticipant.getCourseScheduleId());

		assertNotNull(courseParticipant.getCourseName());
		assertEquals("DUMMY", courseParticipant.getCourseName());

		assertNotNull(courseParticipant.getInstructorName());
		assertEquals("DUMMY", courseParticipant.getInstructorName());

		assertNotNull(courseParticipant.getVenueName());
		assertEquals("DUMMY", courseParticipant.getVenueName());

		assertNotNull(courseParticipant.getParticipantId());
		assertEquals(1L, courseParticipant.getParticipantId());

		assertNotNull(courseParticipant.getParticipantName());
		assertEquals("DUMMY", courseParticipant.getParticipantName());

		assertNotNull(courseParticipant.getRegistrationDate());
		assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), courseParticipant.getRegistrationDate());

		assertNotNull(courseParticipant.getReason());
		assertEquals("DUMMY", courseParticipant.getReason());

		assertNotNull(courseParticipant.getDeclineDate());
		assertEquals(ZonedDateTime.parse("2016-11-16T17:21:00Z"), courseParticipant.getDeclineDate());
	}

	@Test
	void testInvalidId_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(null, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Id should not be empty"));
	}

	@Test
	void testInvalidId_isZero() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(0L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Id should not be empty"));
	}

	@Test
	void testInvalidCourseScheduleId_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, null, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Course Schedule Id should not be empty"));
	}

	@Test
	void testInvalidCourseScheduleId_isZero() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 0L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Course Schedule Id should not be empty"));
	}

	@Test
	void testInvalidCourseName_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, null, "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Course name should not be empty"));
	}

	@Test
	void testInvalidCourseName_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Course name should not be empty"));
	}

	@Test
	void testInvalidInstructorName_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", null, "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
	}

	@Test
	void testInvalidInstructorName_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Instructor Name should not be empty"));
	}

	@Test
	void testInvalidVenueName_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", null, 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Venue name should not be empty"));
	}

	@Test
	void testInvalidVenueName_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Venue name should not be empty"));
	}

	@Test
	void testInvalidParticipantId_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", null, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Participant Id should not be empty"));
	}

	@Test
	void testInvalidParticipantId_isZero() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 0L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Participant Id should not be empty"));
	}

	@Test
	void testInvalidParticipatName_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, null,
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Participant Name should not be empty"));
	}

	@Test
	void testInvalidParticipantName_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.build();
		});
		assertTrue(error.getMessage().equals("Participant Name should not be empty"));
	}

	@Test
	void testInvalidRegistrationDate_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY", null, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z")).build();
		});
		assertTrue(error.getMessage().equals("Registration Date should not be empty"));
	}

	@Test
	void testInvalidReason_isNull() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.decline(null);
		});
		assertTrue(error.getMessage().equals("Reason should not be empty"));
	}

	@Test
	void testInvalidReason_isEmpty() {

		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			new CourseParticipant.Builder(1L, 1L, "DUMMY", "DUMMY", "DUMMY", 1L, "DUMMY",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), "DUMMY", ZonedDateTime.parse("2016-11-16T17:21:00Z"))
							.decline("");
		});
		assertTrue(error.getMessage().equals("Reason should not be empty"));
	}

	@Test
	void testInvalidCourseScheduleDetail_isValid() {

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

}
