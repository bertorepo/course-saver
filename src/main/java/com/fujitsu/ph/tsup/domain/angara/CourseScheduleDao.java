package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

public interface CourseScheduleDao {
    Long save(CourseSchedule id);

    Set<CourseSchedule> findAll();

    CourseSchedule findById(Long id);

    

}
