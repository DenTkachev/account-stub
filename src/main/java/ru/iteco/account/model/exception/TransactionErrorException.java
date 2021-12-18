package ru.iteco.account.model.exception;

public class TransactionErrorException extends RuntimeException {
    public TransactionErrorException() {
    }

    public TransactionErrorException(String message) {
        super(message);
    }

    public TransactionErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionErrorException(Throwable cause) {
        super(cause);
    }

    public TransactionErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
