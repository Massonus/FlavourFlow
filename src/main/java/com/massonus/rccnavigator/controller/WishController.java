package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.ItemDto;
import com.massonus.rccnavigator.entity.*;
import com.massonus.rccnavigator.service.BasketObjectService;
import com.massonus.rccnavigator.service.BasketService;
import com.massonus.rccnavigator.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishes")
public class WishController {

    private final WishService wishService;
    private final BasketService basketService;
    private final BasketObjectService basketObjectService;

    @Autowired
    public WishController(WishService wishService, BasketService basketService, BasketObjectService basketObjectService) {
        this.wishService = wishService;
        this.basketService = basketService;
        this.basketObjectService = basketObjectService;
    }

    @GetMapping
    public String getWishes(Model model, @AuthenticationPrincipal User user) {

        Wish userWish = wishService.getUserWish(user.getId());
        List<WishObject> products = userWish.getWishObjects();
        model.addAttribute("products", products);

        return "wish/wishes";
    }

    @PostMapping("/add-item")
    @ResponseBody
    public Long addWishItem(@RequestParam Long id, @AuthenticationPrincipal User user) {

        return wishService.addProductToWishes(id, user.getId());
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ItemDto deleteProductFromWishes(@RequestBody ItemDto itemDto, @AuthenticationPrincipal User user) {

        return wishService.deleteWishItem(itemDto, user);

    }

    @DeleteMapping("/move")
    @ResponseBody
    public ItemDto moveToBasket(@RequestBody ItemDto itemDto, @AuthenticationPrincipal User user) {

        Basket userBasket = basketService.getUserBasket(user.getId());
        List<BasketObject> basketObjects = userBasket.getBasketObjects();

        if (basketObjects.contains(basketObjectService.getBasketObjectByProductId(itemDto.getProductId()))) {
            itemDto.setIsInBasket(true);
        } else {
            basketService.addProductToBasket(itemDto.getProductId(), user.getId());
            itemDto.setItemId(wishService.deleteWishItem(itemDto, user).getItemId());
            itemDto.setIsInBasket(false);
        }

        return itemDto;
    }

    @DeleteMapping("/clear")
    @ResponseBody
    public String clearWishes(@AuthenticationPrincipal User user) {

        wishService.clearWishes(user);

        return "redirect:/wishes";
    }
}
