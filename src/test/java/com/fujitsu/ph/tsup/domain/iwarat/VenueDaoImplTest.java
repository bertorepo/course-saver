package com.fujitsu.ph.tsup.domain.iwarat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

@JdbcTest
@ActiveProfiles({ "postgres-test-iwarat" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VenueDaoImplTest {
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
        Venue venue1st = new Venue.Builder(7L, "Two/Neo").build();
        venueDao.save(venue1st);
        Long id1 = venueDao.GeneratedKeyHolderId();
        System.out.println("ID1:" + id1);

        Venue venue2nd = new Venue.Builder(8L, "EcoTower").build();
        venueDao.save(venue2nd);
        Long id2 = venueDao.GeneratedKeyHolderId();
        System.out.println("ID1:" + id2);

        Venue datababseVenue1st = venueDao.findById(id1);
        assertEquals("Two/Neo", datababseVenue1st.getName());

        Venue datababseVenue2nd = venueDao.findById(id2);
        assertEquals("EcoTower", datababseVenue2nd.getName());

        Set<Venue> setVenue = venueDao.findAll();
        assertNotNull(setVenue.size());

    }

    @Test
    void Test_NotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            venueDao.findById(7L);
        });

    }
}
