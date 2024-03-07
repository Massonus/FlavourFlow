package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.Order;
import com.massonus.rccnavigator.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    private final OrderObjectService orderObjectService;

    @Autowired
    public OrderService(OrderRepo orderRepo, OrderObjectService orderObjectService) {
        this.orderRepo = orderRepo;
        this.orderObjectService = orderObjectService;
    }

    public void saveOrder(Order order) {
        orderRepo.save(order);
    }
}
