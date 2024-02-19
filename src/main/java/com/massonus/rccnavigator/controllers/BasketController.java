package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Basket;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.BasketService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public String getBasket(Model model, @AuthenticationPrincipal User user) {

        Basket userBasket = basketService.getUserBasket(user);
        Set<Product> products = userBasket.getProducts();
        model.addAttribute("products", products);

        return "basket/basket";
    }

    @GetMapping("/delete-from-basket/{id}")
    public String deleteProductFromBasket(@PathVariable Long id, @AuthenticationPrincipal User user) {

        basketService.deleteBasketItem(id, user);

        return "redirect:/basket";

    }

    @GetMapping("/clear")
    public String clearBasket(@AuthenticationPrincipal User user) {

        basketService.clearBasket(user);

        return "redirect:/basket";
    }


}
