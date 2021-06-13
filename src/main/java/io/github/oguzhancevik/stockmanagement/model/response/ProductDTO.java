package io.github.oguzhancevik.stockmanagement.model.response;

import io.github.oguzhancevik.stockmanagement.model.response.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends BaseDTO {
    private BigDecimal price;
    private Long stockAmount;
    private SubCategoryDTO subCategory;
}
