package bookstore.services.category;

import bookstore.dtos.category.CategoryDto;
import bookstore.dtos.category.CreateUpdateCategoryRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    Page<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CreateUpdateCategoryRequestDto createUpdateCategoryRequestDto);

    CategoryDto update(Long id, CreateUpdateCategoryRequestDto createUpdateCategoryRequestDto);

    void delete(Long id);
}
