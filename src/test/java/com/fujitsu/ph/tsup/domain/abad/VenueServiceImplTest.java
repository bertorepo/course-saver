package com.fujitsu.ph.tsup.domain.abad;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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
        assertEquals(venue.getId(), new Long(1000));
    }
    
    @Test
    public void testFindById_Unmatched() {
        when(venuDao.findById(anyLong()))
            .thenReturn(createVenueIdUnmatched());
        Venue venue = service.findById(2020L);
        assertEquals(venue.getId(), new Long(2020));
    }
    
    private Venue createVenueId() {
        return new Venue.Builder(1000L, "Imus Plaza").build();
    }
    
    private Venue createVenueIdUnmatched() {
        return new Venue.Builder(2020L, "Fujitsu Building").build();
    }
    
    @Test
    public void testSave() {
        Venue venue = new Venue.Builder(1000L, "Imus Plaza").build();
        service.save(venue);
        assertEquals(venue.getId(), new Long(1000));
        assertEquals(venue.getVenueName(), "Imus Plaza");
        
    }
    
    @Test
    public void testSaveUnmatched() {
        Venue venue = new Venue.Builder(2020L, "Imus Plaza").build();
        service.save(venue);
        assertEquals(venue.getId(), new Long(2020));
        assertEquals(venue.getVenueName(), "Fujitsu Building");
    }
     
    @Test
    public void testFindAll() {
        Set<Venue> venue = new HashSet<Venue>();
        venue.add(new Venue.Builder(1000L, "Imus Plaza").build());
        when(venuDao.findAll()).thenReturn(venue);
        assertEquals(service.findAll().size(), venue.size());
    }
    
    @Test
    public void testFindAllUnmatched() {
        Set<Venue> venue = new HashSet<Venue>();
        venue.add(new Venue.Builder(0L, "").build());
        assertEquals(service.findAll().size(), venue.size());
    }
    
}