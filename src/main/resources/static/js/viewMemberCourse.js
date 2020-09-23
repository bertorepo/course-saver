var enrollMemberButton
var searchInput;
$(document).ready(function(){
	searchInput = $('#searchInput');
	searchInput.change(function(){
		searchAjax(searchInput.val());
	});
});

function searchAjax(search){
	var courseScheduleId = document.getElementById('registerCourseScheduleId').value;
//	alert(courseScheduleId+search);
	var search = {
		"courseScheduleId": courseScheduleId,
		"search":search
	};
	
	$.ajax({
		type : "POST",
		url : "findMember",
		data : JSON.stringify(search),
		dataType: "json",
		contentType: 'application/json', 
		success : function (data) {
			alert("success");
			toTableMemberList(data);
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("ERROR searchAjax");
			alert(xhr.status);
		    alert(thrownError);
		}
	});
}
function findMemberNotEnrolledByCourseScheduleId(courseScheduleId){
	$.ajax({
		type : "POST",
		url : "notEnrolledByCourseScheduleId",
		data : courseScheduleId,
		dataType: "json",
		contentType: 'application/json', 
		success : function (data) {
			toTableMemberList(data);
		},
		error: function(){
			alert("ERROR findMemberNotEnrolledByCourseScheduleId");
		}
	});
}
function toTableMemberList(data){
	$('#search-body-table').empty();
	var table = document.getElementById('search-body-table');
	data.forEach(function(object){
//		alert();
		var tr = document.createElement('tr');
		tr.innerHTML = '<td class="text-center">' +object.employeeNumber+ '</td>'+
						'<td class="text-center">' +object.participantName+ '</td>'+ 
						'<td class="text-center">' +object.email+ '</td>';
		
		var td = document.createElement('td');
		var enrollButton = document.createElement('button');
		enrollButton.classList.add("btn");
		enrollButton.classList.add("btn-primary");
		enrollButton.innerHTML="Enroll";
		td.append(enrollButton);
		tr.append(td);
		table.append(tr);
		enrollButton.addEventListener("click", function(){
//			alert(object.participantId);
//			alert(object.participantName);
			enrollmentMember(object.participantName, object.participantId, object.email);
		});
	});
}
function enrollmentMember(participantName, participantId, participantEmail){//ERROR IF METHOD NAME IS enrollMember

	var courseScheduleId = document.getElementById('registerCourseScheduleId').value;
	var courseScheduleDetailId = document.getElementById('registerCourseScheduleDetailId').value;
	var courseName = document.getElementById('registerCourse').innerText;
//	alert(participantEmail);
//	alert(participantId + "1." +participantName);
	//CALL MODAL AND PASS IT TO SPRING
	document.getElementById('enrollMemberName').innerText = participantName;
	document.getElementById('enrollMemberCourseName').innerText = courseName;
	document.getElementById('confirmEnrollMemberCourseScheduleDetailId').value=courseScheduleDetailId;
	document.getElementById('confirmEnrollMemberCourseScheduleId').value=courseScheduleId;
	document.getElementById('confirmEnrollMemberParticipantId').value=participantId;
	document.getElementById('confirmEnrollMemberEmailAddress').value=participantEmail;
	$('#confirmEnrollMemberModal').modal('show');
}
function findEnrolledMemberByCourseScheduleId(courseScheduleId){
	
	$.ajax({
		type : "POST",
		url : "findEnrolledMember",
		data : courseScheduleId,
		dataType: "json",
		contentType: 'application/json', 
		success : function (data) {
			toTableEnrolledMember(data);
		},
		error: function(){
		
		}
	});
	
	
}
function toTableEnrolledMember(data){
	$('#enrolled-data').empty();
	var table = document.getElementById('enrolled-data');
	data.forEach(function(object){
//		alert();
		var tr = document.createElement('tr');
		tr.innerHTML = '<td class="text-center">' +object.employeeNumber+ '</td>'+
						'<td class="text-center">' +object.participantName+ '</td>'+ 
						'<td class="text-center">' +object.email+ '</td>';
		table.append(tr);
	});
}
