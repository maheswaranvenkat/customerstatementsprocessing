package com.customer.statement.processing.exception;

/**
 * Exception class.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */

public class CSVCustomerStatementProcessingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CSVCustomerStatementProcessingException(String message) {
        super(message);
    }

}
