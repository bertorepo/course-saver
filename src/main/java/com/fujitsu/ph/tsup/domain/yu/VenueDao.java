package com.fujitsu.ph.tsup.domain.yu;

import java.util.Set;

public interface VenueDao {
    void save(Venue id);
    Set<Venue> findAll();
    Venue findById(Long id);
    Long generatedKey();
}
