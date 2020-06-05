package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

public class VenueServiceImpl implements VenueService {

    @Autowired
    private VenueDao dao;

    // check if id matches any id from venue
    // (venue.getId() != id) throw exception

    @Override
    public void save(Venue venue) {
        dao.save(venue);
        if (venue.getId() == null || venue.getId() == 0) {
            throw new IllegalArgumentException("Venue not saved.");
        }
    }

    @Override
    public Set<Venue> findAll() {
        Set<Venue> v = dao.findAll();
        if (v.isEmpty()) {
            throw new IllegalArgumentException("No records found.");
        }
        return v;
    }

    @Override
    public Venue findById(Long id) {
        Venue vd = dao.findById(id);
        if (vd.getId() == 0) {
            throw new IllegalArgumentException("No records found.");
        }
        return vd;
    }
}