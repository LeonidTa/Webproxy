package org.campus02.exception;

public class CacheMissException extends Exception{
    public CacheMissException(String message, Throwable cause) {
        super(message, cause);
    }
}
