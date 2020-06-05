package com.fujitsu.ph.tsup.domain.iwarat;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class VenueServiceImplTest {
    @TestConfiguration
    static class VenueServiceImplTestContextConfiguration {

        @Bean
        VenueService venueService() {
            return new VenueServiceImpl();
        }
    }

    @Autowired
    private VenueService venueService;

    @MockBean
    private VenueDao venueDao;

//wala po ako test sa Save kasi void po sya wala sya return.
//sa idea ko lang po
    @Test
    void testFindById() {
        Venue expected = createVenue();
        when(venueDao.findById(any(Long.class))).thenReturn(expected);

        Venue venues = venueService.findById(7L);

        assertEquals(expected.getId(), venues.getId());
        assertEquals(expected.getName(), venues.getName());
    }

    @Test
    void testFindByIdError() {
        when(venueDao.findById(any(Long.class))).thenThrow(new IllegalArgumentException("error"));
        Exception exception = assertThrows(MyException.class, () -> {
            venueService.findById(7L);
        });

        String expectedMessage = "Error not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Set<Venue> Option = new HashSet<Venue>();
        Option.add(new Venue.Builder(7L, "Two/Neo").build());
        Option.add(new Venue.Builder(6L, "EcoTower").build());

        when(venueDao.findAll()).thenReturn(Option);
        assertEquals(2, Option.size());

    }

    @Test
    void testFindAllError() {
        when(venueDao.findAll()).thenThrow(new IllegalArgumentException("error"));
        Exception exception = assertThrows(MyException.class, () -> {
            venueService.findAll().size();
        });

        String expectedMessage = "Error not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private Venue createVenue() {
        return new Venue.Builder(7L, "Two/Neo").build();
    }
}
