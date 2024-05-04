package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.OrderDto;
import com.massonus.flavourflow.entity.BasketObject;
import com.massonus.flavourflow.entity.Order;
import com.massonus.flavourflow.entity.OrderObject;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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

        if (checkOrderTime(orderDto).getIsTimeError()) {
            return orderDto;
        }

        List<BasketObject> basketObjects = getWantedBasketObjects(orderDto);
        Order order = createOrder(orderDto, getTotal(basketObjects));

        orderRepo.save(order);
        createAndSaveOrderObjects(basketObjects, order);

        basketService.deleteBasketItemsByCompanyId(orderDto.getCompanyId(), orderDto.getUserId());

        orderDto.setIsSuccess(true);
        return orderDto;
    }

    private Order createOrder(final OrderDto orderDto, final Double total) {
        Order order = new Order();
        User userById = userService.getUserById(orderDto.getUserId());
        order.setUser(userById);
        order.setDate(orderDto.getDate());
        order.setTime(orderDto.getTime());
        order.setCompany(companyService.getCompanyById(orderDto.getCompanyId()));
        order.setTotal(total - orderDto.getBonuses());
        order.setAddress(orderDto.getAddress());

        if (orderDto.getBonuses() == 0) {
            userById.setBonuses(userById.getBonuses() + order.getOrderBonuses());
        } else {
            userById.setBonuses(userById.getBonuses() - orderDto.getBonuses());
        }

        return order;
    }

    private void createAndSaveOrderObjects(final List<BasketObject> basketObjects, final Order order) {
        for (BasketObject basketObject : basketObjects) {
            OrderObject orderObject = new OrderObject();
            orderObject.setTitle(basketObject.getTitle());
            orderObject.setCompany(basketObject.getCompany());
            orderObject.setAmount(basketObject.getAmount());
            orderObject.setSum(basketObject.getSum());
            orderObject.setProduct(basketObject.getProduct());

            orderObject.setUser(order.getUser());
            orderObject.setOrder(order);

            orderObjectService.saveOrderObject(orderObject);
        }
    }

    private List<BasketObject> getWantedBasketObjects(final OrderDto orderDto) {
        return basketObjectService.getBasketObjectsByUserId(orderDto.getUserId()).stream()
                .filter(b -> b.getCompany().getId().equals(orderDto.getCompanyId()))
                .toList();
    }

    private Double getTotal(final List<BasketObject> basketObjects) {
        return basketObjects.stream()
                .mapToDouble(BasketObject::getSum)
                .sum();
    }

    private OrderDto checkOrderTime(final OrderDto orderDto) {
        boolean isBefore = orderDto.getTime().isBefore(LocalTime.of(7, 0));
        boolean isAfter = orderDto.getTime().isAfter(LocalTime.of(19, 0));

        orderDto.setIsTimeError(isBefore || isAfter);

        return orderDto;
    }

    public List<Order> getUserOrders(Long userId) {

        return orderRepo.findOrdersByUserId(userId);

    }

}
