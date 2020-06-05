package com.fujitsu.ph.tsup.domain.deguzman;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class VenueServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none(); 

    @TestConfiguration
    static class VenueServiceImplestContextConfiguration {
        
        @Bean
        VenueService venueService() {
            return new VenueServiceImpl();
        }
        
    }

    @Autowired
    private VenueService service;
    
    @MockBean
    private VenueDao venueDao;
    
    @Test
    public void testSave(){
        Venue c = new Venue.Builder((long) 1, "Duerr Hall").build();
        service.save(c);
        assertEquals(c.getId(), new Long (1));
        assertEquals(c.getVenueName(), "Duerr Hall");
    }


    @Test
    public void testSaveErr() {
        Venue c = new Venue.Builder((long) 0, "Mutien Hall").build();
        service.save(c);
        assertEquals(c.getId(), new Long(0));
        assertEquals(c.getVenueName(), "Mutien Hall");
    }
    

    @Test
    public void testFindAll(){
        Set<Venue> c = new HashSet<Venue>();
        c.add(new Venue.Builder((long) 1, "Duerr Hall").build());
        when(venueDao.findAll()).thenReturn(c);
        assertEquals(service.findAll().size(), c.size());
    }

    @Test
    public void testFindAllErr() {
        Set<Venue> c = new HashSet<Venue>();
        c.add(new Venue.Builder((long) 0, "Mutien Hall").build());
        assertEquals(service.findAll().size(), c.size());
    }
    
    @Test
    public void testFindById(){
        when(venueDao.findById(anyLong()))
        .thenReturn(createVenueFindById());
        Venue c = service.findById((long) 1);
        assertEquals(c.getId(), new Long(1));
    }

    @Test
    public void testFindByIdErr() {
        when(venueDao.findById(anyLong()))
        .thenReturn(createVenueFindByIdErr());
        Venue c = service.findById((long) 0);
        assertEquals(c.getId(), new Long(0));
    }
    
    private Venue createVenueFindById() {
        return new Venue.Builder((long) 1, "Duerr Hall").build();
    }
    private Venue createVenueFindByIdErr() {
        return new Venue.Builder((long) 0, "Mutien Hall").build();
    }

}
