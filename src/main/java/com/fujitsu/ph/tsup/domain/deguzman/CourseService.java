package com.fujitsu.ph.tsup.domain.deguzman;

import java.util.Set;

import org.omg.CORBA.portable.ApplicationException;


public interface CourseService {
    void save(Course course);
    Set<Course> findAll();
    Course findById(Long id);
}

