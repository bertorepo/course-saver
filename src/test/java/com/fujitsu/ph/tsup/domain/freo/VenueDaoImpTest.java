package com.fujitsu.ph.tsup.domain.freo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
/*

@JdbcTest
@ActiveProfiles({"postgres-test-freo"})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VenueDaoImpTest {
	 @Autowired
	    private VenueDao venueDao;
	    
	    @TestConfiguration
	    static class TestContextConfiguration{
	        
	        @Bean
	        public VenueDaoImp vdao() {
	            return new VenueDaoImp();
	        }
	        
	    }
	    
	    @Test
	    void test() {
	        Venue vn = new Venue.Builder("Crescent Park Hotel").builder();
	        venueDao.save(vn);
	        Long vId = venueDao.returnGeneratedKey();
	        System.out.println("ID: "+ vId);
	        
	        Venue ve = new Venue.Builder("Circuit Mall").builder();
	        venueDao.save(ve);
	        Long vId1 = venueDao.returnGeneratedKey();
	        System.out.println("ID: "+ vId);
	        
	        Venue vuId1 = venueDao.findById(vId1);
	        System.out.println("Venue Name: "+ vuId1.getVenueName());
	        assertEquals("Crescent Park Hotel", vuId1.getVenueName());
	        
	        Venue vuId2 = venueDao.findById(vId1);
	        System.out.println("Venue Name: "+ vuId2.getVenueName());
	        assertEquals("Circuit Mall", vuId2.getVenueName());
	        
	        Set<Venue> VnuSet = venueDao.findAll();
	        assertNotNull(VnuSet.size());
	    }
	    
	    
	    @Test
	    void Test_NotFound() {
	        assertThrows(EmptyResultDataAccessException.class, () -> {
	            venueDao.findById(1L);
	        });
	    }
}
*/