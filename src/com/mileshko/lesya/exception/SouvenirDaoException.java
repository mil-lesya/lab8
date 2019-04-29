package com.mileshko.lesya.exception;

public class SouvenirDaoException extends Exception {
    public SouvenirDaoException() {
    }

    public SouvenirDaoException(String message) {
        super(message);
    }

    public SouvenirDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SouvenirDaoException(Throwable cause) {
        super(cause);
    }

}
