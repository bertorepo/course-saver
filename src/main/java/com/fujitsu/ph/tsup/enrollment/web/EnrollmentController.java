package com.fujitsu.ph.tsup.enrollment.web;

import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.enrollment.model.Certificate;
import com.fujitsu.ph.tsup.enrollment.model.CertificateForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseDeclineForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrollCancelForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrolledListForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrollmentForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.enrollment.model.EnrolledMemberForm;
import com.fujitsu.ph.tsup.enrollment.model.FileStorageProperties;
import com.fujitsu.ph.tsup.enrollment.model.SearchForm;
import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
import com.fujitsu.ph.tsup.enrollment.service.EnrollmentService;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;
//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: EnrollmentController.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 06/25/2020 | WS) K.Freo       | New Creation
//0.01    | 06/29/2020 | WS) M.Lumontad   | Updated
//0.01    | 06/29/2020 | WS) M.Rivera     | Updated
//0.01    | 06/29/2020 | WS) G.Cabiling   | Updated
//0.01    | 06/30/2020 | WS) K.Freo       | Updated
//0.01    | 06/30/2020 | WS) M.Lumontad   | Updated
//0.01    | 07/01/2020 | WS) G.Cabiling   | Updated
//0.01    | 07/01/2020 | WS) T.Oviedo     | Updated
//0.01    | 07/30/2020 | WS) M.Lumontad   | Updated
//0.01    | 08/05/2020 | WS) J.Yu         | Updated
//0.02    | 09/15/2020 | WS) J.Yu         | Updated
//0.03    | 02/23/2021 | WS) E.Ceniza     | Update
//0.03    | 03/24/2021 | WS) K.Sanchez    | Update
//0.03    | 03/23/2021 | WS) C.Macatangay | Update
//0.04    | 05/04/2021 | WS) A.Senamin    | Update
//0.05    | 06/02/2021 | WS) l.Celoso     | Update
//0.05    | 06/16/2021 | WS) M.Taboada    | Update
//=======================================================
/**
 * <pre>
 * The implementation of Enrollment Controller
 * 
 * <pre>
 * 
 * @version 0.01
 * @author K.Freo
 */
@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {
	private static Logger logger = LoggerFactory.getLogger(EnrollmentController.class);
	@Autowired
	private EnrollmentService enrollmentService;
	private CourseEnrolledListForm enrolledListForm = new CourseEnrolledListForm();
	private final static int pageSize = 10;
	/**
	 * <pre>
	 * US02. As a member, I can view all course that I can enroll. URL Value =
	 * /schedules , method = GET 1. if bindingResult.hasErrors() 1.1. Return the
	 * course schedule list form and view 2. Set the following fields Argument |
	 * Condition | Set Value form.fromDateTime | isBlank | Date Today
	 * form.toDateTime | isBlank | Date Today + 5 days 3. if form.toDateTime <
	 * cform.fromDateTime 3.1 Return the course schedule list form and view with a
	 * message "To Date should be greater than or equal to From Date" 4. Call
	 * enrollmentService.findAllScheduledCourses using the given form.fromDateTime,
	 * form.toDateTime 4.1. Return the course schedule list form and view
	 * 
	 * <pre>
	 * 
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return String
	 * @author J.yu
	 */
	@GetMapping("/viewCourseEnroll")
	public String viewAllCourseSchedule(@Valid @ModelAttribute("viewCourseEnroll") CourseScheduleListForm form,
			BindingResult result, Model model) {
		
		logger.debug("CourseScheduleListForm: {}", form);
		logger.debug("Result: {}", result);

		int currentPage = 1;
		String currentPageStr = form.getCurrentPage();
		if(isNumeric(currentPageStr)) {
			currentPage = Integer.parseInt(currentPageStr);
		}
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        
		//Get all Filter dropdown values =======================================================================
		//
		//Get all course categories
		try {
			Set<CourseCategory> courseCategory = enrollmentService.findAllCourseCategory();
	        List<CourseCategory> listOfCourseCategory = courseCategory.stream().collect(Collectors.toList());
	        model.addAttribute("categoryList", listOfCourseCategory);
		} catch (Exception e) {
			model.addAttribute("categoryListError", e.getMessage());
		}
		
		//Get all courses
		try {
			Set<Course> courseName = enrollmentService.findAllCourseName();
	        List<Course> listOfCourseName = courseName.stream().collect(Collectors.toList());
	        model.addAttribute("courseNameList", listOfCourseName);
		} catch (Exception e) {
			model.addAttribute("courseNameListError", e.getMessage());
		}
		
		//Get all instructors
		try {
			Set<InstructorForm> intructor = enrollmentService.findAllInstructor();
	        List<InstructorForm> listOfInstructors = intructor.stream().collect(Collectors.toList());
	        model.addAttribute("instructorList", listOfInstructors);
		} catch (Exception e) {
			model.addAttribute("instructorListError", e.getMessage());
		}
		
		//Get all venues
		try {
			Set<VenueForm> venue = enrollmentService.findAllVenue();
	        List<VenueForm> listOfVenues = venue.stream().collect(Collectors.toList());
	        model.addAttribute("venueList", listOfVenues);
		} catch (Exception e) {
			model.addAttribute("venueListError", e.getMessage());
		}
		//========================================================================================================
		
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", result.getAllErrors());
			return "enrollment/viewCourseEnroll";
		}
		if (form.getFromDateTime() == null) {
			form.setFromDateTime(ZonedDateTime.now().withHour(0).withMinute(0));
		}
		if (form.getToDateTime() == null) {
			form.setToDateTime(ZonedDateTime.now().plusDays(5));
		}
		
		//Check selected filters ----
		form.setCourseCategoryId(checkFilterId(form.getCourseCategoryId()));
		form.setCourseNameId(checkFilterId(form.getCourseNameId()));
		form.setInstructorId(checkFilterId(form.getInstructorId()));
		form.setVenueId(checkFilterId(form.getVenueId()));
		form.setMandatory(checkFilterId(form.getMandatory()));
		form.setDeadline(checkFilterId(form.getDeadline()));
		// --------------------
		
		List<TopLearnerForm> listTopLearnerByMonth = enrollmentService.findTopLearner(ZonedDateTime.now(),
				ZonedDateTime.now().plusMonths(1));
		List<TopLearnerForm> listTopLearnerByQuarter = enrollmentService.findTopLearner(ZonedDateTime.now(),
				ZonedDateTime.now().plusMonths(4));
		
		Set<CourseSchedule> courseScheduleAllActive = enrollmentService.findAllActiveCourseSchedule();
		Set<CourseScheduleForm> courseScheduleSetForm = new LinkedHashSet<>();
		
		for (CourseSchedule courseSchedule : courseScheduleAllActive) {
			CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
			courseScheduleForm.setId(courseSchedule.getId());
			courseScheduleForm.setCourseId(courseSchedule.getCourseId());
			courseScheduleForm.setCourseCategory(courseSchedule.getCourseCategory());
			courseScheduleForm.setCourseName(courseSchedule.getCourseName());
			courseScheduleForm.setInstructorId(courseSchedule.getInstructorId());
			courseScheduleForm.setInstructorName(
					courseSchedule.getInstructorLastName() + " " + courseSchedule.getInstructorFirstName());
            courseScheduleForm.setMandatory(courseSchedule.getMandatory());// added
            courseScheduleForm.setDeadline(courseSchedule.getDeadline());// added
			courseScheduleForm.setVenueId(courseSchedule.getVenueId());
			courseScheduleForm.setVenueName(courseSchedule.getVenueName());
			courseScheduleForm.setCourseStatus(courseSchedule.getCourseStatus());	
			courseScheduleForm.setMinRequired(courseSchedule.getMinRequired());
			courseScheduleForm.setMaxAllowed(courseSchedule.getMaxAllowed());
			courseScheduleForm.setTotalParticipants(courseSchedule.getTotalParticipants());
			courseScheduleForm.setCourseDetails(courseSchedule.getCourseDetails());
			CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
			CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
			courseScheduleDetailForm.setId(courseScheduleDetail.getId());
			courseScheduleDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
			courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
			courseScheduleDetailForm.setDuration(courseScheduleDetail.getDuration());
			courseScheduleForm.setCourseScheduleDetails(courseScheduleDetailForm);
			courseScheduleSetForm.add(courseScheduleForm);
			form.setTopLearnerByMonth(listTopLearnerByMonth);
			form.setTopLearnerByQuarter(listTopLearnerByQuarter);
		}
		model.addAttribute("activeCourseSchedule", courseScheduleSetForm);
		
		if (form.getFromDateTime().isAfter(form.getToDateTime())
				|| form.getFromDateTime().isEqual(form.getToDateTime())) {
			model.addAttribute(form);
			model.addAttribute("error", "Invalid Date");
			model.addAttribute("nullMessage", "No Course Schedule Found");
			model.addAttribute("paginatedViewCourseEnroll", new PageImpl<>(new ArrayList<CourseScheduleForm>()));
			return "enrollment/viewCourseEnroll";
		}
		
		Set<CourseScheduleForm> courseScheduleFormSet = new LinkedHashSet<CourseScheduleForm>();
		try {
			Set<CourseSchedule> courseSchedules = enrollmentService.findAllScheduledCourses(
															form.getFromDateTime(),
															form.getToDateTime(), 
															form.getCourseCategoryId(), 
															form.getCourseNameId(), 
															form.getInstructorId(), 
															form.getVenueId(), 
															form.getMandatory(), 
															form.getDeadline(),
															pageable);
			for (CourseSchedule courseSchedule : courseSchedules) {
				CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
				courseScheduleForm.setId(courseSchedule.getId());
				courseScheduleForm.setCourseCategory(courseSchedule.getCourseCategory());
				courseScheduleForm.setCourseName(courseSchedule.getCourseName());
				courseScheduleForm.setInstructorName(courseSchedule.getInstructorLastName() + ", " + courseSchedule.getInstructorFirstName());
                courseScheduleForm.setMandatory(courseSchedule.getMandatory());
	            courseScheduleForm.setDeadline(courseSchedule.getDeadline());
				courseScheduleForm.setVenueName(courseSchedule.getVenueName());
				courseScheduleForm.setCourseStatus(courseSchedule.getCourseStatus());	
				courseScheduleForm.setMinRequired(courseSchedule.getMinRequired());
				courseScheduleForm.setMaxAllowed(courseSchedule.getMaxAllowed());
				courseScheduleForm.setTotalParticipants(courseSchedule.getTotalParticipants());
				courseScheduleForm.setCourseDetails(courseSchedule.getCourseDetails());
				CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
				CourseScheduleDetailForm courseSchedDetailForm = new CourseScheduleDetailForm();
				courseSchedDetailForm.setId(courseScheduleDetail.getId());
				courseSchedDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
				courseSchedDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
				courseSchedDetailForm.setDuration(courseScheduleDetail.getDuration());
				courseScheduleForm.setCourseScheduleDetails(courseSchedDetailForm);
				courseScheduleFormSet.add(courseScheduleForm);
				form.setCourseSchedules(courseScheduleFormSet);
			}
		} catch (Exception e) {
			model.addAttribute("nullMessage", e.getMessage());
		}
		model.addAttribute("viewCourseEnroll", form);
		
		// For Pagination ================================================================
		List<CourseScheduleForm> listOfviewCourseEnroll = courseScheduleFormSet
			       .stream()
			       .collect(Collectors.toList());
		int availableCourse = enrollmentService.countCourse(form.getFromDateTime(),
															form.getToDateTime(), 
															form.getCourseCategoryId(), 
															form.getCourseNameId(), 
															form.getInstructorId(), 
															form.getVenueId(), 
															form.getMandatory(), 
															form.getDeadline());
		Page<CourseScheduleForm> paginatedCourseEnroll= new PageImpl<>(listOfviewCourseEnroll,pageable,availableCourse);
		model.addAttribute("paginatedViewCourseEnroll", paginatedCourseEnroll);
		// ===============================================================================
		
		return "enrollment/viewCourseEnroll";
	}
	/**
	 * 
	 * @param courseEnrolledListForm
	 * @param bindingResult
	 * @param model
	 * @return String
	 * 
	 * @author m.lumontad
	 */
	@GetMapping("/mySchedules")
	public String viewAllEnrolledCourses(CourseEnrolledListForm courseEnrolledListForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "enrollment/myCourseSched";
		}
		if (courseEnrolledListForm.getFromDateTime() == null || courseEnrolledListForm.getToDateTime() == null) {
			courseEnrolledListForm.setFromDateTime(ZonedDateTime.now().withHour(0).withMinute(0));
			courseEnrolledListForm.setToDateTime(ZonedDateTime.now().plusDays(5));
		}
		if (courseEnrolledListForm.getToDateTime().isBefore(courseEnrolledListForm.getFromDateTime())) {
			model.addAttribute("myCourseSched", courseEnrolledListForm);
			model.addAttribute("errorMessage", "No Course Schedule Found");
			model.addAttribute("error", "To Date should be greater than From Date");
			return "enrollment/myCourseSched";
		}
		
		FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			Set<CourseParticipant> enrolledCourses = enrollmentService.findAllEnrolledCoursesByParticipantId(
					user.getId(), courseEnrolledListForm.getFromDateTime(), courseEnrolledListForm.getToDateTime());
			List<CourseEnrollmentForm> courseSchedules = new ArrayList<CourseEnrollmentForm>();
			for (CourseParticipant enrolledCourse : enrolledCourses) {
				CourseEnrollmentForm courseEnrollmentForm = new CourseEnrollmentForm();
				courseEnrollmentForm.setId(enrolledCourse.getId());
				courseEnrollmentForm.setCourseId(enrolledCourse.getCourseId());
				courseEnrollmentForm.setCourseScheduleId(enrolledCourse.getCourseScheduleId());
				courseEnrollmentForm.setCourseName(enrolledCourse.getCourseName());
				courseEnrollmentForm.setInstructorName(enrolledCourse.getInstructorName());
				courseEnrollmentForm.setVenueName(enrolledCourse.getVenueName());
				courseEnrollmentForm.setCourseStatus(enrolledCourse.getCourseStatus());	
				courseEnrollmentForm.setRegistrationDate(enrolledCourse.getRegistrationDate());
				courseEnrollmentForm.setParticipantId(enrolledCourse.getParticipantId());
				courseEnrollmentForm.setAttendanceStatus(enrolledCourse.getAttendanceStatus());
				courseEnrollmentForm.setCourseDetails(enrolledCourse.getCourseDetails());
				CourseScheduleDetail courseSchedDet = enrolledCourse.getCourseScheduleDetail();
				CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
				courseScheduleDetailForm.setScheduledStartDateTime(courseSchedDet.getScheduledStartDateTime());
				courseScheduleDetailForm.setScheduledEndDateTime(courseSchedDet.getScheduledEndDateTime());
				courseScheduleDetailForm.setDuration(courseSchedDet.getDuration());
				courseEnrollmentForm.setCourseScheduleDetails(courseScheduleDetailForm);
				courseSchedules.add(courseEnrollmentForm);
				courseEnrolledListForm.setCourseScheduleDetailForm(courseSchedules);
			}
			List<CourseEnrollmentForm> setSortedCourseScheduleForm = courseSchedules.stream()
					.collect(Collectors.toCollection(ArrayList::new));
			List<CourseEnrollmentForm> sortedCourseScheduleForm = setSortedCourseScheduleForm.stream()
					.sorted((e1, e2) -> e1.getCourseName().compareTo(e2.getCourseName())).collect(Collectors.toList());
			courseEnrolledListForm.setCourseScheduleDetailForm(sortedCourseScheduleForm);
			
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		List<String> mandatoryCourseList = enrollmentService.findCourseScheduleIfMandatory();
		model.addAttribute("myCourseSched", courseEnrolledListForm);
		model.addAttribute("mandatoryCourseList", mandatoryCourseList);
		return "enrollment/myCourseSched";
	}
	/**
	 * Method for showCourseDeclineForm
	 * 
	 * A member can decline course with reason.URL Value =
	 * /myschedules/{courseParticipantId}/decline, method = GET Call
	 * enrollmentService.findCourseParticipantById using the given id Set the values
	 * from the previous step into the CourseDeclineForm Return the Course decline
	 * form and view
	 */
	@GetMapping("/myschedules/{courseParticipantId}/decline")
	public String showCourseDeclineForm(@PathVariable("courseParticipantId") Long id, Model model) {
		logger.debug("Model:{}", model);
		CourseDeclineForm courseDeclineForm = new CourseDeclineForm();
		CourseParticipant courseParticipant = enrollmentService.findCourseParticipantById(id);
		courseDeclineForm.setId(courseParticipant.getId());
		courseDeclineForm.setCourseName(courseParticipant.getCourseName());
		courseDeclineForm.setInstructorName(courseParticipant.getInstructorName());
		courseDeclineForm.setVenueName(courseParticipant.getVenueName());
		courseDeclineForm.setCourseStatus(courseParticipant.getCourseStatus());
		courseDeclineForm.setParticipantName(courseParticipant.getParticipantName());
		courseDeclineForm.setRegistrationDate(courseParticipant.getRegistrationDate());
		courseDeclineForm.setReason(courseParticipant.getReason());
		model.addAttribute("courseDecline", courseDeclineForm);
		return "enrollment/myCourseSched";
	}
	/**
	 * Method for submitCourseDeclineForm
	 * 
	 * A member can decline course with reason URL Value =
	 * /myschedules/{courseParticipantId}/decline, method = DELETE Transform the
	 * form to courseParticipant. Call enrollmentService.declineCourse using the
	 * courseParticipant Return the Course decline form and view. Return also a
	 * success message.
	 */
	@DeleteMapping("/mySchedules/decline")
	public String submitCourseDeclineForm(@Valid @ModelAttribute("courseDecline") CourseDeclineForm courseDeclineForm,
			Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		System.out.println("DELETE MAPPING");
		logger.debug("courseDeclineForm:{}", courseDeclineForm);
		logger.debug("BindingResult:{}", bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("courseDecline", courseDeclineForm);
			return "enrollment/myCourseSched";
		}
		try {
			CourseEnrolledListForm listForm = new CourseEnrolledListForm();
			listForm.setFromDateTime(enrolledListForm.getFromDateTime());
			listForm.setToDateTime(enrolledListForm.getToDateTime());
			System.out.println("ID: " + courseDeclineForm.getId());
			System.out.println("courseName: " + courseDeclineForm.getCourseName());
			System.out.println("courseId: " + courseDeclineForm.getCourseId());
			System.out.println("courseScheduleId: " + courseDeclineForm.getCourseScheduleId());
			System.out.println("instructorName: " + courseDeclineForm.getInstructorName());
			System.out.println("venueName: " + courseDeclineForm.getVenueName());
			System.out.println("courseStatus: " + courseDeclineForm.getCourseStatus());
			System.out.println("registrationDate: " + courseDeclineForm.getRegistrationDate());
			System.out.println("Reason: " + courseDeclineForm.getReason());
			CourseParticipant courseParticipant = new CourseParticipant.Builder(courseDeclineForm.getId(),
					courseDeclineForm.getCourseId(), courseDeclineForm.getCourseScheduleId(),
					courseDeclineForm.getCourseName(), courseDeclineForm.getInstructorName(),
					courseDeclineForm.getVenueName(), courseDeclineForm.getCourseStatus(), courseDeclineForm.getId(), courseDeclineForm.getParticipantName(),
					courseDeclineForm.getRegistrationDate())
							.decline(courseDeclineForm.getReason()).build();
			enrollmentService.declineCourse(courseParticipant);
			redirectAttributes.addFlashAttribute("myCourseSched", listForm);
			/*
			 * Success Message an attribute to be passed to Thymeleaf to show Success
			 * Message
			 */
			redirectAttributes.addFlashAttribute("courseDeclineSuccess", "You have declined the course successfully!");
			return "redirect:/enrollment/mySchedules";
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * Error Message an attribute to be passed to Thymeleaf to show Error Message
			 */
			redirectAttributes.addFlashAttribute("errorDeclineMessage", e.getMessage());
		}
		return "redirect:/enrollment/mySchedules";
	}
	@GetMapping("/schedules/{courseScheduleId}/enroll")
	public String showCourseEnrollmentForm(@PathVariable("courseScheduleId") Long id, Model model,
			RedirectAttributes redirectAttributes) {
		logger.debug("Model:{}", model);
		if (model.containsAttribute("courseEnrollmentForm")) {
			return "enrollment/viewCourseEnroll";
		}
		CourseSchedule courseSchedule = enrollmentService.findCourseScheduleById(id);
		CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
		CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
		courseScheduleDetailForm.setId(courseScheduleDetail.getCourseScheduleId());
		courseScheduleDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
		courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
		CourseEnrollmentForm courseEnrollmentForm = new CourseEnrollmentForm();
		courseEnrollmentForm.setCourseScheduleId(courseSchedule.getId());
		courseEnrollmentForm.setCourseName(courseSchedule.getCourseName());
		courseEnrollmentForm.setInstructorName(
				courseSchedule.getInstructorLastName() + ", " + courseSchedule.getInstructorFirstName());
		courseEnrollmentForm.setVenueName(courseSchedule.getVenueName());
		courseEnrollmentForm.setCourseScheduleDetails(courseScheduleDetailForm);
		courseEnrollmentForm.setRegistrationDate(ZonedDateTime.now());
		model.addAttribute("courseEnrollmentForm", courseEnrollmentForm);
		logger.debug("courseEnrollmentForm: {}", courseEnrollmentForm);
		return "redirect:/enrollment/viewCourseEnroll";
	}
	@PostMapping("/schedules/{courseScheduleId}/enroll")
	public String submitCourseEnrollmentForm(@Valid @ModelAttribute("enroll") CourseEnrollmentForm courseEnrollmentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("START ENROLL");
		logger.debug("submitCourseEnrollmentForm:{}", courseEnrollmentForm);
		logger.debug("Result:{}", result);
		System.out.println("IF ENROLL");
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMsg", result.getAllErrors());
			return "redirect:/enrollment/viewCourseEnroll";
		}
		System.out.println("TRY ENROLL");
		try {
			FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			CourseScheduleDetailForm courseScheduleDetailForm = courseEnrollmentForm.getCourseScheduleDetails();
			CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(
					courseScheduleDetailForm.getId()).build();
			CourseParticipant courseParticipant = new CourseParticipant.Builder(
					courseEnrollmentForm.getCourseScheduleId(), user.getId(), user.getUserName() + "@fujitsu.com",
					ZonedDateTime.now()).addDetail(courseScheduleDetail).build();
			
			enrollmentService.enroll(courseParticipant);
			enrollmentService.sendCalendarInvite(courseParticipant);
			System.out.println("ADDING FLASH ATTRIBUTE");
			redirectAttributes.addFlashAttribute("successMessage", "Successfully Enrolled a Course!!!");
			redirectAttributes.addFlashAttribute("emailMessage", "A calendar invite has been sent to your email:");
			redirectAttributes.addFlashAttribute("courseEnrollmentForm", courseEnrollmentForm);
			redirectAttributes.addFlashAttribute("courseParticipant", courseParticipant);
			System.out.println("ADDING FLASH ATTRIBUTE");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("duplicateMessage", e.getMessage());
		}
		return "redirect:/enrollment/viewCourseEnroll";
	}
	/**
	 * Cancel Course Schedule
	 * 
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/schedules/{courseScheduleId}/cancel")
	public String submitCourseEnrollmentCancelForm(@RequestParam("courseScheduleId") Long id, Model model,
			RedirectAttributes redirectAttributes) {
		enrollmentService.cancel(id);
		redirectAttributes.addFlashAttribute("successMessage", "Successfully Canceled the Course Schedule");
		return "redirect:/enrollment/viewCourseEnroll";
	}
	/**
	 * View Members Course
	 * 
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@GetMapping("/viewMemberCourse")
	public String viewAllMemberCourse(@Valid @ModelAttribute("viewCourseEnroll") CourseScheduleListForm form,
			BindingResult result, Model model) {
		logger.debug("CourseScheduleListForm: {}", form);
		logger.debug("Result: {}", result);
		
		int currentPage = 1;
		String currentPageStr = form.getCurrentPage();
		if(isNumeric(currentPageStr)) {
			currentPage = Integer.parseInt(currentPageStr);
		}
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		
        //Get all course categories
		try {
			Set<CourseCategory> courseCategory = enrollmentService.findAllCourseCategory();
	        List<CourseCategory> listOfCourseCategory = courseCategory.stream().collect(Collectors.toList());
	        model.addAttribute("categoryList", listOfCourseCategory);
		} catch (Exception e) {
			model.addAttribute("categoryListError", e.getMessage());
		}
		
		//Get all courses
		try {
			Set<Course> courseName = enrollmentService.findAllCourseName();
	        List<Course> listOfCourseName = courseName.stream().collect(Collectors.toList());
	        model.addAttribute("courseNameList", listOfCourseName);
		} catch (Exception e) {
			model.addAttribute("courseNameListError", e.getMessage());
		}
		
		//Get all instructors
		try {
			Set<InstructorForm> intructor = enrollmentService.findAllInstructor();
	        List<InstructorForm> listOfInstructors = intructor.stream().collect(Collectors.toList());
	        model.addAttribute("instructorList", listOfInstructors);
		} catch (Exception e) {
			model.addAttribute("instructorListError", e.getMessage());
		}
		
		//Get all venues
		try {
			Set<VenueForm> venue = enrollmentService.findAllVenue();
	        List<VenueForm> listOfVenues = venue.stream().collect(Collectors.toList());
	        model.addAttribute("venueList", listOfVenues);
		} catch (Exception e) {
			model.addAttribute("venueListError", e.getMessage());
		}
		//
        
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", result.getAllErrors());
			return "enrollment/viewMemberCourse";
		}
		if (form.getFromDateTime() == null) {
			form.setFromDateTime(ZonedDateTime.now().withHour(0).withMinute(0));
		}
		if (form.getToDateTime() == null) {
			form.setToDateTime(ZonedDateTime.now().plusDays(5));
		}
		
		//Check filters ----
		form.setCourseCategoryId(checkFilterId(form.getCourseCategoryId()));
		form.setCourseNameId(checkFilterId(form.getCourseNameId()));
		form.setInstructorId(checkFilterId(form.getInstructorId()));
		form.setVenueId(checkFilterId(form.getVenueId()));
		form.setMandatory(checkFilterId(form.getMandatory()));
		form.setDeadline(checkFilterId(form.getDeadline()));
		// --------------------
		
		if (form.getFromDateTime().isAfter(form.getToDateTime())
				|| form.getFromDateTime().isEqual(form.getToDateTime())) {
			model.addAttribute(form);
			model.addAttribute("error", "Invalid Date");
			model.addAttribute("nullMessage", "No course schedule found");
			model.addAttribute("paginatedViewMemberCourse", new PageImpl<>(new ArrayList<CourseScheduleForm>()));
			return "enrollment/viewMemberCourse";
		}

		Set<CourseScheduleForm> courseScheduleFormSet = new LinkedHashSet<CourseScheduleForm>();
		try {
			Set<CourseSchedule> courseSchedules = enrollmentService.findAllScheduledCourses(form.getFromDateTime(),
																							form.getToDateTime(),
																							form.getCourseCategoryId(),
																							form.getCourseNameId(), 
																							form.getInstructorId(), 
																							form.getVenueId(), 
																							form.getMandatory(), 
																							form.getDeadline(), 
																							pageable);
			for (CourseSchedule courseSchedule : courseSchedules) {
				CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
				courseScheduleForm.setId(courseSchedule.getId());
				courseScheduleForm.setCourseCategory(courseSchedule.getCourseCategory());
				courseScheduleForm.setCourseName(courseSchedule.getCourseName());
				courseScheduleForm.setInstructorName(
						courseSchedule.getInstructorLastName() + ", " + courseSchedule.getInstructorFirstName());
                courseScheduleForm.setMandatory(courseSchedule.getMandatory());
                courseScheduleForm.setDeadline(courseSchedule.getDeadline());
				courseScheduleForm.setVenueName(courseSchedule.getVenueName());
				courseScheduleForm.setMinRequired(courseSchedule.getMinRequired());
				courseScheduleForm.setMaxAllowed(courseSchedule.getMaxAllowed());
				courseScheduleForm.setTotalParticipants(courseSchedule.getTotalParticipants());
				courseScheduleForm.setCourseDetails(courseSchedule.getCourseDetails());
				CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
				CourseScheduleDetailForm courseSchedDetailForm = new CourseScheduleDetailForm();
				courseSchedDetailForm.setId(courseScheduleDetail.getId());
				courseSchedDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
				courseSchedDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
				courseSchedDetailForm.setDuration(courseScheduleDetail.getDuration());
				courseScheduleForm.setCourseScheduleDetails(courseSchedDetailForm);
				courseScheduleFormSet.add(courseScheduleForm);
				form.setCourseSchedules(courseScheduleFormSet);
			}
			model.addAttribute("viewMemberCourse", form);
			logger.debug("courseScheduleListForm: {}", form);
			
		} catch (Exception e) {
			model.addAttribute("nullMessage", e.getMessage());
		}
		
		// For Pagination ================================================================
		List<CourseScheduleForm> listOfviewCourseEnroll = courseScheduleFormSet
			       .stream()
			       .collect(Collectors.toList());
		int availableCourse = enrollmentService.countCourse(form.getFromDateTime(),
															form.getToDateTime(), 
															form.getCourseCategoryId(), 
															form.getCourseNameId(), 
															form.getInstructorId(), 
															form.getVenueId(), 
															form.getMandatory(), 
															form.getDeadline());
		Page<CourseScheduleForm> paginatedCourseEnroll= new PageImpl<>(listOfviewCourseEnroll,pageable,availableCourse);
		model.addAttribute("paginatedViewMemberCourse", paginatedCourseEnroll);
		// ===============================================================================
		
		return "enrollment/viewMemberCourse";
	}
	/**
	 * Find all active course schedules by month/quarter - ajax
	 * 
	 * @param queryBy
	 * @return
	 */
	@PostMapping("/findSchedules")
	@ResponseBody
	public Set<CourseSchedule> findAllCourseScheduleByMonthOrQuarter(@RequestBody String queryBy) {
		
		Set<CourseSchedule> courseScheduleSet = new HashSet<CourseSchedule>();
		
		try {
			courseScheduleSet = enrollmentService.findAllCouresScheduleByMonthOrQuarter(queryBy);
			return courseScheduleSet;
		} catch (Exception e) {
			return courseScheduleSet;
		}
	}
	/**
	 * Find all below minimum course schedule - ajax
	 * 
	 * @return
	 */
	@GetMapping("/getAllScheduleBelowMinimum")
	@ResponseBody
	public Set<CourseScheduleForm> findAllCourseScheduleBelowMinimumParticipants() {

		try {
			Set<CourseSchedule> courseScheduleSet = enrollmentService.findAllCourseScheduleBelowMinimumParticipants();
			Set<CourseScheduleForm> courseScheduleFormSet = new HashSet<CourseScheduleForm>();
			for (CourseSchedule courseSchedule : courseScheduleSet) {
				CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
				CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
				CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
				courseScheduleForm.setId(courseSchedule.getId());
				courseScheduleForm.setCourseName(courseSchedule.getCourseName());
				courseScheduleForm.setInstructorName(
						courseSchedule.getInstructorFirstName() + " " + courseSchedule.getInstructorLastName());
				courseScheduleForm.setTotalParticipants(courseSchedule.getTotalParticipants());
				courseScheduleForm.setMinRequired(courseSchedule.getMinRequired());
				courseScheduleDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
				courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
				courseScheduleDetailForm.setDuration(courseScheduleDetail.getDuration());
				courseScheduleForm.setCourseScheduleDetails(courseScheduleDetailForm);
				courseScheduleFormSet.add(courseScheduleForm);
			}
			return courseScheduleFormSet;
		} catch (Exception e) {
			throw new IllegalArgumentException("Cant get CourseSchedule Below Minimum" + e.getMessage());
		}
	}
	/**
	 * Reschedule course schedule
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/reschedule")
	public String rescheduleCourseScheduleById(
			@Valid @ModelAttribute("courseScheduleDetailForm") CourseScheduleDetailForm form, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		System.out.println("RESCHEDULE!");
		System.out.println("(RESCHEDULE)Course Schedule Detail ID: " + form.getId());
		System.out.println("(RESCHEDULE)Scheduled Start DateTime: " + form.getScheduledStartDateTime());
		System.out.println("(RESCHEDULE)Scheduled End DateTime: " + form.getScheduledEndDateTime());
		try {
			Long durationToHours = Duration.between(form.getScheduledStartDateTime(), form.getScheduledEndDateTime())
					.toHours();
			Long durationToMinutes = Duration.between(form.getScheduledStartDateTime(), form.getScheduledEndDateTime())
					.toMinutes();
			float duration = 0;
			if (durationToMinutes % 60 == 0) {
				duration = durationToHours;
			} else {
				float minutes = durationToMinutes % 60;
				float hour = durationToMinutes / 60;
				duration = hour + (minutes / 60);
			}
			CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(form.getId(),
					form.getScheduledStartDateTime(), form.getScheduledEndDateTime(), duration).build();
			System.out.println("CONTROLLER ID: " + courseScheduleDetail.getId());
			System.out.println("CONTROLLER START: " + courseScheduleDetail.getScheduledStartDateTime());
			System.out.println("CONTROLLER END: " + courseScheduleDetail.getScheduledEndDateTime());
			System.out.println("CONTROLLER DURATION: " + courseScheduleDetail.getDuration());
			enrollmentService.rescheduleCourseScheduleById(courseScheduleDetail);
			return "redirect:/enrollment/viewCourseEnroll";
		} catch (NullPointerException e) {
			redirectAttributes.addFlashAttribute("errorMessageReschedule", "Hours Count must stay the same!");
		}
		return "redirect:/enrollment/viewCourseEnroll";
	}
	/**
	 * Find all member not enrolled in course schedule
	 * 
	 * @param courseScheduleId
	 * @return
	 */
	@PostMapping("/notEnrolledByCourseScheduleId")
	@ResponseBody
	public Set<CourseParticipant> findMemberNotEnrolledByCourseScheduleId(@RequestBody Long courseScheduleId) {
		FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<CourseParticipant> courseParticipantSet = new HashSet<CourseParticipant>();

		CourseParticipant courseParticipant = new CourseParticipant.Builder()
				.addCourseScheduleIdAndEmployeeNumber(courseScheduleId, user.getEmployeeNumber()).build();
		courseParticipantSet = enrollmentService.findAllMemberNotEnrolledByCourseScheduleId(courseParticipant);
		
		return courseParticipantSet;
	}
	/**
	 * Find all member enrolled in course schedule
	 * 
	 * @param courseScheduleId
	 * @return
	 */
	@PostMapping("/findEnrolledMember")
	@ResponseBody
	public Set<CourseParticipant> findAllEnrolledMemberByCourseScheduleId(@RequestBody Long courseScheduleId) {
		Set<CourseParticipant> courseParticipant = new HashSet<CourseParticipant>();
		courseParticipant = enrollmentService.findAllParticipantByCourseScheduleId(courseScheduleId);
		return courseParticipant;
	}
	/**
	 * Find all member not enrolled in course schedule
	 * 
	 * @param search
	 * @param request
	 * @return
	 */
	@PostMapping("/findMember")
	@ResponseBody
	public Set<CourseParticipant> findMember(@RequestBody SearchForm search, HttpServletRequest request) {
		FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Set<CourseParticipant> courseParticipant = new HashSet<CourseParticipant>();
		
		search.setEmployeeNumber(user.getEmployeeNumber());
		courseParticipant = enrollmentService.findMemberNotEnrolledByCourseScheduleId(search);
		
		return courseParticipant;
	}
	/**
	 * Enroll a member to course schedule
	 * 
	 * @param courseEnrollmentForm
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/enrollMember")
	public String submitCourseEnrollmentMemberForm(@Valid @ModelAttribute CourseEnrollmentForm courseEnrollmentForm,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		try {
			CourseScheduleDetailForm courseScheduleDetailForm = courseEnrollmentForm.getCourseScheduleDetails();
			CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(
					courseScheduleDetailForm.getId()).build();
			CourseParticipant courseParticipant = new CourseParticipant.Builder(
					courseEnrollmentForm.getCourseScheduleId(), courseEnrollmentForm.getId(),
					courseEnrollmentForm.getEmailAddress(), ZonedDateTime.now()).addDetail(courseScheduleDetail)
							.build();
			enrollmentService.enroll(courseParticipant);
			enrollmentService.sendCalendarInvite(courseParticipant);
			redirectAttributes.addFlashAttribute("successMsg", "Successfully Enrolled a Member!!!");
			redirectAttributes.addFlashAttribute("courseEnrollmentForm", courseEnrollmentForm);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("errorMessage01", e.getMessage());
		}
		return "redirect:/enrollment/viewMemberCourse";
	}
	/**
	 * Cancel all below minimum participant course schedule
	 * 
	 * @param courseEnrollCancelForm
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/cancelCourseSchedules")
	public String cancelCourseScheduleByMinimumParticipants(
			@Valid @ModelAttribute CourseEnrollCancelForm courseEnrollCancelForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		Set<CourseSchedule> courseScheduleSet = new HashSet<CourseSchedule>();
		for (Long id : courseEnrollCancelForm.getIds()) {
			CourseSchedule courseSchedule = new CourseSchedule.Builder(id).cancel().build();
			courseScheduleSet.add(courseSchedule);
		}
		enrollmentService.cancelCourseSchedules(courseScheduleSet);
		redirectAttributes.addFlashAttribute("successMessage",
				"Successfully cancelled all course schedule below minimum participant.");
		return "redirect:/enrollment/viewCourseEnroll";
	}
	@PostMapping("/findCourseSchedule")
	@ResponseBody
	public Set<CourseSchedule> findCourseScheduleByCourseId(@RequestBody CourseSchedule courseSchedule) {
		System.out.println("Course ID: " + courseSchedule.getCourseId());
		System.out.println("Course Schedule ID: " + courseSchedule.getId());
		return enrollmentService.findCourseScheduleByCourseId(courseSchedule);
	}
	@PostMapping("/updateSchedule")
	public String updateCourseSchedule(@Valid @ModelAttribute CourseEnrollmentForm courseEnrollmentForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("To be replaced: " + courseEnrollmentForm.getId());
		System.out.println("New Course Schedule ID: " + courseEnrollmentForm.getCourseScheduleId());
		FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			CourseParticipant courseParticipant = new CourseParticipant.Builder(courseEnrollmentForm.getId(),
					courseEnrollmentForm.getCourseScheduleId(), user.getId()).build();
			enrollmentService.updateSchedule(courseParticipant);
			redirectAttributes.addFlashAttribute("successMessageChangeSchedule", "Successfully change schedule");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/enrollment/mySchedules";
	}
	
	/**
     * function in getting the courseID 
     * 
	 * @param uploadCertificate
	 * @param model
	 * @return
	 * 
	 */
	
	@GetMapping("/{courseId1}/upload")
    public String getCourseId(@RequestParam(value="courseId1") Long id, CertificateForm form, BindingResult bindingResult,
    		Model model) {
    		form.setCourseId(id);
    		System.out.println(">>>>>>>>>>>>>>> : " + id);
    		return "redirect:/enrollment/mySchedules";
        	
    }
    
    @PostMapping("/{courseId1}/upload") 
    public String submitCertificate(@RequestParam(value="courseId1") Long id, CertificateForm form, BindingResult bindingResult,
    		Model model, @RequestParam("file")MultipartFile file) {
    	FileStorageProperties fileStorageProperties = new FileStorageProperties() ;
    	fileStorageProperties.setUploadDir("/tsup/certificate");
    	FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String fileName = enrollmentService.storeFile(file,id,fileStorageProperties,user.getId());
    	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
    	System.out.println(fileDownloadUri);
    		
    		Certificate certDetails = new Certificate.Builder(id, file.getOriginalFilename(), user.getId(), ZonedDateTime.now(), fileDownloadUri).build();
    		enrollmentService.uploadCertificate(certDetails);
    		return "redirect:/enrollment/mySchedules";
        
    }
	/**
     * function for Certificate Download
     * 
	 * @param downloadFile
	 * @param model
	 * @return
	 * 
	 */
	@GetMapping("/{courseIdHidden}/downloadFile")
	public ResponseEntity<Resource> downloadFile(HttpServletRequest request,@RequestParam("courseIdHidden")long courseId) {
	    // Load file as Resource
		FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		FileStorageProperties fileStorageProperties = new FileStorageProperties() ;
		fileStorageProperties.setUploadDir("/tsup/certificate");
	   	String fileName = enrollmentService.findCertificateName(user.getId(), courseId);
	   	if (StringUtils.isEmpty(fileName))
	    {
	    	HttpHeaders headers = new HttpHeaders();	    	 
	    	headers.add("Location", "/enrollment/mySchedules");	    	
	    	return new ResponseEntity<>(headers,HttpStatus.FOUND);
	    }
	   	
	    Resource resource = enrollmentService.loadFileAsResource(fileName, fileStorageProperties);
	
	    // Try to determine file's content type
	    String contentType = null;
	    try {
	    	contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	    } catch (IOException ex) {
	        logger.info("Could not determine file type.");
	    }
	
	    // Fallback to the default content type if type could not be determined
	    if(contentType == null) {
	        contentType = "application/octet-stream";
	    }
	
	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType(contentType))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	            .body(resource);
	}

	
	@PostMapping("/removeEnrolledMember")
	public String removeEnrolledMember(@Valid @ModelAttribute EnrolledMemberForm removeMembers,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		try {
			enrollmentService.removeBatchMember(removeMembers);
			redirectAttributes.addFlashAttribute("successMsg", "Successfully removed the selected member(s).");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("errorMessage01", e.getMessage());
		}
		return "redirect:/enrollment/viewMemberCourse";
	}
	/**
	 * Enroll a member to course schedule
	 * 
	 * @param courseEnrollmentForm
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/enrollBatchMembers")
	public String enrollBatchMembers(@Valid @ModelAttribute EnrolledMemberForm enrollMembers,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		try {
			enrollmentService.enrollBatchMember(enrollMembers);
			redirectAttributes.addFlashAttribute("successMsg", "Successfully enrolled the selected member(s).");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("errorMessage01", e.getMessage());
		}
		return "redirect:/enrollment/viewMemberCourse";
	}
	
	
	/**
	 * Check if filter Id is null, empty or undefined
	 * 
	 * @return
	 * 
	 */	
	private String checkFilterId(String filterId) {
		
		if(filterId == null || filterId.isEmpty() || filterId.equals("undefined") || filterId.equals("-")) {
			return "%";
		}
		
		return filterId;
	}
	
	/**
     * Check if String is Numeric
     * 
	 * @return
	 * 
	 */		
    public static boolean isNumeric(String strNum) {
    	if (strNum == null || strNum.length() == 0) {
            return false;
        }
 
        try {
            Double.parseDouble(strNum);
            return true;
 
        } catch (NumberFormatException e) {
            return false;
        }
    }
}