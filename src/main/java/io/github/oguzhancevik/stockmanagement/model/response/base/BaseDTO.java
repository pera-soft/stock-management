package io.github.oguzhancevik.stockmanagement.model.response.base;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {
    private Long id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}
