$(document).ready(function() {
	choices();
});

function choices() {
   var id = $('#selectedCourse').val();
   if (id != "") {
		$("#emptyDiv").removeAttr('hidden');
   }
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
function successUrl() {
	var id = document.getElementById("selectedCourse").value;
	var callUrl = '/attendance/generate/' + id + '/absent';
	document.location.href = callUrl;
}
$(function() {
	var select = $('.selectedCourse');
	select.html(select.find('option').sort(function(x, y) {
		return $(x).text() > $(y).text() ? 1 : -1;
	}));
});
