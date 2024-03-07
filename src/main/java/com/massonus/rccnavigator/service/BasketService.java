package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Basket;
import com.massonus.rccnavigator.entity.BasketObject;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.BasketRepo;
import com.massonus.rccnavigator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BasketService {

    private final ProductService productService;
    private final BasketRepo basketRepo;
    private final BasketObjectService basketObjectService;
    private final UserRepo userRepo;

    @Autowired
    public BasketService(ProductService productService, BasketRepo basketRepo, BasketObjectService basketObjectService, UserRepo userRepo) {
        this.productService = productService;
        this.basketRepo = basketRepo;
        this.basketObjectService = basketObjectService;
        this.userRepo = userRepo;
    }

    public Long addProductToBasket(Long id, Long userId) {

        final Product productById = productService.getProductById(id);

        final Basket currentBasket = getUserBasket(userId);
        User userById = userRepo.findUserById(userId);
        List<BasketObject> basketObjects = currentBasket.getBasketObjects();

        BasketObject basketObject = new BasketObject();
        basketObject.setProductId(productById.getId());
        basketObject.setTitle(productById.getTitle());
        basketObject.setImage(productById.getImage());
        basketObject.setImageLink(productById.getImageLink());
        basketObject.setPrice(productById.getPrice());
        basketObject.setUser(userById);
        basketObject.setCompany(productById.getCompany());

        basketObjectService.saveBasketObject(basketObject);

        basketObjects.add(basketObject);

        basketRepo.save(currentBasket);
        userById.setBasket(currentBasket);

        return productById.getCompany().getId();
    }

    public Basket getUserBasket(Long userId) {
        Basket basketByUserId = getBasketByUserId(userId);
        if (Objects.isNull(basketByUserId)) {
            Basket basket = new Basket();
            basket.setUser(userRepo.findUserById(userId));
            return basketRepo.save(basket);
        }
        return basketByUserId;
    }

    public Boolean isInBasket(String productId, String userId) {

        return getUserBasket(Long.valueOf(userId)).getBasketObjects().stream().anyMatch(o -> o.getProductId().equals(Long.valueOf(productId)));
    }

    public void changeAmount(Long productId, Integer amount) {
        BasketObject basketObject = basketObjectService.getBasketObjectById(productId);
        basketObject.setAmount(amount);
    }

    private Basket getBasketByUserId(Long id) {
        return basketRepo.findBasketByUserId(id);
    }

    public Basket getBasketById(Long id) {
        return basketRepo.findBasketById(id);
    }

    public void deleteBasketItem(Long id, User user) {
        BasketObject basketObject = basketObjectService.getBasketByProductIdAndUserId(id, user.getId());
        getBasketByUserId(user.getId()).getBasketObjects().remove(basketObject);
        basketObjectService.deleteBasketObject(basketObject);
    }

    public void clearBasket(final User user) {
        userRepo.findUserById(user.getId()).setBasket(null);
        basketRepo.delete(getBasketByUserId(user.getId()));
    }
}
