package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.ProductDto;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final MessageService messageService;
    private final BasketObjectService basketObjectService;
    private final BasketService basketService;
    private final WishService wishService;

    @Autowired
    public ProductController(ProductService productService, MessageService messageService, BasketObjectService basketObjectService, BasketService basketService, WishService wishService) {
        this.productService = productService;
        this.messageService = messageService;
        this.basketObjectService = basketObjectService;
        this.basketService = basketService;
        this.wishService = wishService;
    }

    @GetMapping("/all-products")
    public String getProductsOfCompany(@RequestParam Long id, Model model,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(required = false) String sort) {

        int pageSize = 4;

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productService.getProductsInPage(id, pageable, sort);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("objects", basketObjectService.getAllBasketObjects());
        model.addAttribute("basketService", basketService);
        model.addAttribute("wishService", wishService);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("id", id);
        model.addAttribute("sort", sort == null ? "Default" : sort);

        return "product/allProducts";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/all-products")
    public String getProductsOfCompanyForAdmin(@RequestParam Long id, Model model) {
        List<Product> products = productService.getAllProductsByCompanyId(id);
        model.addAttribute("products", products);
        model.addAttribute("id", id);

        return "product/productsInAdminPanel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add-product/{companyId}")
    public String addProduct(@PathVariable Long companyId, Model model) {

        model.addAttribute("companyId", companyId);

        return "product/addProductToCompany";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public void newProduct(@RequestBody ProductDto productDto) {

        productService.saveProduct(productDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/productEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/edit")
    public void saveUpdatedProduct(@RequestBody ProductDto productDto) {

        Long companyId = productService.editProduct(productDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        productService.deleteProduct(productById);
        return "redirect:/product/admin/all-products?id=" + productById.getCompany().getId();
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
