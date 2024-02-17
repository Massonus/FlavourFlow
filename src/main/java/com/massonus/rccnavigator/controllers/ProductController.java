package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.service.ImageService;
import com.massonus.rccnavigator.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;

    @Autowired
    public ProductController(ProductService productService, ImageService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public String getProductsOfCompany(@PathVariable Long id, Model model) {
        List<Product> products = productService.getAllProductsByCompanyId(id);

        model.addAttribute("products", products);
        model.addAttribute("id", id);

        return "product/allProducts";
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
                                     Product product) {

        Long companyId = productService.editProduct(id, product);
        return "redirect:/products/" + companyId;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteTree(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        productService.deleteProduct(productById);
        return "redirect:/products/" + productById.getCompany().getId();
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

        return "redirect:/products/" + companyId;
    }
}
