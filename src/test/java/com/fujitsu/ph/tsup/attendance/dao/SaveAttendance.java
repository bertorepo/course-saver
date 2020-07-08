package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

//===============================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : SaveAttendance.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/08/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//===============================================================================
/**
 * <pre>
* The test case for AttendanceDao SaveAttendance
 * </pre>
 * 
 * @version 0.01
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 * 
 */
@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SaveAttendance {

	/**
	 * <pre>
	 * Autowired annotation is used on properties, setters, and constructors.
	 * 
	 * <pre>
	 * 
	 */
	@Autowired
	private AttendanceDao attendanceDao;

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
		public AttendanceDao attendanceDao() {
			return new AttendanceDaoImpl();
		}
	}

	/**
	 * <pre>
	 * This method calls the builder method createCourseAttendance() and inserts the
	 * builder value using sql query in DaoImpl then tests the expected and actual
	 * value
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testSaveAttendance() {

		CourseAttendance courseAttendance = createCourseAttendance();

		attendanceDao.saveAttendance(courseAttendance);

		assertEquals(1L, courseAttendance.getId());
		assertEquals(2L, courseAttendance.getCourseScheduleDetailId());
		assertEquals(3L, courseAttendance.getParticipantId());
		assertEquals('P', courseAttendance.getStatus());
		assertEquals(ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"), courseAttendance.getLoginDateTime());
	}

	/**
	 * <pre>
	 * Creates a builder method instance for the CourseAttendance
	 * 
	 * <pre>
	 * 
	 */
	private CourseAttendance createCourseAttendance() {
		return new CourseAttendance.Builder(1L, 2L, "JAVA", "Lorenzo, Loyce", "TWO/Neo Bldg.", 3L, "Ramos, Ramon",
				ZonedDateTime.now(), ZonedDateTime.now().plusHours(8), 3.0f,
				ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"), 'P').build();
	}
}
