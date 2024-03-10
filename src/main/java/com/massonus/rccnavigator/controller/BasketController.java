package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.BasketCheckDto;
import com.massonus.rccnavigator.dto.BasketObjectDto;
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
import java.util.Objects;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public String getBasket(Model model, @AuthenticationPrincipal User user,
                            @RequestParam(required = false) Integer size,
                            @RequestParam(required = false) Long companyId) {

        Basket userBasket = basketService.getUserBasket(user.getId());
        List<BasketObject> basketObjects = userBasket.getBasketObjects().stream().sorted(Comparator.comparing(BasketObject::getTitle)).toList();
        model.addAttribute("products", basketObjects);
        model.addAttribute("basket", userBasket);
        model.addAttribute("size", size);
        model.addAttribute("orderModal", "modal");

        if (Objects.nonNull(size)) {
            model.addAttribute("modal", "modal open");
            model.addAttribute("companies", basketService.getAllCompaniesInUserBasket(user));
        } else {
            model.addAttribute("modal", "modal");
        }

        if (Objects.nonNull(companyId)) {
            model.addAttribute("orderModal", "modal open");
            model.addAttribute("companyId", companyId);
        } else {
            model.addAttribute("companyId", basketService.getAllCompaniesInUserBasket(user).isEmpty() ? 0L : basketService.getAllCompaniesInUserBasket(user).getFirst().getId());
        }

        return "basket/basket";
    }

    @PostMapping("/add-item")
    @ResponseBody
    public String addProductToBasket(@RequestParam Long productId, @AuthenticationPrincipal User user) {

        Long companyId = basketService.addProductToBasket(productId, user.getId());

        return "redirect:/product/all-products/" + companyId;
    }

    @DeleteMapping("/delete-item")
    @ResponseBody
    public Double deleteProductFromBasket(@RequestParam Long productId, @AuthenticationPrincipal User user) {

        basketService.deleteBasketItem(productId, user);

        return basketService.getBasketTotal(user.getId());

    }

    @PutMapping("/change-amount")
    @ResponseBody
    public BasketObjectDto changeProductAmount(@RequestBody BasketObjectDto basketObjectDto, @AuthenticationPrincipal User user) {

        basketObjectDto.setUserId(user.getId());

        return basketService.changeAmount(basketObjectDto);

    }

    @DeleteMapping("/clear")
    @ResponseBody
    public String clearBasket(@AuthenticationPrincipal User user) {

        basketService.clearBasket(user);

        return "redirect:/basket";
    }

    @GetMapping("/check")
    @ResponseBody
    public BasketCheckDto checkOrder(@AuthenticationPrincipal User user) {

        int amountCompanies = basketService.getAllCompaniesInUserBasket(user).size();

        BasketCheckDto basketCheckDto = new BasketCheckDto();
        basketCheckDto.setSize(amountCompanies);
        basketCheckDto.setIsSuccess(amountCompanies <= 1);

        return basketCheckDto;

    }


}
