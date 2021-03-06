function validations() {
		
		var errorCount = 0;
		var instructor = $('#instructorId').val();
		var venue = $('#venueId').val();
		var minRequired = parseInt($('#minRequired').val().replace("", "0"));
		var maxAllowed = parseInt($('#maxAllowed').val().replace("", "0"));
		var courseScheduleDetails = document.getElementById("updateCourseScheduleDetails");
		var length = courseScheduleDetails.rows.length;
		
		//Empty Fields Validations
		if (instructor == "") {
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
		if (minRequired !==0) {
		  if(!maxAllowed == undefined || !maxAllowed == null|| !maxAllowed == ""){
		   if (maxAllowed <= minRequired) {
			  errorCount++;
	  	      minRequiredError = "Min shouldn't be greater than Max.";
	  	      document.getElementById("minRequired_error").innerHTML = minRequiredError;
		  }
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
	
		    	//Start and End Time Validation
	        	if (startDate > endDate)  {
	        		errorCount++;
	        		endDateError = "Please pick a date that's greater than <br>the value of 'Start Date' field.";
	        	  	document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
	            } 
	        	
	        	//Start and End Date Validation
	        	if (startDate == endDate) {
	        		if (startTime > endTime) {
		        		errorCount++;
		        		endStartTimeError = "Please pick a time that's greater than <br>the value of 'Start Time' field.";
		        	  	document.getElementById("startTime" + i + "_error").innerHTML = endStartTimeError;
		        	  	document.getElementById("endTime" + i + "_error").innerHTML = endStartTimeError;
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
	        	
	        	//If Datetime is equal Validation
	        	if ((startDate == endDate) && (startTime == endTime)) {
	        		errorCount++;
	        		startDateError = "";
	        		endDateError = "Please pick a date where 'Start Date' <br>& 'End Date' field values should not <br>be equal.";
	        	  	document.getElementById("startDate" + i + "_error").innerHTML = startDateError;
	        		document.getElementById("endDate" + i + "_error").innerHTML = endDateError;
	        	}
	        	
	        	if((startDate == "") && (endDate == "") && (startTime == "") && (endTime == "")) {
	        		errorCount++;
	        		document.getElementById("startDate" + i + "_error").innerHTML = "This field is required.";
	        		document.getElementById("endDate" + i + "_error").innerHTML = "This field is required.";
	        		document.getElementById("startTime" + i + "_error").innerHTML = "This field is required.";
	        	  	document.getElementById("endTime" + i + "_error").innerHTML = "This field is required.";
	    	    } else {
	    	    	var startDateTime = rowStartDateTime.toISO().toString();
	    	        /* console.log(rowStartDateTime);
	    	        console.log(startDateTime); */
	    	    	var endDateTime = rowEndDateTime.toISO().toString();
	    		    /* console.log(rowEndDateTime);
	    		    console.log(endDateTime); */
	    	    }
	        	
	        	document.getElementById("startDate" + i).oninput = removeWarning;
	    		document.getElementById("startTime" + i).oninput = removeWarning;
	    		document.getElementById("endDate" + i).oninput = removeWarning;
	    		document.getElementById("endTime" + i).oninput = removeWarning;   		
	    		
	    		document.getElementById("instructorId").oninput = removeWarning;
				document.getElementById("venueId").oninput = removeWarning;
				document.getElementById("minRequired").oninput = removeWarning;
				document.getElementById("maxAllowed").oninput = removeWarning;
				
				var errs = document.querySelectorAll(".er");
				
				setTimeout(function(){
					for (i = 0; i < errs.length; i++) {
						
						errs[i].innerHTML = " ";	
					}
					}, 10000);
				
				document.getElementById("startDateTime"+i).value = startDateTime;
	    		document.getElementById("endDateTime"+i).value = endDateTime; 
	    }
	
		if (errorCount <= 0) {
			$('#updateModal').modal('hide');
			$('#updateConfirmationModal').modal('show');
		}
			
		  $('#updateModal').on('hide.bs.modal', function(e) {
			  if(errorCount > 0) {
				  e.preventDefault();
			  }
			  errorCount = 0;
		    
		   }); 
  
		   //error message remove warning
		   function removeWarning() {
				
				var fieldId = this.id;
				var data = document.getElementById(fieldId).value;
				
				
				if (data !== "") {
					document.getElementById(fieldId + "_error").innerHTML = "";
				}
			}	
	}
	
	function submitUpdate() {
		var updateCourseScheduleId = $("#updateCourseScheduleId").val();
		var Url = "/schedules/courseSchedule/"+ updateCourseScheduleId +"/update";
		document.getElementById("updateView").action = Url;	
	}
	
	$('input[name="delete"]').on('click', function() {
		$('#deleteConfirmationModal').modal('show');
		$('#deleteModal').modal('hide');
	});
	$('input[name="deleteConfirmation"]').on('click', function() {
		$('#deleteSuccessModal').modal('show');
	});
	//Message alert
	//add if statement for the success/fail messages
	$('input[name="error1"]').on('click', function() {
		$('#error1Modal').modal('show');
	});
	$('input[name="error2"]').on('click', function() {
		$('#error2Modal').modal('show');
	});
	//Message alert End
	
//Update
	
	/* function submitDelete(id){
		
		var deleteValue = document.getElementById(id).value;
		var fromDate = document.getElementById("fromDateTime").value;
		var toDate = document.getElementById("toDateTime").value;
		var Url = "/schedules/courseSchedule/"+ deleteValue +"/scheduleDelete";
		
		var deleteForm = {
				"courseId" : document.getElementById("courseId").value,
				"courseName" : document.getElementById("courseName").value,
				"instructorName" : document.getElementById("instructorName").value,
				"courseScheduleDetailList" : {
					"scheduledStartDateTime" : document.getElementById("startDateTime1"),
					"scheduledEndDateTime" : document.getElementById("endDateTime1")
				},
				"venueName" : document.getElementById("venueName"),
				"courseDetails" : document.getElementById("courseDetails")
		};
		
		$.ajax({
			type: "DELETE",
			url: Url,
			data: JSON.stringify(deleteForm),
			contentType: "application/json",
			dataType: "text",
			success: function (result) {
				document.location.href = "/schedules/courseSchedules/view"
				document.getElementById("fromDateTime").value = fromDate;
				document.getElementById("toDateTime").value = toDate;
			},
			error: function (xhr, error) {
				console.log(xhr.status);
				console.log(error);
			}
			
		});
	}; */