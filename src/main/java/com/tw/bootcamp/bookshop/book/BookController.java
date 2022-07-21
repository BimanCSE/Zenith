package com.tw.bootcamp.bookshop.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.POST , value = "/books",consumes = "application/json")
    public String createBook(@RequestBody BookApiObject book){
        if(bookService.createBook(book))
            return "Book record able to Created";
        else
            return "Book record not able created";
    }
    @GetMapping("/books")
    List<BookApiObject> list() {
        List<BookApiObject> result =  bookService.fetchAll();
        return result;
    }
    @RequestMapping(value = "/books", params = "name")
    List<BookApiObject> getBooksByName(@RequestParam String name) {
        return bookService.fetchByName(name);
    }

    @RequestMapping(value = "/searchbooks")
    List<BookApiObject> searchBookDetails(@RequestParam String id) {
        return bookService.fetchById(Long.parseLong(id));
    }


}
