package com.fujitsu.ph.tsup.domain.macabugao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;

import java.util.HashSet;
import java.util.Set;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
class CourseServiceImplTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		CourseService courseService() {

			return new CourseServiceImpl();
		}

	}

	@Autowired
	private CourseService courseService;

	@MockBean
	private CourseDao courseDao;

	@Test
	void testSave() {

		/*
		 * ArgumentCaptor<Course> valueCapture = ArgumentCaptor.forClass(Course.class);
		 * Course course = new Course(1L,"Course");
		 * 
		 * courseDao.save(course);
		 * 
		 * Mockito.verify(courseDao).save(ArgumentCaptor.capture());
		 * 
		 * Course captured = argumentCaptor.getValue();
		 */

		doNothing().when(courseDao).save(any(Course.class));
		
	}



	@Test
	void testSaveWithError() {

		//Course course = new Course.Builder("", null).build();
		
		Course course = createCourse();
		doThrow(new IllegalArgumentException("error")).when(courseDao).save(any(Course.class));

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			courseService.save(course);
	    });
		
		String expectedMessage = "Course not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
		
	}

	@Test
	void testFindAll() {
		
        Set<Course> s = new HashSet<Course>();
        s.add(new Course.Builder(1L ,"Programming").build());

        when(courseDao.findAll()).thenReturn(s);
        assertEquals(courseDao.findAll().size(), s.size());
        
	}


	@Test
	void testFindAllWithError() {
		doThrow(new IllegalArgumentException("error")).when(courseDao).findAll();

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			courseService.findAll();
	    });
		
		String expectedMessage = "Course not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
		
	}

	@Test
	void testFindById() {
		Course expectedResult = createCourse();
		when(courseDao.findById(any(Long.class))).thenReturn(expectedResult);

		Course course = courseService.findById(1L);

		assertEquals(expectedResult.getId(), course.getId());
	}

	@Test
	void testFindByIdWithError() {
		when(courseDao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("error"));

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			courseService.findById(1L);
		});

		String expectedMessage = "Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Course createCourse() {
		return new Course.Builder(1L,"Programming").build();

	}


}
