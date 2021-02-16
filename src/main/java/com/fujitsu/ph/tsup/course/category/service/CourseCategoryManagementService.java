/**
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.category.service;

import java.util.Set;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//System Name : Course Category Management Process
//Class Name: CourseCategoryManagementService.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/16/2020 | WS) G.Cabiling      | Update
//=======================================================
/**
 * <pre>
 * The interface of course category management service
 * 
 * <pre>
 * 
 * @version 0.02
 * @author a.batongbaca
 * @author g.cabiling
 *
 */
public interface CourseCategoryManagementService {

    Set<CourseCategory> findAllCourseCategory();

    Set<CourseCategory> findCourseCategoryByName(String name);
}
