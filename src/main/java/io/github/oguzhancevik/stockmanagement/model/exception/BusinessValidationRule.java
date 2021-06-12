package io.github.oguzhancevik.stockmanagement.model.exception;

public enum BusinessValidationRule {

    CATEGORY_NOT_FOUND("Category Not Found");

    private final String message;

    BusinessValidationRule(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
