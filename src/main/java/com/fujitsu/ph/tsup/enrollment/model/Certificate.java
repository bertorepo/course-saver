package com.fujitsu.ph.tsup.enrollment.model;

public class Certificate {
	
	private String certificate;
	private Long id;
	private Long courseId;
	private Long userId;
	private byte certificateFile;
	
	public byte getCertificateFile() {
		return certificateFile;
	}

	public void setCertificateFile(byte certificateFile) {
		this.certificateFile = certificateFile;
	}

	private Certificate(Builder builder) {

        this.id = builder.id;
        this.certificate = builder.certificate;
        this.courseId = builder.courseId;
        this.userId = builder.userId;
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


        private Long courseId;
		private Long id;
		private String certificate;
		private Long userId;
		private byte certificateFile;
		/** Builder Constructor
         * @param certificate
         */
        public Builder(Long courseId, String certificate, Long userId, byte certificateFile) {

        	validateUserId(userId);
            validateCertificate(certificate);
            this.certificate = certificate;
            this.userId = userId;
            this.courseId = courseId;
            this.setCertificateFile(certificateFile);
        }

        /** Builder Constructor
         * @param id
         * @param certificate
         */
        public Builder(Long id, String certificate, byte certificateFile) {

            validateId(id);
            validateCertificate(certificate);

            this.id = id;
            this.certificate = certificate;
            this.setCertificateFile(certificateFile);

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

		public byte getCertificateFile() {
			return certificateFile;
		}

		public void setCertificateFile(byte certificateFile) {
			this.certificateFile = certificateFile;
		}


    }
	
	
}