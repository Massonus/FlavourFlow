package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.OrderDto;
import com.massonus.rccnavigator.entity.Order;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public OrderDto checkout(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user) {

        orderDto.setUser(user);

        return orderService.checkout(orderDto);

    }

    @GetMapping()
    public String getUserOrders(@AuthenticationPrincipal User user, Model model) {

        List<Order> userOrders = orderService.getUserOrders(user.getId());

        model.addAttribute("orders", userOrders);
        /*model.addAttribute("orderObjects", )*/

        return "order/ordersInfo";

    }


}
