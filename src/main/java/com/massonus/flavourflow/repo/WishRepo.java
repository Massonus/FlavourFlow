package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepo extends JpaRepository<Wish, Long> {

    Wish findWishByUserId(Long userId);

}
