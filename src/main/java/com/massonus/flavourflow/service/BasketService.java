package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.ItemDto;
import com.massonus.flavourflow.entity.*;
import com.massonus.flavourflow.repo.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BasketService {
    private final BasketRepo basketRepo;
    private final BasketObjectService basketObjectService;
    private final UserService userService;

    @Autowired
    public BasketService(BasketRepo basketRepo, BasketObjectService basketObjectService, UserService userService) {
        this.basketRepo = basketRepo;
        this.basketObjectService = basketObjectService;
        this.userService = userService;
    }

    public Long addProductToBasket(final Product product, final User user) {
        final Basket currentBasket = getUserBasket(user.getId());
        List<BasketObject> basketObjects = currentBasket.getBasketObjects();

        BasketObject basketObject = new BasketObject();
        basketObject.setProduct(product);
        basketObject.setTitle(product.getTitle());
        basketObject.setImageLink(product.getImageLink());
        basketObject.setPrice(product.getPrice());
        basketObject.setUser(user);
        basketObject.setCompany(product.getCompany());
        basketObject.setBasket(currentBasket);

        basketObjects.add(basketObject);

        basketRepo.save(currentBasket);
        user.setBasket(currentBasket);

        return product.getCompany().getId();
    }

    public Basket getUserBasket(Long userId) {
        Basket basketByUserId = getBasketByUserId(userId);
        if (Objects.isNull(basketByUserId)) {
            Basket basket = new Basket();
            basket.setUser(userService.getUserById(userId));
            return basketRepo.save(basket);
        }
        return basketByUserId;
    }

    public Boolean isInBasket(String productId, String userId) {

        return getUserBasket(Long.valueOf(userId)).getBasketObjects().stream()
                .anyMatch(o -> o.getProduct().getId().equals(Long.valueOf(productId)));
    }

    public ItemDto changeAmount(final ItemDto itemDto) {
        BasketObject basketObject = basketObjectService.getBasketObjectById(itemDto.getProductId());
        basketObject.setAmount(itemDto.getAmount());
        itemDto.setSum(basketObject.getSum());
        itemDto.setTotal(getBasketTotal(itemDto.getUserId()));
        return itemDto;
    }

    private Double getBasketTotal(Long userId) {
        return getUserBasket(userId).getTotal();
    }

    private Basket getBasketByUserId(Long id) {
        return basketRepo.findBasketByUserId(id);
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

    public Boolean clearBasket(final User user) {
        basketRepo.delete(getBasketByUserId(user.getId()));
        return basketObjectService.getBasketObjectsByUserId(user.getId()).isEmpty();
    }
}
