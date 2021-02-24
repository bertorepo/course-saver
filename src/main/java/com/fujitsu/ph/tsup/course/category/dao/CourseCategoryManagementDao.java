/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */
public interface CourseCategoryManagementDao {

    // Method for finding Course Category by Id
    CourseCategory findCourseCategoryById(Long id);

    // Method for deleting Course Category by Id
    void deleteCourseCategoryById(Long id);

}
