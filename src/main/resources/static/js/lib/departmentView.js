function showDeleteModal(id, name, jduName) {
	$('#departmentName').text(name);

	var form = document.getElementById("departmentDeleteForm");
	form.addEventListener('submit', function() {
		this.action = "/department/" + id + "/delete";
	})

	$('#deleteInfoModal').modal('show');
}

function showUpdateModal(id, jduId, name) {
	$('select#jduId').val(jduId);
	jduOriginal = jduId;

	$('#departmentUpdateId').val(id);
	$('#departmentUpdateName').val(name);
	deptNameOriginal = name;

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
	return $('select#jduId').val() == jduOriginal 
		&& $('#departmentUpdateName').val().trim() == deptNameOriginal;
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

function hasDuplicate(name) {
	return departmentList.some(function(department) {
		return department.name.toLowerCase() === name.toLowerCase();
	});
}

function resetUpdateForm() {
	deptNameOriginal = "";
	jduId = "";

	$('#nameErrorMsg').text('');
	$("#departmentUpdateForm").trigger("reset");
}

function refreshPage() {
	window.location = '/department/load';
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})

var hasInvalidInput = false;
var format = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>\/?~]/;
var deptNameOriginal;
var jduOriginal;

$('#departmentUpdateName').on("input", function() {
	var departmentNameValue = this.value.trim();
	$('#nameErrorMsg').text("");

	if (format.test(departmentNameValue)) {
		$('#nameErrorMsg').text("Department name contains invalid special characters.");
		hasInvalidInput = true;
	} else if(hasDuplicate(departmentNameValue) && deptNameOriginal != departmentNameValue) {
		$('#nameErrorMsg').text("Department already exists.");
		hasInvalidInput = true;
	} else {
		$('#nameErrorMsg').text("");
		hasInvalidInput = false;
	}

	validateInput();
})

$('#jduId').on("input", function() {
	var jduIdValue = this.value.trim();
	if (jduIdValue == "") {
		$('#jduNameErr').text("Please pick a JDU.");
		hasInvalidInput = true;
	} else {
		$('#jduNameErr').text("");
		hasInvalidInput = false;
	}

	validateInput();
})
