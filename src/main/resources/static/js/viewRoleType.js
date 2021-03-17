function deleteButton(id, roleName, roleDesc) {
	document.getElementById("roleIdInput").value = id;
	document.getElementById("roleName").innerHTML = roleName;
	document.getElementById("roleDesc").innerHTML = roleDesc;
	$('#infoModal').modal('show');
}

$(document).ready(function() {
	if (window.location.href.indexOf('#confirmModal') != -1) {
		$('#confirmModal').modal('show');
	}
});

$(document).ready(function() {
	if (window.location.href.indexOf('#successModal') != -1) {
		$('#successModal').modal('show');
	}
});