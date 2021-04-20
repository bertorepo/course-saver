package com.fujitsu.ph.tsup.enrollment.model;

public class Certificate {

	private String certificate;
	private Long userId;
	private Long id;
	private Long courseId;
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	@Override
	public String toString() {
		return "Certificate [certificate=" + certificate + ", userId=" + userId + ", id=" + id + ", courseId="
				+ courseId + "]";
	}
	
	
}
