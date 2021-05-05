package com.fujitsu.ph.tsup.course.dao;

//=======================================================
//$Id: PR04$
//Project Name: Training Sign Up
//Class Name: DeleteCourseByIdTest.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+-----------------+---------------
//0.01    | 04/26/2021 | WS) I.Fajardo     | New Creation
//=======================================================



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.annotation.Rollback;

@JdbcTest
//@ActiveProfiles({ "postgres" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DeleteCourseByIdTest {
	
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
	@Rollback(false)
	public void deleteCourseById() {
	    
	    courseManagementDao.deleteCourseById(45L); //Change the ID that exists in your DB     
	     
	}
}
