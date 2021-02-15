/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CourseCategoryManagementController {

    // Course Category Management Service class
    @Autowired
    CourseCategoryManagementService courseCategoryManagementService;


    @PostMapping("/{categoryId}/update")
    public String submitUpdateCourseCategory(@RequestParam(value = "id") Long id, CourseCategoryForm form,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || form.getCategory().isEmpty() || form.getDetail().isEmpty()) {
            return "redirect:/courseCategory/manage?categoryId=" + id + "#updateModal";
        }

        CourseCategory courseCategory = courseCategoryManagementService.findCourseCategoryById(id);
        Set<CourseCategory> courseCategorySet = courseCategoryManagementService.findCourseCategoryByName(form.getCategory());
        
        if (courseCategory != null && courseCategorySet.isEmpty()) {
            CourseCategory courseDetails = new CourseCategory.Builder(form.getId(), form.getCategory(),
                    form.getDetail()).build();
            this.courseCategoryManagementService.updateCourseCategory(courseDetails);
            redirectAttributes.addFlashAttribute("message", "The course category is successfully updated.");
            return "redirect:/courseCategory/manage#successModal";
        } else {
            redirectAttributes.addFlashAttribute("message", "Unable to update existing course category.");
            return "redirect:/courseCategory/manage#errorModal";
        }
    }
}
