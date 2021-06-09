/**
 * Copyright (C) 2019 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.course.dao.CourseManagementDao;
import com.fujitsu.ph.tsup.course.model.Course;

//==================================================================================================
//Project Name : Training Sign Up
//Class Name   : CoursePaginationTest.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/06/07 | WS) m.aguinaldo       | Initial Version
//==================================================================================================
@ExtendWith(SpringExtension.class)
public class CoursePaginationTest {
    
    /**
     * CourseManagementDao as mock object
     */
    @MockBean
    private CourseManagementDao courseManagementDao;

    /**
     * CourseManagementService as dependency
     */
    @Autowired
    private CourseManagementService courseManagementService;
    
    private Set<Course> courses;

    /**
     * Test Configuration
     */
    @TestConfiguration
    static class TestContextConfiguration {

	/**
	 * CourseManagementService
	 * 
	 * @return CourseManagementServiceImpl
	 */
	@Bean
	public CourseManagementService courseManagementService() {
	    return new CourseManagementServiceImpl();
	}
    }
    
    /* <pre>
     * Init of Course set;
     * <pre>
     */
    @BeforeEach
    public void init() {
	Course course1 = Course.builder()
			       .withId(1L)
			       .withName("CCC")
			       .withDetail("New Data Detail")
			       .withIsMandatory("Yes")
			       .withDeadline("Immediate")
			       .withCourseCategoryId(3L)
			       .build();
	
	Course course2 = Course.builder()
			       .withId(2L)
			       .withName("AAA")
			       .withDetail("New Data Detail2")
			       .withIsMandatory("No")
			       .withDeadline("-")
			       .withCourseCategoryId(4L)
			       .build();

	Course course3 = Course.builder()
			       .withId(3L)
			       .withName("BBB")
			       .withDetail("New Data Detail3")
			       .withIsMandatory("Yes")
			       .withDeadline("Immediate")
			       .withCourseCategoryId(5L)
			       .build();
	
	courses = new LinkedHashSet<Course>(Arrays.asList(course1,course2, course3));
	
    }
    
    /* <pre>
    * Able to paginate;
    * <pre>
    */
    @Test
    void PageOf3WhenPageIs1() {
	Pageable pageable = PageRequest.of(0, 1);
	when(courseManagementDao.findAllCourses(pageable)).thenReturn(courses);
	when(courseManagementDao.countCourse()).thenReturn(3);
	
	Page<Course> paginatedCourse = courseManagementService.findAllCourses(pageable);
	
	assertNotNull(paginatedCourse);
	assertEquals(3, paginatedCourse.getTotalPages());
	assertEquals(1, paginatedCourse.getSize());
    }
    
    /* <pre>
     * Able to sort on ascending;
     * <pre>
     */
    @Test
    void sortedByCourseNameAsc() {
	Pageable pageable = PageRequest.of(0, 1,Sort.by("name").ascending());
	
	when(courseManagementDao.findAllCourses(pageable)).thenReturn(courses.stream()
									     .sorted(Comparator.comparing(
										     Course::getName))
									     .collect(Collectors.toSet()));
	when(courseManagementDao.countCourse()).thenReturn(3);
	
	Page<Course> paginatedCourse = courseManagementService.findAllCourses(pageable);
	
	assertNotNull(paginatedCourse);
	assertTrue(paginatedCourse.getSort().isSorted());
	assertEquals(Direction.ASC,paginatedCourse.getSort().getOrderFor("name").getDirection());
    }
    
    /* <pre>
     * Able to sort on descending;
     * <pre>
     */
    @Test
    void sortedByCourseNameDESC() {
	Pageable pageable = PageRequest.of(0, 1,Sort.by("name").descending());
	
	when(courseManagementDao.findAllCourses(pageable)).thenReturn(courses.stream()
		.sorted(Comparator.comparing(
			Course::getName).reversed())
		.collect(Collectors.toSet()));
	when(courseManagementDao.countCourse()).thenReturn(3);
	
	Page<Course> paginatedCourse = courseManagementService.findAllCourses(pageable);
	
	assertNotNull(paginatedCourse);
	assertTrue(paginatedCourse.getSort().isSorted());
	assertEquals(Direction.DESC,paginatedCourse.getSort().getOrderFor("name").getDirection());
    }

}
