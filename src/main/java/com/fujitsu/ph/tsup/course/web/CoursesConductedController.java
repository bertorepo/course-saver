//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : CoursesConductedController                                                                                                                                                                
// Class Name   : CoursesConductedController.java                                                                                                                                                                           
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/02/17 | WS)M.Rivera           | New Creation    
// 1.0.1   | 2021/03/08 | WS)R.Molina           | Updated
//==================================================================================================
package com.fujitsu.ph.tsup.course.web;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.model.CoursesConducted;
import com.fujitsu.ph.tsup.course.model.CoursesConductedForm;
import com.fujitsu.ph.tsup.course.model.CoursesConductedListForm;
import com.fujitsu.ph.tsup.course.service.CoursesConductedService;


/**
 * <pre>
 * The controller for the courses conducted
 * 
 * <pre>
 * 
 * @version 0.01
 * @author m.rivera
 */

@Controller
@RequestMapping("/report/")
public class CoursesConductedController {

    /**
     * Courses Conducted Service 
     */
    @Autowired
    private CoursesConductedService coursesConductedService;

    /**
     * Logger Factory
     */
    private static Logger logger = LoggerFactory.getLogger(CoursesConductedController.class);
    
    /**
     * <pre>
     * View all course schedule. Method = GET
     * 
     * <pre>
     * 
     * @param courseConductedForm    form
     * @param BindingResult          bindingResult
     * @param Model                  model
     * @return courseConductedListForm and view
     */    
      @GetMapping("/course") 
      public String viewCoursesConducted(@Valid @ModelAttribute("coursesConducted")
      CoursesConductedListForm coursesConductedListForm, Long reportTypeId, BindingResult bindingResult,Model model){
      
      logger.debug("CourseConductedListForm: {}", coursesConductedListForm); logger.debug("Result: {}",
      bindingResult);
      
      
      if (bindingResult.hasErrors()) 
      { 
          return "course-management/generateReports"; 
      }
      
      if (coursesConductedListForm.getScheduledStartDateTime() == null ||
      coursesConductedListForm.getScheduledEndDateTime() == null) { 
          model.addAttribute("nullMessage","No Course Schedule Found");
          coursesConductedListForm.setScheduledStartDateTime(ZonedDateTime.now().withHour(0).withMinute(0));
          coursesConductedListForm.setScheduledEndDateTime(ZonedDateTime.now().plusDays(5)); 
      }
      
      System.out.println(reportTypeId);
      
      Set<CoursesConducted> courseConducted =
      coursesConductedService.findAllCoursesConducted(coursesConductedListForm.getScheduledStartDateTime(),
      coursesConductedListForm.getScheduledEndDateTime());
      
      Set<CoursesConductedForm> coursesConductedFormSet = new HashSet<>();
      
      for(CoursesConducted coursesConducted : courseConducted) 
      { 
          CoursesConductedForm coursesConductedForm = new CoursesConductedForm();
          
          coursesConductedForm.setId(coursesConducted.getId());
          coursesConductedForm.setCourseName(coursesConducted.getCourseName());
          coursesConductedForm.setScheduledStartDateTime(coursesConducted.getScheduledStartDateTime());
          coursesConductedForm.setScheduledEndDateTime(coursesConducted.getScheduledEndDateTime());
          coursesConductedForm.setRescheduledStartDateTime(coursesConducted.getRescheduledStartDateTime());
          coursesConductedForm.setRescheduledEndDateTime(coursesConducted.getRescheduledEndDateTime());
      
          coursesConductedFormSet.add(coursesConductedForm); 
      }
          
          coursesConductedListForm.setCoursesConductedSet(coursesConductedFormSet);
          Comparator<CoursesConductedForm> comparator = Comparator
          .comparing(CoursesConductedForm::getCourseName)
          .thenComparing(CoursesConductedForm::getScheduledStartDateTime); List<CoursesConductedForm>
          listOfCoursesConductedFormSet = coursesConductedFormSet.stream() .sorted(comparator)
          .collect(Collectors.toList());
      
          model.addAttribute("coursesConductedForm", coursesConductedListForm);
          model.addAttribute("coursesConducted", listOfCoursesConductedFormSet);
          model.addAttribute("nullMessage", "No Summary Courses Conducted Found");
      
          return "course-management/generateReports"; 
      }

    @GetMapping("/load")
    public String viewSummaryCourseConducted() {

    	return "course-management/generateReports";
    }

}
