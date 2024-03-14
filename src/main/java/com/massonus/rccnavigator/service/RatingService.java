package com.massonus.rccnavigator.service;


import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Rating;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepo ratingRepo;

    @Autowired
    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public void rateCompany(User author, Company company, Integer rate) {
        Rating rating = ratingRepo.findRatingByAuthorAndCompany(author, company).orElse(new Rating());

        rating.setAuthor(author);
        rating.setCompany(company);
        rating.setRate(rate);
        ratingRepo.save(rating);

        company.setRating(company.getCurrentRating());
    }
}
