/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */

@Controller
@RequestMapping("/courseCategory")

public class CourseCategoryManagementController {

    // Course Category Management Service class
    @Autowired
    CourseCategoryManagementService courseCategoryManagementService;

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
            CourseCategoryForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "redirect:/courseCategory/load?courseCategoryId=" + id + "#confirmDeleteModal";

        }

        // set value for Course Category object
        CourseCategory courseCategory = courseCategoryManagementService.findCourseCategoryById(id);

        // set value for Course Category Form object
        form.setId(courseCategory.getId());
        form.setCategory(courseCategory.getCategory());
        form.setDetail(courseCategory.getDetail());

        model.addAttribute("deleteCourseCategoryForm", form);

        return "redirect:/courseCategory/load?courseCategoryId=" + id + "#confirmDeleteModal";
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
            RedirectAttributes redirectAttributes, Model model) {

        // Call deleteCourseById() method.
        courseCategoryManagementService.deleteCourseCategoryById(id);

        redirectAttributes.addFlashAttribute("message",
                "You have successfully deleted this course category.");

        return "redirect:/courseCategory/load#successModal";
    }

}
