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
//Class Name: FindCoursesByNameTest.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+-----------------+---------------
//0.01    | 04/27/2021 | WS) St.Diaz      | New Creation
//
//
//=======================================================
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

import com.fujitsu.ph.tsup.course.model.Course;

@JdbcTest
@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FindCoursesByNameTest {
	
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
     * testFindCoursesByName with valid values
     * courseManagementDao.findCoursesByName and test if the methods find all the courses by name
     * <pre>
     */
	@Test
	void testFindCoursesByName() {
		Set<Course> courseSet = courseManagementDao.findCoursesByName("Understanding");
		
		for(Course courses : courseSet) {
			System.out.println("Course ID: " + courses.getId());
			System.out.println("Course Name: " + courses.getName());
			System.out.println("Course Detail: " + courses.getDetail());
			System.out.println("Course Mandatory: " + courses.getIsMandatory());
			System.out.println("Course Deadline: " + courses.getDeadline());
		}
		
		System.out.println("Course Size: " + courseSet.size() + "\n");
		assertNotNull(courseSet.size());
	}
}
