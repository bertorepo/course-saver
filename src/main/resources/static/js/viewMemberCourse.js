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
			$('#search-body-table').empty();
			document.getElementById('enrollBtn').disabled = true; 
		}
	});
}
function toTableMemberList(data){
	$('#search-body-table').empty();
	var table = document.getElementById('search-body-table');
	
	data.forEach(function(object){

		var tr = document.createElement('tr');		
		var td = document.createElement('td');
		var enrollChk = document.createElement('input');
		enrollChk.setAttribute("type", "checkbox");
		enrollChk.setAttribute("name", "enrollChk");
		enrollChk.setAttribute("value", object.participantId);
		td.append(enrollChk);
		tr.append(td);
		
		var tdEmpNum = document.createElement('td');
		var tdEmpNam = document.createElement('td');
		var tdEmpEmail = document.createElement('td');
		tdEmpNum.classList.add("align-middle");
		tdEmpNum.append(object.employeeNumber);
		tdEmpNam.classList.add("align-middle");
		tdEmpNam.append(object.participantName); 
		tdEmpEmail.classList.add("align-middle");
		tdEmpEmail.append(object.email);
		
		tr.append(tdEmpNum);
		tr.append(tdEmpNam);
		tr.append(tdEmpEmail);
		
		tr.classList.add("table-row");
		
		table.append(tr);
		enrollChk.addEventListener("change", function(){
			enrollMembers();
		});
	});
	
	document.getElementById('enrollBtn').disabled = true; 
	
	if ($("#search-body-table").children().length == 0) {
		document.getElementById("enrollMemberMsg").style.display = "block";
		document.getElementById("enrollErrMsg").innerHTML = "No available members to enroll."
	} else {
		document.getElementById("enrollMemberMsg").style.display = "none";
		document.getElementById("enrollErrMsg").innerHTML = "Maximum participants allowed reached."
	}
}
//function enrollmentMember(participantName, participantId, participantEmail){//ERROR IF METHOD NAME IS enrollMember
//
//	var courseScheduleId = document.getElementById('registerCourseScheduleId').value;
//	var courseScheduleDetailId = document.getElementById('registerCourseScheduleDetailId').value;
//	var courseName = document.getElementById('registerCourse').innerText;
//
//	//CALL MODAL AND PASS IT TO SPRING
//	document.getElementById('enrollMemberName').innerText = participantName;
//	document.getElementById('enrollMemberCourseName').innerText = courseName;
//	document.getElementById('confirmEnrollMemberCourseScheduleDetailId').value=courseScheduleDetailId;
//	document.getElementById('confirmEnrollMemberCourseScheduleId').value=courseScheduleId;
//	document.getElementById('confirmEnrollMemberParticipantId').value=participantId;
//	document.getElementById('confirmEnrollMemberEmailAddress').value=participantEmail;
//	$('#confirmEnrollMemberModal').modal('show');
//}


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
			$('#enrolled-data').empty();
			document.getElementById('removeBtn').disabled = true; 
		}
	});
	
	
}
function toTableEnrolledMember(data){
	$('#enrolled-data').empty();
	var table = document.getElementById('enrolled-data');
	
	data.forEach(function(object){
		var tr = document.createElement('tr');		
		var td = document.createElement('td');
		var removeChk = document.createElement('input');
		removeChk.setAttribute("type", "checkbox");
		removeChk.setAttribute("name", "removeChk");
		removeChk.setAttribute("value", object.participantId);
		tr.classList.add("table-row");
		td.append(removeChk);
		tr.append(td);
		
		var tdEmpNum = document.createElement('td');
		var tdEmpNam = document.createElement('td');
		var tdEmpEmail = document.createElement('td');
		tdEmpNum.classList.add("align-middle");
		tdEmpNum.append(object.employeeNumber);
		tdEmpNam.classList.add("align-middle");
		tdEmpNam.append(object.participantName); 
		tdEmpEmail.classList.add("align-middle");
		tdEmpEmail.append(object.email);
		
		tr.append(tdEmpNum);
		tr.append(tdEmpNam);
		tr.append(tdEmpEmail);
		
		table.append(tr);
		
		removeChk.addEventListener("change", function(){
//			alert(object.participantId);
//			alert(object.participantName);
			removeMembers();
		});
	});
	
	document.getElementById('removeBtn').disabled = true; 
	
	if ($("#enrolled-data").children().length == 0) {
		document.getElementById("removeMemberMsg").style.display = "block";
	} else {
		document.getElementById("removeMemberMsg").style.display = "none";
	}
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

function showRemoveModal(){
	$('#removeEnrollMemberModal').modal('show');
}

function showEnrollModal(){
	$('#confirmEnrollMemberModal').modal('show');
}
