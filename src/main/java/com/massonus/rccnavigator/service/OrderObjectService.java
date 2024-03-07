package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.repo.OrderObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderObjectService {

    private final OrderObjectRepo orderObjectRepo;

    @Autowired
    public OrderObjectService(OrderObjectRepo orderObjectRepo) {
        this.orderObjectRepo = orderObjectRepo;
    }
}
