var changeCourseSchedule;

//$(document).ready(function() {
//	//	alert("Welcome");
//	//	changeCourseSchedule = $('#changeSched');
//	//	changeCourseSchedule.click(function(){
//	//		changeSchedule();
//	//	});
//});
function declineUpdate(courseId, courseName, courseScheduleId) {
		//alert("Course ID: " + courseId + " Course Name: " + courseName + " Course Schedule Id: " + courseScheduleId);

	document.getElementById('courseScheduleId_replace').value = courseScheduleId;
	document.getElementById('changeCourseSchedule').innerText = courseName;
	var courseSchedule = {
		"id": courseScheduleId,
		"courseId": courseId
	};



	$.ajax({
		type: "POST",
		url: "findCourseSchedule",
		data: JSON.stringify(courseSchedule),
		dataType: "json",
		contentType: 'application/json',
		success: function(data) {

			changeScheduleModal(data);
		},
		error: function() {
			alert("ERROR declineUpdate");
		}
	});

}

function changeScheduleModal(data) {


	$('#change-course-schedule-body').empty();
	var table = document.getElementById('change-course-schedule-body');


	data.forEach(function(object) {

		var courseScheduleDetail = object.courseScheduleDetail;

		/* Date Time Modification */
		var startDate = courseScheduleDetail.scheduledStartDateTime;
		var endDate = courseScheduleDetail.scheduledEndDateTime;
		var startDateTimeInput = formatDate(startDate);
		var endDateTimeInput = formatDate(endDate);
		
		var isDoneDate = new Date(courseScheduleDetail.scheduledEndDateTime.slice(0, 10));
		isDoneDate.setHours(courseScheduleDetail.scheduledEndDateTime.substring(11, 13));
		isDoneDate.setMinutes(courseScheduleDetail.scheduledEndDateTime.substring(14, 16));
		console.log(isDoneDate);
		
		var today = new Date();
		console.log(today);
		/* Date Time Modification End*/

		var tr = document.createElement('tr');
		tr.innerHTML =
			'<td>' + object.instructorLastName + " , " + object.instructorFirstName + '</td>' +
			'<td>' + object.venueName + '</td>' +
			'<td>' + startDateTimeInput + '</td>' +
			'<td>' + endDateTimeInput + '</td>';
		var td = document.createElement('td');
		
		if (isDoneDate < new Date()){
			var notice = document.createElement('p');
			notice.innerHTML = "The Course has already Ended.";
			td.append(notice);
		} else {
			var change = document.createElement('button');

			change.innerHTML = "<span class='fa fa-exchange'><" + "/span>";
			change.title = "Change Course Schedule";
			change.classList.add("btn");
			change.classList.add("btn-primary");
			td.append(change);
			
			change.addEventListener("click", function() {
				showChangeScheduleConfirmationModal(object.id);
			});

		}

		tr.append(td);
		table.append(tr);


		
	});

	$('#changeCourseSchedModal').modal('show');

}



function showChangeScheduleConfirmationModal(courseScheduleId) {

	var toBeCancelled = document.getElementById('courseScheduleId_replace').value;
	//	alert(toBeCancelled);
	document.getElementById('confirmId').value = toBeCancelled;
	document.getElementById('confirmCourseScheduleId').value = courseScheduleId;
	$('#confirmChangeScheduleModal_id').modal('show');

}

//DECLINE MODAL
/*<![CDATA[*/
function decline(courseName, instructorName, venueName,
	scheduledStartDateTime, scheduledEndDateTime, courseScheduleId,
	participantId, registrationDate, id) {
	var startDate = scheduledStartDateTime.slice(0, 11);
	var endDate = scheduledEndDateTime.slice(0, 11);
	var startTime = scheduledStartDateTime.slice(14, 22);
	var endTime = scheduledEndDateTime.slice(14, 22);

	var courseNameInput = document.getElementById('courseSchedName');
	var courseInstructorInput = document.getElementById('courseSchedInstructor');
	var dateInput = document.getElementById('courseSchedDate');
	var timeInput = document.getElementById('courseSchedTime');
	var courseVenueInput = document.getElementById('courseSchedVenue');
	courseNameInput.value = courseName;
	courseInstructorInput.value = instructorName;
	courseVenueInput.value = venueName;



	var courseIdInput = document.getElementById('courseId');
	var participantIdInput = document.getElementById('participantId');
	var registrationDateIdInput = document.getElementById('registrationDate');
	var courseParticipantIdInput = document.getElementById('courseParticipantId');

	courseIdInput.value = courseScheduleId;
	participantIdInput.value = participantId;
	registrationDateIdInput.value = registrationDate;
	courseParticipantIdInput.value = id;
	if (startDate == endDate) {
		dateInput.value = startDate;
		timeInput.value = startTime + " to " + endTime;
	} else {
		dateInput.value = startDate + " to " + endDate;
		timeInput.value = startTime + " to " + endTime;
	}

	$('#declineModal').modal('show');

	var training = " training?";
	var MainConfirmNameInput = document.getElementById('mainConfirmCourseName');
	MainConfirmNameInput.value = courseName + training;

	var confirmNameInput = document.getElementById('confirmCourseName');
	confirmNameInput.value = courseName;

	var confirmCourseIdInput = document.getElementById('confirmCourseId');
	confirmCourseIdInput.value = courseScheduleId;

	var confirmParticipantIdInput = document.getElementById('confirmParticipantId');
	confirmParticipantIdInput.value = participantId;

	var confirmRegistrationDateInput = document.getElementById('confirmRegistrationDate');
	confirmRegistrationDateInput.value = registrationDate;

	var confirmCourseParticipantIdInput = document.getElementById('confirmCourseParticipantId');
	confirmCourseParticipantIdInput.value = id;


}

function declinedCourseSchedule(courseParticipantId,
	courseName,
	courseScheduleId,
	registrationDate,
	instructorName,
	venueName,
	courseStatus,
	startDateTime,
	endDateTime,
	duration,
	courseId, courseDetails) {

	var startDate = startDateTime.slice(0, 18);
	var endDate = endDateTime.slice(0, 18);
	var startTime = startDateTime.slice(21, 29);
	var endTime = endDateTime.slice(21, 29);



	document.getElementById('courseParticipantIdHiddenDecline').value = courseParticipantId;
	document.getElementById('courseScheduleIdHidden').value = courseScheduleId;
	document.getElementById('courseNameDecline').value = courseName;
	document.getElementById('courseSchedInstructor').value = instructorName;
	document.getElementById('courseSchedDate').value = startDate + " to " + endDate;

	document.getElementById('courseSchedTime').value = startTime + " to " + endTime + " ( " + duration + " ) hour/(s)";
	document.getElementById('courseSchedVenue').value = venueName;
	document.getElementById('courseStatus').value = courseStatus;
	document.getElementById('participantName').value = "No need to insert this";
	document.getElementById('registrationDate').value = registrationDate;
	document.getElementById('courseIdHiddenDecline').value = courseId;
	document.getElementById('courseDescription').value = courseDetails;

	document.getElementById('mainConfirmCourseName').value = courseName + " ? ";
	$('#declineMemberModal').modal('show');

}

/*]]>*/


//Confirm Decline Modal 
/*<![CDATA[*/
function confirmDecline() {
	$('#confirmationModal').modal('show');
	$('#declineMemberModal').modal('hide');
}
/*]]>*/

/*
 * Back Modal
 * from Confirmation of Decline back to Decline Member Modal
 */
function backModal() {
	$('#confirmationModal').modal('hide');
	$('#declineMemberModal').modal('show');
}

function formatDate(dateVal) {
    var newDate = new Date(dateVal);
    var sMonth = padValue(newDate.getMonth() + 1);
    var sDay = padValue(newDate.getDate());
    var sYear = newDate.getFullYear();
    var sHour = newDate.getHours();
    var sMinute = padValue(newDate.getMinutes());
    var sAMPM = "AM";
    var iHourCheck = parseInt(sHour);
    if (iHourCheck > 12) {
        sAMPM = "PM";
        sHour = iHourCheck - 12;
    }
    else if (iHourCheck === 0) {
        sHour = "12";
    }
    sHour = padValue(sHour);
  
    return sYear + "-" + sMonth + "-" + sDay + " " + sHour + ":" + sMinute + " " + sAMPM;
}
function padValue(value) {
    return (value < 10) ? "0" + value : value;
}


