package io.github.oguzhancevik.stockmanagement.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryRequest {

    @NotNull
    @Positive
    private Long categoryId;

    @NotEmpty
    @NotBlank
    private String name;

}
