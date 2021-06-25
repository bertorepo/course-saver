function validateInput() {
	var hasInvalidInput = false;
	var departmentNameValue =  $('#departmentName').val().trim();
	var jduIdValue =  $('#jdu').val().trim();
	var format = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>\/?~]/;

	$('#departmentNameError').text('');

	if (departmentNameValue == "") {
		$('#departmentNameError').text("Please enter a venue name.");
		hasInvalidInput = true;
	}
	
	if (jduIdValue == "") {
		$('#jduNameErr').text("Please enter a JDU name.");
		hasInvalidInput = true;
	}

	if (format.test(departmentNameValue))
	{
		$('#departmentNameError').text("Department name contains invalid special characters.");
		hasInvalidInput = true;
	}

	if (checkForDuplicate(departmentNameValue)) { //Can be used for checking for no change for now since only one item
		$('#departmentNameError').text("Department name already exists!");
		hasInvalidInput = true;
	}

	if (hasInvalidInput) {
		$('#submitButton').prop('disabled', true);
	} else {
		$('#submitButton').prop('disabled', false);
	}
}

function checkForDuplicate(name) {
	return departmentList.some(function(department) {
		return department.name.toLowerCase() === name.toLowerCase() && department.jduId;
	});
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})
