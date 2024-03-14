package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Rating;
import com.massonus.rccnavigator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {

    Optional<Rating> findRatingByAuthorAndCompany(User user, Company company);

}
