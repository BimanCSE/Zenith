package com.tw.bootcamp.bookshop.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;



    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldFetchAllBooks() {

        Book book = new Book("title", "author name", 300);
        bookRepository.save(book);

        List<Book> books = bookService.fetchAll();

        assertEquals(1, books.size());
        assertEquals("title", books.get(0).getName());
    }

    @Test
    void shouldFetchAllBooksBeSortedByPrice() {

        Book lowPrice = new Book("title", "author name", 300);
        Book highPrice = new Book("costlier", "author name", 400);
        bookRepository.save(lowPrice);
        bookRepository.save(highPrice);

        List<Book> books = bookService.fetchAll();

        assertEquals(2, books.size());
        assertEquals("costlier", books.get(0).getName());
    }
//    @Test
//    void seeBookDetailsWhenTypePartialString() {
//
//        Book partialString = new Book("iki", "author name", 78);
//        bookRepository.save(partialString);
//
//        List<Book> books = bookService.fetchByName(String.valueOf(partialString));
//
//        assertEquals("ikigai", books.get(0).getName());
//    }

    //when book is not available
    @Test
    void bookIsNotAvailable() {
        Book notAvailable = new Book("do epic shit", "author name", 100);

        List<Book> books = bookService.fetchAll();

        Assertions.assertEquals(false,books.contains(notAvailable));

    }
}