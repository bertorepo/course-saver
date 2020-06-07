package com.fujitsu.ph.tsup.domain.abad;

import java.util.Set;

public interface VenueDao {
    
    void save(Venue venue);
    Long saveVenue();
    Set<Venue> findAll();
    Venue findById(Long id);
}
