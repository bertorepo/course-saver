package com.fujitsu.ph.tsup.domain.lumontad;

import java.util.Set;

public interface VenueDao {
    void save(Venue venueID);
    Set<Venue> findAll();
    Venue findByid(Long venueID);
}
