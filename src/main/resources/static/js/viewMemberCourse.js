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
//			alert("success");
			toTableMemberList(data);
		},
		error: function(xhr, ajaxOptions, thrownError){
			document.getElementById('message').innerHTML="The search returned zero results";
			$('#errorModal').modal('show');
//			alert("ERROR searchAjax");
//			alert(xhr.status);
//		    alert(thrownError);
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
		var td = document.createElement('td');
		var enrollButton = document.createElement('button');
		enrollButton.classList.add("btn");
		enrollButton.classList.add("btn-primary");
		enrollButton.innerHTML="Enroll";
		td.append(enrollButton);
		tr.append(td);
		
		var tdEmpNum = document.createElement('td');
		var tdEmpNam = document.createElement('td');
		var tdEmpEmail = document.createElement('td');
		tdEmpNum.classList.add("text-center");
		tdEmpNum.append(object.employeeNumber);
		tdEmpNam.classList.add("text-center");
		tdEmpNam.append(object.participantName); 
		tdEmpEmail.classList.add("text-center");
		tdEmpEmail.append(object.email);
		
		tr.append(tdEmpNum);
		tr.append(tdEmpNam);
		tr.append(tdEmpEmail);
		
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
		var tr = document.createElement('tr');		
		var td = document.createElement('td');
		var enrollButton = document.createElement('button');
		enrollButton.classList.add("btn");
		enrollButton.classList.add("btn-danger");
		enrollButton.innerHTML="Remove";
		td.append(enrollButton);
		tr.append(td);
		tr.innerHTML = tr.innerHTML + '<td class="text-center">' +object.employeeNumber+ '</td>'+
						'<td class="text-center">' +object.participantName+ '</td>'+ 
						'<td class="text-center">' +object.email+ '</td>';
		table.append(tr);
		enrollButton.addEventListener("click", function(){
			enrollmentMember(object.participantName, object.participantId, object.email);
		});
	});
}


function courseCategoryOnDown(id){
	var holder = document.getElementById(id).value;
	var faceVal = document.getElementById(id).placeholder;
	
	if(holder == "" && faceVal !== ""){
		document.getElementById(id).placeholder = faceVal;
		document.getElementById(id).value = '';
		
		var courseId = $('#courseCategory [value="'+ faceVal +'"]').data('value');
		
		document.getElementById("courseCategoryId").value = courseId;
	} else {
		document.getElementById(id).placeholder = holder;
		document.getElementById(id).value = '';
		
		var courseId = $('#courseCategory [value="'+ holder +'"]').data('value');
		
		document.getElementById("courseCategoryId").value = courseId;
	}
}

function courseCategoryOnChange(){
	
	var courseCategory = $("#courseCategorydetail").val();
	var holder = document.getElementById("courseCategorydetail").placeholder;
	
	if(courseCategory == "" && (holder !== "Enter Course Category" || holder !== "")){
		document.getElementById("courseCategorydetail").value = holder;
		
		var courseId = $('#courseCategory [value="'+ holder +'"]').data('value');
		
		document.getElementById("courseCategoryId").value = courseId;
		
	} else {
		var courseId = $('#courseCategory [value="'+ courseCategory +'"]').data('value');
		
		document.getElementById("courseCategoryId").value = courseId;
	}
}

function courseNameOnDown(id){
	var holder = document.getElementById(id).value;
	var faceVal = document.getElementById(id).placeholder;
	
	if(holder == "" && faceVal !== ""){
		document.getElementById(id).placeholder = faceVal;
		document.getElementById(id).value = '';
		
		var courseId = $('#courseName [value="'+ faceVal +'"]').data('value');
		
		document.getElementById("courseNameId").value = courseId;
	} else {
		document.getElementById(id).placeholder = holder;
		document.getElementById(id).value = '';
		
		var courseId = $('#courseName [value="'+ holder +'"]').data('value');
		
		document.getElementById("courseNameId").value = courseId;
	}
}

function courseNameOnChange(){
	
	var courseName = $("#courseNamedetail").val();
	var holder = document.getElementById("courseNamedetail").placeholder;
	
	if(courseName == "" && (holder !== "Enter Course Category" || holder !== "")){
		document.getElementById("courseNamedetail").value = holder;
		
		var courseId = $('#courseName [value="'+ holder +'"]').data('value');
		
		document.getElementById("courseNameId").value = courseId;
		
	} else {
		var courseId = $('#courseName [value="'+ courseName +'"]').data('value');
		
		document.getElementById("courseNameId").value = courseId;
	}
}

function instructorOnDown(id){
	var holder = document.getElementById(id).value;
	var faceVal = document.getElementById(id).placeholder;
	
	if(holder == "" && faceVal !== ""){
		document.getElementById(id).placeholder = faceVal;
		document.getElementById(id).value = '';
		
		var instructorId = $('#instructor [value="'+ faceVal +'"]').data('value');
		
		document.getElementById("instructorId").value = instructorId;
	} else {
		document.getElementById(id).placeholder = holder;
		document.getElementById(id).value = '';
		
		var instructorId = $('#instructor [value="'+ holder +'"]').data('value');
		
		document.getElementById("instructorId").value = instructorId;
	}
}

function instructorOnChange(){
	
	var instructorName = $("#instructordetail").val();
	var holder = document.getElementById("instructordetail").placeholder;
	
	if(instructorName == "" && (holder !== "Enter Instructor" || holder !== "")){
		document.getElementById("instructordetail").value = holder;
		
		var instructorId = $('#instructor [value="'+ holder +'"]').data('value');
		
		document.getElementById("instructorId").value = instructorId;
		
	} else {
		var instructorId = $('#instructor [value="'+ instructorName +'"]').data('value');
		
		document.getElementById("instructorId").value = instructorId;
	}
}


function venueOnDown(id){
	var holder = document.getElementById(id).value;
	var faceVal = document.getElementById(id).placeholder;
	
	if(holder == "" && faceVal !== ""){
		document.getElementById(id).placeholder = faceVal;
		document.getElementById(id).value = '';
		
		var venueId = $('#venue [value="'+ faceVal +'"]').data('value');
		
		document.getElementById("venueId").value = venueId;
	} else {
		document.getElementById(id).placeholder = holder;
		document.getElementById(id).value = '';
		
		var venueId = $('#venue [value="'+ holder +'"]').data('value');
		
		document.getElementById("venueId").value = venueId;
	}
}

function venueOnChange(){
	
	var venueName = $("#venuedetail").val();
	var holder = document.getElementById("venuedetail").placeholder;
	
	if(venueName == "" && (holder !== "Enter Venue" || holder !== "")){
		document.getElementById("venuedetail").value = holder;
		
		var venueId = $('#venue [value="'+ holder +'"]').data('value');
		
		document.getElementById("venueId").value = venueId;
		
	} else {
		var venueId = $('#venue [value="'+ venueName +'"]').data('value');
		
		document.getElementById("venueId").value = venueId;
	}
}
