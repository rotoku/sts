package br.com.kumabe.sts.exception;

public class InvalidClientCredentialsException extends RuntimeException {
    public InvalidClientCredentialsException(String message) {
        super(message);
    }
}