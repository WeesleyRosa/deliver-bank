package com.deliver.bank.bank.domain.user.service.exceptions;

public class CpfValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CpfValidationException(String message) {
        super(message);
    }

    public CpfValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
