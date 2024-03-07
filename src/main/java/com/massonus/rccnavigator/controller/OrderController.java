package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.OrderDto;
import com.massonus.rccnavigator.entity.Order;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.BasketObjectRepo;
import com.massonus.rccnavigator.repo.BasketRepo;
import com.massonus.rccnavigator.repo.ProductRepo;
import com.massonus.rccnavigator.service.BasketService;
import com.massonus.rccnavigator.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @ResponseBody
    public OrderDto createOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user) {

        return orderService.checkout(orderDto, user);

    }




}
