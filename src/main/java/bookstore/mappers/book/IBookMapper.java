package bookstore.mappers.book;

import bookstore.config.MapperConfig;
import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.CreateUpdateBookRequestDto;
import bookstore.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface IBookMapper {
    BookDto toDto(Book book);

    Book toEntity(CreateUpdateBookRequestDto createUpdateBookRequestDto);

    void updateBookFromDto(@MappingTarget Book book, CreateUpdateBookRequestDto requestDto);
}
