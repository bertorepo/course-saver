package com.fujitsu.ph.tsup.course.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fujitsu.ph.tsup.course.model.Course;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : FindIfCourseNameExists.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/04/26 | WS) i.fajardo         | Initial Version
//==================================================================================================

@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class FindIfCourseNameExistsTest {
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
    	 * @return CourseManagementDaoIml
    	 */
        @Bean
        public CourseManagementDao courseManagementDao() {
        	return new CourseManagementDaoImpl();
        }
    }
	
	/**
	 * @author i.fajardo
     * <pre>
     * testFindIfCourseNameExists with valid values
     * courseManagementDao.findIfCourseNameExists and test if the methods find course by name and id
     * <pre>
     */
	@Test
	void findIfCourseNameExists() {
		Set<Course> course = courseManagementDao.findIfCourseNameExists("Spring", 43L); //Change the course name based on your course DB
		
		for(Course courses : course) {
			System.out.println("\n" + "Test for findIfCourseNameExists()");
			Assert.assertEquals("Spring", courses.getName());
			System.out.println("\n" + "ID: " + courses.getId() + " Name: " + courses.getName() + " Detail: " + courses.getDetail()
    		+ " Mandatory: " + courses.getIsMandatory() + " Deadline: " + courses.getDeadline() + "\n");
		}
		System.out.println("Course Size:"+ course.size()+"\n");
		assertNotNull(course.size());
	}
}
