package com.customer.statement.processing.exception;

/**
 * Exception class.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */
public class CustomerStatementProcessingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerStatementProcessingException(String message) {
        super(message);
    }
}
