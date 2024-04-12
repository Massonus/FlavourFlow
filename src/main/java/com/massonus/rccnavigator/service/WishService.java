package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.ItemDto;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.entity.Wish;
import com.massonus.rccnavigator.entity.WishObject;
import com.massonus.rccnavigator.repo.WishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class WishService {

    private final WishRepo wishRepo;
    private final UserService userService;
    private final WishObjectService objectService;

    @Autowired
    public WishService(WishRepo wishRepo, UserService userService, WishObjectService objectService) {
        this.wishRepo = wishRepo;
        this.userService = userService;
        this.objectService = objectService;
    }

    public Long addProductToWishes(final Product product, final User user) {
        final Wish currentWish = getUserWish(user.getId());
        List<WishObject> wishObjects = currentWish.getWishObjects();

        WishObject wishObject = new WishObject();
        wishObject.setProduct(product);
        wishObject.setTitle(product.getTitle());
        wishObject.setImageLink(product.getImageLink());
        wishObject.setPrice(product.getPrice());
        wishObject.setUser(user);
        wishObject.setCompany(product.getCompany());
        wishObject.setWish(currentWish);

        wishObjects.add(wishObject);

        wishRepo.save(currentWish);
        user.setWish(currentWish);

        return product.getCompany().getId();
    }

    public Wish getUserWish(Long userId) {
        Wish wishByUserId = getWishByUserId(userId);
        if (Objects.isNull(wishByUserId)) {
            Wish wish = new Wish();
            wish.setUser(userService.getUserById(userId));
            return wishRepo.save(wish);
        }
        return wishByUserId;
    }

    public Boolean isInWishes(String productId, String userId) {

        return getUserWish(Long.valueOf(userId)).getWishObjects().stream()
                .anyMatch(o -> o.getProduct().getId().equals(Long.valueOf(productId)));
    }

    private Wish getWishByUserId(Long id) {
        return wishRepo.findWishByUserId(id);
    }

    public ItemDto deleteWishItem(ItemDto itemDto, User user) {
        WishObject wishObjectById = objectService.getWishObjectByProductIdAndUserId(itemDto.getProductId(), user.getId());
        objectService.deleteWishObject(wishObjectById);
        itemDto.setItemId(wishObjectById.getId());
        return itemDto;
    }

    public Boolean clearWishes(final User user) {
        wishRepo.delete(getWishByUserId(user.getId()));
        return objectService.getWishObjectsByUserId(user.getId()).isEmpty();
    }
}
