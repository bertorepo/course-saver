package com.fujitsu.ph.tsup.domain.cabiling;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

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

@ExtendWith(SpringExtension.class)
public class VenueServiceImplTest {
	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		VenueService VenueService() {
			/*
			 * returns the implementation to be injected
			 */
			return new VenueServiceImpl();
		}
	}

	@Autowired
	private VenueService venueService;

	@MockBean
	private VenueDao venueDao;

	@Test
	void testSave() {
		doThrow(new DataRetrievalFailureException("error")).when(venueDao).save(null);

		Venue venue = createVenue();
		venueService.save(venue);

		assertEquals(venue.getId(), 654321L);
		assertEquals(venue.getName(), "Ecotower");
	}

	@Test
	void testSaveEx() {
		doThrow(new DataRetrievalFailureException("error")).when(venueDao).save(null);

		Venue venue = createVenueErr();

		Exception vException = assertThrows(VenueException.class, () -> {
			venueService.save(venue);
		});

		String expectedMessage = "Venue Id cannot be equal to or less than zero.";
		String actualMessage = vException.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testFindById() {
		Venue createVnu = createVenue();
		when(venueDao.findById(any(Long.class))).thenReturn(createVnu);

		Venue venue = venueService.findById(654321L);

		assertEquals(createVnu.getId(), venue.getId());
		;
	}

	@Test
	void testFindById_NotFound() {
		when(venueDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));

		Exception vException = assertThrows(VenueException.class, () -> {
			venueService.findById(321L);
		});

		String expectedMessage = "Venue not found!";
		String actualMessage = vException.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testFindAll() {
		Set<Venue> vnu = new HashSet<Venue>();
		vnu.add(new Venue.Builder(654321L, "Ecotower").builder());
		when(venueDao.findAll()).thenReturn(vnu);
		assertEquals(venueService.findAll().size(), vnu.size());
	}

	@Test
	void testFindAll_NotFound() {
		Exception vException = assertThrows(VenueException.class, () -> {
			venueService.findAll();
		});

		String expectedMessage = "Can't find Venue Details";
		String actualMessage = vException.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Venue createVenue() {
		return new Venue.Builder(654321L, "Ecotower").builder();
	}

	private Venue createVenueErr() {
		return new Venue.Builder(0L, "Two Neo").builder();
	}

}
