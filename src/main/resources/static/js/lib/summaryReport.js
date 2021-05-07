function load() {
    var selectedReportType = document.getElementById("selectReportType").value;
    $("#viewButton").removeAttr('disabled');

    if (selectedReportType == 2) {
        document.getElementById("reportTable").style.display = "block";
        document.getElementById("summaryMainDiv").style.display = "none";
    } else if (selectedReportType == 3) {
        var fromDateTime = $("#scheduledStartDateTime").val();
        var toDateTime = $("#scheduledEndDateTime").val();
        var fromNewDate = fromDateTime.slice(0, 16);
        var toNewDate = toDateTime.slice(0, 16);
        var selectedReportType = document.getElementById("selectReportType").value;

        document.getElementById("startDate").value = fromNewDate;
        document.getElementById("endDate").value = toNewDate;

        const m = moment();
        startDate.max = m.format("YYYY-MM-DDTHH:mm");
        startDate.min = m.format("2017-01-01T00:00");

        endDate.max = m.format("YYYY-MM-DDTHH:mm");
        endDate.min = m.format("2017-01-01T00:00");

        /**
        Format Date Inputs
        */

        Date.prototype.toISO = function() {
            return this.getUTCFullYear() +
                '-' + String(this.getUTCMonth() + 1).padStart(2, "0") +
                '-' + String(this.getUTCDate()).padStart(2, "0") +
                'T' + String(this.getUTCHours()).padStart(2, "0") +
                ':' + String(this.getUTCMinutes()).padStart(2, "0") +
                ':' + String(this.getUTCSeconds()).padStart(2, "0") +
                '.' + (this.getUTCMilliseconds() / 1000).toFixed(3).slice(2, 5) +
                'Z';
        };
        $("#startDate").blur(function() {
            var formDate = document.getElementById("startDate").value;
            var newDate = new Date(formDate);
            var dateConcat = newDate.toISO().toString();
            if (formDate == "") {
                $("#scheduledStartDateTime").prop('value', null);
            } else {
                $("#scheduledStartDateTime").prop('value', dateConcat);
            }
        });
        $("#endDate").blur(function() {
            var formDate = document.getElementById("endDate").value;
            var newDate = new Date(formDate);
            var dateConcat = newDate.toISO().toString();
            if (formDate == "") {
                $("#scheduledEndDateTime").prop('value', null);
            } else {
                $("#scheduledEndDateTime").prop('value', dateConcat);
            }
        });
    } else {}
}

function selectReport() {
    var selectedReportType = document.getElementById("selectReportType").value;

    if (selectedReportType == 1) {
        window.location.href = "/report/course/";
    } else if (selectedReportType == 2) {
        $("#viewButton").removeAttr('disabled');
        $("#exportButton").attr('disabled', 'disabled');
        document.getElementById("reportTable").style.display = "none";
        document.getElementById("summaryMainDiv").style.display = "block";
    } else if (selectedReportType == 3) {
        window.location.href = "/report/summary/standardization/pm/";
    } else {

    }
}

function viewButton() {
    $("#viewButton").attr('disabled', 'disabled');
    $("#exportButton").removeAttr('disabled');
    var selectedReportType = document.getElementById("selectReportType").value;

    if (selectedReportType == 1) {
        window.location.href = "/report/course/";
    } else if (selectedReportType == 2) {
        document.getElementById("reportTable").style.display = "none";
        document.getElementById("summaryMainDiv").style.display = "block";
    } else if (selectedReportType == 3) {
        window.location.href = "/report/summary/standardization/pm/";
    } else {

    }
}
Date.prototype.YYYYMMDDHHMMSS = function() {
    var yyyy = this.getFullYear().toString();
    var MM = pad(this.getMonth() + 1, 2);
    var dd = pad(this.getDate(), 2);
    var hh = pad(this.getHours(), 2);
    var mm = pad(this.getMinutes(), 2)
    var ss = pad(this.getSeconds(), 2)
    return yyyy + MM + dd + hh + mm + ss;
};

function fileNameCreator() {
    var selectedReportType = document.getElementById("selectReportType").value;
    var strFileName = "Summary of ";
    var dt = new Date();
    if (selectedReportType == 2) {
        strFileName += "JDU Standardization Training for Dev - ";
    } else if (selectedReportType == 3) {
        strFileName += "JDU Standardization Training for PM - ";
    } else if (selectedReportType == 4) {
        strFileName += "Mandatory Courses - ";
    }
    strFileName += dt.YYYYMMDDHHMMSS() + ".csv";
    exportTableToCSV(strFileName);
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
        if (rows[i].style.display === "none") {
            continue;
        }
        var row = [];
        var cols = rows[i].querySelectorAll("td, th");
        for (var j = 0; j < cols.length; j++) {
            row.push(cols[j].innerText.replace(/(\r\n|\n|\r)/gm, ""));
        }
        csv.push(row.join(","));
    }
    downloadCSV(csv.join("\n"), filename);
}

function downloadCSV(csv, filename) {
    if ($('#selectReportType').val() == 0) {
        document.getElementById("message").innerHTML = "" // enter error message";
        $('#errorModal').modal('show');
    } else {
        var csvFile;
        var downloadLink;
        csvFile = new Blob([csv], {
            type: "text/csv;charset=utf-8"
        });
        downloadLink = document.createElement("a");
        downloadLink.download = filename;
        downloadLink.href = window.URL.createObjectURL(csvFile);
        downloadLink.style.display = "none";
        downloadLink.click();
        setTimeout(function() {
            alert("Report exported successfully");
        }, 1500);
    }
}

var dateErrorMessage = "To Date should be greater than to From Date";

function viewButtonClick() {

    if ($("#startDate").val() >= $("#endDate").val()) {
        document.getElementById("message").innerHTML = dateErrorMessage;
        $('#errorModal').modal('show');
    } else {
        document.getElementById("summaryGstForPMForm").submit();
    }
}