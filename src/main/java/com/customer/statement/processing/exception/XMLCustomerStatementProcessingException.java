package com.customer.statement.processing.exception;

/**
 * Exception class.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */
public class XMLCustomerStatementProcessingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public XMLCustomerStatementProcessingException(String message) {
        super(message);
    }
}
