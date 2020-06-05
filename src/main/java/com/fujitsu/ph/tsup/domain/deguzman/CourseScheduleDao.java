package com.fujitsu.ph.tsup.domain.deguzman;

import java.util.Set;

public interface CourseScheduleDao {
    void save(CourseSchedule courseSchedule);
    Long saveLong(CourseSchedule courseSchedule);
    Set<CourseSchedule> findAll();
    CourseSchedule findById(Long id);
}
