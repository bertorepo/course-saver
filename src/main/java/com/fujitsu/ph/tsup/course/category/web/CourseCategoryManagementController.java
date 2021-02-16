/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
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

    @PostMapping("/{categoryId}/update")
    public String submitUpdateCourseCategory(@RequestParam(value = "id") Long id, CourseCategoryForm form,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || form.getCategory().isEmpty() || form.getDetail().isEmpty()) {
            return "redirect:/courseCategory/load?categoryId=" + id + "#updateModal";
        }

        CourseCategory courseCategory = courseCategoryManagementService.findCourseCategoryById(id);
        Set<CourseCategory> courseCategorySet = courseCategoryManagementService.findCourseCategoryByName(form.getCategory());
        
        if (courseCategory != null && courseCategorySet.isEmpty()) {
            CourseCategory courseDetails = new CourseCategory.Builder(form.getId(), form.getCategory(),
                    form.getDetail()).build();
            this.courseCategoryManagementService.updateCourseCategory(courseDetails);
            redirectAttributes.addFlashAttribute("message", "The course category is successfully updated.");
            return "redirect:/courseCategory/load#successModal";
        } else {
            redirectAttributes.addFlashAttribute("message", "Unable to update existing course category.");
            return "redirect:/courseCategory/load#errorModal";
        }
    }
}
