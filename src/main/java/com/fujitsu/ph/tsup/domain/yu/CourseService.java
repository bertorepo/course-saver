package com.fujitsu.ph.tsup.domain.yu;

import java.util.Set;

public interface CourseService {
    void save(Course id);
    Set<Course> findAll();
    Course findById(Long id);
}