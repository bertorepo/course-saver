package com.fujitsu.ph.tsup.course.category.dao;

import java.util.Set;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

//==================================================================================================
//$Id:PR10$
//Project Name : Training Sign up
//System Name  : Course Category Management Process
//Class Name   : CourseCategoryManagementDao.java
//
//<<Modification History>>
//Version | Date       | Updated By                                                | Content
//--------+------------+---------------------------------------------------------+-----------------
//0.01    | 06/26/2020 | WS) A.Batongbacal, WS) G.Cabiling, WS) Z.De Guia, WS) J.Lira, WS) R.Piloto, R.Rivero, WS) J.Zamora    | New Creation
//==================================================================================================
/**
 * <pre>
* The data access class for course category related database access
 * </pre>
 *
 * @version 0.01
 * @author a.batongbacal
 * @author g.cabiling
 * @author z.deguia
 * @author j.lira
 * @author r.piloto
 * @author r.rivero
 * @author j.zamora
 *
 */

public interface CourseCategoryManagementDao {
    // Method for searching course category by category
    Set<CourseCategory> findCourseCategoryByName(String name);

    // Method for creating course categories
    void createCourseCategory(CourseCategory courseCategory);
}