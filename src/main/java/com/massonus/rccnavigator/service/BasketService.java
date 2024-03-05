package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Basket;
import com.massonus.rccnavigator.entity.BasketObject;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.BasketObjectRepo;
import com.massonus.rccnavigator.repo.BasketRepo;
import com.massonus.rccnavigator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class BasketService {

    private final ProductService productService;
    private final BasketRepo basketRepo;
    private final BasketObjectRepo basketObjectRepo;
    private final UserRepo userRepo;

    @Autowired
    public BasketService(ProductService productService, BasketRepo basketRepo, BasketObjectRepo basketObjectRepo, UserRepo userRepo) {
        this.productService = productService;
        this.basketRepo = basketRepo;
        this.basketObjectRepo = basketObjectRepo;
        this.userRepo = userRepo;
    }

    public Long addProductToBasket(Long id, User user) {

        final Product productById = productService.getProductById(id);

        final Basket currentBasket = getUserBasket(user);
        Set<BasketObject> basketObjects = currentBasket.getBasketObjects();

        BasketObject basketObject = new BasketObject();
        basketObject.setProductId(productById.getId());
        basketObject.setTitle(productById.getTitle());
        basketObject.setImage(productById.getImage());
        basketObject.setImageLink(productById.getImageLink());
        basketObject.setPrice(productById.getPrice());
        basketObject.setUser(user);
        basketObject.setCompany(productById.getCompany());

        basketObjectRepo.save(basketObject);

        basketObjects.add(basketObject);

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

    public Boolean isInBasket(String id, String userId) {

        Long productId = Long.valueOf(id);
        User userById = userRepo.findUserById(Long.valueOf(userId));

        return getUserBasket(userById).getBasketObjects().stream().anyMatch(o -> o.getProductId().equals(productId));
    }

    public void changeAmount(Long productId, User user, Integer amount) {

        BasketObject basketObject = basketObjectRepo.findBasketObjectByProductIdAndUserId(productId, user.getId());
        basketObject.setAmount(amount);
    }

    private Basket getBasketByUserId(Long id) {
        return basketRepo.findBasketByUserId(id);
    }

    public Basket getBasketById(Long id) {
        return basketRepo.findBasketById(id);
    }

    public void deleteBasketItem(Long id, User user) {
        BasketObject basketObject = basketObjectRepo.findBasketObjectByProductIdAndUserId(id, user.getId());
        Basket basketByUserId = getBasketByUserId(user.getId());
        basketByUserId.getBasketObjects().remove(basketObject);
    }

    public void clearBasket(final User user) {
        Basket basketByUserId = getBasketByUserId(user.getId());
        basketByUserId.getBasketObjects().clear();
        basketRepo.save(basketByUserId);
    }
}
