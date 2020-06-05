package com.fujitsu.ph.tsup.domain.jimenez;

import java.util.Set;

public interface CourseScheduleDao {
    void save(CourseSchedule CourseSchedule);
    Set<CourseSchedule> findAll();
    CourseSchedule findById(Long id);
}

