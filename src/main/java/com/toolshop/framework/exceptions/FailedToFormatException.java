package com.toolshop.framework.exceptions;

/**
 * @author Elnur Abaslı
 */
public class FailedToFormatException extends RuntimeException{

    public FailedToFormatException(String message) {
        super(message);
    }

    public FailedToFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
