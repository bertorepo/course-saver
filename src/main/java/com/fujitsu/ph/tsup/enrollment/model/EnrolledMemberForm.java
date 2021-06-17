package com.fujitsu.ph.tsup.enrollment.model;

//==================================================================================================
//Project Name :Training Sign Up
//System Name  :Enrollment Course
//Class Name   :EnrolledMemberForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+-----------------------------------------------------
//0.01    | --/--/---- | WS) -                 | New Creation
//0.02    | 06/14/2021 | WS) L.Celoso          | Update
//==================================================================================================

public class EnrolledMemberForm {

	private Long courseId;
	
	private String batchId;
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
}
