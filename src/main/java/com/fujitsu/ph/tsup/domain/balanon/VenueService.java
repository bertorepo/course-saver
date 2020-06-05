package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface VenueService {
    void save(Venue VenueId);

    Set<Course> findall();

    Course findById(Long VenueId);

}
