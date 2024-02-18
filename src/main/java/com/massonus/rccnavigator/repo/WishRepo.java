package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WishRepo extends JpaRepository<Wish, Long> {

    Wish findWishById(Long id);

    Set<Wish> findWishesByUserId(Long userId);

    Wish findByUserId(Long userId);

}
