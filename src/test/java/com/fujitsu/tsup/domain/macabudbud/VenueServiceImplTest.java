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
public class VenueServiceImplTest {

	@TestConfiguration
	static class VenueServiceImplestContextConfiguration {

		@Bean
		VenueService venueService() {
			return new VenueServiceImpl();
		}

	}

	@Autowired
	private VenueService service;

	@MockBean
	private VenueDao venueDao;

	@Test
	void testFindAll() {
		Set<Venue> venues = new HashSet<Venue>();
		venues.add(new Venue.Builder(10L, "Main Hall").build());

		when(venueDao.findAll()).thenReturn(venues);
		assertEquals(1, venues.size());
	}

	@Test
	void testFindAllWithError() {
		when(venueDao.findAll()).thenThrow(new DataRetrievalFailureException("error"));
		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findAll();
		});

		String expectedMessage = "Venue could not be save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testSave() {
		doNothing().when(venueDao).save(any(Venue.class));
	}

	@Test
	void testSaveWithEror() {
		doThrow(new IllegalArgumentException("error")).when(venueDao).save(any(Venue.class));

		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.save(this.createVenue());
		});

		String expectedMessage = "Venue could not be save";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testFindById() {
		Venue result = createVenue();
		when(venueDao.findById(any(Long.class))).thenReturn(result);

		Venue venue = service.findById(1L);

		assertEquals(result.getId(), venue.getId());
	}

	@Test
	void testFindByIdWithError() {
		when(venueDao.findById(any(Long.class))).thenThrow(new DataRetrievalFailureException("error"));

		Exception exception = assertThrows(ApplicationException.class, () -> {
			service.findById(1L);
		});

		String expectedMessage = "Venue not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private Venue createVenue() {
		return new Venue.Builder(1L, "Dev Room").build();

	}
}
