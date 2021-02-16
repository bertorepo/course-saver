package com.fujitsu.ph.tsup.course.category.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.course.category.model.CourseCategoryForm;
import com.fujitsu.ph.tsup.course.category.service.CourseCategoryManagementService;

/**
 * @author a.batongbaca
 * @version Creation: 0.01 Date: 2021-02-28
 *
 */

@Controller
@RequestMapping("/category")
public class CourseCategoryManagementController {

    // Access the CourseCategoryManagementService interface
    @Autowired
    CourseCategoryManagementService courseCategoryManagementService;

    // Loads the courseCategoryCreate view
    @GetMapping("/create")
    public String showCreateCourseCategoryForm(Model model) {

        model.addAttribute("create");

        return "course-category-management/CreateCourseCategory";

    }

    // Validates and saves the data in the database
    @PostMapping("/create")
    public String submitCreateCourseCategoryForm(CourseCategoryForm form, BindingResult bindingResult,
            Model model) throws Exception {
        try {
            Set<CourseCategory> categorySize = courseCategoryManagementService
                    .findCourseCategoryByName(form.getCategory());
            if (categorySize.isEmpty()) {
                CourseCategory categoryDetails = new CourseCategory.Builder(form.getCategory(),
                        form.getDetail()).build();
                courseCategoryManagementService.createCourseCategory(categoryDetails);
                model.addAttribute("successMessage", "Registration Complete.");
            } else {
                model.addAttribute("invalid", "The course is already existing.");
            }

        } catch (Exception ex) {
            model.addAttribute("invalid", ex.getMessage());
        }
        return "course-category-management/CreateCourseCategory";

    }
}
