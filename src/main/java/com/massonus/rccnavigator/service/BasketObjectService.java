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

    public void saveBasketObject(final BasketObject basketObject) {

        basketObjectRepo.save(basketObject);
    }

    public List<BasketObject> getAllBasketObjects() {
        return basketObjectRepo.findAll();
    }

    public void deleteBasketObject(final BasketObject basketObject) {
        basketObjectRepo.delete(basketObject);
    }

    public BasketObject getBasketObjectById(Long id) {
        return basketObjectRepo.findBasketObjectById(id);
    }

    public BasketObject getBasketObjectByProductId(Long productId) {
        return basketObjectRepo.findBasketObjectByProductId(productId);
    }
}
