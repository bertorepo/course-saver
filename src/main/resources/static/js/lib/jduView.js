function showDeleteModal(id, name, timezone) {
	$('#jduName').text(name);
	$('#timezoneDelete').text(timezone);

	var form = document.getElementById("jduDeleteForm");
	form.addEventListener('submit', function() {
		this.action = "/jdu/" + id + "/delete";
	})

	$('#deletedDepartmentsList').empty();
	var hasDepartments = false;
	departmentList.forEach(function(dept) {
		if (dept.jduId == id) {
			hasDepartments = true;
			$('#deletedDepartmentsList').append("<li>" + dept.name + "</li>");
		}
	});

	if (!hasDepartments) {
		$('#deletedDepartmentsList').append("<li> None </li>");
	}

	$('#deleteInfoModal').modal('show');
}

function showUpdateModal(id, name, timezone) {
	$('#jduUpdateId').val(id);
	$('#jduUpdateName').val(name);
	$('#timezone').val(timezone);

	$('#updateModal').modal('show');
}

function validateUpdateInput() {
	if (hasInvalidInput || checkRequiredFields() || hasDuplicate() || hasChanged()) {
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

function resetUpdateForm() {
	$('#nameErrorMsg').text('');
	$("#jduUpdateForm").reset();
}

function refreshPage() {
	window.location = '/jdu/load';
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})

var hasInvalidInput = false;
var format = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>\/?~]/;

$('#jduUpdateName').on("input", function() {
	var jduNameValue = this.value.trim();
	$('#nameErrorMsg').text("");

	if (formatCheck.test(jduNameValue)) {
		$('#nameErrorMsg').text("JDU name contains invalid special characters.");
		hasInvalidInput = true;
	} else if (jduNameValue == "") {
		$('#nameErrorMsg').text("Please enter a JDU name.");
		hasInvalidInput = true;
	} else {
		$('#nameErrorMsg').text("");
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

