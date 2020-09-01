package com.fujitsu.ph.tsup.scheduling.web;


//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleController.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/26/2020 | WS) J.Macabugao | New Creation
//0.01    | 06/26/2020 | WS) JC.Jimenez  | New Creation
//0.01    | 06/26/2020 | WS) J.Balanon   | New Creation
//=======================================================

/**
* <pre>
* The controller for course scheduling. 
* <pre>
* @version 0.01
* @author jc.jimenez
* @author j.balanon
* @author j.macabugao
*
*/
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.ChangeStatusForm;
import com.fujitsu.ph.tsup.scheduling.model.ChangeStatusScheduleForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDeleteForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleNewForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleUpdateForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleViewForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;
import com.fujitsu.ph.tsup.scheduling.service.ScheduleService;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    /**
     * Schedule Service 
     */
    @Autowired
    private ScheduleService scheduleService;
    //private DashboardMemberService dashboardMemberService;

    
    /**
     * Logger Factory
     */
    private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    /**
     * <pre>
     * View all course schedule. Method = GET
     * 
     * <pre>
     * 
     * @param CourseScheduleListForm form
     * @param BindingResult          bindingResult
     * @param Model                  model
     * @return courseScheduleListForm and view
     */
    @GetMapping("/view")
    public String viewAllCourseSchedule(
            @Valid @ModelAttribute("scheduleView") CourseScheduleListForm courseScheduleListForm,
            BindingResult bindingResult, Model model) {

        logger.debug("CourseScheduleListForm: {}", courseScheduleListForm);
        logger.debug("Result: {}", bindingResult);
        
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long employeeId = user.getId();

        if (bindingResult.hasErrors()) {
            return "scheduling/scheduleView";
        }

        if (courseScheduleListForm.getToDateTime() == null || courseScheduleListForm.getFromDateTime() == null) {
            courseScheduleListForm.setFromDateTime(ZonedDateTime.now());
            courseScheduleListForm.setToDateTime(ZonedDateTime.now().plusDays(5));
        }

        if (courseScheduleListForm.getToDateTime().isBefore(courseScheduleListForm.getFromDateTime())) {
            model.addAttribute("scheduleView", courseScheduleListForm);
            model.addAttribute("error", "To Date should be greater than or equal to From Date");
            return "scheduling/instructorCourseScheduleList";
        } 

        Set<CourseSchedule> courseSchedule = scheduleService.findAllScheduledCourses(
                courseScheduleListForm.getFromDateTime(), courseScheduleListForm.getToDateTime());

        Set<CourseScheduleViewForm> courseScheduleViewFormSet = new HashSet<>();

        for (CourseSchedule courseSched : courseSchedule) {
            CourseScheduleViewForm courseScheduleViewForm = new CourseScheduleViewForm();

            courseScheduleViewForm.setId(courseSched.getId());
            courseScheduleViewForm.setCourseId(courseSched.getCourseId());
            courseScheduleViewForm.setCourseName(courseSched.getCourseName());
            courseScheduleViewForm.setInstructorId(courseSched.getInstructorId());
            courseScheduleViewForm.setInstructorName(
                    courseSched.getInstructorLastName() + ", " + courseSched.getInstructorFirstName());

            Set<CourseScheduleDetail> courseSchedDetSet = courseSched.getCourseScheduleDetail();
            Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = new HashSet<>();

            for (CourseScheduleDetail courseScheduleDetail : courseSchedDetSet) {
                CourseScheduleDetailForm courseSchedDetailForm = new CourseScheduleDetailForm();

                courseSchedDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
                courseSchedDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
                courseSchedDetailForm.setDuration(courseScheduleDetail.getDuration());
                courseScheduleDetailFormSet.add(courseSchedDetailForm);

            }

            courseScheduleViewForm.setCourseScheduleDetails(courseScheduleDetailFormSet);
            courseScheduleViewFormSet.add(courseScheduleViewForm);
        }
        
        courseScheduleListForm.setCourseSchedules(courseScheduleViewFormSet);
        //model.addAttribute("memberTrainingsToday", dashboardMemberService.getTrainingsToday(employeeId));
        model.addAttribute("scheduleView", courseScheduleListForm);
        return "scheduling/instructorCourseScheduleList";
    }    

    /**
     * <pre>
     * Show the Course Schedule New Form. Method = GET
     * 
     * <pre>
     * 
     * @param Model model
     * @return courseScheduleListForm and view
     */
    @GetMapping("/new")
    public String showCourseScheduleNewForm(Model model) {

        logger.debug("Model:{}", model);
        
        if (model.containsAttribute("scheduleNew")) {
            return "scheduling/createSched";
        }

        Set<CourseForm> courseFormList = scheduleService.findAllCourses();
        Set<VenueForm> venueFormList = scheduleService.findAllVenues();
        Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();

        CourseScheduleNewForm courseScheduleNewForm = new CourseScheduleNewForm();

        courseScheduleNewForm.setInstructors(instructorFormList);
        courseScheduleNewForm.setVenues(venueFormList);
        courseScheduleNewForm.setCourses(courseFormList);

        model.addAttribute("scheduleNew", courseScheduleNewForm);

        return "scheduling/createSched";
    }
    /**
     * <pre>
     * Add a row in Course Schedule Detail. Method = POST
     * 
     * <pre>
     * 
     * @param CourseScheduleNewForm  form
     * @param Model                  model
     * @param RedirectAttributes     redirectAttributes
     * @return courseScheduleNewForm and view
     */
    @PostMapping("/new/addDate")
    public String addNewCourseScheduleDetailRow
            (@ModelAttribute("scheduleNew") CourseScheduleNewForm form, 
                                    Model model, RedirectAttributes redirectAttributes) {
        
        logger.debug("CourseScheduleNewForm : {}", form);
        List<CourseScheduleDetailForm> newCourseScheduleDetailForm = new ArrayList<>();
        
        List<CourseScheduleDetailForm> newCourseScheduleDetailFormRow = 
                    new ArrayList<>(Arrays.asList(new CourseScheduleDetailForm[1]));
        
        newCourseScheduleDetailForm.addAll(form.getCourseScheduleDetailsAsList());
        newCourseScheduleDetailForm.addAll(newCourseScheduleDetailFormRow);
        
        form.setCourseScheduleDetailsAsList(newCourseScheduleDetailForm);
        
        Set<CourseForm> courseFormList = scheduleService.findAllCourses();
        Set<VenueForm> venueFormList = scheduleService.findAllVenues();
        Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();
        
        form.setInstructors(instructorFormList);
        form.setVenues(venueFormList);
        form.setCourses(courseFormList);
        
        redirectAttributes.addFlashAttribute("scheduleNew", form);
        return "redirect:/schedules/new";
    }
    /**
     * <pre>
     * Delete specific rows to Course Schedule Detail List. Method = POST
     * 
     * <pre>
     * 
     * @param PathVariable row 		 int row
     * @param CourseScheduleNewForm  form
     * @param Model                  model
     * @param RedirectAttributes     redirectAttributes
     * @return courseScheduleNewForm and view
     */
    @PostMapping("/new/removeDate/{row}")
    public String deleteCourseScheduleDetailRow(@PathVariable("row") int row, Model model, 
           @ModelAttribute("scheduleNew") CourseScheduleNewForm form, RedirectAttributes redirectAttributes) {
         
         logger.debug("CourseScheduleNewForm : {}", form);
        
         List<CourseScheduleDetailForm> newCourseScheduleDetailForm = form.getCourseScheduleDetailsAsList();
         
         int index = row - 1;
         
         newCourseScheduleDetailForm.remove(index);
         
         form.setCourseScheduleDetailsAsList(newCourseScheduleDetailForm);
         
         Set<CourseForm> courseFormList = scheduleService.findAllCourses();
         Set<VenueForm> venueFormList = scheduleService.findAllVenues();
         Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();
         
         form.setInstructors(instructorFormList);
         form.setVenues(venueFormList);
         form.setCourses(courseFormList);
         
         redirectAttributes.addFlashAttribute("scheduleNew", form);
         return "redirect:/schedules/new";
    }

    /**
     * <pre>
     * Create the course schedule. Method = POST
     * 
     * <pre>
     * 
     * @param CourseScheduleListForm form
     * @param BindingResult          bindingResult
     * @param Model                  model
     * @param RedirectAttributes     redirectAttributes
     * @return courseScheduleListForm and view
     */
    @PostMapping("/new")
    public String submitCourseScheduleNewForm(@Valid @ModelAttribute("scheduleNew") CourseScheduleNewForm form,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        Set<CourseForm> courseFormList = scheduleService.findAllCourses();
        Set<VenueForm> venueFormList = scheduleService.findAllVenues();
        Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();

        logger.debug("CourseScheduleNewForm: {}", form);
        logger.debug("Result: {}", bindingResult);

        if (bindingResult.hasErrors()) {
            form.setCourses(courseFormList);
            form.setVenues(venueFormList);
            form.setInstructors(instructorFormList);
            model.addAttribute("scheduleNew", form);
            return "scheduling/createSched";
        }
        
        Set<CourseScheduleDetailForm> courseScheduleDetailsAsListSet = new HashSet<>();
        
        //For looping inside the binded List
        for (CourseScheduleDetailForm courseSchedDetForm : form.getCourseScheduleDetailsAsList()) {
            if((courseSchedDetForm.getScheduledStartDateTime() != null) 
                    && (courseSchedDetForm.getScheduledEndDateTime() != null)) {
               courseScheduleDetailsAsListSet.add(courseSchedDetForm);
            }
        }
        
        form.setCourseScheduleDetails(courseScheduleDetailsAsListSet);

        Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = form.getCourseScheduleDetails();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();

        for (CourseScheduleDetailForm courseSchedDetForm : courseScheduleDetailFormSet) {
            CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L,
                    courseSchedDetForm.getScheduledStartDateTime(), courseSchedDetForm.getScheduledEndDateTime(), 0.0f)
                            .build();
            courseScheduleDetailSet.add(courseScheduleDetail);
        }

        CourseSchedule courseSchedule = new CourseSchedule.Builder(form.getCourseId(), form.getInstructorId(),
                form.getVenueId(), form.getMinRequired(), courseScheduleDetailSet).maxAllowed(form.getMaxAllowed())
                        .build();
        
        scheduleService.createCourseSchedule(courseSchedule);
        
        form.setCourses(courseFormList);
        form.setVenues(venueFormList);
        form.setInstructors(instructorFormList);

        redirectAttributes.addFlashAttribute("message", "Success!! Schedule has been created");
     
        return "redirect:/schedules/new";

    }
    
	/**
     * <pre>
     * View change course schedule. Method = GET
     * 
     * <pre>
     * 
     * @param Binding result         bindingResult
     * @param Model                  model
     * @param RedirectAttributes     redirectAttributes
     * @return courseScheduleListForm and view
     */
	@GetMapping("courseSchedules/view")
    public String viewChangeCourseSchedule(
            @Valid @ModelAttribute("changeSchedule") CourseScheduleListForm courseScheduleListForm,
            BindingResult bindingResult, Model model) {
	    
	    

        logger.debug("CourseScheduleListForm: {}", courseScheduleListForm);
        logger.debug("Result: {}", bindingResult);

        if (bindingResult.hasErrors()) {
            return "scheduling/changeSchedule";
        }

        if (courseScheduleListForm.getToDateTime() == null || courseScheduleListForm.getFromDateTime() == null) {
            courseScheduleListForm.setFromDateTime(ZonedDateTime.now());
            courseScheduleListForm.setToDateTime(ZonedDateTime.now().plusDays(5));
        }

        if (courseScheduleListForm.getToDateTime().isBefore(courseScheduleListForm.getFromDateTime())) {
            model.addAttribute("changeSchedule", courseScheduleListForm);
            model.addAttribute("error", "To Date should be greater than or equal to From Date");
            return "scheduling/viewSched";
        } 
        
        if (courseScheduleListForm.getToDateTime() == (courseScheduleListForm.getFromDateTime())) {
            model.addAttribute("changeSchedule", courseScheduleListForm);
            model.addAttribute("error", "From Datetime and To Datetime should not be exactly equal to each other");
            return "scheduling/viewSched";
        } 

        Set<CourseSchedule> courseSchedule = scheduleService.findAllScheduledCourses(
                courseScheduleListForm.getFromDateTime(), courseScheduleListForm.getToDateTime());

        Set<CourseScheduleViewForm> courseScheduleViewFormSet = new HashSet<>();

        for (CourseSchedule courseSched : courseSchedule) {
            CourseScheduleViewForm courseScheduleViewForm = new CourseScheduleViewForm();

            courseScheduleViewForm.setId(courseSched.getId());
            courseScheduleViewForm.setCourseId(courseSched.getCourseId());
            courseScheduleViewForm.setCourseName(courseSched.getCourseName());
            courseScheduleViewForm.setInstructorId(courseSched.getInstructorId());
            courseScheduleViewForm.setInstructorName(
                    courseSched.getInstructorLastName() + ", " + courseSched.getInstructorFirstName());

            Set<CourseScheduleDetail> courseSchedDetSet = courseSched.getCourseScheduleDetail();
            Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = new HashSet<>();

            for (CourseScheduleDetail courseScheduleDetail : courseSchedDetSet) {
                CourseScheduleDetailForm courseSchedDetailForm = new CourseScheduleDetailForm();

                courseSchedDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
                courseSchedDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
                courseSchedDetailForm.setDuration(courseScheduleDetail.getDuration());
                courseScheduleDetailFormSet.add(courseSchedDetailForm);

            }

            courseScheduleViewForm.setCourseScheduleDetails(courseScheduleDetailFormSet);
            courseScheduleViewFormSet.add(courseScheduleViewForm);
        }
        
        courseScheduleListForm.setCourseSchedules(courseScheduleViewFormSet);
     
        model.addAttribute("changeSchedule", courseScheduleListForm);
        model.addAttribute("updateView", new CourseScheduleUpdateForm());
        return "scheduling/viewSched";
    } 
    
    /**
     * <pre>
     * View and update course schedule. Method = GET
     * 
     * <pre>
     * 
     * @param CourseScheduleUpdateForm courseScheduleUpdateForm
     * @param BindingResult            bindingResult
     * @param Model                    model
     * @param PathVariable     		   long id
     * @return courseScheduleUpdateForm and view
     */
	@GetMapping("/courseSchedule/{courseScheduleId}/update")
	public String showUpdateCourseScheduleForm(@PathVariable("courseScheduleId") Long id, Model model,
			CourseScheduleUpdateForm courseScheduleUpdateForm, CourseScheduleListForm courseScheduleListForm) {

		if (model.containsAttribute("updateView")) {
			return "scheduling/viewSched";
		}

		CourseSchedule courseSchedule = scheduleService.findCourseScheduleById(id);
		Set<VenueForm> venueFormList = scheduleService.findAllVenues();
		Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();
		List<CourseScheduleDetailForm> detailFormList = new ArrayList<>();
		
		for(CourseScheduleDetail detail : courseSchedule.getCourseScheduleDetail()) {
		    CourseScheduleDetailForm detailForm = new CourseScheduleDetailForm();
		    detailForm.setId(detail.getId());
		    detailForm.setScheduledStartDateTime(detail.getScheduledStartDateTime());
		    detailForm.setScheduledEndDateTime(detail.getScheduledEndDateTime());
		    detailForm.setDuration(detail.getDuration());
		    
		    detailFormList.add(detailForm);
		}
		
		courseScheduleUpdateForm.setId(courseSchedule.getId());
		courseScheduleUpdateForm.setCourseName(courseSchedule.getCourseName());
		courseScheduleUpdateForm.setCourseId(courseSchedule.getCourseId());
		courseScheduleUpdateForm.setInstructorId(courseSchedule.getInstructorId());
		courseScheduleUpdateForm.setVenueId(courseSchedule.getVenueId());
		courseScheduleUpdateForm.setMinRequired(courseSchedule.getMinRequired());
		courseScheduleUpdateForm.setMaxAllowed(courseSchedule.getMaxAllowed());
		courseScheduleUpdateForm.setInstructors(instructorFormList);
		courseScheduleUpdateForm.setVenues(venueFormList);
		courseScheduleUpdateForm.setCourseScheduleDetailList(detailFormList);
		
		model.addAttribute("updateView", courseScheduleUpdateForm);
		model.addAttribute("changeSchedule", courseScheduleListForm);
		return "scheduling/viewSched";

    }
    
    
    @GetMapping("/courseSchedule/{courseScheduleId}/delete")
	public String showDeleteCourseScheduleForm(@PathVariable("courseId") long id, Model model,
			BindingResult bindingResult, CourseScheduleDeleteForm courseScheduleDeleteForm) {
    
		logger.debug("Result: {}", bindingResult);

		if (model.containsAttribute("deleteView")) {
			return "scheduling/viewSched";
		}
		
		CourseSchedule courseSchedule = scheduleService.findCourseScheduleById(id);
	
		courseScheduleDeleteForm.setId(courseSchedule.getId());
        
         return "scheduling/viewSched";

    }
    
    
    /**
     * <pre>
     * Update the course schedule. Method = POST
     * 
     * <pre>
     * 
     * @param CourseScheduleListForm form
     * @param BindingResult          bindingResult
     * @param Model                  model
     * @param RedirectAttributes     redirectAttributes
     * @return courseScheduleListForm and view
     */
    @PostMapping("/courseSchedule/courseScheduleId/update")
    public String submitUpdateCourseScheduleForm(@Valid @ModelAttribute("scheduleNew") CourseScheduleNewForm form,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		Set<VenueForm> venueFormList = scheduleService.findAllVenues();
		Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();

		logger.debug("CourseScheduleNewForm: {}", form);
		logger.debug("Result: {}", bindingResult);

		if (bindingResult.hasErrors()) {
			form.setVenues(venueFormList);
			form.setInstructors(instructorFormList);
			model.addAttribute("scheduleNew", form);
			return "scheduling/createSched";
		}

		Set<CourseScheduleDetailForm> courseScheduleDetailsAsListSet = new HashSet<>();

		// For looping inside the binded List
		for (CourseScheduleDetailForm courseSchedDetForm : form.getCourseScheduleDetailsAsList()) {
			if ((courseSchedDetForm.getScheduledStartDateTime() != null)
					&& (courseSchedDetForm.getScheduledEndDateTime() != null)) {
				courseScheduleDetailsAsListSet.add(courseSchedDetForm);
			}
		}

		form.setCourseScheduleDetails(courseScheduleDetailsAsListSet);

		Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = form.getCourseScheduleDetails();
		Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();

		for (CourseScheduleDetailForm courseSchedDetForm : courseScheduleDetailFormSet) {
			CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L,
					courseSchedDetForm.getScheduledStartDateTime(), courseSchedDetForm.getScheduledEndDateTime(), 0.0f)
							.build();
			courseScheduleDetailSet.add(courseScheduleDetail);
		}

		CourseSchedule courseSchedule = new CourseSchedule.Builder(form.getCourseId(), form.getInstructorId(),
				form.getVenueId(), form.getMinRequired(), courseScheduleDetailSet).maxAllowed(form.getMaxAllowed())
						.build();

		scheduleService.updateCourseSchedule(courseSchedule);

		form.setVenues(venueFormList);
		form.setInstructors(instructorFormList);


    	return "redirect:/schedules/viewSched";
    
    }
    
    /**
     * <pre>
     * Delete the course schedule. Method = DELETE
     * 
     * <pre>
     * 
     * @param path variable          Long id
     * @param Model                  model
     * @param RedirectAttributes     redirectAttributes
     * @return courseScheduleListForm and view
     */
	@DeleteMapping("/courseSchedules/{courseScheduleId}/delete")
	public String submitDeleteCourseScheduleForm(@PathVariable("courseId") long id, Model model,
			RedirectAttributes redirectAttributes) {

		scheduleService.deleteCourseScheduleById(id);

		return "redirect:/schedules/viewSched";

    }
	
	/**
     * <pre>
     * Delete the course schedule. Method = DELETE
     * 
     * <pre>
     * 
     * @param path variable          Long id
     * @param Model                  model
     * @param RedirectAttributes     redirectAttributes
     * @return courseScheduleListForm and view
     */
	 @GetMapping("/courseSchedule/{courseId}/changeStatus")
		public String showChangeScheduleForm(@PathVariable("courseId") long id, Model model,
			    ChangeStatusForm changeStatusForm) {

		  Set<CourseForm> courseFormList = scheduleService.findAllCourses();

		 if(changeStatusForm.getId() != 0L) {
			 CourseSchedule courseSchedule = scheduleService.findCourseScheduleByCourseId(id);
			 changeStatusForm.setId(courseSchedule.getId());
			 changeStatusForm.setCourses(courseFormList);
		 }
	        
	         return "scheduling/viewSched";

	    }
	    
	
	
}
