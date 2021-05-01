package com.fujitsu.ph.tsup.enrollment.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Lob;

public class CertificateForm {
	
	private String certificate;
	private Long id;
	private Long courseId;
	private ZonedDateTime uploadDate;
	private String fileDownloadUri;

	public String getFileDownloadUri() {
		
		return fileDownloadUri;
		
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		
		this.fileDownloadUri = fileDownloadUri;
		
	}

	public ZonedDateTime getUploadDate() {
		
		return uploadDate;
		
	}

	public void setUploadDate(ZonedDateTime uploadDate) {
		
		this.uploadDate = uploadDate;
		
	}

	public String getCertificate() {
		
		return certificate;
		
	}
	
	public void setCertificate(String certificate) {
		
		this.certificate = certificate;
		
	}
	
	public Long getId() {
		
		return id;
		
	}
	
	public void setId(Long id) {
		
		this.id = id;
		
	}
	
	public Long getCourseId() {
		
		return courseId;
		
	}
	
	public void setCourseId(Long courseId) {
		
		this.courseId = courseId;
		
	}
	
	
}