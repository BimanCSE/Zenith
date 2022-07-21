package com.tw.bootcamp.bookshop.book.reviews;

import com.tw.bootcamp.bookshop.book.Book;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookReview;

    private Long bookId;
    public Reviews(String bookReview, Long bookId){
        this.bookReview = bookReview;
        this.bookId = bookId;
    }
    public Long getId(){
        return this.id;
    }
    public String getBookReview(){
        return this.bookReview;
    }
    public Long getBook(){
        return this.bookId;
    }
}
