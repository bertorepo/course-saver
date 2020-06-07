package com.fujitsu.ph.tsup.domain.yu;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VenueServiceImpl implements VenueService {
    private Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);

    @Autowired
    private VenueDao dao;

    @Override
    public void save(Venue id) {
        dao.save(id);
        if (id.getId() == 2) {
            throw new IllegalArgumentException("Save Failed");
        }
    }

    @Override
    public Venue findById(Long id) {
        Venue c = dao.findById(id);
        if (c.getId() == 2) {
            throw new IllegalArgumentException("Venue id not found");
        }
        return c;

    }

    @Override
    public Set<Venue> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}