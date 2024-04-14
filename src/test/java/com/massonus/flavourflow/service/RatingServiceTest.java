package com.massonus.flavourflow.service;

import com.massonus.flavourflow.entity.Company;
import com.massonus.flavourflow.entity.Rating;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.repo.RatingRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    private RatingRepo ratingRepo;

    @InjectMocks
    private RatingService target;

    @Test
    void shouldRateCompany() {
        Optional<Rating> rating = Optional.of(new Rating());
        User user = new User();
        Company company = new Company();

        when(ratingRepo.findRatingByAuthorAndCompany(user, company)).thenReturn(rating);

        target.rateCompany(user, company, 2);

        ArgumentCaptor<Rating> ratingCaptor = ArgumentCaptor.forClass(Rating.class);
        verify(ratingRepo, times(1)).save(ratingCaptor.capture());

        Rating savedRating = ratingCaptor.getValue();
        Assertions.assertEquals(savedRating.getRate(), 2);
    }
}