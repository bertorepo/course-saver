package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;

public interface CourseDao {
    void save(CourseDao id);

    Set<CourseDao> findAll();

    CourseDao findById(Long id);

}
