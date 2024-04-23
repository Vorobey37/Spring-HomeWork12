package org.example.book_service;

import jakarta.annotation.PostConstruct;

import org.example.Time;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("book")
@Time
public class BookController {
    private final List<Book> books = new ArrayList<>();

    @PostConstruct
    public void createBooks(){
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setId(UUID.randomUUID());
            book.setName("Книга №" + i);

            Author author = new Author();
            author.setFirstName("Имя автора №" + i);
            author.setLastName("Фамилия автора №" + i);
            author.setId(UUID.randomUUID());
            book.setAuthor(author);

            books.add(book);

        }
    }

    @GetMapping()
    public List<Book> getAllBooks(){
        return books;
    }

    @GetMapping("random")
    public Book getRandomBook(){
        Random random = new Random();
        return books.get(random.nextInt(books.size()));
    }

}
