package com.massonus.rccnavigator.repo;

import com.massonus.rccnavigator.entity.BasketObject;
import com.massonus.rccnavigator.entity.WishObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishObjectRepo extends JpaRepository<WishObject, Long> {

    WishObject findWishObjectById(Long id);

    WishObject findWishObjectByProductId(Long id);
}
