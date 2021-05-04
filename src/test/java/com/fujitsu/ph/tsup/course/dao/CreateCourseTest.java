package com.fujitsu.ph.tsup.course.dao;
/**
* <pre>
* The Unit Testing of course management dao
* <pre>
* @version 0.01
* @author st.diaz
* @author st.diaz
*/

//=======================================================
//$Id: PR04$
//Project Name: Training Sign Up
//Class Name: CreateCourseTest.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+-----------------+---------------
//0.01    | 04/26/2021 | WS) St.Diaz 	  | New Creation
//
//
//=======================================================
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.annotation.Rollback;

import com.fujitsu.ph.tsup.course.model.Course;

@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CreateCourseTest {

	/**
     * CourseManagementDao as dependency
     */
	@Autowired
	private CourseManagementDao courseManagementDao;
	
	/**
     * Test Configuration
     */
	@TestConfiguration
	static class TestContextConfiguration {
		
		/**
    	 * CourseManagementDao
    	 * @return CourseManagementDaoImpl
    	 */
		@Bean
		public CourseManagementDao courseManagementDao() {
			return new CourseManagementDaoImpl();
		}
	}
	
	/**
     * <pre>
     * testCreateCourseOne with valid values
     * Call courseManagementDao.createCourse and test if course is save in the database
     * <pre>
     */
	@Test
	@Rollback(false)
	void testCreateCourseOne() {
		Course course = createCourseOne();
		courseManagementDao.createCourse(course);
		
		assertEquals(course.getName(), "Javascript");
		assertEquals(course.getDetail(), "Details");
		assertEquals(course.getIsMandatory(), "Yes");
		assertEquals(course.getDeadline(), "Monthly");
		
	}
	
	/**
     * <pre>
     * testCreateCourseTwo with valid values
     * Call courseManagementDao.createCourse and test if course is save in the database
     * <pre>
     */
	@Test
	@Rollback(false)
	void testCreateCourseTwo() {
		Course course = createCourseTwo();
		courseManagementDao.createCourse(course);
		
		assertEquals(course.getName(), "Python");
		assertEquals(course.getDetail(), "Details");
		assertEquals(course.getIsMandatory(), "No");
		assertEquals(course.getDeadline(), "-");
	}


	/**
     * <pre>
     * Builded data for create course
     * <pre>
     */
	private Course createCourseOne() {
		return new Course.Builder("Javascript", "Details", "Yes", "Monthly").build();
	}
	
	private Course createCourseTwo() {
		return new Course.Builder("Python", "Details", "No", "-").build();
	}
}
