package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface VenueService {
    void save(Venue Id);

    Set<Venue> findall();

    Venue findById(Long Id);

}
