/**
 *  Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.category.web;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.course.category.service.CourseCategoryManagementService;

//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//System Name : Course Category Management Process
//Class Name: CourseCategoryManagementController.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/16/2020 | WS) G.Cabiling      | Update
//=======================================================
/**
 * <pre>
 * The controller for Course Category Management
 * 
 * <pre>
 * 
 * @version 0.02
 * @author a.batongbaca
 * @author g.cabiling
 *
 */
@Controller
@RequestMapping("/courseCategory")
public class CourseCategoryManagementController {

    // Course Category Management Service class
    @Autowired
    CourseCategoryManagementService courseCategoryManagementService;

    @GetMapping("/load")
    public String loadCourseCategory(Model model) {

        Set<CourseCategory> courseCategory = courseCategoryManagementService.findAllCourseCategory();

        List<CourseCategory> listOfCourseCategory = courseCategory.stream().collect(Collectors.toList());

        model.addAttribute("courseCategoryList", listOfCourseCategory);

        return "course-category-management/manageCourseCategory";

    }

    @PostMapping("/search")
    public String searchCourseCategory(
            @RequestParam(name = "searchCourseCategoryName") String searchCourseCategoryName, Model model,
            RedirectAttributes redirectAttributes) {

        Set<CourseCategory> courseCategory;

        if (searchCourseCategoryName.isEmpty() || searchCourseCategoryName == null) {
            return "redirect:/courseCategory/load";
        } else {
            courseCategory = courseCategoryManagementService
                    .findCourseCategoryByName(searchCourseCategoryName);
        }

        if (courseCategory == null || courseCategory.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Category does not exist.");
            return "redirect:/courseCategory/load#errorModal";
        } else {
            List<CourseCategory> listOfCourseCategory = courseCategory.stream().collect(Collectors.toList());
            model.addAttribute("courseCategoryList", listOfCourseCategory);
            return "course-category-management/manageCourseCategory";
        }

    }
}
