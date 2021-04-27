package com.fujitsu.ph.tsup.enrollment.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Lob;

public class CertificateForm {
	
	private String certificate;
	private Long id;
	private Long courseId;
	private ZonedDateTime uploadDate;
	
	public ZonedDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(ZonedDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Lob
	@Column(name = "certificateFile")
	private byte[] certificateFile;
	
	public byte[] getCertificateFile() {
	
		return certificateFile;
	}

	public void setCertificateFile(byte[] certificateFile) {
		this.certificateFile = certificateFile;
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