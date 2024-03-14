package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.OrderObject;
import com.massonus.rccnavigator.repo.OrderObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderObjectService {

    private final OrderObjectRepo objectRepo;

    @Autowired
    public OrderObjectService(OrderObjectRepo objectRepo) {
        this.objectRepo = objectRepo;
    }

    public void saveOrderObject(OrderObject orderObject) {
        objectRepo.save(orderObject);
    }
}
