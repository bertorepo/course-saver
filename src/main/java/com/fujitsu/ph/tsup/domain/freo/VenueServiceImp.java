package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


@Service
public class VenueServiceImp implements VenueService {

	@Autowired
	private VenueDao vndao;

	@Override
	public void save(Venue venue) {
		 if (venue.getId() != 0) {
	            vndao.save(venue);
	        } else if (venue.getId() <= 0) {
	            throw new VenueException("Venue Id should not be zero or less than zero.");
	        }
	}

	@Override
	public Set<Venue> findAll() {
		 Set<Venue> venueList = vndao.findAll();
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
	public Venue findById(Long Id) {
		 try {
	            return vndao.findById(Id);
	        } catch (DataAccessException ve) {
	            throw new VenueException("Venue not found!", ve);
	        }
	}
}
