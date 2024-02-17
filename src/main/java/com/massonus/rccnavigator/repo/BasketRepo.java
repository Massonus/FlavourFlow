package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

    Basket findBasketById(Long id);

    Basket findBasketByUserId(Long userId);

}
