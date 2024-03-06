package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.entity.*;
import com.massonus.rccnavigator.repo.BasketObjectRepo;
import com.massonus.rccnavigator.service.BasketService;
import com.massonus.rccnavigator.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/wishes")
public class WishController {

    private final WishService wishService;
    private final BasketService basketService;
    private final BasketObjectRepo basketObjectRepo;

    @Autowired
    public WishController(WishService wishService, BasketService basketService, BasketObjectRepo basketObjectRepo) {
        this.wishService = wishService;
        this.basketService = basketService;
        this.basketObjectRepo = basketObjectRepo;
    }

    @GetMapping
    public String getWishes(Model model, @AuthenticationPrincipal User user) {

        Wish userWish = wishService.getUserWish(user.getId());
        Set<Product> products = userWish.getProducts();
        model.addAttribute("products", products);

        return "wish/wishes";
    }

    @GetMapping("/new-wish-item/{id}")
    public String addProductToWishes(@PathVariable Long id, @AuthenticationPrincipal User user) {

        Long companyId = wishService.addProductToWishes(id, user);

        return "redirect:/product/all-products?id=" + companyId;
    }

    @GetMapping("/move-wish-to-basket")
    @ResponseBody
    public Boolean moveToBasket(@RequestParam Long id, @AuthenticationPrincipal User user) {

        Basket userBasket = basketService.getUserBasket(user.getId());
        List<BasketObject> basketObjects = userBasket.getBasketObjects();

        if (basketObjects.contains(basketObjectRepo.findBasketObjectByProductId(id))) {
            return true;
        } else {
            basketService.addProductToBasket(id, user.getId());
            wishService.deleteWishItem(id, user);
            return false;
        }
    }

    @GetMapping("/delete-from-wishes/{id}")
    public String deleteProductFromWishes(@PathVariable Long id, @AuthenticationPrincipal User user) {

        wishService.deleteWishItem(id, user);

        return "redirect:/wishes";

    }
}
