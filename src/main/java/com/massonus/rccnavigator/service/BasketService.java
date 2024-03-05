package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Basket;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class BasketService {

    private final ProductService productService;

    private final BasketRepo basketRepo;

    @Autowired
    public BasketService(ProductService productService, BasketRepo basketRepo) {
        this.productService = productService;
        this.basketRepo = basketRepo;
    }

    public Long addProductToBasket(Long id, User user) {

        final Product productById = productService.getProductById(id);
        final Basket currentBasket = getUserBasket(user);
        final Set<Product> products = currentBasket.getProducts();
        products.add(productById);

        basketRepo.save(currentBasket);

        return productById.getCompany().getId();
    }

    public Basket getUserBasket(User user) {
        Basket basketByUserId = getBasketByUserId(user.getId());
        if (Objects.isNull(basketByUserId)) {
            Basket basket = new Basket();
            basket.setUser(user);
            return basketRepo.save(basket);
        }
        return basketByUserId;
    }

    private Basket getBasketByUserId(Long id) {
        return basketRepo.findBasketByUserId(id);
    }

    public Basket getBasketById(Long id) {
        return basketRepo.findBasketById(id);
    }

    public void deleteBasketItem(Long id, User user) {
        Product productById = productService.getProductById(id);
        Basket basketByUserId = getBasketByUserId(user.getId());
        basketByUserId.getProducts().remove(productById);
    }

    public void clearBasket(final User user) {
        Basket basketByUserId = getBasketByUserId(user.getId());
        basketByUserId.getProducts().clear();
        basketRepo.save(basketByUserId);
    }

}
