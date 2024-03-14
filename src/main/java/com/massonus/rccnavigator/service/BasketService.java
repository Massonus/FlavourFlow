package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.ItemDto;
import com.massonus.rccnavigator.entity.*;
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

    public Long addProductToBasket(Long productId, Long userId) {

        final Product productById = productService.getProductById(productId);

        final Basket currentBasket = getUserBasket(userId);
        User userById = userRepo.findUserById(userId);
        List<BasketObject> basketObjects = currentBasket.getBasketObjects();

        BasketObject basketObject = new BasketObject();
        basketObject.setProduct(productById);
        basketObject.setTitle(productById.getTitle());
        basketObject.setImage(productById.getImage());
        basketObject.setImageLink(productById.getImageLink());
        basketObject.setPrice(productById.getPrice());
        basketObject.setUser(userById);
        basketObject.setCompany(productById.getCompany());
        basketObject.setBasket(currentBasket);

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

        return getUserBasket(Long.valueOf(userId)).getBasketObjects().stream().anyMatch(o -> o.getProduct().getId().equals(Long.valueOf(productId)));
    }

    public ItemDto changeAmount(final ItemDto itemDto) {
        BasketObject basketObject = basketObjectService.getBasketObjectById(itemDto.getProductId());
        basketObject.setAmount(itemDto.getAmount());
        itemDto.setSum(basketObject.getSum());
        itemDto.setTotal(getBasketTotal(itemDto.getUserId()));
        return itemDto;
    }

    public Double getBasketTotal(Long userId) {
        return getUserBasket(userId).getTotal();
    }

    private Basket getBasketByUserId(Long id) {
        return basketRepo.findBasketByUserId(id);
    }

    public Basket getBasketById(Long id) {
        return basketRepo.findBasketById(id);
    }

    public List<Company> getAllCompaniesInUserBasket(User user) {

        List<BasketObject> basketObjects = basketObjectService.getBasketObjectsByUserId(user.getId());

        return basketObjects.stream()
                .map(BasketObject::getCompany)
                .distinct()
                .toList();
    }

    public ItemDto deleteBasketItem(final ItemDto itemDto, User user) {
        BasketObject basketObject = basketObjectService.getBasketObjectByProductIdAndUserId(itemDto.getProductId(), user.getId());
        getBasketByUserId(user.getId()).getBasketObjects().remove(basketObject);
        basketObjectService.deleteBasketObject(basketObject);
        itemDto.setTotal(getBasketTotal(user.getId()));
        itemDto.setItemId(basketObject.getId());
        return itemDto;
    }

    public void deleteBasketItemsByCompanyId(Long id, Long userId) {
        List<BasketObject> basketObjects = basketObjectService.getBasketObjectsByCompanyIdAndUserId(id, userId);
        getBasketByUserId(userId).getBasketObjects().removeAll(basketObjects);
        basketObjectService.deleteBasketObjectsByList(basketObjects);
    }

    public void clearBasket(final User user) {
        userRepo.findUserById(user.getId()).setBasket(null);
        basketRepo.delete(getBasketByUserId(user.getId()));
    }
}
