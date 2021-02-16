package com.fujitsu.ph.tsup.course.category.service;

import java.util.Set;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca
 * @version Creation: 0.01 Date: 2021-02-28
 *
 */

public interface CourseCategoryManagementService {

    // A method that checks if the input has a duplicate
    Set<CourseCategory> findCourseCategoryByName(String name);

    // A method that creates course category
    void createCourseCategory(CourseCategory courseCategory);
}
