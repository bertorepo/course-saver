package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

public interface CourseScheduleService {
    void save(CourseSchedule CourseScheduleId);

    Set<CourseSchedule> findAll();

    CourseSchedule findById(Long CourseScheduleId);
}