package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

public interface CourseScheduleDao {
    void save(CourseSchedule id);

    Set<CourseSchedule> findAll();

    CourseSchedule findById(Long id);

}
