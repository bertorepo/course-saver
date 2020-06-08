package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface CourseDao {

    void save(Course Id);

    Set<Course> findAll();

    Course findById(Long Id);
    
    
}
