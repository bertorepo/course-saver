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
//Class Name: FindCourseByIdTest.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+-----------------+---------------
//0.01    | 04/26/2021 | WS) St.Diaz      | New Creation
//
//
//=======================================================
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
class FindCourseByIdTest {

	@Autowired
	private CourseManagementDao courseManagementDao;
	
	@TestConfiguration
	static class TestContextConfiguration {
		
		@Bean
		public CourseManagementDao courseManagementDao() {
			return new CourseManagementDaoImpl();
		}
	}
	
	@Test
	void testFindCourseById() {
		Long id = 1L;
		Course course = courseManagementDao.findCourseById(id);
		System.out.println("Course ID: " + course.getId());
		System.out.println("Course Name: " + course.getName());
		System.out.println("Course Detail: " + course.getDetail());
		System.out.println("Course Mandatory: " + course.getIsMandatory());
		System.out.println("Course Deadline: " + course.getDeadline());
		assertNotNull(course);
	}
}
