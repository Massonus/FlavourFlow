package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.BasketService;
import com.massonus.rccnavigator.service.ImageService;
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

import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;
    private final BasketService basketService;
    private final WishService wishService;


    @Autowired
    public ProductController(ProductService productService, ImageService imageService, BasketService basketService, WishService wishService) {
        this.productService = productService;
        this.imageService = imageService;
        this.basketService = basketService;
        this.wishService = wishService;
    }

    @GetMapping("/all-products/{id}")
    public String getProductsOfCompany(@PathVariable Long id, Model model) {
        Set<Product> products = productService.getAllProductsByCompanyId(id);

        model.addAttribute("products", products);
        model.addAttribute("id", id);

        return "product/allProducts";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new-product/{companyId}")
    public String newProduct(@PathVariable Long companyId, @Valid Product product,
                             @RequestParam("file") MultipartFile multipartFile) {

        Image uploadImage;
        try {
            uploadImage = imageService.upload(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        productService.saveProduct(product, uploadImage, companyId);

        return "redirect:/product/all-products/" + companyId;
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
                                     Product product) {

        Image uploadImage;
        try {
            uploadImage = imageService.upload(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Long companyId = productService.editProduct(id, product, uploadImage);

        return "redirect:/product/all-products/" + companyId;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        productService.deleteProduct(productById);
        return "redirect:/product/all-products/" + productById.getCompany().getId();
    }

    @GetMapping("/new-basket-item/{id}")
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
        return "product/productInfo";
    }

}
