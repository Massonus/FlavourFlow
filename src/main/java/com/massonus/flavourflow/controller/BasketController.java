package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.dto.CheckDto;
import com.massonus.flavourflow.dto.ItemDto;
import com.massonus.flavourflow.entity.Basket;
import com.massonus.flavourflow.entity.BasketObject;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.service.BasketService;
import com.massonus.flavourflow.service.ProductService;
import com.massonus.flavourflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public BasketController(BasketService basketService, ProductService productService, UserService userService) {
        this.basketService = basketService;
        this.productService = productService;
        this.userService = userService;
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
        model.addAttribute("bonuses", userService.getUserBonuses(user.getId()));

        if (Objects.nonNull(size)) {
            model.addAttribute("modal", "modal open");
            model.addAttribute("companies", basketService.getAllCompaniesInUserBasket(user));
        } else {
            model.addAttribute("modal", "modal");
        }

        if (Objects.nonNull(companyId)) {
            model.addAttribute("orderModal", "modal open");
            model.addAttribute("companyId", companyId);
            model.addAttribute("bonuses", userService.getUserBonuses(user.getId()));
        } else {
            model.addAttribute("companyId", basketService.getAllCompaniesInUserBasket(user).isEmpty() ? 0L : basketService.getAllCompaniesInUserBasket(user).getFirst().getId());
        }

        return "basket/basket";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-item")
    @ResponseBody
    public Long addProductToBasket(@RequestParam Long productId, @AuthenticationPrincipal User user) {

        return basketService.addProductToBasket(productService.getProductById(productId), user);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete-item")
    @ResponseBody
    public ItemDto deleteProductFromBasket(@RequestBody ItemDto itemDto, @AuthenticationPrincipal User user) {

        return basketService.deleteBasketItem(itemDto, user);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change-amount")
    @ResponseBody
    public ItemDto changeProductAmount(@RequestBody ItemDto itemDto, @AuthenticationPrincipal User user) {

        itemDto.setUserId(user.getId());

        return basketService.changeAmount(itemDto);

    }

    @GetMapping("/count")
    @ResponseBody
    public Integer getBasketCount(@AuthenticationPrincipal User user) {

        return basketService.getBasketObjectsCount(user.getId());

    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/clear")
    @ResponseBody
    public String clearBasket(@AuthenticationPrincipal User user) {

        basketService.clearBasket(user);

        return "redirect:/basket";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/check")
    @ResponseBody
    public CheckDto checkOrder(@AuthenticationPrincipal User user) {

        int amountCompanies = basketService.getAllCompaniesInUserBasket(user).size();

        CheckDto checkDto = new CheckDto();
        checkDto.setSize(amountCompanies);
        checkDto.setIsSuccess(amountCompanies <= 1);

        return checkDto;
    }
}
