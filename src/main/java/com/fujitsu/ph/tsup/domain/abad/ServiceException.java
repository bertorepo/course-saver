package com.fujitsu.ph.tsup.domain.abad;

    public class ServiceException extends RuntimeException{
        private static final long serialVersionUID = 2253098711666393964L;
        
        public ServiceException(String message) {
            super(message);
        }
        
        public ServiceException(String message, Throwable cause) {
            super(message, cause);
        }
}
