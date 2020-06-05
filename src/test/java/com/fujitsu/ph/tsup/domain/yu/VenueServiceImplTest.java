package com.fujitsu.ph.tsup.domain.yu;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import com.fujitsu.ph.tsup.domain.yu.VenueService;
import com.fujitsu.ph.tsup.domain.yu.VenueDao;
import com.fujitsu.ph.tsup.domain.yu.Venue;

@RunWith(SpringRunner.class)
public class VenueServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @TestConfiguration
    static class VenueServiceImplTestContextConfiguration {

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
    public void testSave() {
        Venue c = new Venue.Builder((long) 1, "TwoNeo").build();
        service.save(c);
        assertEquals(c.getId(), new Long(1));
        assertEquals(c.getName(), "TwoNeo");
    }

    @Test
    public void testSaveError() {
        Venue c = new Venue.Builder((long) 2, "EcoTower").build();
        service.save(c);
        assertEquals(c.getId(), new Long(2));
        assertEquals(c.getName(), "EcoTower");
    }

    @Test
    public void testFindAll() {
        Set<Venue> c = new HashSet<Venue>();
        c.add(new Venue.Builder((long) 1, "TwoNeo").build());
        when(venueDao.findAll()).thenReturn(c);
        assertEquals(service.findAll().size(), c.size());
    }

    @Test
    public void testFindAllError() {
        Set<Venue> c = new HashSet<Venue>();
        c.add(new Venue.Builder((long) 2, "EcoTower").build());
        assertEquals(service.findAll().size(), c.size());
    }

    @Test
    public void testFindById() {
        when(venueDao.findById(anyLong())).thenReturn(createVenueFindById());
        Venue c = service.findById((long) 1);
        assertEquals(c.getId(), new Long(1));
    }

    @Test
    public void testFindByIdError() {
        when(venueDao.findById(anyLong()))
                .thenReturn(createVenueFindByIdError());
        Venue c = service.findById((long) 2);
        assertEquals(c.getId(), new Long(2));
    }

    private Venue createVenueFindById() {
        return new Venue.Builder((long) 1, "TwoNeo").build();
    }
    private Venue createVenueFindByIdError() {
        return new Venue.Builder((long) 2, "EcoTower").build();
    }
}