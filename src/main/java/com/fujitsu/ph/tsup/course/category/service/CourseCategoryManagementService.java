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
* The interface of course category management service
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
public interface CourseCategoryManagementService {

    void updateCourseCategory(CourseCategory courseCategory);

    // A method that checks if the input has a duplicate
    Set<CourseCategory> findCourseCategoryByName(String name);

    // A method that creates course category
    void createCourseCategory(CourseCategory courseCategory);    

    Set<CourseCategory> findAllCourseCategory();
    
    boolean deleteCourseCategoryById(Long id);

    CourseCategory findCourseCategoryById(Long id);

}
