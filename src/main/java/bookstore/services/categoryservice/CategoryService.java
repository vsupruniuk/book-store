package bookstore.services.categoryservice;

import bookstore.dtos.category.CategoryDto;
import bookstore.dtos.category.CreateUpdateCategoryRequestDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.mappers.category.ICategoryMapper;
import bookstore.models.Category;
import bookstore.repositories.categoryrepository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository
                .findAll(pageable)
                .map(categoryMapper::toDto);
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryRepository
                .findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Category with id %d not found".formatted(id)
                ));
    }

    @Override
    public CategoryDto save(CreateUpdateCategoryRequestDto createUpdateCategoryRequestDto) {
        return categoryMapper.toDto(
                categoryRepository.save(
                        categoryMapper.toEntity(createUpdateCategoryRequestDto)
                )
        );
    }

    @Override
    public CategoryDto update(
            Long id,
            CreateUpdateCategoryRequestDto createUpdateCategoryRequestDto
    ) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Category with id %d not found".formatted(id)
                ));

        categoryMapper.updateCategoryFromDto(category, createUpdateCategoryRequestDto);

        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        getById(id);

        categoryRepository.deleteById(id);
    }
}
