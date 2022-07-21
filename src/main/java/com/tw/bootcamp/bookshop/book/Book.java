package com.tw.bootcamp.bookshop.book;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String authorName;

    private Integer price;

    public Book() {
    }

    public Book(String name, String authorName, Integer price) {
        this.name = name;
        this.authorName = authorName;
        this.price = price;
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

}
