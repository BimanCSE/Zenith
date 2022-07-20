package com.tw.bootcamp.bookshop.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    List<Book> list() {
        return bookService.fetchAll();
    }
    @RequestMapping(value = "/books", params = "name")
    List<Book> getBooksByName(@RequestParam String name) {
        return bookService.fetchByName(name);
    }

//   @GetMapping("/partial")
//    List<Book> fetchByPartialSearch(@RequestParam String name) {
//        return bookService.fetchByName(name);
//    }


}
