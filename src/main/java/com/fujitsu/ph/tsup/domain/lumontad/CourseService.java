package com.fujitsu.ph.tsup.domain.lumontad;

public interface CourseService {
    Long save(Course courseid);

    Course findById(Long id);
}
