package com.fujitsu.ph.tsup.course.category.web;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.course.category.model.CourseCategoryForm;
import com.fujitsu.ph.tsup.course.category.service.CourseCategoryManagementService;
//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//System Name : Course Category Management Process
//Class Name: CourseCategoryManagementController.java
//
//<<Modification History>>
//Version | Date       | Updated by          | Content
//--------+------------+---------------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
//0.02    | 02/15/2020 | WS) J.Zamora        | Update
//=======================================================
/**
* <pre>
* The controller for Course Category Management
* 
* <pre>
* 
* @version 0.03
* @author a.batongbaca
* @author j.zamora
*
*/
@Controller
@RequestMapping("/courseCategory")
public class CourseCategoryManagementController {

    // Course Category Management Service class
    @Autowired
    CourseCategoryManagementService courseCategoryManagementService;

    @PostMapping("/{categoryId}/update")
    public String submitUpdateCourseCategory(@RequestParam(value = "id") Long id, CourseCategoryForm form,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || form.getCategory().isEmpty() || form.getDetail().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Unable to update existing course category.");
            return "redirect:/courseCategory/load#errorModal";
        }

        CourseCategory courseCategory = courseCategoryManagementService.findCourseCategoryById(id);
        
        if (courseCategory != null) {
            if ( courseCategory.getCategory().equals(form.getCategory()) && courseCategory.getDetail().equals(form.getDetail()) ) {
                redirectAttributes.addFlashAttribute("message", "No change in course category information.");
                return "redirect:/courseCategory/load#errorModal";
                
            } else {
                Set<CourseCategory> courseCategorySet = courseCategoryManagementService.findCourseCategoryByName(form.getCategory());
                List<CourseCategory> listOfCourseCategory = courseCategorySet.stream().collect(Collectors.toList());
                for(CourseCategory category: listOfCourseCategory) {
                    if (!category.getId().equals(form.getId()) && category.getCategory().equals(form.getCategory()) ) {
                        redirectAttributes.addFlashAttribute("message", "Unable to update existing course category.");
                        return "redirect:/courseCategory/load#errorModal";
                    } 
                }
                CourseCategory courseDetails = new CourseCategory.Builder(form.getId(), form.getCategory(),
                        form.getDetail()).build();
                this.courseCategoryManagementService.updateCourseCategory(courseDetails);
                redirectAttributes.addFlashAttribute("message", "You have successfully updated this course category");
                return "redirect:/courseCategory/load#successModal";
            }
        
        } else {
            redirectAttributes.addFlashAttribute("message", "Unable to update existing course category.");
            return "redirect:/courseCategory/load#errorModal";
        }
    }

    // Loads the courseCategoryCreate view
    @GetMapping("/create")
    public String showCreateCourseCategoryForm(Model model) {
        model.addAttribute("create");
        return "course-category-management/CreateCourseCategory";
    }

    // Validates and saves the data in the database
    @PostMapping("/create")
    public String submitCreateCourseCategoryForm(CourseCategoryForm form, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Set<CourseCategory> categorySize = courseCategoryManagementService
                    .findCourseCategoryByName(form.getCategory());
            if (categorySize.isEmpty()) {
                CourseCategory categoryDetails = new CourseCategory.Builder(form.getCategory(),
                        form.getDetail()).build();
                courseCategoryManagementService.createCourseCategory(categoryDetails);
                model.addAttribute("successMessage", "Registration Complete.");
            } else {
                model.addAttribute("invalid", "The specified course category is already existing. Please change the Course Category Name.");
            }
        } catch (Exception ex) {
            model.addAttribute("invalid", ex.getMessage());
        }
        return "course-category-management/CreateCourseCategory";
    }
}

