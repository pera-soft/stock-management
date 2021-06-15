package io.github.oguzhancevik.stockmanagement.model.exception;

public enum BusinessValidationRule {

    CATEGORY_NOT_FOUND("Category Not Found"),
    SUB_CATEGORY_NOT_FOUND("Sub Category Not Found"),
    PRODUCT_NOT_FOUND("Product Not Found"),
    INVALID_TRANSACTION("Invalid Transaction"),
    SUCCESSFUL_TRANSACTION("Successful Transaction");

    private final String message;

    BusinessValidationRule(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
