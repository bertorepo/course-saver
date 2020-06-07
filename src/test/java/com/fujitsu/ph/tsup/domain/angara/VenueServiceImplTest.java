package com.fujitsu.ph.tsup.domain.angara;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class VenueServiceImplTest {

    // two test each to create
    // (1) successful retrieval (2) error/dao throws exceptions

    @TestConfiguration
    static class TestingConfiguration {

        @Bean
        VenueService venueService() {
            return new VenueServiceImpl();
        }
    }

    @Autowired
    private VenueService venueService;

    @MockBean
    private VenueDao venueDao;

    // testing save
    @Test
    public void TestSave() {
        Venue v = new Venue.Builder("Mass Hall", (long) 1).build();
        venueService.save(v);
        assertEquals(v.getId(), new Long (1));
        assertEquals(v.getVenueName(), "Mass Hall");
    }
    @Test
    public void TestSaveError() {
        Venue v = new Venue.Builder("City Hall", (long) 2).build();
        venueService.save(v);
        assertEquals(v.getId(), new Long(2));
        assertEquals(v.getVenueName(), "City Hall");
    }
    
    // testing findAll
    // testing findById

    }

