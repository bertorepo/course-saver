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
class CourseScheduleServiceTest {

	@TestConfiguration
	static class CourseScheduleContextConfiguration {
		@Bean
		CourseScheduleService courseScheduleService() {
			return new CourseScheduleServiceImpl();
		}
	}

	@Autowired
	CourseScheduleService service;

	@MockBean
	CourseScheduleDao csDao;

	@Test
	void testFindByID() {
		CourseSchedule expected = createCourseSchedule();
		when(csDao.findById(anyLong())).thenReturn(expected);
		CourseSchedule courseSchedule = service.findById(12L);
		assertEquals(courseSchedule.getId(), 12L);
	}

	@Test
	void testFindByID_invalid() {
		when(csDao.findById(anyLong())).thenThrow(new ApplicationException("error"));
		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findById(1L);
		});

		String expectedMessage = "Employee not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindAll() {
		CourseSchedule expected = createCourseSchedule();
		when(csDao.findById(anyLong())).thenReturn(expected);
		CourseSchedule courseSchedule = service.findById(12L);
		assertEquals(courseSchedule.getId(), 12L);
		assertEquals(expected.getCourseId(), courseSchedule.getCourseId());
		assertEquals(expected.getInstructorId(),courseSchedule.getInstructorId());
		assertEquals(expected.getVenueId(),courseSchedule.getVenueId());
		assertEquals(expected.getMinReq(),courseSchedule.getMinReq());
		assertEquals(expected.getMaxAllowed(),courseSchedule.getMaxAllowed());
		assertEquals(expected.getStatus(),courseSchedule.getStatus());
	}

	private CourseSchedule createCourseSchedule() {
		return new CourseSchedule.Builder(12L, 121L, 1004L, 10002L, 30,50,'o').build();
	}

}
