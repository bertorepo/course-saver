package com.fujitsu.ph.tsup.domain.jimenez;

import java.util.Set;

public interface CourseService {
    void save(Course course);
    Set<Course> findAll();
    Course findById(Long id);
}
