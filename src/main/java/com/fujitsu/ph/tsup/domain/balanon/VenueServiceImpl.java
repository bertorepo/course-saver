package com.fujitsu.ph.tsup.domain.balanon;

import com.fujitsu.ph.tsup.domain.balanon.VenueDao;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fujitsu.ph.tsup.domain.balanon.Venue;

public class VenueServiceImpl implements VenueService {

    private Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);
   
    @Autowired
    private VenueDao dao;

    @Override
    public void save(Venue Id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Set<Venue> findall() {
        Set<Venue> k = dao.findall();
        if (k.isEmpty()) {
            throw new IllegalArgumentException("Input to find");
        }
        return k;
    }

    @Override
    public Venue findById(Long Id) {
        Venue j = dao.findById(Id);
        if (j.getId() == 'D') {
            throw new IllegalArgumentException("Can't Find Course ID");
        }
        return j;
    }
    
}

