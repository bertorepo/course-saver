package com.fujitsu.ph.tsup.domain.jimenez;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
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
        
        assertEquals(venue.getId(), 123434L);
        assertEquals(venue.getName(), "Mutien Hall");
    }
    
    @Test
    void testSaveEx() {
        doThrow(new DataRetrievalFailureException("error")).when(venueDao).save(null);
        
        Venue vnu = createErrVenue();
        
        Exception vException = assertThrows(VenueException.class, () -> {
            venueService.save(vnu);
        });
        
        String expectedMessage = "Venue Id should not be zero or less than zero.";
        String actualMessage = vException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
    }



    @Test
    void testFindById() {
        Venue createVnu = createVenue();
        when(venueDao.findById(any(Long.class)))
            .thenReturn(createVnu);
        
        Venue venue = venueService.findById(123434L);
        
        assertEquals(createVnu.getId(), venue.getId());
;
    }

    @Test
    void testFindById_NotFound() {
        when(venueDao.findById(any(Long.class)))
            .thenThrow(new DataRetrievalFailureException("error"));
        
        Exception vException = assertThrows(VenueException.class, () -> {
            venueService.findById(1L);
        });
        
        String expectedMessage = "Venue not found!";
        String actualMessage = vException.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testFindAll() {
        Set<Venue> vnu = new HashSet<Venue>();
        vnu.add(createVenue());
        
    }

    private Venue createVenue() {
        return new Venue.Builder(123434L, "Mutien Hall").builder(); 
    }
    
    private Venue createErrVenue() {
       return new Venue.Builder(0L, "name").builder();
    }
    
    private Venue createNullVenue() {
        return new Venue.Builder(null, null).builder();
    }
}
