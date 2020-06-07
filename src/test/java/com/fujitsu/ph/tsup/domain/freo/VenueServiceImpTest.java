package com.fujitsu.ph.tsup.domain.freo;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;




@ExtendWith(SpringExtension.class)
class VenueServiceImpTest {
	  @TestConfiguration
	    static class TestContextConfiguration {
	        
	        @Bean
	        VenueService VenueService() {
	          
	            return new VenueServiceImp();
	        }
	    }

	    @Autowired
	    private VenueService venueService;
	  
	    
	    @MockBean
	    private VenueDao vndao;
	    
	    @Test
	    void testSave() {
	        doThrow(new DataRetrievalFailureException("error occurs")).when(vndao).save(null);
	        
	        Venue ven = createVenue();        
	        venueService.save(ven);     
	        
	        assertEquals(ven.getId(), 062020L);
	        assertEquals(ven.getVenueName(), "Crescent park Hotel");
	    }
	    
	    @Test
	    void testSaveEx() {
	        doThrow(new DataRetrievalFailureException("error")).when(vndao).save(null);
	        
	        Venue vnu = createVnVenue();
	        
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
	        when(vndao.findById(any(Long.class)))
	            .thenReturn(createVnu);
	        
	        Venue venue = venueService.findById(062020L);
	        
	        assertEquals(createVnu.getId(), venue.getId());
	
	    }

	    @Test
	    void testFindById_NotFound() {
	        when(vndao.findById(any(Long.class)))
	            .thenThrow(new DataRetrievalFailureException("error occurs"));
	        
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
	        vnu.add(new Venue.Builder(062020L, "Crescent park Hotel").builder());
	        when(vndao.findAll()).thenReturn(vnu);
	        assertEquals(venueService.findAll().size(), vnu.size()); 
	    }
	    
	    @Test
	    void testFindAll_NotFound() {
	        Exception vException = assertThrows(VenueException.class, () -> {
	            venueService.findAll();
	        });
	        
	        String expectedMessage = "Can't find Venue Details";
	        String actualMessage = vException.getMessage();
	        assertTrue(actualMessage.contains(expectedMessage));
	    }

	    private Venue createVenue() {
	        return new Venue.Builder(062020L, "Crescent park Hotel").builder(); 
	    }
	    
	    private Venue createVnVenue() {
	       return new Venue.Builder(0L, "venueName").builder();
	    }
	  
}