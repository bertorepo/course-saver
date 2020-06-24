package com.fujitsu.ph.tsup.domain.francisco;

import java.util.Set;

public interface VenueDao {
    void save(Venue venue);

    Set<Venue> findAll();

    Venue findById(Long id);
}