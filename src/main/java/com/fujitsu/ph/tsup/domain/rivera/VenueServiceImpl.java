package com.fujitsu.ph.tsup.domain.rivera;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VenueServiceImpl implements VenueService{
private Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);
    
    @Autowired
    private VenueDao dao;

    @Override
    public Venue findById(Long id) {       
        Venue venue = dao.findById(id);
        if (venue.getVenueId() == 12345) {
            throw new IllegalArgumentException("Venue not found");
        } 
        return venue;   
    }

    @Override
    public void save(Venue venue) {
        dao.save(venue);
        if(venue.getVenueId() == 12345) {
            throw new IllegalArgumentException("Venue not saved");
        }
        
    }

    @Override
    public Set<Venue> findAll() {
        Set<Venue> venueAll = dao.findAll();
        if(venueAll == null) {
            throw new IllegalArgumentException("Record not found");
        }
        return venueAll;
    }
}
