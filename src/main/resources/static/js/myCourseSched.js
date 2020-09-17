var changeCourseSchedule;

$(document).ready(function() {
	//	alert("Welcome");
	//	changeCourseSchedule = $('#changeSched');
	//	changeCourseSchedule.click(function(){
	//		changeSchedule();
	//	});
});
function declineUpdate(courseId, courseName, courseScheduleId) {
	//	alert("Course ID: " + courseId + " Course Name: " + courseName + " Course Schedule Id: " + courseScheduleId);

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
		var startDate = courseScheduleDetail.scheduledStartDateTime.slice(0, 10);
		var endDate = courseScheduleDetail.scheduledEndDateTime.slice(0, 10);
		var startTime = courseScheduleDetail.scheduledStartDateTime.slice(11, 19);
		var endTime = courseScheduleDetail.scheduledEndDateTime.slice(11, 19);
		var startDateTimeInput = startDate +" "+startTime;
		var endDateTimeInput = endDate + " " + endTime;
		/* Date Time Modification End*/

		var tr = document.createElement('tr');
		tr.innerHTML =
			'<td>' + object.instructorLastName + " , " + object.instructorFirstName + '</td>' +
			'<td>' + object.venueName + '</td>' +
			'<td>' + startDateTimeInput + '</td>' +
			'<td>' + endDateTimeInput + '</td>';
		var td = document.createElement('td');

		var change = document.createElement('button');

		change.innerHTML = "Change"
		change.classList.add("btn");
		change.classList.add("btn-success");

		td.append(change);
		tr.append(td);
		table.append(tr);

		change.addEventListener("click", function() {
			showChangeScheduleConfirmationModal(object.id);
		});

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
function decline(courseId, courseName, instructorName, venueName,
	scheduledStartDateTime, scheduledEndDateTime) {
	var startDate = scheduledStartDateTime.slice(0, 11);
	var endDate = scheduledEndDateTime.slice(0, 11);
	var startTime = scheduledStartDateTime.slice(14, 22);
	var endTime = scheduledEndDateTime.slice(14, 22);
	var courseIdInput = document.getElementById('courseSchedId');
	var courseNameInput = document.getElementById('courseSchedName');
	var courseInstructorInput = document
		.getElementById('courseSchedInstructor');
	var courseVenueInput = document.getElementById('courseSchedVenue');

	var dateInput = document.getElementById('courseSchedDate');
	var timeInput = document.getElementById('courseSchedTime');
	courseIdInput.value = courseId;
	courseNameInput.value = courseName;
	courseInstructorInput.value = instructorName;
	courseVenueInput.value = venueName;
	if (startDate == endDate) {
		dateInput.value = startDate;
		timeInput.value = startTime + " to " + endTime;
	} else {
		dateInput.value = startDate + " to " + endDate;
		timeInput.value = startTime + " to " + endTime;
	}

	$('#declineModal').modal('show');
}
		/*]]>*/

