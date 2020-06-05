package com.fujitsu.ph.tsup.domain.iwarat;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    private VenueDao vdao;

    @Override
    public void save(Venue venue) {
        try {
            vdao.save(venue);
        } catch (IllegalArgumentException e) {
            throw new  MyException("Error found", e);
        }

    }

    @Override
    public Set<Venue> findAll() {
        try {
            return vdao.findAll();
        } catch (IllegalArgumentException e) {
            throw new MyException("Error found", e);
        }
    }

    @Override
    public Venue findById(Long id) {
        try {
            return vdao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new MyException("Error found", e);
        }
    }

}
