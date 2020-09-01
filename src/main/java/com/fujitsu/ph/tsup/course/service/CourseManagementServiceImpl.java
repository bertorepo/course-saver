/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.course.dao.CourseManagementDao;
import com.fujitsu.ph.tsup.course.model.Course;

/**
 * CourseManagementServiceImpl Class
 * 
 * @author c.lepiten (New Creation by: c.Lepiten)
 * @version Revision: 0.01 Date: 2020-08-28
 */
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

    @Override
    public Set<Course> findAllCourses() {

        return courseManagementDao.findAllCourses();
    }

}