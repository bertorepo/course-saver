package com.fujitsu.ph.tsup.domain.lumontad;

import java.util.Set;

public interface CourseDao {
    void save(Course course);

    Set<Course> findAll();

    Course findById(Long id);

    Long saveLong(Course courseName);
}
