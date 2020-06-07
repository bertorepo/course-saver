package com.fujitsu.ph.tsup.domain.oviedo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
class VenueServiceTest {

	@TestConfiguration
	static class EmployeeServiceContextConfiguration {
		@Bean
		VenueService venueService() {
			return new VenueServiceImpl();
		}
	}

	@Autowired
	VenueService service;

	@MockBean
	VenueDao vDao;

	@Test
	void testFindByID() {
		Venue expected = createVenue();
		when(vDao.findById(anyLong())).thenReturn(expected);
		Venue venue = service.findById(10L);
		assertEquals(venue.getId(), 10L);


	}

	@Test
	void testFindByID_invalid() {
		when(vDao.findById(anyLong())).thenThrow(new ApplicationException("error"));
		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findById(1L);
		});

		String expectedMessage = "Employee not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindAll() {
		Venue expected = createVenue();
		when(vDao.findById(anyLong())).thenReturn(expected);
		Venue venue = service.findById(10L);
		assertEquals(venue, expected);


	}
	private Venue createVenue() {
		return new Venue.Builder(100L, "Gym").build();
	}
}
