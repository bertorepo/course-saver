package com.fujitsu.ph.tsup.domain.lumontad;

public interface CourseDao {
    Long save(Course courseid);

    Course findById(Long id);
}
