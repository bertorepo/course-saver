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
@RequestMapping("/coursesCategory")
public class CourseCategoryManagementController {

    // Course Category Management Service class
    @Autowired
    CourseCategoryManagementService courseCategoryManagementService;

    @GetMapping("/load")
    public String load(Model model) {

        Set<CourseCategory> courseCategory = courseCategoryManagementService.findAllCourseCategory();

        List<CourseCategory> listOfCourseCategory = courseCategory.stream()
                .collect(Collectors.toList());

        model.addAttribute("courseCategoryList", listOfCourseCategory);

        return "course-management/manageCourseCategory";

    }
    
    @PostMapping("/search")
    public String submitSearchCourseCategoryForm(@RequestParam(name = "searchCourseCategoryName") String searchCourseCategoryName, Model model) {
        
        if(searchCourseCategoryName.isEmpty()) {
            
            return "redirect:/coursesCategory/load";
        }
        
        Set<CourseCategory> courseCategoryByName = courseCategoryManagementService.findCourseCategoryByName(searchCourseCategoryName);
        
        List<CourseCategory> listOfCourseCategory = courseCategoryByName.stream()
                .collect(Collectors.toList());
        
        model.addAttribute("courseCategoryList", listOfCourseCategory);
        

        return "course-management/manageCourseCategory";
    }
}

