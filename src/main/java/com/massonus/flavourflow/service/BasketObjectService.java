package com.massonus.flavourflow.service;

import com.massonus.flavourflow.entity.BasketObject;
import com.massonus.flavourflow.repo.BasketObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BasketObjectService {

    private final BasketObjectRepo basketObjectRepo;

    @Autowired
    public BasketObjectService(BasketObjectRepo basketObjectRepo) {
        this.basketObjectRepo = basketObjectRepo;
    }

    public List<BasketObject> getAllBasketObjects() {
        return basketObjectRepo.findAll();
    }

    public void deleteBasketObject(final BasketObject basketObject) {
        basketObjectRepo.delete(basketObject);
    }

    public void deleteBasketObjectsByList(List<BasketObject> basketObjects) {
        basketObjectRepo.deleteAll(basketObjects);
    }

    public void setBasketObjectDbxImage(final Long productId, final String url) {
        BasketObject object = getBasketObjectByProductId(productId);
        if (Objects.nonNull(object)) {
            object.setImageLink(url);
        }
    }

    public BasketObject getBasketObjectById(Long id) {
        return basketObjectRepo.findBasketObjectById(id);
    }

    public BasketObject getBasketObjectByProductId(Long productId) {
        return basketObjectRepo.findBasketObjectByProductId(productId);
    }

    public BasketObject getBasketObjectByProductIdAndUserId(Long productId, Long userId) {

        return basketObjectRepo.findBasketObjectByProductIdAndUserId(productId, userId);
    }

    public List<BasketObject> getBasketObjectsByUserId(Long userId) {
        return basketObjectRepo.findBasketObjectsByUserId(userId);
    }

    public List<BasketObject> getBasketObjectsByCompanyIdAndUserId(Long companyId, Long userId) {

        return basketObjectRepo.findBasketObjectsByCompanyIdAndUserId(companyId, userId);

    }
}
