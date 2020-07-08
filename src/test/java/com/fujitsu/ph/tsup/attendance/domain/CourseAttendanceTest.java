package com.fujitsu.ph.tsup.attendance.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

//===============================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : CourseAttendanceTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/08/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//===============================================================================
/**
 * <pre>
 * The test cases for CourseAttendance
 * </pre>
 * 
 * @version 0.01
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 * 
 */
class CourseAttendanceTest {

	/**
	 * <pre>
	 * Tests the first builder if expected is equals to the actual value.
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testBuilderOne() {
		CourseAttendance expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getId(), 1L);
		assertEquals(expected.getCourseScheduleDetailId(), 1L);
		assertEquals(expected.getCourseName(), "JUnit");
		assertEquals(expected.getInstructorName(), "Mr.Lorenzo");
		assertEquals(expected.getVenueName(), "TWO/Neo Bldg.");
		assertEquals(expected.getParticipantId(), 1L);
		assertEquals(expected.getParticipantName(), "BootCampers");
		assertEquals(expected.getScheduleStartDateTime(), ZonedDateTime.parse("2020-07-04T01:14:24.983+08:00"));
		assertEquals(expected.getScheduleEndDateTime(), ZonedDateTime.parse("2020-07-04T01:14:24.983+08:00"));
		assertEquals(expected.getDuration(), 2.5f);
	}

	/**
	 * <pre>
	 * Tests the second builder if expected is equals to the actual value.
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testBuilderTwo() {
		CourseAttendance expected = createBuilderTwo();

		assertNotNull(expected);
		assertEquals(expected.getId(), 1L);
		assertEquals(expected.getCourseScheduleDetailId(), 1L);
		assertEquals(expected.getCourseName(), "JUnit");
		assertEquals(expected.getInstructorName(), "Mr.Lorenzo");
		assertEquals(expected.getVenueName(), "TWO/Neo Bldg.");
		assertEquals(expected.getParticipantId(), 1L);
		assertEquals(expected.getParticipantName(), "BootCampers");
		assertEquals(expected.getScheduleStartDateTime(), ZonedDateTime.parse("2016-11-16T17:21:00Z"));
		assertEquals(expected.getScheduleEndDateTime(), ZonedDateTime.parse("2016-11-16T17:21:00Z"));
		assertEquals(expected.getDuration(), 3.5f);
	}

	/**
	 * <pre>
	 * Tests the third builder if expected is equals to the actual value.
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testBuilderThree() {
		CourseAttendance expected = createBuilderThree();

		assertNotNull(expected);
		assertEquals(expected.getId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the fourth builder if expected is equals to the actual value.
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testBuilderFour() {
		CourseAttendance expected = createBuilderFour();

		assertNotNull(expected);
		assertEquals(expected.getCourseScheduleDetailId(), 1L);
		assertEquals(expected.getParticipantId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the validation for id if value is null and zero. If invalid then throws
	 * an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testId_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(null).build();
		});
		assertThat(t.getMessage(), equalTo("Id should not be empty"));
	}

	@Test
	void testId_IsZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(0L).build();
		});
		assertThat(t.getMessage(), equalTo("Id should not be empty"));
	}

	@Test
	void testId_IsValid() {
		CourseAttendance expected = createBuilderThree();

		assertNotNull(expected);
		assertEquals(expected.getId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the validation for course schedule detail id if value is null and zero.
	 * If invalid then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testCourseScheduleDetailId_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(null, 1L).build();
		});
		assertThat(t.getMessage(), equalTo("Course schedule detail id should not be empty"));
	}

	@Test
	void testCourseScheduleDetailId_IsZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(0L, 1L).build();
		});
		assertThat(t.getMessage(), equalTo("Course schedule detail id should not be empty"));
	}

	@Test
	void testCourseScheduleDetailId_IsValid() {
		CourseAttendance expected = createBuilderFour();

		assertNotNull(expected);
		assertEquals(expected.getCourseScheduleDetailId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the validation for participant id if value is null and zero. If invalid
	 * then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testParticipantId_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, null).build();
		});
		assertThat(t.getMessage(), equalTo("Participant should not be empty"));
	}

	@Test
	void testParticipantId_IsZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 0L).build();
		});
		assertThat(t.getMessage(), equalTo("Participant should not be empty"));
	}

	@Test
	void testParticipantId_IsValid() {
		CourseAttendance expected = createBuilderFour();

		assertNotNull(expected);
		assertEquals(expected.getParticipantId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the validation for course name if value is null and empty. If invalid
	 * then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testCourseName_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, null, "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, "BootCampers",
					ZonedDateTime.now(), ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Course Name should not be empty"));
	}

	@Test
	void testCourseName_IsEmpty() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "", "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, "BootCampers",
					ZonedDateTime.now(), ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Course Name should not be empty"));
	}

	@Test
	void testCourseName_IsValid() {
		CourseAttendance expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getCourseName(), "JUnit");
	}

	/**
	 * <pre>
	 * Tests the validation for instructor name if value is null and empty. If
	 * invalid then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testInstructorName_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", null, "TWO/Neo Bldg.", 1L, "BootCampers", ZonedDateTime.now(),
					ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Instructor Name should not be empty"));
	}

	@Test
	void testInstructorName_IsEmpty() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", "", "TWO/Neo Bldg.", 1L, "BootCampers", ZonedDateTime.now(),
					ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Instructor Name should not be empty"));
	}

	@Test
	void testInstructorName_IsValid() {
		CourseAttendance expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getInstructorName(), "Mr.Lorenzo");
	}

	/**
	 * <pre>
	 * Tests the validation for venue name if value is null and empty. If invalid
	 * then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testVenueName_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", null, 1L, "BootCampers", ZonedDateTime.now(),
					ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Venue Name should not be empty"));
	}

	@Test
	void testVenueName_IsEmpty() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", "", 1L, "BootCampers", ZonedDateTime.now(),
					ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Venue Name should not be empty"));
	}

	@Test
	void testVenueName_IsValid() {
		CourseAttendance expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getVenueName(), "TWO/Neo Bldg.");
	}

	/**
	 * <pre>
	 * Tests the validation for participant name if value is null and empty. If
	 * invalid then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testParticipantName_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, null, ZonedDateTime.now(),
					ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Participant Name should not be empty"));
	}

	@Test
	void testParticipantName_IsEmpty() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, "", ZonedDateTime.now(),
					ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Participant Name should not be empty"));
	}

	@Test
	void testParticipantName_IsValid() {
		CourseAttendance expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getParticipantName(), "BootCampers");
	}

	/**
	 * <pre>
	 * Tests the validation for scheduled start date time if value is null. If
	 * invalid then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testScheduledStartDateTime_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, "BootCampers", null,
					ZonedDateTime.now().plusHours(8L), 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Scheduled start date should not be empty"));
	}

	@Test
	void testScheduledStartDateTime_IsValid() {
		CourseAttendance expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getScheduleStartDateTime(), ZonedDateTime.parse("2020-07-04T01:14:24.983+08:00"));
	}

	/**
	 * <pre>
	 * Tests the validation for scheduled end date time if value is null. If invalid
	 * then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testScheduledEndDateTime_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, "BootCampers",
					ZonedDateTime.now(), null, 7.5f).build();
		});
		assertThat(t.getMessage(), equalTo("Scheduled end date and time should not be empty"));
	}

	/**
	 * <pre>
	 * Tests the validation for scheduled end date time if value is less than
	 * scheduled start date time. If invalid then throws an argument with the
	 * corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testScheduledEndDateTime_IsAfter() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, "BootCampers",
					ZonedDateTime.parse("2016-11-16T17:21:00Z"), ZonedDateTime.parse("2015-11-16T17:21:00Z"), 7.5f)
							.build();
		});
		assertThat(t.getMessage(), equalTo(
				"Scheduled end date and time should be greater than or equal to the the scheduled start date and time"));
	}

	/**
	 * <pre>
	 * Tests the validation for absent if expected value is equals to the actual
	 * value
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testAbsent() {
		CourseAttendance courseAttendance = new CourseAttendance.Builder(4L).absent().build();
		ZonedDateTime loginDateTime = null;

		assertNotNull(courseAttendance);
		assertEquals(courseAttendance.getStatus(), 'A');
		assertEquals(courseAttendance.getLoginDateTime(), loginDateTime);
	}

	/**
	 * <pre>
	 * Tests the validation for present if expected value is equals to the actual
	 * value
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testPresent() {
		CourseAttendance courseAttendance = new CourseAttendance.Builder(3L)
				.present(ZonedDateTime.parse("2020-07-04T01:14:24.983+08:00")).build();
		LocalDateTime localDateTimeNow = LocalDateTime.now();
		ZonedDateTime zonedDateTimeUTC = localDateTimeNow.atZone(ZoneId.of("UTC"));

		assertNotNull(courseAttendance);
		assertEquals(courseAttendance.getStatus(), 'P');
		assertNotSame(courseAttendance.getLoginDateTime(), ZonedDateTime.parse("2020-07-04T01:14:24.983+08:00"));
		assertNotSame(courseAttendance.getLoginDateTime(), zonedDateTimeUTC);
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the first builder
	 * 
	 * <pre>
	 * 
	 */
	private CourseAttendance createBuilderOne() {
		return new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, "BootCampers",
				ZonedDateTime.parse("2020-07-04T01:14:24.983+08:00"),
				ZonedDateTime.parse("2020-07-04T01:14:24.983+08:00"), 2.5f,
				ZonedDateTime.parse("2020-07-04T01:14:24.983+08:00"), 'P').build();
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the second builder
	 * 
	 * <pre>
	 * 
	 */
	private CourseAttendance createBuilderTwo() {
		return new CourseAttendance.Builder(1L, 1L, "JUnit", "Mr.Lorenzo", "TWO/Neo Bldg.", 1L, "BootCampers",
				ZonedDateTime.parse("2016-11-16T17:21:00Z"), ZonedDateTime.parse("2016-11-16T17:21:00Z"), 3.5f).build();
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the third builder
	 * 
	 * <pre>
	 * 
	 */
	private CourseAttendance createBuilderThree() {
		return new CourseAttendance.Builder(1L).build();
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the fourth builder
	 * 
	 * <pre>
	 * 
	 */
	private CourseAttendance createBuilderFour() {
		return new CourseAttendance.Builder(1L, 1L).build();
	}
}
