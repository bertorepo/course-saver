package com.fujitsu.ph.tsup.domain.jimenez;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;

@JdbcTest
@ActiveProfiles({"postgres-test-jimenez"})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VenueDaoImplTest {
        
    @Autowired
    private VenueDao venueDao;
    
    @TestConfiguration
    static class TestContextConfiguration{
        
        @Bean
        public VenueDao venueDao() {
            return new VenueDaoImpl();
        }
        
    }
    
    @Test
    void test() {
        Venue vnu1 = new Venue.Builder("Mutien Hall").builder();
        venueDao.save(vnu1);
        Long vId1 = venueDao.returnGeneratedKey();
        System.out.println("ID: "+ vId1);
        
        Venue vnu2 = new Venue.Builder("SMX Convention Center").builder();
        venueDao.save(vnu2);
        Long vId2 = venueDao.returnGeneratedKey();
        System.out.println("ID: "+ vId2);
        
        Venue vnuId1 = venueDao.findById(vId1);
        System.out.println("Venue Name: "+ vnuId1.getName());
        assertEquals("Mutien Hall", vnuId1.getName());
        
        Venue vnuId2 = venueDao.findById(vId2);
        System.out.println("Venue Name: "+ vnuId2.getName());
        assertEquals("SMX Convention Center", vnuId2.getName());
        
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
