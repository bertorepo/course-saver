/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.service;

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

    @Autowired
    CourseCategoryManagementDao courseCategoryManagementDao;

    @Override
    public void deleteCourseCategoryById(Long id) {

        courseCategoryManagementDao.deleteCourseCategoryById(id);

    }

    @Override
    public CourseCategory findCourseCategoryById(Long id) {

        CourseCategory courseCategoryResult = courseCategoryManagementDao.findCourseCategoryById(id);

        return courseCategoryResult;
    }
}
