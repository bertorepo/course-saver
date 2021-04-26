package com.fujitsu.ph.tsup.enrollment.model;

public class CertificateForm {
	
	private String certificate;
	private Long id;
	private Long courseId;
	private byte certificateFile;
	
	public byte getCertificateFile() {
		return certificateFile;
	}

	public void setCertificateFile(byte certificateFile) {
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