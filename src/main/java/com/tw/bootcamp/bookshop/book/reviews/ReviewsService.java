package com.tw.bootcamp.bookshop.book.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;

    @Autowired
    public ReviewsService(ReviewsRepository reviewsRepository){
        this.reviewsRepository = reviewsRepository;
    }

    public boolean createReviews(Reviews reviews){
        try{
            this.reviewsRepository.save(reviews);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<String> getAllReviewsByID(Long id) {
        return this.reviewsRepository.findReviewsById(id);
    }
}
