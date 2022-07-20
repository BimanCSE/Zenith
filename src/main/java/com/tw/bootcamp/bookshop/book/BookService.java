package com.tw.bootcamp.bookshop.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> fetchAll() {
        return bookRepository.findAllByOrderByPriceDesc();
    }

    public <name> List<Book> fetchByName(String name) {

        return bookRepository.searchBookByNameLike(name);
    }

    public List<Book> findByName(String name){
        List<Book> books = fetchAll();
        List<Book> results = new ArrayList<>();
        for(Book b : books){
            if(b.getName().contains(name))
            {
                results.add(b);
            }
        }
        return results;
    }

}
