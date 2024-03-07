package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.OrderDto;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        System.out.println(orderDto.getDate());

        return orderDto;

    }

    
}
