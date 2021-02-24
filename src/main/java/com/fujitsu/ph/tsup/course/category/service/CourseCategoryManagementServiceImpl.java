package com.fujitsu.ph.tsup.course.category.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.course.category.dao.CourseCategoryManagementDao;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//System Name : Course Category Management Process
//Class Name: CourseCategoryManagementServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by          | Content
//--------+------------+---------------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
//0.02    | 02/15/2020 | WS) J.Zamora        | Update
//=======================================================
/**
* <pre>
* The implementation of course category management service
* 
* <pre>
* 
* @version 0.03
* @author a.batongbaca
* @author j.zamora
*
*/
@Service
public class CourseCategoryManagementServiceImpl implements CourseCategoryManagementService{

    @Autowired
    CourseCategoryManagementDao courseCategoryManagementDao;

    @Override
    public void updateCourseCategory(CourseCategory courseCategory) {
        this.courseCategoryManagementDao.updateCourseCategory(courseCategory);        
    }

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

