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
public class CourseScheduleServiceImplTest {
	@TestConfiguration
	static class CourseScheduleServiceImplestContextConfiguration {

		@Bean
		CourseScheduleService courseScheduleService() {
			return new CourseScheduleServiceImpl();
		}

	}

	@Autowired
	private CourseScheduleService service;

	@MockBean
	private CourseScheduleDao courseScheduleDao;

	@Test
	void testFindAll() {
		Set<CourseSchedule> courseSchedules = new HashSet<CourseSchedule>();
		courseSchedules.add(new CourseSchedule.Builder(1L, 1L, 1L, 1, 10, 'A').build());

		when(courseScheduleDao.findAll()).thenReturn(courseSchedules);
		assertEquals(1, courseSchedules.size());
	}

	@Test
	void testFindAllWithError() {
		when(courseScheduleDao.findAll()).thenThrow(new DataRetrievalFailureException("error"));
		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findAll();
		});

		String expectedMessage = "Course Schedule could not be save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testSave() {
		doNothing().when(courseScheduleDao).save(any(CourseSchedule.class));
	}

	@Test
	void testSaveWithEror() {
		doThrow(new IllegalArgumentException("error")).when(courseScheduleDao).save(any(CourseSchedule.class));

		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.save(this.createCourseSchedule());
		});

		String expectedMessage = "Course Schedule could not be save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testFindById() {
		CourseSchedule result = createCourseSchedule();
		when(courseScheduleDao.findById(any(Long.class))).thenReturn(result);

		CourseSchedule courseSchedule = service.findById(1L);

		assertEquals(result.getId(), courseSchedule.getId());
	}

	@Test
	void testFindByIdWithError() {
		when(courseScheduleDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));

		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findById(1L);
		});

		String expectedMessage = "Course Schedule not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private CourseSchedule createCourseSchedule() {
		return new CourseSchedule.Builder(1L, 1L, 1L, 1, 10, 'A').build();

	}
}
