package com.fujitsu.ph.tsup.domain.macabugao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-macabugao" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
	
class VenueDaoImplTest {
	
	@Autowired
	private VenueDao venueDao;
	
	@TestConfiguration
    static class TestContextConfiguration {
        
        @Bean
        public VenueDao venueDao() {
        	return new VenueDaoImpl();
        }
    }

	@Test
	void test() {
		Venue venue1 = new Venue.Builder("Hall").build();
		venueDao.save(venue1);
		Long id1 = venueDao.generatedKey();
		System.out.println("ID:" + id1);
		
		Venue venue2 = new Venue.Builder("Hall").build();
		venueDao.save(venue2);
		Long id2 = venueDao.generatedKey();
		System.out.println("ID:" + id2);
		
		Venue dbVenue1 = venueDao.findById(id1);
		assertEquals("Hall", dbVenue1.getVenueName());
		
		Venue dbVenue2 = venueDao.findById(id2);
		assertEquals("Hall", dbVenue2.getVenueName());
		
		Set<Venue> venueSet = venueDao.findAll();
		assertNotNull(venueSet.size());

	}
	
	@Test
	void Test_NotFound() {
		assertThrows(EmptyResultDataAccessException.class, () -> {
			venueDao.findById(1L);
		});
	}

}
