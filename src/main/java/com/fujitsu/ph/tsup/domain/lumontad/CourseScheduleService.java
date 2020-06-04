package com.fujitsu.ph.tsup.domain.lumontad;

import java.util.Set;

public interface CourseScheduleService {
    void save(CourseSchedule courseScheduleID);
    Set<CourseSchedule> findAll();
    CourseSchedule findByid(Long courseScheduleID);
}
