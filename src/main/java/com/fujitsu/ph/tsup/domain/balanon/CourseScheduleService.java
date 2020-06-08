package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface CourseScheduleService {

    void save(CourseSchedule Id);

    Set<CourseSchedule> findAll();

    CourseSchedule findById(Long Id);
}
