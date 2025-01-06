package bookstore.controllers;

import bookstore.dtos.book.BookDtoWithoutCategoryIds;
import bookstore.dtos.category.CategoryDto;
import bookstore.dtos.category.CreateUpdateCategoryRequestDto;
import bookstore.services.bookservice.IBookService;
import bookstore.services.categoryservice.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final IBookService bookService;

    @Operation(description = "Get all categories")
    @GetMapping
    public Page<CategoryDto> getAllCategories(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @Operation(description = "Get category by id")
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @Operation(description = "Get all books by category")
    @GetMapping("/{id}/books")
    public Page<BookDtoWithoutCategoryIds> getBooksByCategoryId(
            @PathVariable Long id,
            Pageable pageable
    ) {
        return bookService.findAllByCategoryId(pageable, id);
    }

    @Operation(description = "Create category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public CategoryDto createCategory(
            @RequestBody
            @Valid
            CreateUpdateCategoryRequestDto createUpdateCategoryRequestDto
    ) {
        return categoryService.save(createUpdateCategoryRequestDto);
    }

    @Operation(description = "Update category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public CategoryDto updateCategory(
            @PathVariable
            Long id,

            @RequestBody
            @Valid
            CreateUpdateCategoryRequestDto createUpdateCategoryRequestDto
    ) {
        return categoryService.update(id, createUpdateCategoryRequestDto);
    }

    @Operation(description = "Delete category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
