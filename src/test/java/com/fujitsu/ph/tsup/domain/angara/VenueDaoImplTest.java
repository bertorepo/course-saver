package com.fujitsu.ph.tsup.domain.angara;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.AccessException;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({ "postgres-test-angara" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VenueDaoImplTest {

   @Autowired
   private VenueDao venueDao;
   
   @TestConfiguration
   static class TestingConfiguration {
       
       @Bean
       public VenueDao venueDao() {
           return (VenueDao) new VenueDaoImpl();
       }
   }
   
   @Test
   public void test() {
       Venue venue1 = new Venue.Builder("Mass Hall", "10002000").build();
       Long v1 = venueDao.save(venue1);
       System.out.println("ID1: " + v1);
       
       Venue dbVenue = venueDao.findById(v1);
       assertEquals("Mass Hall", dbVenue.getVenueName());
       
       Set<Venue> v = venueDao.findAll();
       assertNotNull(v.size());
   }
   
   @Test
   public void notFound_Test() {
       assertThrows(AccessException.class, () -> {
           venueDao.findById(1L);
       });
   }

}
