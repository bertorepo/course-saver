package com.fujitsu.tsup.domain.macabudbud;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fujitsu.ph.tsup.domain.macabudbud.*;

@ExtendWith(SpringExtension.class)
public class CourseServiceImplTest {

	@TestConfiguration
	static class CourseServiceImplestContextConfiguration {

		@Bean
		CourseService courseService() {
			return new CourseServiceImpl();
		}

	}

	@Autowired
	private CourseService service;

	@MockBean
	private CourseDao courseDao;

	@Test
	void testFindAll() {
		Set<Course> courses = new HashSet<Course>();
		courses.add(new Course.Builder(10L, "Java Fundamentals").build());

		when(courseDao.findAll()).thenReturn(courses);
		assertEquals(1, courses.size());
	}

	@Test
	void testFindAllWithError() {
		when(courseDao.findAll()).thenThrow(new DataRetrievalFailureException("error"));
		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findAll();
		});

		String expectedMessage = "Course could not be save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testSave() {
		doNothing().when(courseDao).save(any(Course.class));
	}

	@Test
	void testSaveWithEror() {
		doThrow(new IllegalArgumentException("error")).when(courseDao).save(any(Course.class));

		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.save(this.createCourse());
		});

		String expectedMessage = "Course could not be save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testFindById() {
		Course result = createCourse();
		when(courseDao.findById(any(Long.class))).thenReturn(result);

		Course course = service.findById(1L);

		assertEquals(result.getId(), course.getId());
	}

	@Test
	void testFindByIdWithError() {
		when(courseDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));

		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findById(1L);
		});

		String expectedMessage = "Course not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Course createCourse() {
		return new Course.Builder(1L, "SpringBoot Development").build();

	}
}
