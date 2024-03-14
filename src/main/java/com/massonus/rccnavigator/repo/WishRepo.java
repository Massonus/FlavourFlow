package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepo extends JpaRepository<Wish, Long> {

    Wish findWishByUserId(Long userId);

}
