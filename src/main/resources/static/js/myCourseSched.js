var changeCourseSchedule;

$(document).ready(function() {
//	alert("Welcome");
//	changeCourseSchedule = $('#changeSched');
//	changeCourseSchedule.click(function(){
//		changeSchedule();
//	});
});
function declineUpdate(courseId, courseName, courseScheduleId){
//	alert("Course ID: " + courseId + " Course Name: " + courseName + " Course Schedule Id: " + courseScheduleId);
	document.getElementById('courseScheduleId_replace').value= courseScheduleId;
	document.getElementById('changeCourseSchedule').innerText= courseId + " " + courseName;
	var courseSchedule = {
		"id": courseScheduleId,
		"courseId":courseId
	};
	
	$.ajax({
		type : "POST",
		url : "findCourseSchedule",
		data : JSON.stringify(courseSchedule),
		dataType: "json",
		contentType: 'application/json', 
		success : function (data) {
			
			changeScheduleModal(data);
		},
		error: function(){
			alert("ERROR declineUpdate");
		}
	});
}

function changeScheduleModal(data){
//	alert(data);
	$('#change-course-schedule-body').empty();
	var table = document.getElementById('change-course-schedule-body');

	data.forEach(function(object){
		var courseScheduleDetail = object.courseScheduleDetail;

		var tr = document.createElement('tr');
		tr.innerHTML = '<td>'+ object.instructorFirstName + " " + object.instructorLastName + '</td>' + 
						'<td>'+ object.venueName +'</td>' +
						'<td>' + courseScheduleDetail.scheduledStartDateTime + '</td>' +
						'<td>' + courseScheduleDetail.scheduledEndDateTime + '</td>' ;
		
		var td = document.createElement('td');

		var change = document.createElement('button');

		change.innerHTML = "Change"
		change.classList.add("btn");
		change.classList.add("btn-success");
		change.classList.add("float-right");
		
		td.append(change);
		tr.append(td);
		table.append(tr);
		
		change.addEventListener("click", function(){
			showChangeScheduleConfirmationModal(object.id);
		});

	});
	$('#changeCourseSchedModal').modal('show');
}
function showChangeScheduleConfirmationModal(courseScheduleId){

	var toBeCancelled = document.getElementById('courseScheduleId_replace').value;
//	alert(toBeCancelled);
	document.getElementById('confirmId').value = toBeCancelled;
	document.getElementById('confirmCourseScheduleId').value = courseScheduleId;
	$('#confirmChangeScheduleModal_id').modal('show');

}