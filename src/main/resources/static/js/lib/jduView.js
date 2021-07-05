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
	jduNameOriginal = name;

	$('#timezone').val(timezone);
	timezoneOriginal = timezone;

	$('#updateModal').modal('show');
}

function validateInput() {
	if (hasInvalidInput || checkRequiredFields() || isFormModified()) {
		$('#updateButton').prop('disabled', true);
	} else {
		$('#updateButton').prop('disabled', false);
	}
}

function isFormModified() {
	return $('#jduUpdateName').val() == jduNameOriginal
		&& $('#timezone').val() == timezoneOriginal;
}


function hasDuplicate(jduName) {
	return jduList.some(function(jdu) {
		return jdu.jduName.toLowerCase() == jduName.toLowerCase() 
			&& jduNameOriginal != jduName;
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
	jduNameOriginal = "";
	timezoneOriginal = "";
	$('#nameErrorMsg').text('');
	$("#jduUpdateForm").trigger("reset");
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
var jduNameOriginal;
var timezoneOriginal

$('#jduUpdateName').on("input", function() {
	var jduNameValue = this.value;
	$('#nameErrorMsg').text("");

	if (format.test(jduNameValue)) {
		$('#nameErrorMsg').text("JDU name contains invalid special characters.");
		hasInvalidInput = true;
	} else if (hasDuplicate(jduNameValue)) {
		$('#nameErrorMsg').text("JDU already exists.");
		hasInvalidInput = true;
	} else if (jduNameValue == "") {
		$('#nameErrorMsg').text("Please enter a JDU name.");
		hasInvalidInput = false;
	} else {
		$('#nameErrorMsg').text("");
		hasInvalidInput = false;
	}

	validateInput();
})

$('#timezone').on("input", function() {
	var timezoneValue = this.value.trim();
	if (timezoneValue == "") {
		$('#timezoneErr').text("Please pick a timezone.");
		hasInvalidInput = true;
	} else {
		$('#timezoneErr').text("");
		hasInvalidInput = false;
	}

	validateInput();
})

