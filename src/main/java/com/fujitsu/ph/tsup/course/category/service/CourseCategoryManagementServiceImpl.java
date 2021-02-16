/**
 * 
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
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
//=======================================================
/**
* <pre>
* The implementation of course category management service
* 
* <pre>
* 
* @version 0.02
* @author a.batongbaca
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

}
