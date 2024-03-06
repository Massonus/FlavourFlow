package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Basket;
import com.massonus.rccnavigator.entity.BasketObject;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public String getBasket(Model model, @AuthenticationPrincipal User user) {

        Basket userBasket = basketService.getUserBasket(user.getId());
        List<BasketObject> basketObjects = userBasket.getBasketObjects().stream().sorted(Comparator.comparing(BasketObject::getTitle)).toList();
        model.addAttribute("products", basketObjects);

        return "basket/basket";
    }

    @GetMapping("/new-basket-item/{id}")
    @ResponseBody
    public String addProductToBasket(@PathVariable Long id, @AuthenticationPrincipal User user) {

        Long companyId = basketService.addProductToBasket(id, user.getId());

        return "redirect:/product/all-products/" + companyId;
    }

    @GetMapping("/delete-from-basket/{id}")
    public String deleteProductFromBasket(@PathVariable Long id, @AuthenticationPrincipal User user) {

        basketService.deleteBasketItem(id, user);

        return "redirect:/basket";

    }

    @GetMapping("/change-amount")
    public String changeProductAmount(@RequestParam Integer amount,
                                      @RequestParam Long productId) {

        basketService.changeAmount(productId, amount);

        return "redirect:/basket";
    }

    @GetMapping("/clear")
    public String clearBasket(@AuthenticationPrincipal User user) {

        basketService.clearBasket(user);

        return "redirect:/basket";
    }


}
