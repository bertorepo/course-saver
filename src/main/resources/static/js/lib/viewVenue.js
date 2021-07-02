function showDeleteModal(id, name) {
	document.getElementById("venueName").innerHTML = name;

	var form = document.getElementById("venueDeleteForm");
	form.addEventListener('submit', function() {
		this.action = "/venue/" + id + "/delete";
	})

	$('#deleteInfoModal').modal('show');
}

function showUpdateModal(id, name, overlap) {
	$('#venueUpdateId').val(id);
	$('#venueUpdateName').val(name);
	venueNameOriginal = name;
	
	overlap == "true" ? $('#venueOverlap').prop('checked', true) : $('#venueOverlap').prop('checked', false);
	overlapOriginal = overlap;

	$('#updateModal').modal('show');
}

function validateInput() {
	if (hasInvalidInput || isFormModified()) {
		$('#updateButton').prop('disabled', true);
	} else {
		$('#updateButton').prop('disabled', false);
	}
}

function isFormModified() {
	return $('#venueOverlap').is(":checked").toString() == overlapOriginal 
		&& $('#venueUpdateName').val().trim() == venueNameOriginal;
}


function hasDuplicate(name) {
	return venueList.some(function(venue) {
		return venue.name.toLowerCase() === name.toLowerCase();
	});
}

function resetUpdateForm() {
	venueNameOriginal = "";
	$('#nameErrorMsg').text('');
	$("#venueUpdateForm").trigger("reset");
}

function refreshPage() {
	window.location = '/venue/load';
}

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
})

var hasInvalidInput = false;
var format = /[`!@#$%^&*+\=\[\]{};':"\\|,.<>?~]/;
var venueNameOriginal;
var overlapOriginal;

$('#venueUpdateName').on("input", function() {
	var venueNameValue = this.value.trim();
	$('#nameErrorMsg').text('');

	if (format.test(venueNameValue)) {
		$('#nameErrorMsg').text("Venue name contains invalid special characters.");
		hasInvalidInput = true;
	} else if (hasDuplicate(venueNameValue) && venueNameOriginal != venueNameValue) { 
		$('#nameErrorMsg').text("Venue name already exists!");
		hasInvalidInput = true;
	} else {
		$('#nameErrorMsg').text('');
		hasInvalidInput = false;
	}
	
	validateInput();
})

$('#venueOverlap').on("input", function() {
	validateInput();
})

