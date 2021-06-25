function showDeleteModal(id, name, jduName) {
	document.getElementById("departmentName").innerHTML = name;

	var form = document.getElementById("departmentDeleteForm");
	form.addEventListener('submit', function() {
		this.action = "/department/" + id + "/delete";
	})

	$('#deleteInfoModal').modal('show');
}

function showUpdateModal(id, jduId, name, jduName) {
	document.getElementById("departmentName").innerHTML = name;

	$('#departmentUpdateId').val(id);
	$('#departmentUpdateName').val(name);

	$('#updateModal').modal('show');
}

function validateUpdateInput() {
	var hasInvalidInput = false;
	var departmentNameValue =  $('#departmentUpdateName').val().trim();
	var format = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>\/?~]/;

	$('#nameErrorMsg').text('');

	if (departmentNameValue == "") {
		$('#nameErrorMsg').text("Please enter a department name.");
		hasInvalidInput = true;
	}

	if (format.test(departmentNameValue))
	{
		$('#nameErrorMsg').text("Department name contains invalid special characters.");
		hasInvalidInput = true;
	}

	if (checkForDuplicate(departmentNameValue)) { //Can be used for checking for no change for now since only one item
		$('#nameErrorMsg').text("Department name already exists!");
		hasInvalidInput = true;
	}

	if (hasInvalidInput) {
		$('#updateButton').prop('disabled', true);
	} else {
		$('#updateButton').prop('disabled', false);
	}
}

function checkForDuplicate(name) {
	return departmentList.some(function(department) {
		return department.name.toLowerCase() === name.toLowerCase();
	});
}

function resetUpdateForm() {
	$('#nameErrorMsg').text('');
	$("#departmentUpdateForm").reset();
}

function refreshPage() {
	window.location = '/venue/load';
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})
