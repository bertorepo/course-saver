/**
 * Copyright (C) 2019 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.exception;

public class TsupException extends RuntimeException {

    /**
     * @param message
     * @param cause
     */
    public TsupException(String message, Throwable cause) {
	super(message, cause);

    }

    /**
     * @param message
     */
    public TsupException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public TsupException(Throwable cause) {
	super(cause);
    }

}
