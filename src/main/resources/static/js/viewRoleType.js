function showUpdateModal(id, rolename, roledesc) {

	document.getElementById("nameErrorMsg").innerHTML = "";
	document.getElementById("descErrorMsg").innerHTML = "";
	
	if (id == localStorage.getItem("id")){
		document.getElementById("roleIdUpdate").value = localStorage.getItem("id");
		document.getElementById("roleNameUpdate").value = localStorage.getItem("rolename");
		document.getElementById("roleDescUpdate").value = localStorage.getItem("roledesc");
		
	} else {
		document.getElementById("roleIdUpdate").value = id;
		document.getElementById("roleNameUpdate").value = rolename;
		document.getElementById("roleDescUpdate").value = roledesc;
	}
	
	$('#updateModal').modal('show');
	validateIfEmpty();
}

function validateIfEmpty() {

	var id = document.getElementById("roleIdUpdate");
	var roleName = document.getElementById("roleNameUpdate");
	var roleDesc = document.getElementById("roleDescUpdate");
	var format = /[^a-zA-Z0-9 ]/g;
	
	document.getElementById("updateBtn").disabled = false;
	document.getElementById("nameErrorMsg").innerHTML = "";
	document.getElementById("descErrorMsg").innerHTML = "";
	
	// validate empty category
	if (roleName.value == "" ) {
		document.getElementById("nameErrorMsg").innerHTML = "*Required";
		document.getElementById("updateBtn").disabled = true;
	} 
	
	// validate empty detail
	if (roleDesc.value == "") {
		document.getElementById("descErrorMsg").innerHTML = "*Required";
		document.getElementById("updateBtn").disabled = true;
	}
	 
	// validate duplicate --to work--
	if (roleNameExist(roleName.value, id.value)) { 
		document.getElementById("nameErrorMsg").innerHTML = "*Role Name already exist";
		document.getElementById("updateBtn").disabled = true;
		//localStorage.setItem("id", id.value);
		//localStorage.setItem("category", category.value);
		//localStorage.setItem("detail", detail.value);
	} 
	
	// validation for special character 
	
	if (roleName.value.match(format)){
		document.getElementById("nameErrorMsg").innerHTML = "*Role Name is invalid. Please remove invalid characters";
		document.getElementById("updateBtn").disabled = true;
	}
	
	// validation for special character 
	if (format.test(roleDesc.value)) { 
		document.getElementById("descErrorMsg").innerHTML = "*Detail is invalid. Please remove invalid characters ";
		document.getElementById("updateBtn").disabled = true;
		//localStorage.setItem("id", id.value);
		//localStorage.setItem("category", category.value);
		//localStorage.setItem("detail", detail.value);
	} 
	
	// validate no change
	if (checkingForNoChange(roleName.value, id.value, roleDesc.value)) { 
		document.getElementById("updateBtn").disabled = true;
		localStorage.clear();
	} 
}

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

$('#updateButton').click(function() {
		$('#successModal').modal('show');
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