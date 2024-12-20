package bookstore.mappers.book;

import bookstore.config.MapperConfig;
import bookstore.dtos.book.BookDto;
import bookstore.dtos.book.CreateBookRequestDto;
import bookstore.models.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface IBookMapper {
    BookDto toDto(Book book);

    Book toEntity(CreateBookRequestDto createBookRequestDto);
}