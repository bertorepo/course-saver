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
//Version | Date       | Updated by          | Content
//--------+------------+---------------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
//0.03    | 02/15/2020 | WS) J.Zamora        | Update
//0.04    | 02/15/2020 | WS) G.Cabiling      | Update
//0.05    | 02/24/2020 | WS) Z.DeGuia        | Update
//=======================================================
/**
 * <pre>
 * The implementation of course category management service
 * 
 * <pre>
 * 
 * @version 0.05
 * @author a.batongbaca
 * @author j.zamora
 * @author g.cabiling
 * @author z.deguia
 *
 */
@Service
public class CourseCategoryManagementServiceImpl implements CourseCategoryManagementService {

    @Autowired
    private CourseCategoryManagementDao courseCategoryManagementDao;

    @Override
    public void updateCourseCategory(CourseCategory courseCategory) {
        this.courseCategoryManagementDao.updateCourseCategory(courseCategory);
    }

    // Creates course category
    @Override
    public void createCourseCategory(CourseCategory courseCategory) {
        try {
            courseCategoryManagementDao.createCourseCategory(courseCategory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Set<CourseCategory> findAllCourseCategory() {

        return courseCategoryManagementDao.findAllCourseCategory();
    }

    @Override
    public Set<CourseCategory> findCourseCategoryByName(String name) {

        return courseCategoryManagementDao.findCourseCategoryByName(name);
    }

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
