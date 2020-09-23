

//Setting Date and Time on blur
function datetimeBlur(){
	var courseScheduleDetails = document.getElementById("courseScheduleDetails");
	var length = courseScheduleDetails.rows.length;
	for (var i = 1; i <= length; i++) {

	    var startDate = $("#startDate" + i).val();
	    var startTime = $("#startTime" + i).val();
	    var endDate = $("#endDate" + i).val();
	    var endTime = $("#endTime" + i).val();
	    
	    if(startDate !== ""){
	    	var rowStartDateTime = new Date(startDate);
	    }
	    
	    if(endDate !== ""){
	    	var rowEndDateTime = new Date(endDate);
	    }
	    
	    if(startTime !== ""){
	    	rowStartDateTime.setHours(startTime.substring(0, startTime.indexOf(":")));
		    rowStartDateTime.setMinutes(startTime.substring(startTime.indexOf(":") + 1));
	    }
	    
	    if(endTime !=="") {
	    	rowEndDateTime.setHours(endTime.substring(0, endTime.indexOf(":")));
		    rowEndDateTime.setMinutes(endTime.substring(endTime.indexOf(":") + 1)); 
	    }
	    
	    if((startDate == "") && (startTime == "")){
	    	var startDateTime = null;
	    }
	    
	    if((endDate == "") && (endTime == "")){
	    	var endDateTime = null;
	    }
	    
	    if((startDate !== "") && (startTime !== "")){
	    	var startDateTime = rowStartDateTime.toISO().toString();
	    	document.getElementById("startDateTime"+i).value = startDateTime;
	    }
	    
	    if((endDate !== "") && (endTime !== "")){
	    	var endDateTime = rowEndDateTime.toISO().toString();
	    	document.getElementById("endDateTime"+i).value = endDateTime;
	    }
	}
}


//Add New Rows
function addNewRow() {
	var courseScheduleDetails = document.getElementById("courseScheduleDetails");
	var length = courseScheduleDetails.rows.length;
	var Url = '/schedules/new/addDate/';
	document.getElementById("scheduleNew").action = Url;
}

function removeNewRow(id) {
	var courseScheduleDetails = document.getElementById(id);
	var removeRowVal = courseScheduleDetails.value;
	var Url = '/schedules/new/removeDate/'+removeRowVal;
	document.getElementById("scheduleNew").action = Url;
}

//Set Course ID
$("#courseName").blur(function (){
	var courseName = $("#courseName").val();
	var courseId = $('#courses [value="'+ courseName +'"]').data('value');
	
	document.getElementById("courseId").value = courseId;
});

function validations() {
	var errorCount = 0;
	var courseName = $('#courseName').val();
	var instructor = $('#instructorId').val();
	var venue = $('#venueId').val();
	var courseId = $('#courseId').val();
	var minRequired = parseInt($('#minRequired').val().replace("", "0"));
	var maxAllowed = parseInt($('#maxAllowed').val().replace("", "0"));
	
	
	//Empty Fields Validations
	if (courseName == "") {
		errorCount++;
		courseNameError = "Please fill up Course Name"; 
        document.getElementById("courseName_error").innerHTML = courseNameError;
	}
	
	if ((courseId == "undefined") || (courseId == null) || (courseId == "")) {
		errorCount++;
		courseNameError = 'No Course found with the name, "'+courseName+'".  Please Select from the List.'; 
        document.getElementById("courseName_error").innerHTML = courseNameError;
	}
	
	if (instructor == "") {
		errorCount++;
		instructorError = "Please fill up Instructor";
        document.getElementById("instructorId_error").innerHTML = instructorError;
	}
	if (venue == "") {
		errorCount++;
		venueError = "Please fill up Venue";
        document.getElementById("venueId_error").innerHTML = venueError;
	}
	if (minRequired <= 0) {
		errorCount++;
	  	minRequiredError = "Please fill up Minimum no. of Participants";
	  	 document.getElementById("minRequired_error").innerHTML = minRequiredError;
	}
	if (maxAllowed <=0) {
		errorCount++;
	  	maxAllowedError = "Please fill up Maximum no. of Participants";
	  	 document.getElementById("maxAllowed_error").innerHTML = maxAllowedError;
	}
	if (minRequired !==0) {
		if (maxAllowed <= minRequired) {
			errorCount++;
	  	minRequiredError = "Minimum can't be greater or equal to Maximum Participants";
	  	 document.getElementById("minRequired_error").innerHTML = minRequiredError;
		}
	}
	
	//Empty Fields Validation for Start and End date
	var startDateCount = 0;
	var startTimeCount = 0;
	var endDateCount = 0;
	var endTimeCount = 0;
	var courseScheduleDetails = document.getElementById("courseScheduleDetails");
	var length = courseScheduleDetails.rows.length;

	for (var i = 1; i <= length; i++) {

	    var startDate = $("#startDate" + i).val();
	    var startTime = $("#startTime" + i).val();
	    var endDate = $("#endDate" + i).val();
	    var endTime = $("#endTime" + i).val();

	    if (startDate !== "") {
	        startDateCount++;
	    }    
	    if (startTime !== "") {
	        startTimeCount++;
	    }
	    if (endDate !== "") {
	        endDateCount++;
	    }
	    if (endTime !== "") {
	        endTimeCount++;
	    }

	if (startDateCount <= 0) {
		errorCount++;
		startDateError = "Please fill up Start Date";
	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
	}
	if (startTimeCount <= 0) {
		errorCount++;
		startTimeError = "Please fill up Start Time";
	  	document.getElementById("startTime" + i + "_error").innerHTML = startTimeError;
	}

	if (endDateCount <= 0) {
		errorCount++;
		endDateError = "Please fill up End Date";
	  	document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
	}
	if (endTimeCount <= 0) {
		errorCount++;
		endTimeError = "Please fill up End Time";
	  	document.getElementById("endTime" + i + "_error").innerHTML = endTimeError;
	}
	
	}
	//Scheduled Start Date Time and Scheduled End Date Time Part
    for(i = 1; i<= length; i++){
    	var startDate = document.getElementById("startDate"+i).value;
        var endDate =  document.getElementById("endDate"+i).value;
        var startTime =  document.getElementById("startTime"+i).value;
        var endTime =  document.getElementById("endTime"+i).value;
        
        /* var rowStartDateTime = new Date(startDate+"T"+startTime+":00Z");
	    var rowEndDateTime = new Date(endDate+"T"+startTime+":00Z"); */
        
        var rowStartDateTime = new Date(startDate);
	    var rowEndDateTime = new Date(endDate);
	    
	    rowStartDateTime.setHours(startTime.substring(0, startTime.indexOf(":")));
	    rowStartDateTime.setMinutes(startTime.substring(startTime.indexOf(":") + 1));
	    rowEndDateTime.setHours(endTime.substring(0, endTime.indexOf(":")));
	    rowEndDateTime.setMinutes(endTime.substring(endTime.indexOf(":") + 1)); 
	    
	    console.log(rowStartDateTime);
	    console.log(rowEndDateTime);
       	
	
	    if((startDateCount !==0) && (endDateCount !==0)) {
	    	//Start and End Time Validation
        	if (startDate > endDate)  {
        		errorCount++;
        		startDateError = "Start Date can't be greater than or equal to end date";
        	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
            }
        	
        	//Start and End Date Validation
        	if (startTime > endTime) {
        		errorCount++;
        		startDateError = "Start time can't be greater than or equal to end time";
        	  	document.getElementById("startTime" + i + "_error").innerHTML = startDateError;
            }
        	
        	//If Date is Less Than Today
        	if (isLessThanToday(startDate)) {
        		errorCount++;
        		startDateError = "Date can't be less than today";
        	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
        	}
        	
        	if (isLessThanToday(endDate)) {
        		errorCount++;
        		endDateError = "Date can't be less than today";
        	  	document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
        	}
        	if((startDate !== "") && (endDate !== "")) {
        		//If Datetime is equal Validation
            	if ((startDate == endDate) && (startTime == endTime)) {
            		errorCount++;
            		startDateError = "Start Date Time and End Date Time shouldn't be equal";
            		endDateError = "Start Date Time and End Date Time shouldn't be equal";
            	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
            		document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
            	}
        		
        	}
        	
        	if(startDate == "") {
        		errorCount++;
        		startDateError = "Please fill up Start Date";
        	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
        	}
        	
        	if(endDate == "") {
        		errorCount++;
        		endDateError = "Please fill up End Date";
        	  	document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
        	}
        	
        	
        	if (startTime == "") {
        		errorCount++;
        		startTimeError = "Please fill up Start Time";
        	  	document.getElementById("startTime" + i + "_error").innerHTML = startTimeError;
        	}
        	
        	if (endTime == "") {
        		errorCount++;
        		endTimeError = "Please fill up End Time";
        	  	document.getElementById("endTime" + i + "_error").innerHTML = endTimeError;
        	}
        	
        	if((length >= 2) && i > 1) {
        		var startDateBefore = document.getElementById("startDate"+(i-1)).value;
                var endDateBefore =  document.getElementById("endDate"+(i-1)).value;
                var startTimeBefore =  document.getElementById("startTime"+(i-1)).value;
                var endTimeBefore =  document.getElementById("endTime"+(i-1)).value;
        		
                if((startDate == startDateBefore) && (endDate == endDateBefore) && 
                		(startTime == startTimeBefore) && (endTime == endTimeBefore)) {
                
                	errorCount++;
            		startDateError = "Start Date Time and End Date Time should be not the same.";
            		endDateError = "Start Date Time and End Date Time should be not the same.";
            	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
            		document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
            		document.getElementById("startDate" + (i-1) + "_error").innerHTML = startDateError;
            		document.getElementById("endDate" + (i-1) + "_error").innerHTML = endDateError;
                
                }
        	}
        	
	    }

		function removeWarning() {
			
			var fieldId = this.id;
			var data = document.getElementById(fieldId).value;
			
			
			if (data !== "") {
				document.getElementById(fieldId + "_error").innerHTML = "";
			}
		}
		
		document.getElementById("courseName").oninput = removeWarning;
		document.getElementById("instructorId").oninput = removeWarning;
		document.getElementById("venueId").oninput = removeWarning;
		document.getElementById("minRequired").oninput = removeWarning;
		document.getElementById("maxAllowed").oninput = removeWarning;
		
		//Remove warning for Date and Time
		document.getElementById("startDate" + i).oninput = removeWarning;
		document.getElementById("startTime" + i).oninput = removeWarning;
		document.getElementById("endDate" + i).oninput = removeWarning;
		document.getElementById("endTime" + i).oninput = removeWarning;
		
		setTimeout(function(){document.getElementById("courseName") = removeWarning;}, 5000);
		setTimeout(function(){document.getElementById("instructorId") = removeWarning;}, 5000);
		setTimeout(function(){document.getElementById("venueId") = removeWarning;}, 5000);
		setTimeout(function(){document.getElementById("minRequired") = removeWarning;}, 5000);
		setTimeout(function(){document.getElementById("maxAllowed") = removeWarning;}, 5000);
		
		setTimeout(function(){document.getElementById("startDate" + i) = removeWarning;}, 5000);
		setTimeout(function(){document.getElementById("startTime" + i) = removeWarning;}, 5000);
		setTimeout(function(){document.getElementById("endDate" + i) = removeWarning;}, 5000);
		setTimeout(function(){document.getElementById("endTime" + i) = removeWarning;}, 5000);
		
	    if((startDate == "") && (endDate == "") && (startTime == "") && (endTime == "")) {
	    	var endDateTime = null;
	    	var startDateTime = null;
	    } else {
	    	var startDateTime = rowStartDateTime.toISO().toString();
	        /* console.log(rowStartDateTime);
	        console.log(startDateTime); */
	    	var endDateTime = rowEndDateTime.toISO().toString();
		    /* console.log(rowEndDateTime);
		    console.log(endDateTime); */
	    }
	    
	    document.getElementById("startDateTime"+i).value = startDateTime;
		document.getElementById("endDateTime"+i).value = endDateTime;
	
    }
	if (errorCount <= 0) {

		$('#confirmModal').modal('show');
	}
 }