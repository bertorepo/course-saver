function showUpdateModal(id, rolename, roledesc) {

	document.getElementById("nameErrorMsg").innerHTML = "";
	document.getElementById("descErrorMsg").innerHTML = "";
	
	if (id == localStorage.getItem("id")){
		document.getElementById("id").value = localStorage.getItem("id");
		document.getElementById("rolename").value = localStorage.getItem("rolename");
		document.getElementById("roledesc").value = localStorage.getItem("roledesc");
		
	} else {
		document.getElementById("id").value = id;
		document.getElementById("rolename").value = rolename;
		document.getElementById("roledesc").value = roledesc;
	}
	
	$('#updateModal').modal('show');
	validateIfEmpty();
}

function validateIfEmpty() {

	var id = document.getElementById("id");
	var roleName = document.getElementById("rolename");
	var roleDesc = document.getElementById("roledesc");
	var format = /[^a-zA-Z0-9 ]/g;
	
	document.getElementById("updateBtn").disabled = false;
	document.getElementById("nameErrorMsg").innerHTML = "";
	document.getElementById("descErrorMsg").innerHTML = "";
	
	// validate empty category
	if (roleName.value == "" ) {
		document.getElementById("nameErrorMsg").innerHTML = "Please enter a Role Name";
		document.getElementById("updateBtn").disabled = true;
	} 
	
	// validate empty detail
	if (roleDesc.value == "") {
		document.getElementById("descErrorMsg").innerHTML = "Please enter a Role Description";
		document.getElementById("updateBtn").disabled = true;
	}
	 
	// validate duplicate --to work--
	if (roleNameExist(roleName.value, id.value)) { 
		document.getElementById("nameErrorMsg").innerHTML = "Role Name already exist";
		document.getElementById("updateBtn").disabled = true;
	} 
	
	// validation for special character for Role Name
	if (roleName.value.match(format)){
		document.getElementById("nameErrorMsg").innerHTML = "Role Name is invalid, please omit special characters";
		document.getElementById("updateBtn").disabled = true;
	}
	
	// validate no change
	if (checkingForNoChange(roleName.value, id.value, roleDesc.value)) { 
		document.getElementById("updateBtn").disabled = true;
		localStorage.clear();
	} 
}

$(document).ready(function() {

	if (window.location.href.indexOf('#successModal') != -1) {
			$('#successyModal').modal('show');
			localStorage.clear();
	}
	
	$('#confirmUpdateBtn').click(function() {
		$("#roleForm").submit();
	});
});

function roleNameExist(rolename, id) {
  return roletypeList.some(function(role) {
    return role.rolename.toLowerCase() === rolename.toLowerCase() && role.id != id;
  }); 
}

function checkingForNoChange(roleName, id, roleDesc) {
	return roletypeList
		.some(function(role) {
			return role.rolename.toLowerCase() === roleName.toLowerCase()
					&& role.id == id
					&& role.roledesc.toLowerCase() === roleDesc.toLowerCase();
		});
}

function deleteButton(id, roleName, roleDesc) {
	document.getElementById("roleName").innerHTML = roleName;
	document.getElementById("roleDesc").innerHTML = roleDesc;
	
	var form  = document.getElementById("infoModalFormId");
	form.addEventListener('submit', function(){
		this.action = "/roletype/" + id + "/delete";
	});

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

$(function() {
  $("[autofocus]").on("focus", function() {
    if (this.setSelectionRange) {
      var len = this.value.length * 2;
      this.setSelectionRange(len, len);
    } else {
      this.value = this.value;
    }
    this.scrollTop = 999999;
  }).focus();
});