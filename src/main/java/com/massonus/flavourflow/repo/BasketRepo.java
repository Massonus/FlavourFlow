package com.massonus.flavourflow.repo;

import com.massonus.flavourflow.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

    Basket findBasketByUserId(Long userId);

}
