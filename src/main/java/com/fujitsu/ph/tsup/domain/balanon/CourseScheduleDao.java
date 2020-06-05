package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface CourseScheduleDao {
    void save(CourseSchedule CourseScheduleId);

    Set<Course> findall();

    Course findById(Long CourseScheduleId);

}
