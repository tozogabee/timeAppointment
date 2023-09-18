package com.sonrisa.timeappointmentapp.service.exception;

public class ReservationException extends Exception {

    public ReservationException() {
    }

    public ReservationException(String message) {
        super(message);
    }

    public ReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationException(Throwable cause) {
        super(cause);
    }

    public ReservationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
