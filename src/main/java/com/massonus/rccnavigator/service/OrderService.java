package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.OrderDto;
import com.massonus.rccnavigator.entity.BasketObject;
import com.massonus.rccnavigator.entity.Order;
import com.massonus.rccnavigator.entity.OrderObject;
import com.massonus.rccnavigator.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderObjectService orderObjectService;
    private final BasketService basketService;
    private final BasketObjectService basketObjectService;
    private final CompanyService companyService;
    private final UserService userService;

    @Autowired
    public OrderService(OrderRepo orderRepo, OrderObjectService orderObjectService, BasketService basketService, BasketObjectService basketObjectService, CompanyService companyService, UserService userService) {
        this.orderRepo = orderRepo;
        this.orderObjectService = orderObjectService;
        this.basketService = basketService;
        this.basketObjectService = basketObjectService;
        this.companyService = companyService;
        this.userService = userService;
    }

    public OrderDto checkout(final OrderDto orderDto) {

        if (orderDto.getTime().isBefore(LocalTime.of(7, 0)) || orderDto.getTime().isAfter(LocalTime.of(19, 0))) {
            orderDto.setIsTimeError(true);
            return orderDto;
        }

        Order order = new Order();
        order.setUser(userService.getUserById(orderDto.getUserId()));
        order.setDate(orderDto.getDate());
        order.setTime(orderDto.getTime());
        order.setCountGuests(orderDto.getCountGuests());
        order.setCompany(companyService.getCompanyById(orderDto.getCompanyId()));

        List<BasketObject> basketObjects = basketObjectService.getBasketObjectsByUserId(orderDto.getUserId()).stream()
                .filter(b -> b.getCompany().getId().equals(orderDto.getCompanyId()))
                .toList();

        double total = basketObjects.stream()
                .mapToDouble(BasketObject::getSum)
                .sum();

        order.setTotal(total);

        orderRepo.save(order);

        for (BasketObject basketObject : basketObjects) {

            OrderObject orderObject = new OrderObject();
            orderObject.setTitle(basketObject.getTitle());
            orderObject.setCompany(basketObject.getCompany());
            orderObject.setUser(userService.getUserById(orderDto.getUserId()));
            orderObject.setAmount(basketObject.getAmount());
            orderObject.setSum(basketObject.getSum());
            orderObject.setProduct(basketObject.getProduct());
            orderObject.setOrder(order);

            orderObjectService.saveOrderObject(orderObject);
        }

        createOrder(orderDto, order.getId());

        basketService.deleteBasketItemsByCompanyId(orderDto.getCompanyId(), orderDto.getUserId());

        orderDto.setIsSuccess(true);
        return orderDto;
    }

    private void createOrder(final OrderDto orderDto, final Long orderId) {

        Order order = getOrderById(orderId);

        orderRepo.save(order);

        companyService.getCompanyById(orderDto.getCompanyId()).getOrders().add(order);

        userService.getUserById(orderDto.getUserId()).setOrders(Collections.singleton(order));

    }

    public Order getOrderById(Long id) {
        return orderRepo.findOrderById(id);
    }

    public List<Order> getUserOrders(Long userId) {

        return orderRepo.findOrdersByUserId(userId);

    }

}
