package io.github.oguzhancevik.stockmanagement.model.response;

import lombok.Data;

@Data
public class ApiResponse {
    private String UUID = java.util.UUID.randomUUID().toString();
    private long timeStamp = System.currentTimeMillis();
    private Boolean success = Boolean.FALSE;
    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }
}
