package com.fujitsu.ph.tsup.coursemanagement.service;

import com.fujitsu.ph.tsup.coursemanagement.model.Course;


public interface CourseManagementService {

    void deleteCourseById(Long id);

    Course findCourseById(Long id);
    
}
