package org.campus02.exception;

public class UrlLoaderException extends Exception{

    public UrlLoaderException(String message) {
        super(message);
    }

    public UrlLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
