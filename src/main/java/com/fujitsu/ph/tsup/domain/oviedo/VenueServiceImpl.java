package com.fujitsu.ph.tsup.domain.oviedo;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class VenueServiceImpl implements VenueService {
	private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	VenueDao dao;

	@Override
	public void save(Venue venue) {
		// TODO Auto-generated method stub
		dao.save(venue);
	}

	@Override
	public Set<Venue> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Venue findById(Long id) {
		// TODO Auto-generated method stub
		try {
			return dao.findById(id);
		}catch(IllegalArgumentException ex) {
			throw new ApplicationException("Venue not found",ex);
		}
	}
}
