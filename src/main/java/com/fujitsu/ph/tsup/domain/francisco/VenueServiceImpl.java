package com.fujitsu.ph.tsup.domain.francisco;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl implements VenueService{

    @Autowired
    private VenueDao venueDao;

    @Override
    public void save(Venue venue) {
        try {
            venueDao.save(venue);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

    @Override
    public Set<Venue> findAll() {
        try {
            return venueDao.findAll();
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

    @Override
    public Venue findById(Long id) {
        try {
            return venueDao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Application Error!", e);
        }
    }

}
