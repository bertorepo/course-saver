$(document).ready(function() {
	choices();
});

function choices() {
	var id = $('#courseId').val();
	if (id != "") {
		$("#emptyDiv").removeAttr('hidden');
	}
}

function successUrl() {
	var id = document.getElementById("courseId").value;
	var callUrl = '/attendance/schedules/' + id + '/participants';
	document.location.href = callUrl;
}

function ValidateDropDown(input) {
	var button = document.getElementById(".btn-success")
	if (input.value == '') {
		document.getElementById('button').className = "btn btn-large btn-success disabled";
		buttoninput.disabled = true;
	} else {
		document.getElementById('button').className = "btn btn-large btn-success";
		button.disabled = false;
	}
}
$(function() {
	var select = $('.courseSelect');
	select.html(select.find('option').sort(function(x, y) {
		return $(x).text() > $(y).text() ? 1 : -1;
	}));
});