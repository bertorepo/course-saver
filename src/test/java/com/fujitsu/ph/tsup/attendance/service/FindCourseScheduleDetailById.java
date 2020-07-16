package com.fujitsu.ph.tsup.attendance.service;

import com.fujitsu.ph.tsup.attendance.dao.AttendanceDao;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//===============================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : FindCourseScheduleDetailById.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/08/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//0.02    | 07/09/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | Updated
//===============================================================================
/**
 * <pre>
* The test case for AttendanceService FindCourseScheduleDetailById
 * </pre>
 * 
 * @version 0.02
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 * 
 */
@ExtendWith(SpringExtension.class)
public class FindCourseScheduleDetailById {

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
	 * This method calls the builder method createCourseAttendance() and adds the
	 * value to the Set<CourseAttendance> and tests the expected and actual value
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testFindCourseScheduleDetailById() {
		CourseAttendance courseAttendance = createCourseAttendance();

		Set<CourseAttendance> courseAttendanceSet = new HashSet<>();
		courseAttendanceSet.add(courseAttendance);

		when(attendanceDao.findCourseScheduleDetailById(any(Long.class))).thenReturn(courseAttendanceSet);
		Set<CourseAttendance> coursesAttendance = attendanceService.findCourseScheduleDetailById(1L);

		assertNotNull(coursesAttendance);
		assertEquals(1, coursesAttendance.size());
	}
	
	@Test
	void testFindCourseScheduleDetailById_Error() {
		when(attendanceDao.findCourseScheduleDetailById(any(Long.class))).thenThrow(new DataRetrievalFailureException("Error"));

		Exception attendanceException = assertThrows(IllegalArgumentException.class, () -> attendanceService
				.findCourseScheduleDetailById(1L));

		String expectedMessage = "No records found.";
		String actualMessage = attendanceException.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
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
