package com.fujitsu.ph.tsup.course.category.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.course.category.dao.CourseCategoryManagementDao;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */

@Service
public class CourseCategoryManagementServiceImpl implements CourseCategoryManagementService {

    // Call CourseCategoryManagementDao
    @Autowired
    CourseCategoryManagementDao courseCategoryManagementDao;

    // Checks if the category name has a duplicate in the database
    @Override
    public Set<CourseCategory> findCourseCategoryByName(String name) {
        try {
            Set<CourseCategory> categoryFormList = courseCategoryManagementDao.findCourseCategoryByName(name);
            if (!categoryFormList.isEmpty()) {
                throw new IllegalArgumentException("The specified course category is already existing. Please change the Course Category Name.");
            } else {
                return categoryFormList;
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("The specified course category is already existing. Please change the Course Category Name.");
        }
    }

    // Creates course category
    public void createCourseCategory(CourseCategory courseCategory) {
        try {
            courseCategoryManagementDao.createCourseCategory(courseCategory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

