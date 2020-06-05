package com.fujitsu.ph.tsup.domain.jimenez;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService{
    @Autowired
    private VenueDao venueDao;

    @Override
    public Venue findById(Long id) {
        try {
            return venueDao.findById(id);
        } catch (DataAccessException ex) {
            throw new VenueException("Venue not found!", ex);
        }
    }
    
    @Override
    public Set<Venue> findAll() {
        Set<Venue> venueList = venueDao.findAll();
        try {
            if(venueList.isEmpty() || venueList == null) {
                throw new VenueException("Can't find Venue Details");
            } else {
                return venueList;
            }    
        } catch (DataAccessException ex) {
            throw new VenueException("Can't access Venue Details.");
        }
        
    }

    @Override
    public void save(Venue venue) {
        if (venue.getId() != 0) {
            venueDao.save(venue);
        } else if (venue.getId() <= 0) {
            throw new VenueException("Venue Id should not be zero or less than zero.");
        }
        
     }
    
    

}
