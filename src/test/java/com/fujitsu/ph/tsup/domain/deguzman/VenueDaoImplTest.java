package com.fujitsu.ph.tsup.domain.deguzman;

import static org.junit.jupiter.api.Assertions.*;

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
@ActiveProfiles({"postgres-test-deguzman"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class VenueDaoImplTest {

    @Autowired
    private VenueDao venueDao;

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public VenueDao venueDao() {
            return new VenueDaoImpl();
        }
    }

    @Test
    void test() {
        Venue venue1 = new Venue.Builder("AAAAAAAAAA").build();
        Long v1 = venueDao.saveLong(venue1);
        System.out.println("ID1:" + v1);

        Venue venue2 = new Venue.Builder("BBBBBBBBBB").build();
        Long v2 = venueDao.saveLong(venue2);
        System.out.println("ID2:" + v2);

        Venue dbVenue1 = venueDao.findById(v1);
        assertEquals("AAAAAAAAAA", dbVenue1.getVenueName());
        
        Venue dbVenue2 = venueDao.findById(v2);
        assertEquals("BBBBBBBBBB", dbVenue2.getVenueName());
        
    }

    @Test
    void test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            venueDao.findById(1L);
        });

    }

}
