package com.fujitsu.ph.tsup.coursemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.coursemanagement.dao.CourseManagementDao;
import com.fujitsu.ph.tsup.coursemanagement.model.Course;

@Service
public class CourseManagementServiceImpl implements CourseManagementService {

    @Autowired
    CourseManagementDao courseManagementDao;

    @Override
    public void deleteCourseById(Long id) {

        courseManagementDao.deleteCourseById(id);

    }

    @Override
    public Course findCourseById(Long id) {

        Course courseResult = courseManagementDao.findCourseById(id);

        return courseResult;
    }
    
}
