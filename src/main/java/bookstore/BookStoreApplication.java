package bookstore;

import bookstore.models.Book;
import bookstore.services.bookService.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class BookStoreApplication {
    @Autowired
    private IBookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Book lastWish = Book
                    .builder()
                    .title("The Witcher: The Last Wish")
                    .author("Andrzej Sapkowski")
                    .isbn("9781473231061")
                    .price(BigDecimal.valueOf(496.00))
                    .description("Amazing book")
                    .coverImage("Very beautiful image")
                    .build();

            Book swordOfDestiny = Book
                    .builder()
                    .title("The Witcher: Sword of Destiny")
                    .author("Andrzej Sapkowski")
                    .isbn("9781473231085")
                    .price(BigDecimal.valueOf(496.00))
                    .description("One more amazing book")
                    .coverImage("One more very beautiful image")
                    .build();

            bookService.save(lastWish);
            bookService.save(swordOfDestiny);

            System.out.println(bookService.findAll());
        };
    }
}
