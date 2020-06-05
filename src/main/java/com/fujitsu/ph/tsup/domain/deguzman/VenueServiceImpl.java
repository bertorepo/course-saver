package com.fujitsu.ph.tsup.domain.deguzman;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class VenueServiceImpl implements VenueService{
private Logger logger = LoggerFactory.getLogger(VenueServiceImpl.class);
    
    @Autowired
    private VenueDao dao;
    
    @Override
    public void save(Venue venue){
        Venue v = new Venue.Builder("Duerr Hall").build();
        if(v.getVenueName() == "Mutien Hall") {
            throw new IllegalArgumentException("Invalid venue");
        }
    }
    
    @Override
    public Set<Venue> findAll(){
        Set<Venue> v = dao.findAll();
        if (v.isEmpty() || v == null) {
            throw new IllegalArgumentException("No records found");
        } else if(v.size()==0) {
            throw new IllegalArgumentException("Invalid venue");
        }
        return v;
    }
    
    @Override
    public Venue findById(Long id){
        Venue v = dao.findById(id);
        if (v.getVenueName() == "Mutien Hall") {
            throw new IllegalArgumentException("Invalid venue");
        }
        return v;
        
    }
  
}
