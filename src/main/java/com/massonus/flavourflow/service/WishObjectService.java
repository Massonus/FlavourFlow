package com.massonus.flavourflow.service;

import com.massonus.flavourflow.entity.BasketObject;
import com.massonus.flavourflow.entity.WishObject;
import com.massonus.flavourflow.repo.WishObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public WishObject getWishObjectByProductId(final Long productId) {
        return objectRepo.findWishObjectByProductId(productId);
    }

    public void setWishObjectDbxImage(final Long productId, final String url) {
        WishObject object = getWishObjectByProductId(productId);
        if (Objects.nonNull(object)) {
            object.setImageLink(url);
        }
    }

    public WishObject getWishObjectByProductIdAndUserId(final Long productId, final Long userId) {

        return objectRepo.findWishObjectByProductIdAndUserId(productId, userId);
    }

    public List<WishObject> getWishObjectsByUserId(final Long userId) {
        return objectRepo.findWishObjectsByUserId(userId);
    }
}
