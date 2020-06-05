package com.fujitsu.ph.tsup.domain.yu;

import java.util.Set;

public interface CourseScheduleService {
    void save(CourseSchedule id);
    Set<CourseSchedule> findAll();
    CourseSchedule findById(Long id);

}
