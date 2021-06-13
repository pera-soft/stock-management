package io.github.oguzhancevik.stockmanagement.model.response;

import io.github.oguzhancevik.stockmanagement.model.response.base.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO extends BaseDTO {
    private BigDecimal price;
    private Long stockAmount;
    private SubCategoryDTO subCategory;
}
