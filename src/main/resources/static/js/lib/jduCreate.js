function validateInput() {
	if (hasInvalidInput || checkRequiredFields() || hasDuplicate()) {
		$('#submitButton').prop('disabled', true);
	} else {
		$('#submitButton').prop('disabled', false);
	}
}

function hasDuplicate() {
	var jduName = $('#jduName').val().trim();
	var timezone = $('#timezone').val();
	return jduList.some(function(jdu) {
		if (jdu.jduName.toLowerCase() == jduName.toLowerCase() 
				&& jdu.timezone.toLowerCase() == timezone.toLowerCase()) {
			$('#jduNameError').text("JDU with same timezone already exists.");
			return true;
		}
	});
}

function checkRequiredFields() {
	var missing = false;
	$('input,select').filter('[required]:visible').each(function() {
		if ($(this).val() == "") {
			missing = true;
			return false;
		}
	});

	return missing;
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})

var hasInvalidInput = false;
var formatCheck = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>\/?~]/;

$('#jduName').on("input", function() {
	var jduNameValue = this.value.trim();
	$('#jduNameError').text("");

	if (formatCheck.test(jduNameValue)) {
		$('#jduNameError').text("JDU name contains invalid special characters.");
		hasInvalidInput = true;
	} else if (jduNameValue == "") {
		$('#jduNameError').text("Please enter a JDU name.");
		hasInvalidInput = true;
	} else {
		$('#jduNameError').text("");
		hasInvalidInput = false;
	}

	validateInput();
})

$('#timezone').on("input", function() {
	var timezoneValue = this.value.trim();
	if (timezoneValue == "") {
		$('#timezoneErr').text("Please pick a Timezone.");
		hasInvalidInput = true;
	} else {
		$('#timezoneErr').text("");
		hasInvalidInput = false;
	}

	validateInput();
})


