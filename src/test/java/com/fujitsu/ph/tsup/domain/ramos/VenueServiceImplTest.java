package com.fujitsu.ph.tsup.domain.ramos;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
public class VenueServiceImplTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@TestConfiguration
	static class VenueServiceImplTestContextConfiguration {

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
	public void testFindById() {
		when(venueDao.findById(anyLong())).thenReturn(createVenueId());
		Venue venue = service.findById(100L);
		assertEquals(venue.getId(), new Long(100));
	}
	
	@Test
	public void testFindAll() {
		Set<Venue> venue = new HashSet<Venue>();
		venue.add(new Venue.Builder("Eco Tower").build());
		when(venueDao.findAll()).thenReturn(venue);
		assertEquals(service.findAll().size(), venue.size());
	}
	
	@Test
	public void testSave() {
		Venue venue = new Venue.Builder("TWO/NEO Building").build();
		service.save(venue);
		assertEquals(venue.getId(), new Long(100));
		assertEquals(venue.getVenueName(), "TWO/NEO Building");
	}
	
	private Venue createVenueId() {
		return new Venue.Builder("TWO/NEO Building").build();

	}

}
