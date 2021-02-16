/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.service;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */
public interface CourseCategoryManagementService {

    void deleteCourseCategoryById(Long id);

    CourseCategory findCourseCategoryById(Long id);
    
}
