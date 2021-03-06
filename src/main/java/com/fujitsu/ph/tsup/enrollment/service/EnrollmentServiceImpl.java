package com.fujitsu.ph.tsup.enrollment.service;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Course
//Class Name   :EnrollmentServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+-----------------------------------------------------
//0.01    | 06/25/2020 | WS) T.Oviedo          | New Creation
//0.01    | 07/08/2020 | WS) K.Freo            | Update
//0.01    | 07/08/2020 | WS) M.lumontad        | Update
//0.02    | 09/07/2020 | WS) J.Yu              | Update
//0.03    | 03/05/2021 | WS) E.Ceniza          | Update
//0.03    | 03/24/2021 | WS) K.Sanchez         | Update
//0.03    | 03/23/2021 | WS) C.Macatangay      | Update
//0.04    | 05/04/2021 | WS) A.Senamin         | Update
//0.05    | 06/14/2021 | WS) L.Celoso          | Update
//0.06    | 06/30/2021 | WS) L.Celoso          | Update
//==================================================================================================

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.enrollment.model.Certificate;
import com.fujitsu.ph.tsup.enrollment.model.EnrolledMemberForm;
import com.fujitsu.ph.tsup.enrollment.model.FileStorageProperties;
import com.fujitsu.ph.tsup.enrollment.model.SearchForm;
import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * JavaBean for EnrollmentServiceImpl
 * 
 * <pre>
 * 
 * @version 0.01
 * @author t.oviedo
 */
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentDao enrollmentDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${sender.email}")
    private String senderEmail;
    
    /**
     * 
     * Sends calendar invite to the successfully enrolled participant
     * 
     * @param courseParticipant
     * @author E.Ceniza, K.Sanchez, C.Macatangay
     * 
     */
    public void sendCalendarInvite(CourseParticipant courseParticipant) {
        CourseSchedule courseSchedule = enrollmentDao
                .findCourseScheduleById(courseParticipant.getCourseScheduleId());
        CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multipart = new MimeMultipart();
        BodyPart bodyPart = new MimeBodyPart();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        DateTimeFormatter zonedtf = DateTimeFormatter.ofPattern("zzzz");
        StringBuilder ical = new StringBuilder();
        int hour = courseScheduleDetail.getScheduledEndDateTime().getHour()
                - courseScheduleDetail.getScheduledStartDateTime().getHour();
        int minute = courseScheduleDetail.getScheduledEndDateTime().getMinute()
                - courseScheduleDetail.getScheduledStartDateTime().getMinute();
        String zone = zonedtf.format(courseScheduleDetail.getScheduledStartDateTime());

        // iCalendar Specs https://tools.ietf.org/html/rfc554
        // iCalendar iTiP https://tools.ietf.org/html/rfc5546
        ical.append("BEGIN:VCALENDAR");
        ical.append("\nMETHOD:REQUEST");
        ical.append("\nPRODID:-//Microsoft Corporation//Outlook 16.0 MIMEDIR//EN");
        ical.append("\nVERSION:2.0");
        ical.append("\nBEGIN:VEVENT");
        ical.append("\nATTENDEE;CN:").append(user.getFirstName()).append(" ").append(user.getLastName())
                .append(";ROLE:OPT-PARTICIPANT;RSVP=TRUE:mailto:").append(courseParticipant.getEmail());
        ical.append("\nDTSTART:").append(dtf.format(courseScheduleDetail.getScheduledStartDateTime()));
        ical.append("\nORGANIZER;CN=").append(courseSchedule.getInstructorFirstName()).append(" ")
                .append(courseSchedule.getInstructorLastName());
        ical.append("\nSUMMARY:").append(courseSchedule.getCourseName());
        ical.append("\nUID:").append(UUID.randomUUID().toString());
        ical.append("\nDESCRIPTION:").append(courseSchedule.getCourseName());
        ical.append(
                "\nX-ALT-DESC;FMTTYPE=text/html:<html><body style=\"font-family:Arial\"; \"font-size:10pt\">Dear ")
                .append(user.getFirstName()).append(" ").append(user.getLastName())
                .append(",<br><br>You have successfully enrolled to ").append(courseSchedule.getCourseName())
                .append(". Please see details below:<br><br>")
                .append("<table cellspacing=3 style=\"font-family:Arial\"; \"font-size:10pt\">")
                .append("<tr><td width=120><b>Start Date:</b></td><td>")
                .append(dateFormatter.format(courseScheduleDetail.getScheduledStartDateTime()))
                .append("</td></tr><tr><td width=120><b>Start Time:</b></td><td>")
                .append(timeFormatter.format(courseScheduleDetail.getScheduledStartDateTime()))
                .append("</td></tr><tr><td width=120><b>End Date:</b></td><td>")
                .append(dateFormatter.format(courseScheduleDetail.getScheduledEndDateTime()))
                .append("</td></tr><tr><td width=120><b>End Time:</b></td><td>")
                .append(timeFormatter.format(courseScheduleDetail.getScheduledEndDateTime()))
                .append("</td></tr><tr><td width=120><b>Venue:</b></td><td>")
                .append(courseSchedule.getVenueName()).append("</td></tr></table></body></html>");
        ical.append("\nDURATION:PT").append(hour).append("H").append(minute).append("M0S");
        ical.append("\nLOCATION:").append(courseSchedule.getVenueName());
        ical.append("\nPRIORITY:5");
        if (!courseScheduleDetail.getScheduledStartDateTime().toLocalDate()
                .isEqual(courseScheduleDetail.getScheduledEndDateTime().toLocalDate())) {
            ical.append("\nRRULE:FREQ=WEEKLY;UNTIL=")
                    .append(dtf.format(courseScheduleDetail.getScheduledEndDateTime()) + "Z")
                    .append(";BYDAY=MO,TU,WE,TH,FR");
        }
        ical.append("\nTRANSP:OPAQUE");
        ical.append("\nEND:VEVENT");
        ical.append("\nBEGIN:VTIMEZONE");
        ical.append("\nTZID:").append(zone);
        ical.append("\nEND:VTIMEZONE");
        ical.append("\nEND:VCALENDAR");
        System.out.println(ical.toString());

        try {
            bodyPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
            bodyPart.addHeader("Content-ID", "calendar_message");
            bodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(ical.toString(),
                    "text/calendar; charset=UTF-8; method=REQUEST; name=\"invite.ics\"")));
            multipart.addBodyPart(bodyPart);
            message.addHeaderLine("method=REQUEST");
            message.addHeaderLine("charset=UTF-8");
            message.addHeaderLine("component=VEVENT");
            message.setSubject(courseSchedule.getCourseName());
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(courseParticipant.getEmail()));
            message.setContent(multipart);
            
            System.out.println("Sending email...");
            javaMailSender.send(message);
            System.out.println("Sent email...");
        } catch (MessagingException e) {
            throw new IllegalArgumentException("This should never happen unless mail properties are invalid.", e);
        } catch (MailAuthenticationException e) {
            CourseParticipant enrolledCourseParticipant = enrollmentDao
                    .findCourseParticipantByCourseScheduleIdAndParticipantId(courseSchedule.getId(),
                            courseParticipant.getParticipantId());
            enrollmentDao.deleteCourseParticipantById(enrolledCourseParticipant.getId());
            throw new RuntimeException("Can't communicate with the mail server. Please try again later.", e);
        }  catch (IOException e) {
            throw new RuntimeException(
                    "This should never happen because ByteArraySource will always have correct and non empty values for the data and type arguments.",
                    e);
        }
    }
    
    /**
     * Finds the scheduled courses starting from today onwards
     * 
     * @param fromDateTime
     * @param toDateTime
     * @author J.Yu
     * 
     */
    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime, String courseCategoryId,String courseNameId, 
    		String instructorId, String venueId, String mandatory, String deadline, Pageable pageable) {
        try {
            Set<CourseSchedule> courseScheduleSet = enrollmentDao.findAllScheduledCourses(fromDateTime,
                    toDateTime, courseCategoryId, courseNameId, instructorId, venueId, mandatory, deadline, pageable);
            
            if (courseScheduleSet == null || courseScheduleSet.isEmpty()) {
                throw new IllegalArgumentException("No Course Schedule Found");
            }
            
            return courseScheduleSet;
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Can't Access From Datetime and To Datetime");
        }
    }

    @Override
    public Set<CourseSchedule> findAllMemberScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime, String courseCategoryId, 
    		String courseNameId, String instructorId, String venueId, String mandatory, String deadline, Pageable pageable) {
        try {
            Set<CourseSchedule> courseScheduleSet = enrollmentDao.findAllScheduledCourses(fromDateTime,
                    toDateTime,courseCategoryId,courseNameId, instructorId, venueId, mandatory, deadline, pageable);
            
            return courseScheduleSet;
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Can't Access From Datetime and To Datetime");
        }
    }

    /** Finds the course schedule by id */
    @Override
    public CourseSchedule findCourseScheduleById(Long id) {
        return enrollmentDao.findCourseScheduleById(id);
    }

    /** enroll using the courseParticipant */
    @Override
    public void enroll(CourseParticipant courseParticipant) {

        // try {
        CourseSchedule courseRecord = enrollmentDao
                .findCourseScheduleById(courseParticipant.getCourseScheduleId());

        if (courseRecord == null) {
            throw new IllegalArgumentException("This course schedule id "
                    + courseParticipant.getCourseScheduleId() + " is not existing");
        }

        CourseParticipant participantRecord = enrollmentDao
                .findCourseParticipantByCourseScheduleIdAndParticipantId(
                        courseParticipant.getCourseScheduleId(), courseParticipant.getParticipantId());
        if (participantRecord != null) {
            throw new IllegalArgumentException("You are already enrolled in this course.");
        }

        enrollmentDao.saveCourseParticipant(courseParticipant);
    }

    /**
     * 
     * Call enrollmentDao.findAllEnrolledCoursesByParticipantId using ID Finds All Enrolled Courses by
     * Participant ID
     * 
     * @author m.lumontad
     * 
     */
    @Override
    public Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId,
            ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {

        try {
            Set<CourseParticipant> courseParticipant = enrollmentDao
                    .findAllEnrolledCoursesByParticipantId(participantId, fromDateTime, toDateTime);
            if (courseParticipant == null || courseParticipant.isEmpty()) {
                throw new IllegalArgumentException("No Course Schedule Found");
            }
            return courseParticipant;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Can't Access Id");
        }
    }

    /**
     * 
     * Call enrollmentDao.findCourseParticipantById using the given id Finds course participant by id
     * 
     * @author k.freo
     * 
     * 
     */
    @Override
    public CourseParticipant findCourseParticipantById(Long id) {
        try {
            CourseParticipant courseParticipant = enrollmentDao.findCourseParticipantById(id);
            if (courseParticipant == null) {
                throw new IllegalArgumentException("Participant not Found");
            }
            return courseParticipant;
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException(" Participant not Found ");
        }
    }

    /**
     * Decline the course which the participant was previously enrolled Call
     * enrollmentDao.deleteCourseParticipantById using the given courseParticipant.id Call
     * enrollmentDao.saveCourseNonParticipant using the given dbCourseParticipant
     * 
     * @author k.freo
     * 
     **/
    @Override
    public void declineCourse(CourseParticipant courseParticipant) {

        CourseParticipant findCourseParticipant = enrollmentDao
                .findCourseParticipantById(courseParticipant.getId());

        CourseParticipant dbCourseParticipant = new CourseParticipant.Builder(findCourseParticipant.getId())
                .decline(courseParticipant.getReason())
                .addDetail(findCourseParticipant.getCourseScheduleDetail()).build();

        enrollmentDao.saveCourseNonParticipant(dbCourseParticipant);
        enrollmentDao.deleteCourseParticipantById(courseParticipant.getId());

    }

    @Override
    public void cancel(Long id) {

        try {
            CourseSchedule courseSchedule = enrollmentDao.findCourseScheduleById(id);

            if (courseSchedule.getId() == null) {
                throw new IllegalArgumentException("This course{" + id + "} is not existing");
            }
            CourseSchedule courseScheduleInstance = new CourseSchedule.Builder(id).cancel().build();
            enrollmentDao.changeCourseScheduleStatus(courseScheduleInstance);

        } catch (DataAccessException ex) {
            throw new IllegalArgumentException(" Can't cancel Course.");
        }
    }

    @Override
    public void cancelCourseSchedules(Set<CourseSchedule> courseScheduleSet) {
        // TODO Auto-generated method stub
        try {
            enrollmentDao.cancelCourseSchedulesById(courseScheduleSet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cant Cancel Course Schedule Set.");
        }
    }

    @Override
    public Set<CourseSchedule> findAllActiveCourseSchedule() {
        // TODO Auto-generated method stub
        return enrollmentDao.findAllActiveCourseSchedule();
    }

    @Override
    public Set<CourseSchedule> findAllCouresScheduleByMonthOrQuarter(String queryBy) {
        // TODO Auto-generated method stub
        Set<CourseSchedule> courseSchedule = new HashSet<CourseSchedule>();
        if (queryBy.equals("month")) {
            courseSchedule = enrollmentDao.findAllCourseScheduleByMonth();
        } else if (queryBy.equals("quarter")) {
            courseSchedule = enrollmentDao.findAllCourseScheduleByQuarter();
        }

        // validation
        if (courseSchedule == null || courseSchedule.isEmpty()) {
            throw new IllegalArgumentException("No Course Schedule found!");
        }
        return courseSchedule;
    }

    @Override
    public void rescheduleCourseScheduleById(CourseScheduleDetail courseScheduleDetail) {
        // TODO Auto-generated method stub
        System.out.println("SERVICE");
        try {
            enrollmentDao.reschedule(courseScheduleDetail);
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not reschedule course schedule" + e.getMessage());
        }
    }

    @Override
    public Set<CourseSchedule> findAllCourseScheduleBelowMinimumParticipants() {
        // TODO Auto-generated method stub
        try {
            Set<CourseSchedule> courseScheduleSet = enrollmentDao
                    .findAllCourseScheduleBelowMinimumParticipants();
            return courseScheduleSet;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cant get CourseSchedules below minimum" + e.getMessage());
        }
    }

    @Override
    public Set<CourseParticipant> findAllParticipantByCourseScheduleId(Long courseScheduleId) {
        // TODO Auto-generated method stub
        Set<CourseParticipant> courseParticipantSet = new HashSet<CourseParticipant>();
        try {
            courseParticipantSet = enrollmentDao.findAllParticipantByCourseScheduleId(courseScheduleId);
            if (courseParticipantSet == null || courseParticipantSet.isEmpty()) {
                throw new IllegalArgumentException("No Course Participant Found.");
            }
            return courseParticipantSet;
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not find Course Participant in this Course Schedule");
        }
    }

    @Override
    public Set<CourseParticipant> findAllMemberNotEnrolledByCourseScheduleId(
            CourseParticipant courseParticipant) {
        // TODO Auto-generated method stub
        try {
            if (courseParticipant == null) {
                throw new IllegalArgumentException("Course Participant Null");
            }
            return enrollmentDao.findAllMemberNotEnrolledByCourseScheduleId(courseParticipant);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Set<CourseParticipant> findMemberNotEnrolledByCourseScheduleId(SearchForm searchForm) {
        // TODO Auto-generated method stub
        try {
            System.out.println("1");
            Set<CourseParticipant> courseParticipantSet = enrollmentDao
                    .findMemberNotEnrolledByCourseScheduleId(searchForm);
            if (courseParticipantSet == null || courseParticipantSet.isEmpty()) {
                throw new IllegalArgumentException("No Participants Found");
            }
            return courseParticipantSet;
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not find Course Participants");
        }
    }

    @Override
    public Set<CourseSchedule> findCourseScheduleByCourseId(CourseSchedule courseSchedule) {
        // TODO Auto-generated method stub
        return enrollmentDao.findCourseScheduleByCourseId(courseSchedule);
    }

    @Override
    public void updateSchedule(CourseParticipant courseParticipant) {

        CourseSchedule courseRecord = enrollmentDao
                .findCourseScheduleById(courseParticipant.getCourseScheduleId());
        if (courseRecord == null) {
            throw new IllegalArgumentException("This course schedule id "
                    + courseParticipant.getCourseScheduleId() + " is not existing");
        }

        CourseParticipant participantRecord = enrollmentDao
                .findCourseParticipantByCourseScheduleIdAndParticipantId(
                        courseParticipant.getCourseScheduleId(), courseParticipant.getParticipantId());

        if (participantRecord != null) {
            throw new IllegalArgumentException("You are already enrolled to this course.");

        } else if (participantRecord == null) {
            enrollmentDao.updateCourseParticipant(courseParticipant);
        }

    }

    @Override
    public List<TopLearnerForm> findTopLearner(ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {
        try {
            if (Period.between(fromDateTime.toLocalDate(), toDateTime.toLocalDate()).getMonths() >= 4) {
                return enrollmentDao.findTopLearnerByQuarter();
            } else {
                return enrollmentDao.findTopLearnerByMonth();
            }
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("No Top Learners");
        }

    }

    /**
     * Upload button enabled for Mandatory courses 
     * Renamed the uploaded certificate according to the specifications
     * 
     * @param uploadCertificate
     * @author A.Senamin
     * 
     */
    public void uploadCertificate(Certificate certificate) {
    	//call the dao method
    	enrollmentDao.uploadCertificate(certificate);	
    }
    
    public String storeFile(MultipartFile file, Long id, FileStorageProperties fileStorageProperties,Long userId) {
        // Normalize file name
    	Path fileStorageLocation =  Paths.get(fileStorageProperties.getUploadDir())
				.toAbsolutePath().normalize();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileName = userId +"_"+ id +"_"+ fileName ;
        try {
            // Check if the file's name contains invalid characters
        	 Files.createDirectories(fileStorageLocation);
            if(fileName.contains("..")) {
                throw new IllegalArgumentException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not store file " + fileName + ". Please try again!", ex);
        }catch (Exception ex){
            throw new IllegalArgumentException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Resource loadFileAsResource(String fileName,FileStorageProperties fileStorageProperties) {
        fileName = fileName.substring(38).replaceAll("%20", " ");
       
    	try {
            Path filePath = Paths.get(fileStorageProperties.getUploadDir())
    				.toAbsolutePath().normalize().resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new IllegalArgumentException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("File not found " + fileName, ex);
        }
    }
    
    public List<String> findCourseScheduleIfMandatory(){
    	return enrollmentDao.findCourseScheduleIfMandatory();
    }

	@Override
	public String findCertificateName(long userId, long courseId) {
		
		return enrollmentDao.findCertificateName(userId, courseId);
	}
	
    @Override
    public Set<CourseCategory> findAllCourseCategory() {

        return enrollmentDao.findAllCourseCategory();
    }
    
    @Override
    public Set<Course> findAllCourseName() {

        return enrollmentDao.findAllCourseName();
    }
    
    @Override
    public Set<InstructorForm> findAllInstructor() {

        return enrollmentDao.findAllInstructor();
    }
    
    @Override
    public Set<VenueForm> findAllVenue() {

        return enrollmentDao.findAllVenue();
    }
    
    @Override
    public void removeBatchMember(EnrolledMemberForm enrolledMember) {

    	//Check if the target Course Schedule still exists
        CourseSchedule courseRecord = enrollmentDao
                .findCourseScheduleById(enrolledMember.getCourseId());

        if (courseRecord == null) {
            throw new IllegalArgumentException("This course schedule id "
                    + enrolledMember.getCourseId() + " is not existing");
        }
        //-------------------------------------------------
    	
        // Remove the selected members by batch from the Course schedule
        enrollmentDao.removeBatchMember(enrolledMember);
    }
    
    @Override
    public void enrollBatchMember(EnrolledMemberForm enrolledMember) {

    	//Check if the target Course Schedule still exists
    	CourseSchedule courseRecord = enrollmentDao
                .findCourseScheduleById(enrolledMember.getCourseId());

        if (courseRecord == null) {
            throw new IllegalArgumentException("This course schedule id "
                    + enrolledMember.getCourseId() + " is not existing");
        }
        //-------------------------------------------------
        
        // Enroll the selected members by batch to the Course schedule
        enrollmentDao.enrollBatchMember(enrolledMember);
    }
    
    @Override
    public int countCourse(ZonedDateTime fromDateTime, ZonedDateTime toDateTime, String courseCategoryId,String courseNameId, String instructorId, String venueId, String mandatory, String deadline) {
    	
    	return enrollmentDao.countCourse(fromDateTime, toDateTime, courseCategoryId, courseNameId, instructorId, venueId, mandatory, deadline);
    	
    }
    
    /**
     * 
     * Sends multiple calendar invite for selected enrolled members in a course
     * 
     * @param courseParticipant
     * @author L.Celoso
     * 
     */
    public void sendBatchCalendarInvite(Long courseScheduleId, Set<CourseParticipant> courseParticipant) {
    	
        CourseSchedule courseSchedule = enrollmentDao.findCourseScheduleById(courseScheduleId);
        CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multipart = new MimeMultipart();
        BodyPart bodyPart = new MimeBodyPart();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        DateTimeFormatter zonedtf = DateTimeFormatter.ofPattern("zzzz");
        StringBuilder ical = new StringBuilder();
        int hour = courseScheduleDetail.getScheduledEndDateTime().getHour()
                - courseScheduleDetail.getScheduledStartDateTime().getHour();
        int minute = courseScheduleDetail.getScheduledEndDateTime().getMinute()
                - courseScheduleDetail.getScheduledStartDateTime().getMinute();
        String zone = zonedtf.format(courseScheduleDetail.getScheduledStartDateTime());

        ical.append("BEGIN:VCALENDAR");
        ical.append("\nMETHOD:REQUEST");
        ical.append("\nPRODID:-//Microsoft Corporation//Outlook 16.0 MIMEDIR//EN");
        ical.append("\nVERSION:2.0");
        ical.append("\nBEGIN:VEVENT");
        
        for (CourseParticipant employee : courseParticipant) {
	        ical.append("\nATTENDEE;CN:").append(user.getFirstName()).append(" ").append(user.getLastName())
	                .append(";ROLE:OPT-PARTICIPANT;RSVP=TRUE:mailto:").append(employee.getEmail());
        }
        
        ical.append("\nDTSTART:").append(dtf.format(courseScheduleDetail.getScheduledStartDateTime()));
        ical.append("\nORGANIZER;CN=").append(courseSchedule.getInstructorFirstName()).append(" ")
                .append(courseSchedule.getInstructorLastName()).append(":MAILTO:").append(senderEmail);
        ical.append("\nSUMMARY:").append(courseSchedule.getCourseName());
        ical.append("\nUID:").append(UUID.randomUUID().toString());
        ical.append("\nDESCRIPTION:").append(courseSchedule.getCourseName());
        ical.append(
                "\nX-ALT-DESC;FMTTYPE=text/html:<html><body style=\"font-family:Arial\"; \"font-size:10pt\">Dear ")
                .append(user.getFirstName()).append(" ").append(user.getLastName())
                .append(",<br><br>You have successfully enrolled to ").append(courseSchedule.getCourseName())
                .append(". Please see details below:<br><br>")
                .append("<table cellspacing=3 style=\"font-family:Arial\"; \"font-size:10pt\">")
                .append("<tr><td width=120><b>Start Date:</b></td><td>")
                .append(dateFormatter.format(courseScheduleDetail.getScheduledStartDateTime()))
                .append("</td></tr><tr><td width=120><b>Start Time:</b></td><td>")
                .append(timeFormatter.format(courseScheduleDetail.getScheduledStartDateTime()))
                .append("</td></tr><tr><td width=120><b>End Date:</b></td><td>")
                .append(dateFormatter.format(courseScheduleDetail.getScheduledEndDateTime()))
                .append("</td></tr><tr><td width=120><b>End Time:</b></td><td>")
                .append(timeFormatter.format(courseScheduleDetail.getScheduledEndDateTime()))
                .append("</td></tr><tr><td width=120><b>Venue:</b></td><td>")
                .append(courseSchedule.getVenueName()).append("</td></tr></table></body></html>");
        ical.append("\nDURATION:PT").append(hour).append("H").append(minute).append("M0S");
        ical.append("\nLOCATION:").append(courseSchedule.getVenueName());
        ical.append("\nPRIORITY:5");
        if (!courseScheduleDetail.getScheduledStartDateTime().toLocalDate()
                .isEqual(courseScheduleDetail.getScheduledEndDateTime().toLocalDate())) {
            ical.append("\nRRULE:FREQ=WEEKLY;UNTIL=")
                    .append(dtf.format(courseScheduleDetail.getScheduledEndDateTime()) + "Z")
                    .append(";BYDAY=MO,TU,WE,TH,FR");
        }
        ical.append("\nTRANSP:OPAQUE");
        ical.append("\nEND:VEVENT");
        ical.append("\nBEGIN:VTIMEZONE");
        ical.append("\nTZID:").append(zone);
        ical.append("\nEND:VTIMEZONE");
        ical.append("\nEND:VCALENDAR");
        System.out.println(ical.toString());

        try {
            bodyPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
            bodyPart.addHeader("Content-ID", "calendar_message");
            bodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(ical.toString(),
                    "text/calendar; charset=UTF-8; method=REQUEST; name=\"invite.ics\"")));
            multipart.addBodyPart(bodyPart);
            message.addHeaderLine("method=REQUEST");
            message.addHeaderLine("charset=UTF-8");
            message.addHeaderLine("component=VEVENT");
            message.setSubject(courseSchedule.getCourseName());
            message.setFrom(new InternetAddress(senderEmail));
            
            for (CourseParticipant employee : courseParticipant) {
            	message.addRecipient(Message.RecipientType.TO, new InternetAddress(employee.getEmail()));
            }
            
            message.setContent(multipart);
            
            System.out.println("Sending email...");
            javaMailSender.send(message);
            System.out.println("Sent email...");
        } catch (MessagingException e) {
            throw new IllegalArgumentException("This should never happen unless mail properties are invalid.", e);
        } catch (MailAuthenticationException e) {
            throw new RuntimeException("Can't communicate with the mail server. Please try again later.", e);
        }  catch (IOException e) {
            throw new RuntimeException(
                    "This should never happen because ByteArraySource will always have correct and non empty values for the data and type arguments.",
                    e);
        }
    }
    
    public Set<CourseParticipant> getAllEmails(String batchId) {
    	
    	return enrollmentDao.getAllEmails(batchId);
   
    }
}