package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface VenueDao {
    void save(Venue Id);

    Set<Venue> findall();

    Venue findById(Long Id);
}
