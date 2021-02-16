/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.dao;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//System Name : Course Category Management Process
//Class Name: CourseCategoryManagementDao.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
//=======================================================
/**
* <pre>
* The interface of Course Category Management Dao
* 
* <pre>
* 
* @version 0.02
* @author a.batongbaca
*
*/
public interface CourseCategoryManagementDao {

    void updateCourseCategory(CourseCategory courseCategory);

}
