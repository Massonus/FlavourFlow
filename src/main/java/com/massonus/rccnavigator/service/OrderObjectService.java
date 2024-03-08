package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.OrderObject;
import com.massonus.rccnavigator.repo.OrderObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderObjectService {

    private final OrderObjectRepo orderObjectRepo;

    @Autowired
    public OrderObjectService(OrderObjectRepo orderObjectRepo) {
        this.orderObjectRepo = orderObjectRepo;
    }

    public void saveOrderObject(OrderObject orderObject) {
        orderObjectRepo.save(orderObject);
    }

    public List<OrderObject> getOrderObjectsByUserId(Long userId) {
        return orderObjectRepo.findOrderObjectsByUserId(userId);
    }
}
