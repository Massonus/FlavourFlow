package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.entity.Wish;
import com.massonus.rccnavigator.service.BasketService;
import com.massonus.rccnavigator.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/wishes")
public class WishController {

    private final WishService wishService;
    private final BasketService basketService;

    @Autowired
    public WishController(WishService wishService, BasketService basketService) {
        this.wishService = wishService;
        this.basketService = basketService;
    }

    @GetMapping
    public String getWishes(Model model, @AuthenticationPrincipal User user) {

        Wish userWish = wishService.getUserWish(user);
        Set<Product> products = userWish.getProducts();
        model.addAttribute("products", products);

        return "wish/wishes";
    }

    @GetMapping("/move-wish-to-basket/{id}")
    public String moveToBasket(@PathVariable Long id, @AuthenticationPrincipal User user) {

        basketService.addProductToBasket(id, user);
        wishService.deleteWishItem(id, user);

        return "redirect:/wishes";

    }

    @GetMapping("/delete-from-wishes/{id}")
    public String deleteProductFromWishes(@PathVariable Long id, @AuthenticationPrincipal User user) {


        wishService.deleteWishItem(id, user);

        return "redirect:/wishes";

    }
}
