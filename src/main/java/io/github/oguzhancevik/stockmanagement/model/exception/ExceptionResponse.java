package io.github.oguzhancevik.stockmanagement.model.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private Boolean success = Boolean.FALSE;
    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
