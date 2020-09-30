var dropDown;
var cancelBelowMinimum;
var cancelBelowMinimumButton;
var enrollMemberButton;

$(document).ready(function() {
//	//On load
	findCourseSchedule("month");
	
	//Disabled Cancel Below Minimum Button if there are no course schedule
	cancelBelowMinimumCallDisabledButton();

	//On dropdown change value
	dropDown = $("#dropDown");
	dropDown.change(function() {
		value = dropDown.val();
		findCourseSchedule(value);
	});

	//On click CancelBelowMinimum
	cancelBelowMinimum = $("#cancelBelowMinimumbtn");
	cancelBelowMinimum.click(function(){
		cancelBelowMinimumCall();
	});

	cancelBelowMinimumButton = $('#cancelBelowMinimumButton');
	cancelBelowMinimumButton.click(function(){
		cancellingBelowMinimum();
	});

});

function cancelBelowMinimumCallDisabledButton(){
	$.ajax({
		type:"GET",
		url: "getAllScheduleBelowMinimum",
		dataType:"json",
		contentType: 'application/json',
		success: function(data){
			if(data.length > 0){
				
				$("#cancelBelowMinimumbtn").attr("disabled", false);
			}else if(data.length < 0){
				
				$("#cancelBelowMinimumbtn").attr("disabled", true);
			}
			
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("ERROR searchAjax");
			alert(xhr.status);
		    alert(thrownError);
		}
	});

}

function cancelBelowMinimumCall(){
	$.ajax({
		type:"GET",
		url: "getAllScheduleBelowMinimum",
		dataType:"json",
		contentType: 'application/json',
		success: function(data){
			bewlowCourseScheduleToModal(data);
		},
		error: function(xhr, ajaxOptions, thrownError){
			alert("ERROR searchAjax");
			alert(xhr.status);
		    alert(thrownError);
		}
	});

}
function bewlowCourseScheduleToModal(data){
	$('#cancel-below-minimum-table-body').empty();
	dataLength = data.length;
	document.getElementById('cancelBelowMinimumLength').value = dataLength;

	var table = document.getElementById('cancel-below-minimum-table-body')
	var courseScheduleIdSlot = 0;
	data.forEach(function(object){
		var courseScheduleDetail = object.courseScheduleDetails;
		var tr = document.createElement('tr');

		var courseNameInput ='<input type="text" class="form-control-plaintext" readonly value="' +object.courseName +'"/>';
		var startDateTimeInput ='<input type="text" class="form-control-plaintext" readonly value="' +courseScheduleDetail.scheduledStartDateTime + '"/>';
		var endDateTimeInput ='<input type="text" class="form-control-plaintext" readonly value="' +courseScheduleDetail.scheduledEndDateTime + '"/>';
		var instructorInput ='<input type="text" class="form-control-plaintext" readonly value="' +object.instructorName +'"/>';
		var durationInput ='<input type="text" class="form-control-plaintext" readonly value="' +courseScheduleDetail.duration +'"/>';
		var courseScheduleIdInput ='<input type="hidden" id="courseScheduleId_'+courseScheduleIdSlot+'" class="form-control-plaintext" readonly value="' +object.id +'"/>';

		tr.innerHTML = '<td>'+
			courseScheduleIdInput+
			'<div class="form-group row">'+
		'<label class="col-lg-3 col-form-label">Name:</label>'+
		'<div class="col-lg-9">'+
			courseNameInput +
		'</div>'+
		'<label class="col-lg-3 col-form-label">Start DateTime:</label>'+
		'<div class="col-lg-9">'+
			startDateTimeInput+
		'</div>'+
		'<label class="col-lg-3 col-form-label">End DateTime:</label>'+
		'<div class="col-lg-9">'+
			endDateTimeInput+
		'</div>'+
		'<label class="col-lg-3 col-form-label">Instructor:</label>'+
		'<div class="col-lg-9">'+
			instructorInput+
		'</div>'+
		'<label class="col-lg-3 col-form-label">Duration:</label>'+
		'<div class="col-lg-9">'+
			durationInput+
		'</div>'+
	'</div>'+
	'</td>';

		var tdParticipant = document.createElement('td');
		tdParticipant.innerHTML = object.totalParticipants;
		tdParticipant.classList.add('text-center');

		var tdMinRequired = document.createElement('td');
		tdMinRequired.innerHTML = object.minRequired;
		tdMinRequired.classList.add('text-center');

		tr.append(tdParticipant);
		tr.append(tdMinRequired);
		table.append(tr);
		courseScheduleIdSlot++;
		$('#cancelMinimumModal').modal('show');	
	});
}

function cancellingBelowMinimum(){
	$('#div-inputs').empty();
	var dataLength = document.getElementById('cancelBelowMinimumLength').value;
	var div = document.getElementById('div-inputs');
	
	for(var i = 0; i < dataLength; i++){
		var input = document.createElement('input');
		var courseScheduleId = document.getElementById('courseScheduleId_'+i).value;
//		alert("CourseScheduleId " + i + ": " + courseScheduleId);

		input.setAttribute('type', 'hidden');
		input.setAttribute('name', "ids["+i+"]");
	    input.value = courseScheduleId;
	    div.append(input);
	}
	
	$('#cancelModal').modal('show');
}

function findCourseSchedule(queryBy){

	$.ajax({
		type : "POST",
		url : "findSchedules",
		data : queryBy,
		dataType: "json",
		contentType: 'application/json', 
		success : function (data) {
			//SUCCESS
//			alert("Success");
			console.log(data);
			toTable(data);

		},
		error: function () {
			alert("ERROR");
		},
		always:function(){
			alert("PLSSSSS");
		}
	});

}

function toTable(data){
	//clear table body to not append the new data retrieve
	$('#table-body').empty();
	var len = data.length;
	var table = document.getElementById('table-body')
	
	data.forEach(function(object){
		
		var courseScheduleDetail = object.courseScheduleDetail;
		var tr = document.createElement('tr'); 
		tr.innerHTML = 
			'<td>' + object.id + '</td>' +
			'<td>' + object.courseName + '</td>' +
			'<td>' + object.instructorLastName + ' ' + object.instructorFirstName + '</td>' +
			'<td>' + courseScheduleDetail.scheduledStartDateTime + '</td>' +
			'<td>' + courseScheduleDetail.scheduledEndDateTime + '</td>' +
			'<td>' + courseScheduleDetail.duration + '</td>' +
			'<td>' + object.venueName + '</td>' +
			'<td>' + object.minRequired + '</td>' +
			'<td>' + object.maxAllowed + '</td>' +
			'<td>' + object.totalParticipants + '</td>';
		//creating td element
		var tdButtonReschedule = document.createElement("td");
		//creating button element
		var buttonReschedule = document.createElement("button");
		//give a value of a button
		buttonReschedule.innerHTML = "Reschedule"
			//assigning class of button
		buttonReschedule.classList.add("btn");
		buttonReschedule.classList.add("btn-success");
		buttonReschedule.classList.add("float-right");

		tdButtonReschedule.append(buttonReschedule);

		//creating td element
		var tdButtonCancel = document.createElement("td");
		//creating button element
		var buttonCancel = document.createElement('button');
		//give a value of a button
		buttonCancel.innerHTML = "Cancel"
			//assigning class of button
		buttonCancel.classList.add("btn");
		buttonCancel.classList.add("btn-danger");
		buttonCancel.classList.add("float-right");

		tdButtonCancel.append(buttonCancel);

		tr.append(tdButtonReschedule);
		tr.append(tdButtonCancel);
		table.append(tr);
		//give a button a eventListener
		buttonReschedule.addEventListener("click", function(){
			reschedule(object.id,object.courseName, courseScheduleDetail.id, courseScheduleDetail.scheduledStartDateTime, courseScheduleDetail.scheduledEndDateTime);
		});
		//give a button a eventListener
		buttonCancel.addEventListener("click", function(){
			cancellation(object.id,object.courseName);
		});
		
	});
	
}
function cancellation(courseScheduleId, courseName){
//	alert("(Cancellation) CourseName: "+courseScheduleId + courseName);
	//call cancel confirmation modall
	document.getElementById("courseName").innerHTML = courseName ;
	var input = document.getElementById("id");
	input.value=courseScheduleId;
	$('#confirmModal').modal('show');
}

function reschedule(csId, csName, csdId, csdStartDateTime, csdEndDateTime){
//	alert(csId+ " " + csName + " csd ID: " + csdId);
	document.getElementById('header-modal').innerHTML = csId+ ". " + csName;
	var rescheduleCourseScheduleDetailId = document.getElementById('rescheduleCourseScheduleDetailId');
	rescheduleCourseScheduleDetailId.value = csdId;
	
	var startDateTime = csdStartDateTime.slice(0,19);
	var endDateTime = csdEndDateTime.slice(0,19);
	document.getElementById('rescheduleStartDateTime').value= startDateTime;
	document.getElementById('rescheduleEndDateTime').value= endDateTime;

	document.getElementById('rescheduleStartDateTime_old').value= startDateTime;
	document.getElementById('rescheduleEndDateTime_old').value= endDateTime;
	
	$('#rescheduleModal').modal('show');
}

function confirmReschedule(){
	var x = 0;
	var toAppend = ":00.000+08:00[Asia/Singapore]";
	var courseScheduleDetailId = document.getElementById('rescheduleCourseScheduleDetailId').value;
	var startDateTime = document.getElementById('rescheduleStartDateTime').value;
	var endDateTime = document.getElementById('rescheduleEndDateTime').value;
	
	var momentStartDateTime = moment(startDateTime);
	var momentEndDateTime = moment(endDateTime);
	
	var oldStartDateTime = document.getElementById('rescheduleStartDateTime_old').value;
	var oldEndDateTime = document.getElementById('rescheduleEndDateTime_old').value;
//	
//	alert("START: " + startDateTime);
//	alert("START OLD: " + oldStartDateTime);
//	alert("END OLD: " + oldEndDateTime);
//	alert("END: " + endDateTime);
//	alert("START MOMENT: " + momentStartDateTime);
//	alert("END MOMENT: " + momentEndDateTime);
	//validate here
	if(oldStartDateTime.isAfter(startDateTime)){
		alert("DONE");
		var confirmRescheduleInputId = document.getElementById('confirmRescheduleCSDID');
		var confirmRescheduleInputStartDateTime = document.getElementById('confirmRescheduleCSDStartDateTime');
		var confirmRescheduleInputEndDateTime = document.getElementById('confirmRescheduleCSDEndDateTime');
		
		confirmRescheduleInputId.value = courseScheduleDetailId;
		confirmRescheduleInputStartDateTime.value = startDateTime+toAppend;
		confirmRescheduleInputEndDateTime.value = endDateTime+toAppend;
		$('#confirmRescheduleModal').modal('show');
	}else if(startDateTime.isAfter(oldStartDateTime)){
		alert("the hell");
	}
	else{
		alert("Hi");
	}

}
