

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

function courseNameOnDown(id){
	var holder = document.getElementById(id).value;
	var faceVal = document.getElementById(id).placeholder;
	
	if(holder == "" && faceVal !== ""){
		document.getElementById(id).placeholder = faceVal;
		document.getElementById(id).value = '';
		
		var courseId = $('#courses [value="'+ holder +'"]').data('value');
		
		document.getElementById("courseId").value = courseId;
	} else {
		document.getElementById(id).placeholder = holder;
		document.getElementById(id).value = '';
		
		var courseId = $('#courses [value="'+ holder +'"]').data('value');
		
		document.getElementById("courseId").value = courseId;
	}
}

function courseNameOnChange(){
	
	var courseName = $("#courseName").val();
	var holder = document.getElementById("courseName").placeholder;
	
	if(courseName == "" && (holder !== "Please select..." || holder !== "")){
		document.getElementById("courseName").value = holder;
		
		var courseId = $('#courses [value="'+ holder +'"]').data('value');
		
		 document.getElementById("courseId").value = courseId;
	} else {
		var courseId = $('#courses [value="'+ courseName +'"]').data('value');
         if(courseId == undefined){
		    document.getElementById("courseId").innherHtml = "";
		    var courseNameNotFound = "Course name not found. Please enter a valid selection."
		    document.getElementById("courseName_error").innerHTML = courseNameNotFound;
		    $("#courseName").val("Please select...");
		  } else {				
		    document.getElementById("courseId").value = courseId;
		    document.getElementById("courseName_error").innerHTML = "";
		}
	}
}

//Add New Rows
function addNewRow() {
	var Url = '/schedules/new/addDate/';
	document.getElementById("scheduleNew").action = Url;
}

function removeNewRow(id) {
	var courseScheduleDetails = document.getElementById(id);
	var removeRowVal = courseScheduleDetails.value;
	var Url = '/schedules/new/removeDate/'+removeRowVal;
	document.getElementById("scheduleNew").action = Url;
}

function validations() {
	var errorCount = 0;
	var courseName = $('#courseName').val();
	var venue = $('#venueId').val();
	var courseId = $('#courseId').val();
	var instructorId = $('#instructorId').val();
	var minRequired = parseInt($('#minRequired').val().replace("", "0"));
	var maxAllowed = parseInt($('#maxAllowed').val().replace("", "0"));
	
	
	//Empty Fields Validations
	if (courseName == "") {
		errorCount++;
		courseNameError = "This field is required."; 
        document.getElementById("courseName_error").innerHTML = courseNameError;
	}
	
	if ((courseId == "undefined") || (courseId == null) || (courseId == "")) {
		errorCount++;
		courseNameError = 'No course found with the name, "'+courseName+'".  Please select one from the list.'; 
        document.getElementById("courseName_error").innerHTML = courseNameError;
	}
	
	if (instructorId == "") {
		errorCount++;
		instructorError = "This field is required.";
        document.getElementById("instructorId_error").innerHTML = instructorError;
	}
	if (venue == "") {
		errorCount++;
		venueError = "This field is required.";
        document.getElementById("venueId_error").innerHTML = venueError;
	}
	if (minRequired <= 0) {
		errorCount++;
	  	minRequiredError = "This field is required.";
	  	 document.getElementById("minRequired_error").innerHTML = minRequiredError;
	}
	if (maxAllowed <=0) {
		errorCount++;
	  	maxAllowedError = "This field is required.";
	  	 document.getElementById("maxAllowed_error").innerHTML = maxAllowedError;
	}
	if (minRequired !==0) {
		if (maxAllowed <= minRequired) {
			errorCount++;
	  	minRequiredError = "Min shouldn't be greater than Max.";
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
		startDateError = "This field is required.";
	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
	}
	if (startTimeCount <= 0) {
		errorCount++;
		startTimeError = "This field is required.";
	  	document.getElementById("startTime" + i + "_error").innerHTML = startTimeError;
	}

	if (endDateCount <= 0) {
		errorCount++;
		endDateError = "This field is required.";
	  	document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
	}
	if (endTimeCount <= 0) {
		errorCount++;
		endTimeError = "This field is required.";
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
        		endDateError = "Please pick a date that's greater than <br>the value of 'Start Date' field.";
        	  	document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
            }
        	
        	if(startDate == endDate) {
        		//Start and End Date Validation
            	if (startTime > endTime) {
            		errorCount++;
            		startDateError = "Start time can't be greater than or equal to end time";
            	  	document.getElementById("startTime" + i + "_error").innerHTML = startDateError;
                }
        	}
        	
        	//If Date is Less Than Today
        	if (isLessThanToday(rowStartDateTime)) {
        		errorCount++;
        		startDateError = "Please pick a future date and time.";
        	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
        	}
        	
        	if (isLessThanToday(rowEndDateTime)) {
        		errorCount++;
        		endDateError = "Please pick a future date and time.";
        	  	document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
        	}
        	if((startDate !== "") && (endDate !== "")) {
        		//If Datetime is equal Validation
            	if ((startDate == endDate) && (startTime == endTime)) {
            		errorCount++;
            		startDateError = "";
            		endDateError = "Please pick a date where 'Start Date' & 'End <br>Date' field values should not <br>be equal.";
            	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
            		document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
            	}
        		
        	}
        	
        	if(startDate == "") {
        		errorCount++;
        		startDateError = "This field is required.";
        	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
        	}
        	
        	if(endDate == "") {
        		errorCount++;
        		endDateError = "This field is required.";
        	  	document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
        	}
        	
        	
        	if (startTime == "") {
        		errorCount++;
        		startTimeError = "This field is required.";
        	  	document.getElementById("startTime" + i + "_error").innerHTML = startTimeError;
        	}
        	
        	if (endTime == "") {
        		errorCount++;
        		endTimeError = "This field is required.";
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
            		startDateError = "";
            		endDateError = "Please pick a date where 'Start Date' & 'End <br>Date' field values should not be equal.";
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
		
		var errs = document.querySelectorAll(".er");
		
		setTimeout(function(){
			for (i = 0; i < errs.length; i++) {
				
				errs[i].innerHTML = " ";	
			}
			}, 10000);
		
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
	    	
	    	document.getElementById("startDateTime"+i).value = startDateTime;
			document.getElementById("endDateTime"+i).value = endDateTime;
	    }  
	
    }
    
	if (errorCount <= 0) {

		$('#confirmModal').modal('show');
	}
 }