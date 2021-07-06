//For HTML with FromDateTime and ToDateTime
$("#fromDate").blur(function () {
	var formDate = document.getElementById("fromDate").value;
	var newDate = new Date(formDate);
	var dateConcat = newDate.toISO().toString();
	if (formDate == "") {
		$("#fromDateTime").prop('value', null);
	} else {
		$("#fromDateTime").prop('value', dateConcat);
	}
}); 

$("#toDate").blur(function () {
	var formDate = document.getElementById("toDate").value;
	var newDate = new Date(formDate);
	var dateConcat = newDate.toISO().toString(); 
	if (formDate == "") {
		$("#toDateTime").prop('value', null);
	} else {
		$("#toDateTime").prop('value', dateConcat);
	}
}); 

//Format Date Inputs
Date.prototype.toISO = function() {
	 return this.getUTCFullYear() +
        '-' + String(this.getUTCMonth() + 1).padStart(2, "0") +
        '-' + String(this.getUTCDate()).padStart(2, "0") +
        'T' + String(this.getUTCHours()).padStart(2, "0") +
        ':' + String(this.getUTCMinutes()).padStart(2, "0") +
        ':' + String(this.getUTCSeconds()).padStart(2, "0") +
        '.' + (this.getUTCMilliseconds() / 1000).toFixed(3).slice(2, 5) +
        'Z';
};

//Checks if Date is Today
function isLessThanToday(date){
	var dateEntry = new Date(date);
	var today = new Date();
	console.log(dateEntry + " Date entry");
	console.log(today + " Today");
	return dateEntry < today;
}  

//Formats the text to only accept numbers on keypress
function numOnly(id)
{
	 var number = document.getElementById(id);
	 var regex = /[^0-9]/gi;
	 number.value = number.value.replace(regex, "");
}

//Formats the time to only accept numbers add Semicolon at 3rd index 
function timeOnly(id)
{
	var time = document.getElementById(id);
	var regexWithColon = /[^0-9:]/gi;
	var regexWithoutColon = /[^0-9]/gi;
	
	if (time.value.length == 2 && !(time.value.includes(":"))){
		
		time.value = time.value.replace(regexWithColon, "") + ":";
	} 
	
	if(time.value.includes(":")) {
		
		var hour = time.value.substring(0, time.value.indexOf(':')).replace(regexWithoutColon, "");
		var minute = time.value.substring(time.value.indexOf(':') + 1).replace(regexWithoutColon, "");
		var intHour = parseInt(hour);
		var intMinute = parseInt(minute);
		
		time.value = hour + ":" + minute;
		
		if (hour.length == 1 && (time.value.length == 4 || time.value.length == 3)){
			time.value = hour.padStart(2, "0") + ":" + minute.padEnd(2,"0");
			
			if (intMinute > 5) {	
				time.value = hour.padStart(2, "0") + ":00";	
			}
		}
		
		if (intHour > 24) {
			time.value = "00:" + minute;
		}
		
		if (intMinute > 59 || intHour == 24) {	
			time.value = hour.padStart(2, "0") + ":00";	
		}
		
	}	
}

//For search boxes of Instructor and Venue in Create scheduling and Update Scheduling
function instructorNameOnDown(id){
	var holder = document.getElementById(id).value;
	var faceVal = document.getElementById(id).placeholder;
	
	if(holder == "" && faceVal !== ""){
		document.getElementById(id).placeholder = faceVal;
		document.getElementById(id).value = '';
		
		var instructorId = $('#instructors [value="'+ faceVal +'"]').data('value');
		
		document.getElementById("instructorId").value = instructorId;
	} else {
		document.getElementById(id).placeholder = holder;
		document.getElementById(id).value = '';
		
		var instructorId = $('#instructors [value="'+ holder +'"]').data('value');
		
		document.getElementById("instructorId").value = instructorId;
	}
}

function instructorNameOnChange(){
	
	var instructorName = $("#instructorName").val();
	var holder = document.getElementById("instructorName").placeholder;
	
	if(instructorName == "" && (holder !== "Please select..." || holder !== "")){
		document.getElementById("instructorName").value = holder;
		
		var instructorId = $('#instructors [value="'+ holder +'"]').data('value');
		
		document.getElementById("instructorId").value = instructorId;
		
	} else {
		var instructorId = $('#instructors [value="'+ instructorName +'"]').data('value');
		if(instructorId == undefined){
		    var instructorNotFound = "Please specify a valid instructor name. Use the drop-down list for courses available."
		    document.getElementById("instructorId_error").innerHTML = instructorNotFound;
		    $("#instructorName").val("Please select...");
		  } else {				
		    document.getElementById("instructorId").value = instructorId;
		    document.getElementById("instructorId_error").innerHTML = "";
		}
	}
}

function venueNameOnDown(id){
	var holder = document.getElementById(id).value;
	var faceVal = document.getElementById(id).placeholder;
	
	if(holder == "" && faceVal !== ""){
		document.getElementById(id).placeholder = faceVal;
		document.getElementById(id).value = '';
		
		var venueId = $('#venues [value="'+ faceVal +'"]').data('value');
		
		document.getElementById("venueId").value = venueId;
	} else {
		document.getElementById(id).placeholder = holder;
		document.getElementById(id).value = '';
		
		var venueId = $('#venues [value="'+ holder +'"]').data('value');
		
		document.getElementById("venueId").value = venueId;
	}
}

function venueNameOnChange(){
	
	var venueName = $("#venueName").val();
	var holder = document.getElementById("venueName").placeholder;
	
	if(venueName == "" && (holder !== "Please select..." || holder !== "")){
		document.getElementById("venueName").value = holder;
		
		var venueId = $('#venues [value="'+ holder +'"]').data('value');
		
		document.getElementById("venueId").value = venueId;
		
	} else {
		var venueId = $('#venues [value="'+ venueName +'"]').data('value');
		if(venueId == undefined){
		   var venueNotFound = "Please specify a valid venue name. Use the drop-down list for courses available."
		   document.getElementById("venueId_error").innerHTML = venueNotFound;
		   $("#venueName").val("Please select...");
		} else {				
		   document.getElementById("venueId").value = venueId;
		   document.getElementById("venueId_error").innerHTML = "";
		}
	}
}