package com.tw.bootcamp.bookshop.book.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
    @Query("SELECT bookReview FROM Reviews r WHERE r.bookId=:id")
    ArrayList<String> findReviewsById(@Param("id") Long id);
}
