/**
 *  Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
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
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/16/2020 | WS) G.Cabiling      | Update
//=======================================================
/**
 * <pre>
 * The implementation of course category management service
 * 
 * <pre>
 * 
 * @version 0.02
 * @author a.batongbaca
 * @author g.cabiling
 *
 */
@Service
public class CourseCategoryManagementServiceImpl implements CourseCategoryManagementService {

    @Autowired
    CourseCategoryManagementDao courseCategoryManagementDao;

    @Override
    public Set<CourseCategory> findAllCourseCategory() {

        return courseCategoryManagementDao.findAllCourseCategory();
    }

    @Override
    public Set<CourseCategory> findCourseCategoryByName(String name) {

        Set<CourseCategory> courseCategoryFormList = null;

        try {

            courseCategoryFormList = courseCategoryManagementDao.findCourseCategoryByName(name);

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return courseCategoryFormList;

    }
}