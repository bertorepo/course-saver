package com.fujitsu.ph.tsup.domain.abad;

import java.util.Set;

public interface CourseScheduleDao {
    void save(CourseSchedule courseSchedule);
    
    Set<CourseSchedule> findAll();
    
    CourseSchedule findById(Long id);
}
