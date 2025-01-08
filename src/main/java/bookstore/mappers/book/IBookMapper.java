package bookstore.mappers.book;

import bookstore.config.MapperConfig;
import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.BookDtoWithoutCategoryIds;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import bookstore.models.Book;
import bookstore.models.Category;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface IBookMapper {
    @Mapping(target = "categoryIds", ignore = true)
    BookDto toDto(Book book);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @Mapping(target = "categories", ignore = true)
    Book toEntity(CreateUpdateBookRequestDto createUpdateBookRequestDto);

    @Mapping(target = "categories", ignore = true)
    void updateBookFromDto(@MappingTarget Book book, CreateUpdateBookRequestDto requestDto);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        List<Long> categoryIds = book
                .getCategories()
                .stream()
                .map(Category::getId)
                .collect(Collectors.toList());

        bookDto.setCategoryIds(categoryIds);
    }

    @AfterMapping
    default void setCategories(
            @MappingTarget Book book,
            CreateUpdateBookRequestDto createUpdateBookRequestDto
    ) {
        List<Category> categories = createUpdateBookRequestDto
                .getCategories()
                .stream()
                .map(Category::new)
                .collect(Collectors.toList());

        book.setCategories(categories);
    }

    @Named("bookFromId")
    default Book bookFromId(Long id) {
        return Optional
                .ofNullable(id)
                .map(Book::new)
                .orElse(null);
    }
}
