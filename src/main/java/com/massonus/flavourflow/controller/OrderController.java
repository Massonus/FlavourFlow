package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.dto.OrderDto;
import com.massonus.flavourflow.entity.Order;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@PreAuthorize("isAuthenticated()")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @ResponseBody
    public OrderDto checkout(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user) {

        orderDto.setUserId(user.getId());
        return orderService.checkout(orderDto);
    }

    @GetMapping()
    public String getUserOrders(@AuthenticationPrincipal User user, Model model) {

        List<Order> userOrders = orderService.getUserOrders(user.getId());

        model.addAttribute("orders", userOrders);

        return "order/ordersInfo";

    }


}
