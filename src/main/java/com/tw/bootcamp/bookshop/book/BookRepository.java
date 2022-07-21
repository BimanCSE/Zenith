package com.tw.bootcamp.bookshop.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {
    List<Book> findAllByOrderByPriceDesc();



    List<Book> findByName(@Param("name") String name);

    @Query(value = "SELECT b.* FROM books b WHERE lower(b.name) LIKE %:name%", nativeQuery = true)
    List<Book> searchBookByNameLike(@Param("name") String name);

    @Query("SELECT b from Book b WHERE b.id=:id")
    List<Book>  searchBookById(@Param("id")Long id);
}
