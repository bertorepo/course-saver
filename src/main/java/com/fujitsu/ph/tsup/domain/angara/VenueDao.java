package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

public interface VenueDao {
    Long save(Venue venue);

    Set<Venue> findAll();

    Venue findById(Long id);
}
