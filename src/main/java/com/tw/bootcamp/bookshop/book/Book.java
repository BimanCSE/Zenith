package com.tw.bootcamp.bookshop.book;


import javax.persistence.*;


@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String authorName;

    private Integer price;

    private String description;
    private boolean availability;
    private Integer ratings;
    public Book() {
    }

    public Book(String name, String authorName, Integer price ) {
        this.name = name;
        this.authorName = authorName;
        this.price = price;
        this.description = null;
        this.availability = false;
        this.ratings = null;
    }
    public Book(String name, String authorName, Integer price , String description ) {
        this(name,authorName,price);
        this.description = description;
    }
    public Book(String name, String authorName, Integer price , String description,boolean availability) {
        this(name,authorName,price,description);
        this.availability = availability;
    }


    public Book(String name, String authorName, Integer price , String description,boolean availability,Integer ratings) {
        this(name,authorName,price,description,availability);
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Integer getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getDescription(){return description;}
    public boolean getAvailability(){return availability;}
    public Integer getRatings(){return ratings;}
}
