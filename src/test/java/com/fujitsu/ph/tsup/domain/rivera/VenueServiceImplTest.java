package com.fujitsu.ph.tsup.domain.abad;

import java.util.HashSet;
import java.util.Set;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class VenueServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @TestConfiguration
    static class VenueServiceImplestContextConfiguration {
        
        @Bean
        VenueService VenueService() {
            return new VenueServiceImpl();
        }
    }
    
    @Autowired
    private VenueService service;
    
    @MockBean
    private VenueDao venuDao;
    
    @Test
    public void testFindById() {
        when(venuDao.findById(anyLong()))
            .thenReturn(createVenueId());
        Venue venue = service.findById(1000L);
        assertEquals(venue.getVenueId(), new Long(1000));
    }
    
    private Venue createVenueId() {
        return new Venue.Builder(1000L, "Home").build();
    }
    
    @Test
    public void testSave() {
        Venue venue = new Venue.Builder(1000L, "Home").build();
        service.save(venue);
        assertEquals(venue.getVenueId(), new Long(1000));
        assertEquals(venue.getVenueName(), "Home");
        
    }
    

     
    @Test
    public void testFindAll() {
        Set<Venue> venue = new HashSet<Venue>();
        venue.add(new Venue.Builder(1000L, "Home").build());
        when(venuDao.findAll()).thenReturn(venue);
        assertEquals(service.findAll().size(), venue.size());
    }
    
 
    
}