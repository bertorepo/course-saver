/**
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.category.service;

import java.util.Set;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//Class Name: CourseCategoryManagementController.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) G.Cabiling      | Update
//=======================================================
/**
 * 
 * <pre>
 * JavaBean for CourseCategoryManagementService
 * <pre>
 * 
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */
public interface CourseCategoryManagementService {

    Set<CourseCategory> findAllCourseCategory();

    Set<CourseCategory> findCourseCategoryByName(String name);
}
