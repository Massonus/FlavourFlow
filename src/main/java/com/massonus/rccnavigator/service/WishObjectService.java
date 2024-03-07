package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.WishObject;
import com.massonus.rccnavigator.repo.WishObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishObjectService {

    private final WishObjectRepo wishObjectRepo;

    @Autowired
    public WishObjectService(WishObjectRepo wishObjectRepo) {
        this.wishObjectRepo = wishObjectRepo;
    }

    public void saveWishObject(final WishObject wishObject) {

        wishObjectRepo.save(wishObject);
    }

    public void deleteWishObject(final WishObject wishObject) {
        wishObjectRepo.delete(wishObject);
    }

    public WishObject getWishObjectById(Long id) {
        return wishObjectRepo.findWishObjectById(id);
    }

    public WishObject getWishObjectByProductId(Long productId) {
        return wishObjectRepo.findWishObjectByProductId(productId);
    }
}
