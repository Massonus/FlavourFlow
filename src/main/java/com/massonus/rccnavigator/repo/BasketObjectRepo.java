package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.BasketObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketObjectRepo extends JpaRepository<BasketObject, Long> {

    BasketObject findBasketObjectById(Long id);

    BasketObject findBasketObjectByProductId(Long id);

    BasketObject findBasketObjectByProductIdAndUserId(Long productId, Long userId);

    List<BasketObject> findBasketObjectsByUserId(Long id);
}
