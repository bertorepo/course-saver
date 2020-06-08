package com.fujitsu.ph.tsup.domain.francisco;

import java.util.Set;

public interface CourseDao {
    void save(Course course);

    Set<Course> findAll();

    Course findById(Long id);

}
