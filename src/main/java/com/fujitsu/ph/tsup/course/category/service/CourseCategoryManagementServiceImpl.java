/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fujitsu.ph.tsup.course.category.dao.CourseCategoryManagementDao;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */
public class CourseCategoryManagementServiceImpl implements CourseCategoryManagementService{

    @Autowired
    CourseCategoryManagementDao courseCategoryManagementDao;

    @Override
    public void updateCourseCategory(CourseCategory courseCategory) {
        this.courseCategoryManagementDao.updateCourseCategory(courseCategory);        
    }

}
