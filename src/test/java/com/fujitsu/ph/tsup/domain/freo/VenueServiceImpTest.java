package com.fujitsu.ph.tsup.domain.freo;

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
class VenueServiceImpTest {

	 @Rule
	    public ExpectedException thrown = ExpectedException.none(); 

	    @TestConfiguration
	    static class VenueServiceImplestContextConfiguration {
	        
	        @Bean
	        VenueService venueService() {
	            return new VenueServiceImp();
	        }
	        
	    }

	    @Autowired
	    private VenueService service;
	    
	    @MockBean
	    private VenueDao venueDao;
	    
	    @Test
	    public void testSave(){
	        Venue c = new Venue.Builder((long) 1, "Convention Center").builder();
	        service.save(c);
	        assertEquals(c.getId(), new Long (1));
	        assertEquals(c.getVenueName(), "Convention Center");
	    }


	    @Test
	    public void testSaveEe() {
	        Venue c = new Venue.Builder((long) 0, "Convention Center").builder();
	        service.save(c);
	        assertEquals(c.getId(), new Long(0));
	        assertEquals(c.getVenueName(), "Convention Center");
	    }
	    

	    @Test
	    public void testFindAll(){
	        Set<Venue> c = new HashSet<Venue>();
	        c.add(new Venue.Builder((long) 1, "Convention Center").builder());
	        when(venueDao.findAll()).thenReturn(c);
	        assertEquals(service.findAll().size(), c.size());
	    }

	    @Test
	    public void testFindAllEe() {
	        Set<Venue> c = new HashSet<Venue>();
	        c.add(new Venue.Builder((long) 0, "Convention Center").builder());
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
	    public void testFindByIdEe() {
	        when(venueDao.findById(anyLong()))
	        .thenReturn(createVenueFindByIdEe());
	        Venue c = service.findById((long) 0);
	        assertEquals(c.getId(), new Long(0));
	    }
	    
	    private Venue createVenueFindById() {
	        return new Venue.Builder((long) 1, "Convention Center").builder();
	    }
	    private Venue createVenueFindByIdEe() {
	        return new Venue.Builder((long) 0, "Convention Center").builder();
	    }

}