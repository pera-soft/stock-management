package io.github.oguzhancevik.stockmanagement.util;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER.COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseMapper {

    BaseMapper INSTANCE = Mappers.getMapper(BaseMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryDTO toDTO(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<CategoryDTO> toDTO(List<Category> categories);

}
