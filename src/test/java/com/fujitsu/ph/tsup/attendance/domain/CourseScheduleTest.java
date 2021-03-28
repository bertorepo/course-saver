package com.fujitsu.ph.tsup.attendance.domain;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//===============================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : CourseScheduleTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/08/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//===============================================================================
/**
 * <pre>
 * The test cases for CourseSchedule
 * </pre>
 * 
 * @version 0.01
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 * 
 */
class CourseScheduleTest {

	/**
	 * <pre>
	 * Tests the first builder if expected is equals to the actual value.
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testBuilderOne() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getId(), 1L);
		assertEquals(expected.getCourseId(), 1L);
		assertEquals(expected.getCourseName(), "JUnit");
		assertEquals(expected.getInstructorId(), 1L);
		assertEquals(expected.getInstructorLastName(), "Lorenzo");
		assertEquals(expected.getInstructorFirstName(), "Loyce");
		assertEquals(expected.getVenueId(), 1L);
		assertEquals(expected.getVenueName(), "TWO/Neo Bldg.");
		assertEquals(expected.getMinRequired(), 1);
		assertEquals(expected.getMaxAllowed(), 5);
		assertEquals(expected.getStatus(), 'C');
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
		CourseSchedule expected = createBuilderTwo();

		assertNotNull(expected);
		assertEquals(expected.getId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the max allowed builder if expected is equals to the actual value.
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testMaxAllowed() {
		CourseSchedule courseSchedule = new CourseSchedule.Builder(1L).maxAllowed(5).build();

		assertNotNull(courseSchedule);
		assertEquals(courseSchedule.getMaxAllowed(), 5);
	}

	/**
	 * <pre>
	 * Tests the add detail builder if expected is equals to the actual value.
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testAddDetail() {
		CourseScheduleDetail courseScheduleDetail = createCourseScheduleDetail();

		Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<CourseScheduleDetail>();
		courseScheduleDetailSet.add(courseScheduleDetail);

		assertNotNull(courseScheduleDetail);
		assertNotNull(courseScheduleDetailSet);
	}

	/**
	 * <pre>
	 * Tests the cancel builder if expected is equals to the actual value.
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testCancel() {
		CourseSchedule courseSchedule = new CourseSchedule.Builder(3L).cancel().build();

		assertNotNull(courseSchedule);
		assertEquals(courseSchedule.getStatus(), 'C');
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
			new CourseSchedule.Builder(null).build();
		});
		assertThat(t.getMessage(), equalTo("Id should not be empty"));
	}

	@Test
	void testId_IsZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(0L).build();
		});
		assertThat(t.getMessage(), equalTo("Id should not be empty"));
	}

	@Test
	void testId_IsValid() {
		CourseSchedule expected = createBuilderTwo();

		assertNotNull(expected);
		assertEquals(expected.getId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the validation for course id if value is null and zero. If invalid then
	 * throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testCourseId_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, null, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C')
					.build();
		});
		assertThat(t.getMessage(), equalTo("Course should not be empty"));
	}

	@Test
	void testCourseId_IsZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 0L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C')
					.build();
		});
		assertThat(t.getMessage(), equalTo("Course should not be empty"));
	}

	@Test
	void testCourseId_IsValid() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getCourseId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the validation for instructor id if value is null and zero. If invalid
	 * then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testInstructorId_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", null, "Lorenzo", "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C')
					.build();
		});
		assertThat(t.getMessage(), equalTo("Instructor should not be empty"));
	}

	@Test
	void testInstructorId_IsZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 0L, "Lorenzo", "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C')
					.build();
		});
		assertThat(t.getMessage(), equalTo("Instructor should not be empty"));
	}

	@Test
	void testInstructorId_IsValid() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getInstructorId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the validation for venue id if value is null and zero. If invalid then
	 * throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testVenueId_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", null, "TWO/Neo Bldg.", 1, 5, 5, 'C')
					.build();
		});
		assertThat(t.getMessage(), equalTo("Venue should not be empty"));
	}

	@Test
	void testVenueId_IsZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 0L, "TWO/Neo Bldg.", 1, 5, 5, 'C')
					.build();
		});
		assertThat(t.getMessage(), equalTo("Venue should not be empty"));
	}

	@Test
	void testVenueId_IsValid() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getVenueId(), 1L);
	}

	/**
	 * <pre>
	 * Tests the validation for min required if value is null and zero. If invalid
	 * then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testMinRequired_IsZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "TWO/Neo Bldg.", 0, 5, 5, 'C')
					.build();
		});
		assertThat(t.getMessage(), equalTo("Mininum No. of Participants should be greater than 0"));
	}

	@Test
	void testMinRequired_IsValid() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getMinRequired(), 1L);
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
			new CourseSchedule.Builder(1L, 1L, null, 1L, "Lorenzo", "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C').build();
		});
		assertThat(t.getMessage(), equalTo("Course name should not be empty"));
	}

	@Test
	void testCourseName_IsEmpty() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "", 1L, "Lorenzo", "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C').build();
		});
		assertThat(t.getMessage(), equalTo("Course name should not be empty"));
	}

	@Test
	void testCourseName_IsValid() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getCourseName(), "JUnit");
	}

	/**
	 * <pre>
	 * Tests the validation for instructor last name if value is null and empty. If
	 * invalid then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testInstructorLastName_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, null, "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C').build();
		});
		assertThat(t.getMessage(), equalTo("Instructor Name should not be empty"));
	}

	@Test
	void testInstructorLastName_IsEmpty() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "", "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C').build();
		});
		assertThat(t.getMessage(), equalTo("Instructor Name should not be empty"));
	}

	@Test
	void testLastName_IsValid() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getInstructorLastName(), "Lorenzo");
	}

	/**
	 * <pre>
	 * Tests the validation for instructor first name if value is null and empty. If
	 * invalid then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testInstructorFirstName_IsNull() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", null, 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C').build();
		});
		assertThat(t.getMessage(), equalTo("Instructor Name should not be empty"));
	}

	@Test
	void testInstructorFirstName_IsEmpty() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C').build();
		});
		assertThat(t.getMessage(), equalTo("Instructor Name should not be empty"));
	}

	@Test
	void testFirstName_IsValid() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getInstructorFirstName(), "Loyce");
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
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, null, 1, 5, 5, 'C').build();
		});
		assertThat(t.getMessage(), equalTo("Venue should not be empty"));
	}

	@Test
	void testVenueName_IsEmpty() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "", 1, 5, 5, 'C').build();
		});
		assertThat(t.getMessage(), equalTo("Venue should not be empty"));
	}

	@Test
	void testVenueName_IsValid() {
		CourseSchedule expected = createBuilderOne();

		assertNotNull(expected);
		assertEquals(expected.getVenueName(), "TWO/Neo Bldg.");
	}

	/**
	 * <pre>
	 * Tests the validation for max allowed if value is less than zero. If invalid
	 * then throws an argument with the corresponding error message
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testMaxAllowed_IsLessThanZero() {
		IllegalArgumentException t = assertThrows(IllegalArgumentException.class, () -> {
			new CourseSchedule.Builder(1L).maxAllowed(-1).build();
		});
		assertThat(t.getMessage(), equalTo("Maximum No. of Participants should not be less than 0"));
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the first builder
	 * 
	 * <pre>
	 * 
	 */
	private CourseSchedule createBuilderOne() {
		return new CourseSchedule.Builder(1L, 1L, "JUnit", 1L, "Lorenzo", "Loyce", 1L, "TWO/Neo Bldg.", 1, 5, 5, 'C')
				.build();
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the second builder
	 * 
	 * <pre>
	 * 
	 */
	private CourseSchedule createBuilderTwo() {
		return new CourseSchedule.Builder(1L).build();
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the CourseScheduleDetail builder
	 * 
	 * <pre>
	 * 
	 */
	private CourseScheduleDetail createCourseScheduleDetail() {
		return new CourseScheduleDetail.Builder(1L, 1L, ZonedDateTime.parse("2020-07-06T11:15:40.146+08:00"),
				ZonedDateTime.parse("2020-07-07T17:30:40.146+08:00")).build();
	}
}
