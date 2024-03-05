package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.BasketObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketObjectRepo extends JpaRepository<BasketObject, Long> {

    BasketObject findBasketObjectById(Long id);
}
