package com.fujitsu.ph.tsup.enrollment.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Lob;

public class CertificateForm {
	
	private String certificate;
	private Long id;
	private Long courseId;
	private ZonedDateTime uploadDate;
	
	@Lob
	@Column(name = "certificateFile")
	private byte[] certificateFile;
	
	private String fileDownloadUri;
	private String fileType;
	private long size;
	
	
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public ZonedDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(ZonedDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

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