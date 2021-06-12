package io.github.oguzhancevik.stockmanagement.model.exception;

public class BusinessValidationException extends RuntimeException {

    private final String message;

    public BusinessValidationException(BusinessValidationRule rule) {
        super(rule.getMessage());
        message = rule.getMessage();
    }

    public String getMessage() {
        return message;
    }

}
