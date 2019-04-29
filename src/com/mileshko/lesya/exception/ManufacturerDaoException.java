package com.mileshko.lesya.exception;

public class ManufacturerDaoException extends Exception {
    public ManufacturerDaoException() {
    }

    public ManufacturerDaoException(String message) {
        super(message);
    }

    public ManufacturerDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManufacturerDaoException(Throwable cause) {
        super(cause);
    }

}
