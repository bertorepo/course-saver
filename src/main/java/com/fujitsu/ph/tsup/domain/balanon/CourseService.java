package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface CourseService {

    void save(Course CourseId);

    Set<Course> findall();

    Course findById(Long CourseId);

}
