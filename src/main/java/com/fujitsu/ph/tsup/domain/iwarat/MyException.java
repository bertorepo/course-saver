package com.fujitsu.ph.tsup.domain.iwarat;

public class MyException extends RuntimeException {

    private static final long serialVersionUID = 2445281598429784297L;

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
