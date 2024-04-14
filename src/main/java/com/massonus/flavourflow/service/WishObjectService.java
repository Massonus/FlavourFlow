package com.massonus.flavourflow.service;

import com.massonus.flavourflow.entity.WishObject;
import com.massonus.flavourflow.repo.WishObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishObjectService {

    private final WishObjectRepo objectRepo;

    @Autowired
    public WishObjectService(WishObjectRepo objectRepo) {
        this.objectRepo = objectRepo;
    }

    public void deleteWishObject(final WishObject wishObject) {
        objectRepo.delete(wishObject);
    }

    public WishObject getWishObjectByProductIdAndUserId(Long productId, Long userId) {

        return objectRepo.findWishObjectByProductIdAndUserId(productId, userId);
    }

    public List<WishObject> getWishObjectsByUserId(Long userId) {
        return objectRepo.findWishObjectsByUserId(userId);
    }
}
