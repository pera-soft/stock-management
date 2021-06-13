package io.github.oguzhancevik.stockmanagement.model.response;

import io.github.oguzhancevik.stockmanagement.model.response.base.BaseDTO;
import lombok.Data;

@Data
public class SubCategoryDTO extends BaseDTO {

    private CategoryDTO category;

}
