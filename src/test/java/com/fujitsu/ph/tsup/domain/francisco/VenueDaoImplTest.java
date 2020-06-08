package com.fujitsu.ph.tsup.domain.francisco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
@ActiveProfiles({"postgres-test-francisco"})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VenueDaoImplTest {
    
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
        Venue venue = new Venue.Builder(new Long(1), "VenueA").build();
        venueDao.save(venue);
        
        Venue dbVenue = venueDao.findById(venue.getId());
        assertEquals("VenueA", dbVenue.getName());
    }
    
    @Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            venueDao.findById(6L);
        });
        
    }

}
