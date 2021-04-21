function load() {
	$("#viewButton").removeAttr('disabled');
	document.getElementById("reportTable").style.display = "block";
	document.getElementById("summaryMainDiv").style.display = "none";
}

function viewButton() {
	$("#viewButton").attr('disabled', 'disabled');
	$("#exportButton").removeAttr('disabled');
	var selectedReportType = document.getElementById("selectReportType").value;
	
	if(selectedReportType == 1){
		window.location.href = "/report/course/";
	} else if(selectedReportType == 2){
		document.getElementById("reportTable").style.display = "none";
		document.getElementById("summaryMainDiv").style.display = "block";
	} else{		
	
	}
}

Date.prototype.YYYYMMDDHHMMSS = function () {
	var yyyy = this.getFullYear().toString();
	var MM = pad(this.getMonth() + 1,2);
	var dd = pad(this.getDate(), 2);
	var hh = pad(this.getHours(), 2);
	var mm = pad(this.getMinutes(), 2)
	var ss = pad(this.getSeconds(), 2)
	return yyyy + MM + dd+  hh + mm + ss;
};

function fileNameCreator(){
	var selectedReportType = document.getElementById("selectReportType").value;
	var strFileName = "Summary of "; 
	var dt = new Date();
	if (selectedReportType == 2){
		strFileName += "G3CC Standardization Training for Dev - ";
	} else if (selectedReportType == 3){
		strFileName += "G3CC Standardization Training for PM - ";
	} else if (selectedReportType == 4){
		strFileName += "Mandatory Courses - ";
	}
	strFileName +=  dt.YYYYMMDDHHMMSS() + ".csv";
	exportTableToCSV(strFileName)
}

function pad(number, length) {
	var str = '' + number;
	while (str.length < length) {
		str = '0' + str;
	}
	return str;
}

function exportTableToCSV(filename) {
	var csv = [];
	var rows = document.querySelectorAll("#exportSummaryTable tr");
	for (var i = 0; i < rows.length; i++) {
	// Don't add the row to the csv if it's hidden due to filtering.
		if (rows[i].style.display === "none"){
			continue;
		}
		var row = [];
		var cols = rows[i].querySelectorAll("td, th");
		for (var j = 0; j < cols.length; j++){
			row.push(cols[j].innerText.replace(/(\r\n|\n|\r)/gm, ""));
		}
		csv.push(row.join(","));
	}
	downloadCSV(csv.join("\n"), filename);
}
		
function downloadCSV(csv, filename) {
	if ($('#selectReportType').val() == 0) {
		document.getElementById("message").innerHTML = ""// enter
														// error
														// message";
		$('#errorModal').modal('show');
	} else {
		var csvFile;
		var downloadLink;
		alert("Report exported successfully");
		csvFile = new Blob([ csv ], {
			type : "text/csv;charset=utf-8"
		});
		downloadLink = document.createElement("a");
		downloadLink.download = filename;
		downloadLink.href = window.URL.createObjectURL(csvFile);
		downloadLink.style.display = "none";
		downloadLink.click();
	}
}