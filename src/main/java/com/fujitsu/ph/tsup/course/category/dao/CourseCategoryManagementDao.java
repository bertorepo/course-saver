/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//System Name : Course Category Management Process
//Class Name: CourseCategoryManagementDao.java
//
//<<Modification History>>
//Version | Date       | Updated by          | Content
//--------+------------+---------------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
//0.03    | 02/24/2020 | WS) R.Rivero        | Update
//0.04    | 02/24/2020 | WS) J.Lira          | Update
//0.05    | 02/24/2020 | WS) R.Piloto        | Update
//=======================================================
/**
* <pre>
* The interface of Course Category Management Dao
* 
* <pre>
* 
* @version 0.05
* @author a.batongbacal
* @author r.rivero
* @author j.lira
* @author r.piloto
*
*/
public interface CourseCategoryManagementDao {

    void updateCourseCategory(CourseCategory courseCategory);

    // Method for searching course category by category
    Set<CourseCategory> findCourseCategoryByName(String name);

    // Method for creating course categories
    void createCourseCategory(CourseCategory courseCategory);
 
    // Method for loading all course category in Course Category View
    Set<CourseCategory> findAllCourseCategory();

    // Method for finding Course Category by Id
    CourseCategory findCourseCategoryById(Long id);

    // Method for deleting Course Category by Id
    void deleteCourseCategoryById(Long id);

}
