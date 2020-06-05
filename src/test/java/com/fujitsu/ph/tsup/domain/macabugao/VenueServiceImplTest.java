package com.fujitsu.ph.tsup.domain.macabugao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class VenueServiceImplTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		VenueService venueService() {

			return new VenueServiceImpl();
		}

	}

	@Autowired
	private VenueService venueService;

	@MockBean
	private VenueDao venueDao;

	@Test
	void testSave() {

		doNothing().when(venueDao).save(any(Venue.class));

	}

	@Test
	void testSaveWithError() {

		Venue venue = createVenue();

		doThrow(new DataRetrievalFailureException("error")).when(venueDao).save(any(Venue.class));

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			venueService.save(venue);
		});

		String expectedMessage = "Venue not save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindAll() {
		Venue expectedResult = createVenue();
		when(venueDao.findAll()).thenReturn((Set<Venue>) expectedResult);

		Set<Venue> venue = venueService.findAll();
		
		assertEquals(expectedResult.getVenueName(), venue.getClass());
		

	}

	@Test
	void testFindAllWithError() {
		
		doThrow(new DataRetrievalFailureException("error")).when(venueDao).findAll();

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			venueService.findAll();
	    });
		
		String expectedMessage = "Cannot find venue";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindById() {
		Venue expectedResult = createVenue();
		when(venueDao.findById(any(Long.class))).thenReturn(expectedResult);

		Venue venue = venueService.findById(1L);

		assertEquals(expectedResult.getId(), venue.getId());
	}

	@Test
	void testFindByIdWithError() {
		when(venueDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));

		Exception exception = assertThrows(IllegalApplicationException.class, () -> {
			venueService.findById(1L);
		});

		String expectedMessage = "Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Venue createVenue() {
		return new Venue.Builder(1L, "Philippine Arena").build();

	}

	private Long createId() {

		return 1L;
	}

}
