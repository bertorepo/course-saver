// <!--Message alert--> 
//add if statement for the success/fail messages
/* $('input[name="submit"]').on('click', function() {
	$('#successModal').modal('show');
}); */
/* <!--Message alert End--> */


function statusSelect(index) {
	var selected = document.getElementById("status" + index).value;
	document.getElementById("courseSchedules"+index+".status").value = selected;
}

function changeUrl() {
    var id = document.getElementById("courses").value;
    if (id != "") {
        var callUrl = '/schedules/courseSchedule/' + id + '/changeStatus';
        document.location.href = callUrl;
    }
}
/* 
$(function() {
    var select = $('.courseSelect');
    select.html(select.find('option').sort(function(x, y) {
        return $(x).text() > $(y).text() ? 1 : -1;
    }));
}); */

function validateDropDown(input) {
    var button = document.getElementById("button");
    if (input.value == '') {
        document.getElementById('button').className = "btn btn-large btn-success disabled";
        button.disabled = true;
    } else {
        document.getElementById('button').className = "btn btn-large btn-success";
        button.disabled = false;
    }
}