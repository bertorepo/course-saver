package com.fujitsu.ph.tsup.domain.lumontad;

import java.util.Set;
public interface CourseService {
void save(Course courseID);
Set<Course> findAll();
Course findByid(Long courseID);
}
