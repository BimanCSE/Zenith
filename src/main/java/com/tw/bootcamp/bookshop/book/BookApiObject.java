package com.tw.bootcamp.bookshop.book;

import java.util.ArrayList;
import java.util.List;

public class BookApiObject {
    private String name = null;
    private String authorName = null;
    private Integer price = 0;
    private String description = null;
    private boolean availability = false;
    private Integer ratings = -1;
    private List<String> reviewsList = new ArrayList<String>();
    public BookApiObject(){

    }


    public BookApiObject(String name, String authorName, Integer price ) {
        this.name = name;
        this.authorName = authorName;
        this.price = price;
        this.description = null;
        this.availability = false;
        this.ratings = null;
    }
    public BookApiObject(String name, String authorName, Integer price , String description ) {
        this(name,authorName,price);
        this.description = description;
    }
    public BookApiObject(String name, String authorName, Integer price , String description,boolean availability) {
        this(name,authorName,price,description);
        this.availability = availability;
    }


    public BookApiObject(String name, String authorName, Integer price , String description,boolean availability,Integer ratings) {
        this(name,authorName,price,description,availability);
        this.ratings = ratings;
    }
    public BookApiObject(String name, String authorName , Integer price , String description , boolean availability , Integer ratings , ArrayList<String> reviewsList){
        this.name = name;
        this.authorName = authorName;
        this.price = price;
        this.description = description;
        this.availability = availability;
        this.ratings = ratings;
        this.reviewsList = reviewsList;

    }
    public String getName(){
        return this.name;
    }
    public String getAuthorName(){
        return this.authorName;
    }
    public Integer getPrice(){
        return this.price;
    }
    public String getDescription(){
        return this.description;
    }
    public Integer getRatings(){
        return this.ratings;
    }
    public boolean getAvailability(){
        return this.availability;
    }

    public List<String> getReviewsList(){
        return this.reviewsList;
    }

}
