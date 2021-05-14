package com.fujitsu.ph.tsup.enrollment.model;

import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class Certificate {
	
	private String certificate;
	private Long id;
	private Long courseId;
	private Long userId;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
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

	private Certificate(Builder builder) {

        this.id = builder.id;
        this.certificate = builder.certificate;
        this.courseId = builder.courseId;
        this.userId = builder.userId;
        this.uploadDate = builder.uploadDate;
        this.fileDownloadUri = builder.fileDownloadUri;
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
	
	public Long getUser() {
		
		return userId;
		
	}
	
	public void setUser(Long userId) {
		
		this.userId = userId;
		
	}
	
    public static class Builder {


        public ZonedDateTime uploadDate;
		private Long courseId;
		private Long id;
		private String certificate;
		private Long userId;
		private String fileDownloadUri;
		/** Builder Constructor
         * @param certificate
		 * @param uploadDate 
         */
        public Builder(Long courseId, String certificate, Long userId, ZonedDateTime uploadDate, String fileDownloadUri) {

        	validateUserId(userId);
            validateCertificate(certificate);
            this.certificate = certificate;
            this.userId = userId;
            this.courseId = courseId;
            this.uploadDate = uploadDate;
            this.fileDownloadUri = fileDownloadUri;
            
        }

        public Builder(Long id, String certificate) {

            validateId(id);
            validateCertificate(certificate);
            this.id = id;
            this.certificate = certificate;

        }
        
        public Builder(String certificate, String fileType, Long size , String fileDownloadUri) {
        	this.certificate = certificate;
        	this.fileDownloadUri = fileDownloadUri;

        }
        
        public Certificate build() {

            return new Certificate(this);

        }

        /** Validate course name if null or empty
         * @param name Course Name
         */
        private void validateCertificate(String certificate) {

            if (certificate.equals(null) || certificate.isEmpty()) {

                throw new IllegalArgumentException("Course should not be empty");

            }

        }

        /**
         * Validate Course id if null or 0
         * @param id
         */
        private void validateId(Long id) {

            if (id == null || id == 0) {

                throw new IllegalArgumentException("Id should not be empty");

            }
        }
        
        /**
         * Validate User id if null or 0
         * @param id
         */
        private void validateUserId(Long userId) {

            if (userId == null) {

                throw new IllegalArgumentException("Id should not be empty");

            }
        }
       
    }	
	
}