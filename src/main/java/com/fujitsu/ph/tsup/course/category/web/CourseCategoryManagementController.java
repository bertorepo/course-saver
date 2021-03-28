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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
//0.03    | 02/15/2020 | WS) J.Zamora        | Update
//0.04    | 02/16/2020 | WS) G.Cabiling      | Update
//0.05    | 02/24/2020 | WS) Z.DeGuia        | Update
//=======================================================
/**
 * <pre>
 * The controller for Course Category Management
 * 
 * <pre>
 * 
 * @version 0.05
 * @author a.batongbaca
 * @author j.zamora
 * @author g.cabiling
 * @author z.deguia
 *
 */
@Controller
@RequestMapping("/courseCategory")
public class CourseCategoryManagementController {

    // Course Category Management Service class
    @Autowired
    private CourseCategoryManagementService courseCategoryManagementService;

    private static String MSG_EXISTING = "Course Category Name already exist.";
    private static String MSG_SPECIAL_CHARACTER = "Category Name or Detail is invalid. Please remove invalid characters.";
    private static String MSG_NO_CHANGE = "No change in course category information.";
    private static String MSG_NOT_EXISTING = "Unable to update existing course category";
    private static String MSG_UPDATE_SUCCESSFUL = "You have successfully updated this course category";
    
    /*
     * Method for Updating Course Category Validates and save course category
     * 
     * @param id Course Category Id
     * @param form Course Category Form
     * @param bindingResult Binding Result
     * @param redirectAttributes RedirectAttributes
     * @return View
     */
    @PostMapping("/{categoryId}/update")
    public String submitUpdateCourseCategory(@RequestParam(value = "id") Long id, CourseCategoryForm form,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || form.getCategory().isEmpty() || form.getDetail().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Unable to update existing course category.");
            return "redirect:/courseCategory/load#errorModal";
        }

        CourseCategory courseDetails = new CourseCategory.Builder(form.getId(), form.getCategory(),
                form.getDetail()).build();
        
        String returnMsg = this.courseCategoryManagementService.updateCourseCategory(courseDetails);
        String message = MSG_UPDATE_SUCCESSFUL;
        String url = "redirect:/courseCategory/load";
        
        if (returnMsg == "SUCCESSFUL") {
            url = url + "#successModal";
        } else {
            redirectAttributes.addFlashAttribute("localStorage", form);
            url = url + "#errorModal";
            if(returnMsg == "EXISTING") {
                message = MSG_EXISTING;
            } else if (returnMsg == "SPECIAL") {
                message = MSG_SPECIAL_CHARACTER;
            } else if (returnMsg == "NO_CHANGE") {
                message = MSG_NO_CHANGE;
            } else {
                message = MSG_NOT_EXISTING;
            }
        }
        redirectAttributes.addFlashAttribute("message", message);
        return url;
    }

    // Loads the courseCategoryCreate view
    @GetMapping("/create")
    public String showCreateCourseCategoryForm(Model model) {
        Set<CourseCategory> courseCategory = courseCategoryManagementService.findAllCourseCategory();
        List<CourseCategory> listOfCourseCategory = courseCategory.stream().collect(Collectors.toList());
        model.addAttribute("categoryList", listOfCourseCategory);
        return "course-category-management/CreateCourseCategory";
    }

    // Validates and saves the data in the database
    @PostMapping("/create")
    public String submitCreateCourseCategoryForm(CourseCategoryForm form, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes) throws Exception {
        CourseCategory categoryDetails = new CourseCategory.Builder(form.getCategory(), form.getDetail())
                .build();
        model.addAttribute("message", courseCategoryManagementService.createCourseCategory(categoryDetails));
        Set<CourseCategory> courseCategory = courseCategoryManagementService.findAllCourseCategory();
        List<CourseCategory> listOfCourseCategory = courseCategory.stream()
                .collect(Collectors.toList());
        model.addAttribute("categoryList", listOfCourseCategory);
        return "course-category-management/CreateCourseCategory";
    }

    /**
     * Method for load all course categories
     * 
     * @param model Model
     * @return View
     */
    @GetMapping("/load")
    public String loadCourseCategory(Model model) {

        Set<CourseCategory> courseCategory = courseCategoryManagementService.findAllCourseCategory();
        List<CourseCategory> listOfCourseCategory = courseCategory.stream().collect(Collectors.toList());
        model.addAttribute("localStorage", model.getAttribute("localStorage"));
        model.addAttribute("courseCategoryList", listOfCourseCategory);
        return "course-category-management/manageCourseCategory";
    }

    /**
     * Method for searching course category
     * 
     * @param searchCourseCategoryName
     * @param model Model
     * @return View
     */
    @PostMapping("/search")
    public String searchCourseCategory(
            @RequestParam(name = "searchCourseCategoryName") String searchCourseCategoryName, Model model) {

        Set<CourseCategory> courseCategory = courseCategoryManagementService
                .findCourseCategoryByName(searchCourseCategoryName);

        List<CourseCategory> listOfCourseCategory = courseCategory.stream().collect(Collectors.toList());
        model.addAttribute("courseCategoryList", listOfCourseCategory);
        return "course-category-management/manageCourseCategory";
    }

    /**
     * Method for getting course category id to delete
     * 
     * @param id Course Category Id
     * @param form Course Category Form
     * @param bindingResult Binding Result
     * @param model Model
     * @return View
     */
    @GetMapping("/{courseCategoryId}/delete")
    public String showDeleteCourseCategoryForm(@RequestParam(value = "courseCategoryIdInput") Long id,
            CourseCategoryForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "redirect:/courseCategory/load?courseCategoryId=" + id + "#confirmDeleteModal";
        }

        CourseCategory courseCategory = courseCategoryManagementService.findCourseCategoryById(id);

        if (courseCategory != null) {
            form.setId(courseCategory.getId());
            form.setCategory(courseCategory.getCategory());
            form.setDetail(courseCategory.getDetail());
            model.addAttribute("deleteCourseCategoryForm", form);
            return "redirect:/courseCategory/load?courseCategoryId=" + id + "#confirmDeleteModal";

        } else {
            redirectAttributes.addFlashAttribute("message",
                    "Unable to delete. Course Category has already been deleted/not existing.");
            return "redirect:/courseCategory/load#errorModal";
        }

    }

    /**
     * Method for deleting course category with the given id
     * 
     * @param id Course category id
     * @param redirectAttributes RedirectAttributes
     * @param model Model
     * @return View
     */
    @PostMapping("/{courseCategoryId}/delete")
    public String submitDeleteCourseCategoryForm(@PathVariable("courseCategoryId") Long id,
            CourseCategoryForm form, RedirectAttributes redirectAttributes, Model model) {

        if (courseCategoryManagementService.deleteCourseCategoryById(id)) {
            redirectAttributes.addFlashAttribute("message",
                    "You have successfully deleted this course category.");
            return "redirect:/courseCategory/load#successModal";
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "Unable to delete. Course Category has already been deleted/not existing.");
            return "redirect:/courseCategory/load#errorModal";
        }
    }
}