package com.fujitsu.ph.tsup.attendance.service;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

//===============================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : Attend.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/08/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//===============================================================================
/**
 * <pre>
* The test case for AttendanceService Attend
 * </pre>
 * 
 * @version 0.01
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 * 
 */
@ExtendWith(SpringExtension.class)
class Attend {

	/**
	 * <pre>
	 * TestConfiguration annotation is used to define/override beans for unit tests
	 * 
	 * <pre>
	 * 
	 */
	@TestConfiguration
	static class TestContextConfiguration {

		/**
		 * <pre>
		 * Bean annotation tells that a method produces a bean to be managed by the
		 * Spring container
		 * 
		 * <pre>
		 * 
		 */
		@Bean
		AttendanceService attendanceService() {
			return new AttendanceServiceImpl();
		}
	}

	/**
	 * <pre>
	 * Autowired annotation is used on properties, setters, and constructors.
	 * 
	 * <pre>
	 * 
	 */
	@Autowired
	AttendanceService attendanceService;

	/**
	 * <pre>
	 * MockBean add mock objects to the Spring application context
	 * 
	 * <pre>
	 * 
	 */
	@MockBean
	AttendanceDao attendanceDao;

	/**
	 * <pre>
	 * This method calls the builder method createCourseAttendance() and inputs the
	 * value on the attendanceDao.saveAttendance() and tests the expected value and
	 * actual value
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testAttend() {
		doThrow(new DataRetrievalFailureException("error")).when(attendanceDao).saveAttendance(null);

		CourseAttendance courseAttendance = createCourseAttendance();
		attendanceDao.saveAttendance(courseAttendance);

		assertNotNull(courseAttendance);
		assertEquals(1L, courseAttendance.getId());
		assertEquals(2L, courseAttendance.getCourseScheduleDetailId());
		assertEquals("JAVA", courseAttendance.getCourseName());
		assertEquals("Lorenzo, Loyce", courseAttendance.getInstructorName());
		assertEquals("TWO/Neo Bldg.", courseAttendance.getVenueName());
		assertEquals(3L, courseAttendance.getParticipantId());
		assertEquals("Abad, Kenneth", courseAttendance.getParticipantName());
		assertEquals(ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"), courseAttendance.getScheduleStartDateTime());
		assertEquals(ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), courseAttendance.getScheduleEndDateTime());
		assertEquals(3.0f, courseAttendance.getDuration());
		assertEquals(ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), courseAttendance.getLoginDateTime());
		assertEquals('P', courseAttendance.getStatus());
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the CourseAttendance
	 * 
	 * <pre>
	 * 
	 */
	private CourseAttendance createCourseAttendance() {
		return new CourseAttendance.Builder(1L, 2L, "JAVA", "Lorenzo, Loyce", "TWO/Neo Bldg.", 3L, "Abad, Kenneth",
				ZonedDateTime.parse("2020-07-06T08:30:47.946+08:00"),
				ZonedDateTime.parse("2020-07-06T17:30:34.983+08:00"), 3.0f,
				ZonedDateTime.parse("2019-08-08T09:15:24.983+08:00"), 'P').build();
	}

}