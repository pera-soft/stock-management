package io.github.oguzhancevik.stockmanagement.util;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import io.github.oguzhancevik.stockmanagement.model.entity.SubCategory;
import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER.COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseMapper {

    BaseMapper INSTANCE = Mappers.getMapper(BaseMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryDTO toDTO(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<CategoryDTO> toDTO(List<Category> categories);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubCategoryDTO toDTO(SubCategory subCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductDTO toDTO(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromRequest(ProductRequest request, @MappingTarget Product product);

}
