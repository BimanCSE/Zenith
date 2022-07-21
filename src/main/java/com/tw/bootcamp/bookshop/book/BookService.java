package com.tw.bootcamp.bookshop.book;

import com.tw.bootcamp.bookshop.book.reviews.Reviews;
import com.tw.bootcamp.bookshop.book.reviews.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ReviewsService reviewsService;
    @Autowired
    public BookService(BookRepository bookRepository,ReviewsService reviewsService) {
        this.bookRepository = bookRepository;
        this.reviewsService = reviewsService;
    }


    public List<BookApiObject> fetchAll() {
        List<Book> listOfBooks = bookRepository.findAllByOrderByPriceDesc();
        List<BookApiObject> listOfBookApiObjects = new ArrayList<>();
        for(Book book : listOfBooks){
            ArrayList<String> ListOfReviews = this.reviewsService.getAllReviewsByID(book.getId());
            BookApiObject bookApiObject = new BookApiObject(book.getName(),book.getAuthorName(),book.getPrice(),book.getDescription(),
                                            book.getAvailability(),book.getRatings(),ListOfReviews);
            listOfBookApiObjects.add(bookApiObject);
        }
        return listOfBookApiObjects.stream().sorted((object1,object2) -> object1.getName().compareTo(object2.getName())).collect(Collectors.toList());
    }

    public <name> List<BookApiObject> fetchByName(String name) {

        List<Book> listOfBooks = bookRepository.searchBookByNameLike(name.toLowerCase());
        List<BookApiObject> listOfBookApiObjects = new ArrayList<>();
        for(Book book : listOfBooks){
            ArrayList<String> ListOfReviews = this.reviewsService.getAllReviewsByID(book.getId());
            BookApiObject bookApiObject = new BookApiObject(book.getName(),book.getAuthorName(),book.getPrice(),book.getDescription(),
                    book.getAvailability(),book.getRatings(),ListOfReviews);
            listOfBookApiObjects.add(bookApiObject);
        }

        return listOfBookApiObjects.stream().sorted((object1,object2) -> object1.getName().compareTo(object2.getName())).collect(Collectors.toList());
    }



    public boolean createBook(BookApiObject book) {
        System.out.println(book.getReviewsList().size());
        try{
            if(book.getRatings()<0 && book.getRatings()>5 && book.getPrice()>0){
                return false;
            }
            Book newBook = new Book(book.getName(),book.getAuthorName(),book.getPrice(),book.getDescription(),book.getAvailability(),book.getRatings());
            Book savedBook = bookRepository.saveAndFlush(newBook);
            for(String review : book.getReviewsList()){
                this.reviewsService.createReviews(new Reviews(review,savedBook.getId()));
            }
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public List<BookApiObject> fetchById(Long id) {
        List<Book> listOfBooks = bookRepository.searchBookById(id);
        List<BookApiObject> listOfBookApiObjects = new ArrayList<>();
        for(Book book : listOfBooks){
            ArrayList<String> ListOfReviews = this.reviewsService.getAllReviewsByID(book.getId());
            BookApiObject bookApiObject = new BookApiObject(book.getName(),book.getAuthorName(),book.getPrice(),book.getDescription(),
                    book.getAvailability(),book.getRatings(),ListOfReviews);
            listOfBookApiObjects.add(bookApiObject);
        }
        return listOfBookApiObjects.stream().sorted((object1,object2) -> object1.getName().compareTo(object2.getName())).collect(Collectors.toList());
    }
}
