function validateInput() {
	if (hasInvalidInput || checkRequiredFields() || hasDuplicate()) {
		$('#submitButton').prop('disabled', true);
	} else {
		$('#submitButton').prop('disabled', false);
	}
}

function checkRequiredFields() {
	var missing = false;
	$('input,select').filter('[required]:visible').each(function() {
		if ($(this).val() == "") {
			console.log("return true");
			missing = true;
			return false;
		}
	});

	return missing;
}

function hasDuplicate() {
	var deptName = $('#departmentName').val().trim();
	var jduId = $('#jduId').val();
	return departmentList.some(function(department) {
		return department.name.toLowerCase() === deptName.toLowerCase() && department.jduId == jduId;
	});
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})

var hasInvalidInput = false;
var formatCheck = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>\/?~]/;

$('#departmentName').on("input", function() {
	var departmentNameValue = this.value.trim();
	$('#departmentNameError').text("");

	if (formatCheck.test(departmentNameValue)) {
		$('#departmentNameError').text("Department name contains invalid special characters.");
		hasInvalidInput = true;
	} else if (departmentNameValue == "") {
		$('#departmentNameError').text("Please enter a venue name.");
		hasInvalidInput = true;
	} else {
		$('#departmentNameError').text("");
		hasInvalidInput = false;
	}

	validateInput();
})

$('#jduId').on("input", function() {
	var jduValue = this.value.trim();
	if (jduValue == "") {
		$('#jduNameErr').text("Please enter a JDU name.");
		hasInvalidInput = true;
	} else {
		$('#jduNameErr').text("");
		hasInvalidInput = false;
	}

	validateInput();
})
