package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.entity.Wish;
import com.massonus.rccnavigator.repo.WishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class WishService {

    private final ProductService productService;

    private final WishRepo wishRepo;

    @Autowired
    public WishService(ProductService productService, WishRepo wishRepo) {
        this.productService = productService;
        this.wishRepo = wishRepo;
    }

    public Long addProductToWishes(Long id, User user) {

        final Product productById = productService.getProductById(id);

        final Wish currentWish = getUserWish(user);
        final Set<Product> products = currentWish.getProducts();

        products.add(productById);

        wishRepo.save(currentWish);

        return productById.getCompany().getId();
    }

    public Wish getUserWish(User user) {
        Wish wishByUserId = getWishByUserId(user.getId());

        if (Objects.isNull(wishByUserId)) {
            Wish wish = new Wish();
            wish.setUser(user);
            return wishRepo.save(wish);
        }
        return wishByUserId;
    }

    private Wish getWishByUserId(Long id) {
        return wishRepo.findWishByUserId(id);
    }

    public Wish getWishById(Long id) {
        return wishRepo.findWishById(id);
    }

    public void deleteWishItem(Long id, User user) {

        Product productById = productService.getProductById(id);
        Wish wishByUserId = wishRepo.findWishByUserId(user.getId());
        wishByUserId.getProducts().remove(productById);

    }
}
