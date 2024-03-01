package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.BasketService;
import com.massonus.rccnavigator.service.MessageService;
import com.massonus.rccnavigator.service.ProductService;
import com.massonus.rccnavigator.service.WishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final BasketService basketService;
    private final WishService wishService;
    private final MessageService messageService;

    @Autowired
    public ProductController(ProductService productService, BasketService basketService, WishService wishService, MessageService messageService) {
        this.productService = productService;
        this.basketService = basketService;
        this.wishService = wishService;
        this.messageService = messageService;
    }

    @GetMapping("/all-products/{id}")
    public String getProductsOfCompany(@PathVariable Long id, Model model) {
        Set<Product> products = productService.getAllProductsByCompanyId(id);

        model.addAttribute("products", products);
        model.addAttribute("id", id);

        return "product/allProducts";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/all-products/{id}")
    public String getProductsOfCompanyForAdmin(@PathVariable Long id, Model model) {
        Set<Product> products = productService.getAllProductsByCompanyId(id);
        model.addAttribute("products", products);
        model.addAttribute("id", id);

        return "product/productsInAdminPanel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add-product/{id}")
    public String addProduct(@PathVariable Long id, Model model) {

        model.addAttribute("id", id);

        return "product/addProductToCompany";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new-product/{companyId}")
    public String newProduct(@PathVariable Long companyId,
                             @Valid Product product,
                             @RequestParam("file") MultipartFile multipartFile,
                             @RequestParam String imageLink) {

        productService.saveProduct(product, multipartFile, imageLink, companyId);

        return "redirect:/product/admin/all-products/" + companyId;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/productEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit/{id}")
    public String saveUpdatedProduct(@PathVariable Long id,
                                     @RequestParam("file") MultipartFile multipartFile,
                                     @RequestParam String imageLink,
                                     Product product) {

        Long companyId = productService.editProduct(id, product, multipartFile, imageLink);

        return "redirect:/product/admin/all-products/" + companyId;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        productService.deleteProduct(productById);
        return "redirect:/product/admin/all-products/" + productById.getCompany().getId();
    }

    @GetMapping("/new-basket-item/{id}")
    @ResponseBody
    public String addProductToBasket(@PathVariable Long id, @AuthenticationPrincipal User user) {

        Long companyId = basketService.addProductToBasket(id, user);

        return "redirect:/product/all-products/" + companyId;
    }

    @GetMapping("/new-wish-item/{id}")
    public String addProductToWishes(@PathVariable Long id, @AuthenticationPrincipal User user) {

        Long companyId = wishService.addProductToWishes(id, user);

        return "redirect:/product/all-products/" + companyId;
    }

    @GetMapping("/{id}")
    public String getProduct(@AuthenticationPrincipal User user, @PathVariable Long id, Model model) {
        Product productById = productService.getProductById(id);

        model.addAttribute("user", user);
        model.addAttribute("product", productById);
        model.addAttribute("messages", messageService.getMessagesByProductId(id));
        return "product/productInfo";
    }

}
