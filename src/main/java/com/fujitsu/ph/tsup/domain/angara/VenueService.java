package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

public interface VenueService {
    void save(Venue venueId);
    Set<Venue> findAll();
    Venue findById (Long venueId);

}