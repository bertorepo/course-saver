package com.fujitsu.ph.tsup.enrollment.model;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class Certificate {
	
	private String certificate;
	private Long id;
	private Long courseId;
	private Long userId;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime uploadDate;
	private byte[] certificateFile;
	private String fileDownloadUri;
	private String fileType;
	private Long size;
	
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
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

	private Certificate(Builder builder) {

        this.id = builder.id;
        this.certificate = builder.certificate;
        this.courseId = builder.courseId;
        this.userId = builder.userId;
        this.uploadDate = builder.uploadDate;
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
	
	/** Builder Class
     * @author c.arias
     *
     */
    public static class Builder {


        public ZonedDateTime uploadDate;
		private Long courseId;
		private Long id;
		private String certificate;
		private Long userId;
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		/** Builder Constructor
         * @param certificate
		 * @param uploadDate 
         */
        public Builder(Long courseId, String certificate, Long userId, ZonedDateTime uploadDate) {

        	validateUserId(userId);
            validateCertificate(certificate);
            this.certificate = certificate;
            this.userId = userId;
            this.courseId = courseId;
            this.uploadDate = uploadDate;
            
        }

        public Builder(Long id, String certificate, byte[] certificateFile) {

            validateId(id);
            validateCertificate(certificate);
            this.id = id;
            this.certificate = certificate;

        }
        
        public Builder(String certificate, String fileDownloadUri, String fileType, Long size) {
        	this.certificate = certificate;

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