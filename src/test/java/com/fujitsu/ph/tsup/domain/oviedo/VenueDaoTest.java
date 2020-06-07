package com.fujitsu.ph.tsup.domain.oviedo;

import static org.junit.jupiter.api.Assertions.*;

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
@ActiveProfiles({"postgres-test-oviedo"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class VenueDaoTest {
	@Autowired
	private VenueDao vdao;

	 @TestConfiguration
	    static class TestContextConfiguration {

	        @Bean
	        public VenueDao venueDao() {
	            return new VenueDaoImpl();
	        }
	    }
	@Test
	void test() {
		 	Venue venue1 = new Venue.Builder("Gym").build();
	        Long vId1 = vdao.saveVenue(venue1);

	        Venue venue2 = new Venue.Builder("ITS").build();
	        Long vId2 = vdao.saveVenue(venue2);


	        Venue dc1 = vdao.findById(vId1);
	        assertEquals("Gym", venue1.getVenueName());

	        Venue dc2 = vdao.findById(vId2);
	        assertEquals("ITS", venue1.getVenueName());
	}

	@Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
        	vdao.findById(1L);
        });

    }
}
