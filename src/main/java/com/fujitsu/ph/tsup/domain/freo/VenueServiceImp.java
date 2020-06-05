package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;


@Service
public class VenueServiceImp implements VenueService {

	@Autowired
	private VenueDao vndao;
	
	 @Override
	 public void save(Venue venue) {
	        vndao.save(venue);
	 }
	
	@Override
	public Set<Venue> findAll()  {
		   Set <Venue> vn = vndao.findAll();
	        if (vn.isEmpty() || vn == null) {
	            throw new IllegalArgumentException("Venue not found");
	        }
	        return vn;
	    }
		
	@Override
	public  Venue findById(Long Id) {
		 Venue vn = vndao.findById(Id);
	        if (vn.getId() ==  0 ) {
	            throw new IllegalArgumentException("Venue not found");
	        }
	        return vn;
	            
	}
}
