package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.BasketObjectRepo;
import com.massonus.rccnavigator.service.BasketService;
import com.massonus.rccnavigator.service.MessageService;
import com.massonus.rccnavigator.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final MessageService messageService;
    private final BasketObjectRepo basketObjectRepo;
    private final BasketService basketService;

    @Autowired
    public ProductController(ProductService productService, MessageService messageService, BasketObjectRepo basketObjectRepo, BasketService basketService) {
        this.productService = productService;
        this.messageService = messageService;
        this.basketObjectRepo = basketObjectRepo;
        this.basketService = basketService;
    }

    @GetMapping("/all-products")
    public String getProductsOfCompany(@RequestParam Long id, Model model,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(required = false) String sort) {

        int pageSize = 4;

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productService.getProductsInPage(id, pageable, sort);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("objects", basketObjectRepo.findAll());
        model.addAttribute("service", basketService);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("id", id);
        model.addAttribute("sort", sort == null ? "Default" : sort);

        return "product/allProducts";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/all-products/{id}")
    public String getProductsOfCompanyForAdmin(@PathVariable Long id, Model model) {
        List<Product> products = productService.getAllProductsByCompanyId(id);
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


    @GetMapping("/{id}")
    public String getProduct(@AuthenticationPrincipal User user, @PathVariable Long id, Model model) {
        Product productById = productService.getProductById(id);

        model.addAttribute("user", user);
        model.addAttribute("product", productById);
        model.addAttribute("messages", messageService.getMessagesByProductId(id));
        return "product/productInfo";
    }

}
