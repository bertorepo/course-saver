package com.fujitsu.ph.tsup.domain.macabugao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;

import static org.junit.jupiter.api.Assertions.assertEquals; 


@ExtendWith(SpringExtension.class)
class CourseScheduleServiceImplTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		CourseScheduleService courseScheduleService() {

			return new CourseScheduleServiceImpl();
		}

	}

	@Autowired
	private CourseScheduleService courseScheduleService;

	@MockBean
	private CourseScheduleDao courseScheduleDao;

	@Test
	void testSave() {

		doNothing().when(courseScheduleDao).save(any(CourseSchedule.class));

	}

	@Test
	void testSaveWithError() {

		CourseSchedule courseSchedule = createCourse();

		doThrow(new DataRetrievalFailureException("error")).when(courseScheduleDao).save(any(CourseSchedule.class));

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			courseScheduleService.save(courseSchedule);
			
		});

		String expectedMessage = "Course Schedule not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindAll() {
        Set<CourseSchedule> s = new HashSet<CourseSchedule>();
        s.add(new CourseSchedule.Builder(1L, 1L, 1L, 45, 45, "Any").build());

        when(courseScheduleDao.findAll()).thenReturn(s);
        assertEquals(courseScheduleService.findAll().size(), s.size());
        

	}

	@Test
	void testFindAllWithError() {
		
		doThrow(new DataRetrievalFailureException("error")).when(courseScheduleDao).findAll();

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			courseScheduleService.findAll();
	    });
		
		String expectedMessage = "Cannot find Course Schedule";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindById() {
		CourseSchedule expectedResult = createCourse();
		when(courseScheduleDao.findById(any(Long.class))).thenReturn(expectedResult);

		CourseSchedule courseSchedule = courseScheduleService.findById(1L);

		assertEquals(expectedResult.getId(), courseSchedule.getId());
	}

	@Test
	void testFindByIdWithError() {
		when(courseScheduleDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			courseScheduleService.findById(1L);
		});

		String expectedMessage = "Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private CourseSchedule createCourse() {
		return new CourseSchedule.Builder(1L, 1L, 1L, 45, 45, "Any").build();

	}

}
