package com.fujitsu.ph.tsup.domain.oviedo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CourseServiceTest {
	@TestConfiguration
	static class CourseServiceImplestContextConfiguration {
		@Bean
		CourseService courseService() {
			return new CourseServiceImpl();
		}
	}

	@Autowired
	CourseService service;

	@MockBean
	CourseDao cDao;

	@Test
	void testFindByID() {
		Course expected = createCourse();
		when(cDao.findById(anyLong())).thenReturn(expected);
		Course course = service.findById(12L);
		assertEquals(course.getId(), 12L);


	}

	@Test
	void testFindByID_invalid() {
		when(cDao.findById(anyLong())).thenThrow(new ApplicationException("error"));
		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findById(1L);
		});

		String expectedMessage = "Employee not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindAll() {
		Course expected = createCourse();
		when(cDao.findById(anyLong())).thenReturn(expected);
		Course course = service.findById(12L);
		assertEquals(course.getId(), 12L);
		assertEquals(expected.getCourse(), course.getCourse());
	}

	private Course createCourse() {
		return new Course.Builder(12L, "ITE Major 021").build();
	}

	private Course createCourse_invalid() {
		return new Course.Builder(0L, "ITE Major 022").build();
	}
}


