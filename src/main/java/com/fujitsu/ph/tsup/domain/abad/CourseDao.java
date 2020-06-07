package com.fujitsu.ph.tsup.domain.abad;

import java.util.Set;

public interface CourseDao {
    void save(Course course);
    Long saveCourse();
    Set<Course> findAll();
    Course findById(Long id);  
}
