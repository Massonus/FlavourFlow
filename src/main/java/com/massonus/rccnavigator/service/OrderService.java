package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.OrderDto;
import com.massonus.rccnavigator.entity.BasketObject;
import com.massonus.rccnavigator.entity.Order;
import com.massonus.rccnavigator.entity.OrderObject;
import com.massonus.rccnavigator.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderObjectService orderObjectService;
    private final BasketService basketService;
    private final BasketObjectService basketObjectService;
    private final CompanyService companyService;

    @Autowired
    public OrderService(OrderRepo orderRepo, OrderObjectService orderObjectService, BasketService basketService, BasketObjectService basketObjectService, CompanyService companyService) {
        this.orderRepo = orderRepo;
        this.orderObjectService = orderObjectService;
        this.basketService = basketService;
        this.basketObjectService = basketObjectService;
        this.companyService = companyService;
    }

    public OrderDto checkout(final OrderDto orderDto) {

        List<BasketObject> basketObjects = basketObjectService.getBasketObjectsByUserId(orderDto.getUser().getId()).stream()
                .filter(b -> b.getCompany().getId().equals(orderDto.getCompanyId()))
                .toList();

        List<OrderObject> orderObjects = new ArrayList<>();

        for (BasketObject basketObject : basketObjects) {

            OrderObject orderObject = new OrderObject();
            orderObject.setTitle(basketObject.getTitle());
            orderObject.setCompany(basketObject.getCompany());
            orderObject.setUser(orderDto.getUser());
            orderObject.setAmount(basketObject.getAmount());
            orderObject.setSum(basketObject.getSum());
            orderObject.setProductId(basketObject.getProduct().getId());
            orderObjects.add(orderObject);

            orderObjectService.saveOrderObject(orderObject);
        }

        createOrder(orderObjects, orderDto);
        basketService.deleteBasketItemsByCompanyId(orderDto.getCompanyId(), orderDto.getUser());

        orderDto.setIsSuccess(true);
        return orderDto;
    }

    private void createOrder(final List<OrderObject> orderObjects, final OrderDto orderDto) {

        Order order = new Order();
        order.setUser(orderDto.getUser());
        order.setDate(orderDto.getDate());
        order.setOrderObjects(orderObjects);
        order.setCompany(companyService.getCompanyById(orderDto.getCompanyId()));


        double total = orderObjects.stream()
                .mapToDouble(OrderObject::getSum)
                .sum();

        order.setTotal(total);

        orderRepo.save(order);

        companyService.getCompanyById(orderDto.getCompanyId()).getOrders().add(order);

        orderDto.getUser().setOrders(Collections.singleton(order));

        List<OrderObject> objects = orderObjectService.getOrderObjectsByUserId(orderDto.getUser().getId());

        for (OrderObject object : objects) {
            object.setOrder(order);
        }

        orderDto.getUser().setOrderObjects(objects);

    }

    public List<Order> getUserOrders(Long userId) {

        return orderRepo.findOrdersByUserId(userId);

    }

}
