function showDeleteModal(id, name) {
	document.getElementById("venueName").innerHTML = name;

	var form = document.getElementById("venueDeleteForm");
	form.addEventListener('submit', function() {
		this.action = "/venue/" + id + "/delete";
	})

	$('#deleteInfoModal').modal('show');
}

function showUpdateModal(id, name) {
	document.getElementById("venueName").innerHTML = name;

	$('#venueUpdateId').val(id);
	$('#venueUpdateName').val(name);

	$('#updateModal').modal('show');
}

function validateUpdateInput() {
	var hasInvalidInput = false;
	var venueNameValue =  $('#venueUpdateName').val().trim();
	var format = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>\/?~]/;

	$('#nameErrorMsg').text('');

	if (venueNameValue == "") {
		$('#nameErrorMsg').text("Please enter a venue name.");
		hasInvalidInput = true;
	}

	if (format.test(venueNameValue))
	{
		$('#nameErrorMsg').text("Venue name contains invalid special characters.");
		hasInvalidInput = true;
	}

	if (checkForDuplicate(venueNameValue)) { //Can be used for checking for no change for now since only one item
		$('#nameErrorMsg').text("Venue name already exists!");
		hasInvalidInput = true;
	}

	if (hasInvalidInput) {
		$('#updateButton').prop('disabled', true);
	} else {
		$('#updateButton').prop('disabled', false);
	}
}

function checkForDuplicate(name) {
	return venueList.some(function(venue) {
		return venue.name.toLowerCase() === name.toLowerCase();
	});
}

function resetUpdateForm() {
	$('#nameErrorMsg').text('');
	$("#venueUpdateForm").reset();
}

function refreshPage() {
	window.location = '/venue/load';
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})
