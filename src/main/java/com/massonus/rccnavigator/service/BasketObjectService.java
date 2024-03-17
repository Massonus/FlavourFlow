package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.BasketObject;
import com.massonus.rccnavigator.repo.BasketObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public BasketObject getBasketObjectById(Long id) {
        return basketObjectRepo.findBasketObjectById(id);
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
