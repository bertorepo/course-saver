package com.fujitsu.ph.tsup.attendance.dao;

import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusParticipant;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//===============================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : UpdateAttendanceTest.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/08/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//0.02    | 09/02/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | Update
//===============================================================================
/**
 * <pre>
* The test case for AttendanceDao UpdateAttendance
 * </pre>
 * 
 * @version 0.02
 * @author k.abad
 * @author j.iwarat
 * @author r.ramos
 * 
 */
@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UpdateAttendanceTest {

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
	 * This method calls the method createChangeStatusParticipant_Present() and updates the
	 * builder value using sql query in DaoImpl then tests the expected and actual
	 * value
	 * 
	 * <pre>
	 * 
	 */
	@Test
	void testUpdateAttendance_Present() {
        ChangeStatusParticipant changeStatusParticipant = createChangeStatusParticipant_Present();
        
        List<ChangeStatusParticipant> changeStatusParticipantAsList = new ArrayList<ChangeStatusParticipant>();
        changeStatusParticipantAsList.add(changeStatusParticipant);
        
        List<ChangeStatusParticipant> expected = new ArrayList<ChangeStatusParticipant>();
        expected.addAll(changeStatusParticipantAsList);
        
        CourseAttendance courseAttendance = new CourseAttendance.Builder(1L, expected).build();

		attendanceDao.updateAttendance(courseAttendance);

		assertEquals(1L, courseAttendance.getId());
		assertNotEquals(2L, courseAttendance.getId());
		assertNotNull(courseAttendance);
	    assertNotNull(expected);
	    assertEquals(1L, changeStatusParticipant.getCourseAttendanceId());
	    assertEquals("k.abad@fujitsu.com", changeStatusParticipant.getEmail());
	    assertEquals(ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"), changeStatusParticipant.getLoginDateTime());
	    assertEquals(ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"), changeStatusParticipant.getLogoutDateTime());
	    assertEquals("Abad, Kenneth", changeStatusParticipant.getName());
	    assertEquals(3L, changeStatusParticipant.getParticipantId());
	    assertEquals('P', changeStatusParticipant.getStatus());
	}
	
	   /**
     * <pre>
     * This method calls the method createChangeStatusParticipant_Absent() and updates the
     * builder value using sql query in DaoImpl then tests the expected and actual
     * value
     * 
     * <pre>
     * 
     */
	@Test
	void testUpdateAttendance_Absent() {		
	    ChangeStatusParticipant changeStatusParticipant = createChangeStatusParticipant_Absent();
	    
	    List<ChangeStatusParticipant> changeStatusParticipantAsList = new ArrayList<ChangeStatusParticipant>();
	    changeStatusParticipantAsList.add(changeStatusParticipant);
	    
        List<ChangeStatusParticipant> expected = new ArrayList<ChangeStatusParticipant>();
        expected.addAll(changeStatusParticipantAsList);
        
        CourseAttendance courseAttendance = new CourseAttendance.Builder(1L, expected).build();
		
        attendanceDao.updateAttendance(courseAttendance);
		
        assertEquals(1L, courseAttendance.getId());
        assertNotEquals(2L, courseAttendance.getId());
        assertNotNull(courseAttendance);
		assertNotNull(expected);	
		assertThat(expected, not(IsEmptyCollection.empty()));
		assertThat(expected, is(changeStatusParticipantAsList));
		assertEquals(2L, changeStatusParticipant.getCourseAttendanceId());
		assertEquals("r.ramos@fujitsu.com", changeStatusParticipant.getEmail());
		assertEquals(null, changeStatusParticipant.getLoginDateTime());
		assertEquals(null, changeStatusParticipant.getLogoutDateTime());
		assertEquals("Ramos, Ramon", changeStatusParticipant.getName());
		assertEquals(5L, changeStatusParticipant.getParticipantId());
		assertEquals('A', changeStatusParticipant.getStatus());
		
	}

	 /**
     * <pre>
     * Creates a method instance for the ChangeStatusParticipant
     * 
     * <pre>
     * 
     */
	private ChangeStatusParticipant createChangeStatusParticipant_Absent() {         
	    List<ChangeStatusParticipant> changeStatusParticipantAsList = new ArrayList<ChangeStatusParticipant>();
	    ChangeStatusParticipant changeStatusParticipant = new ChangeStatusParticipant();
	    changeStatusParticipant.setCourseAttendanceId(2L);
	    changeStatusParticipant.setEmail("r.ramos@fujitsu.com");
	    changeStatusParticipant.setLoginDateTime(null);
	    changeStatusParticipant.setLogoutDateTime(null);
	    changeStatusParticipant.setName("Ramos, Ramon");
	    changeStatusParticipant.setParticipantId(5L);
	    changeStatusParticipant.setStatus('A');
	    changeStatusParticipantAsList.add(changeStatusParticipant);
        return changeStatusParticipant;	       
	}
	
    /**
     * <pre>
     * Creates a method instance for the ChangeStatusParticipant
     * 
     * <pre>
     * 
     */
    private ChangeStatusParticipant createChangeStatusParticipant_Present() {         
        List<ChangeStatusParticipant> changeStatusParticipantAsList = new ArrayList<ChangeStatusParticipant>();
        ChangeStatusParticipant changeStatusParticipant = new ChangeStatusParticipant();
        changeStatusParticipant.setCourseAttendanceId(1L);
        changeStatusParticipant.setEmail("k.abad@fujitsu.com");
        changeStatusParticipant.setLoginDateTime(ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"));
        changeStatusParticipant.setLogoutDateTime(ZonedDateTime.parse("2020-07-08T08:49:09.349+08:00"));
        changeStatusParticipant.setName("Abad, Kenneth");
        changeStatusParticipant.setParticipantId(3L);
        changeStatusParticipant.setStatus('P');
        changeStatusParticipantAsList.add(changeStatusParticipant);
        return changeStatusParticipant;        
    }
}
