package com.fujitsu.ph.tsup.domain.francisco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

public class VenueServiceImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        VenueService venuService() {
            return new VenueServiceImpl();
        }
    }

    @Autowired
    private VenueService venueService;

    @MockBean
    private VenueDao venueDao;

    @Test
    void testSave() {
        Venue venue = new Venue.Builder(new Long(1), "venueA").build();
        assertEquals(venue.getId(), new Long(1));
        assertEquals(venue.getName(), "venueA");
    }

    @Test
    void testSaveError() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Venue.Builder(new Long(1), "").build();
        });

        String expectedMessage = "venue name should not be null or empty!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Set<Venue> venue = new HashSet<Venue>();
        venue.add(new Venue.Builder(new Long(1), "VenueA").build());
        venue.add(new Venue.Builder(new Long(2), "VenueB").build());
        when(venueDao.findAll()).thenReturn(venue);
        assertEquals(venueService.findAll().size(), venue.size());
    }

    @Test
    void testFindAllError() {
        when(venueDao.findAll()).thenThrow(new IllegalArgumentException("Application Error!"));
        Exception exception = assertThrows(ApplicationException.class, () -> {
            venueService.findAll().size();
        });

        String expectedMessage = "Application Error!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindById() {
        Venue c = new Venue.Builder(new Long(1), "venueA").build();
        when(venueDao.findById(c.getId())).thenReturn(c);
        Venue course = venueService.findById(c.getId());
        assertEquals(c.getId(), course.getId());
        assertEquals(c.getName(), course.getName());
    }
    
    @Test
    void testFindByIdError () {
        when(venueDao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("Application Error!"));
        Exception exception = assertThrows(ApplicationException.class, () -> {
            venueService.findById(any(Long.class));
        });
        
        String expectedMessage = "Application Error!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
