package bookstore.mappers.category;

import bookstore.config.MapperConfig;
import bookstore.dtos.category.CategoryDto;
import bookstore.dtos.category.CreateUpdateCategoryRequestDto;
import bookstore.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface ICategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CreateUpdateCategoryRequestDto createUpdateCategoryRequestDto);

    void updateCategoryFromDto(
            @MappingTarget Category category,
            CreateUpdateCategoryRequestDto createUpdateCategoryRequestDto
    );
}
