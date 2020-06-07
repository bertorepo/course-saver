package com.fujitsu.ph.tsup.domain.abad;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({"postgres-test-abad"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
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
        Venue venueOne = new Venue.Builder("Imus Plaza").build();
        venueDao.save(venueOne);
        Long vnOne = venueDao.saveVenue();
        System.out.println("ID1: " + vnOne);
        
        Venue dbVenueOne = venueDao.findById(vnOne);
        
        System.out.println("Venue Name: " +dbVenueOne.getVenueName());
        assertEquals("Imus Plaza", dbVenueOne.getVenueName());
        
        Venue venueTwo = new Venue.Builder("Fujitsu Building").build();
        venueDao.save(venueTwo);
        Long vnTwo = venueDao.saveVenue();
        System.out.println("ID2: " + vnTwo);
        
        Venue dbVenueTwo = venueDao.findById(vnTwo);
        System.out.println("Venue Name: " +dbVenueTwo.getVenueName());
        assertEquals("Fujitsu Building", dbVenueTwo.getVenueName());
         
        Set<Venue> venue = venueDao.findAll();
        assertNotNull(venue.size());
        
    }
    
    @Test
    void NoTestFound() {
        assertThrows(EmptyResultDataAccessException.class, () ->{
            venueDao.findById(1L);
        });
    }
}
