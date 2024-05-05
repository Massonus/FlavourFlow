package com.massonus.flavourflow.service;

import com.massonus.flavourflow.entity.OrderObject;
import com.massonus.flavourflow.repo.OrderObjectRepo;
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
