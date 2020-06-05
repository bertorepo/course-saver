package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

public interface CourseService {
    void save(Course courseId);

    Set<Course> findAll();

    Course findById(Long courseId);
}
