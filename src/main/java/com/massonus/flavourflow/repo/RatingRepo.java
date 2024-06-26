package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.Company;
import com.massonus.flavourflow.entity.Rating;
import com.massonus.flavourflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {

    Optional<Rating> findRatingByAuthorAndCompany(User user, Company company);

}
