package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.dto.ImageResponseDto;
import com.massonus.flavourflow.dto.ProductDto;
import com.massonus.flavourflow.entity.Product;
import com.massonus.flavourflow.entity.ProductCategory;
import com.massonus.flavourflow.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final BasketObjectService basketObjectService;
    private final BasketService basketService;
    private final WishService wishService;
    private final CompanyService companyService;

    @Autowired
    public ProductController(ProductService productService, BasketObjectService basketObjectService, BasketService basketService, WishService wishService, CompanyService companyService) {
        this.productService = productService;
        this.basketObjectService = basketObjectService;
        this.basketService = basketService;
        this.wishService = wishService;
        this.companyService = companyService;
    }

    @GetMapping("/all-products")
    public String getProductsOfCompany(@RequestParam(required = false) Long companyId, Model model,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(required = false) String sort,
                                       @RequestParam(required = false) ProductCategory productCategory,
                                       @RequestParam(required = false) String search) {

        int pageSize = 4;

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productService.getProductsInPage(companyId, pageable, sort, productCategory, search);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("objects", basketObjectService.getAllBasketObjects());
        model.addAttribute("basketService", basketService);
        model.addAttribute("wishService", wishService);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);

        if (Objects.isNull(companyId)) {
            model.addAttribute("companyId", productPage.getContent().getFirst().getCompany().getId());
        } else {
            model.addAttribute("companyId", companyId);
        }

        model.addAttribute("sort", sort == null ? "Default" : sort);
        model.addAttribute("productCategory", productCategory == null ? "Default" : productCategory);

        return "product/allProducts";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/all-products")
    public String getProductsOfCompanyForAdmin(@RequestParam Long companyId, Model model) {
        List<Product> products = productService.getAllProductsByCompanyId(companyId);
        model.addAttribute("products", products);
        model.addAttribute("company", companyService.getCompanyById(companyId));

        return "product/productsInAdminPanel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add/{companyId}")
    public String addProduct(@PathVariable Long companyId, Model model) {

        model.addAttribute("companyId", companyId);

        return "product/addProduct";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    @ResponseBody
    public ProductDto newProduct(@RequestBody ProductDto productDto) {

        return productService.saveProduct(productDto);
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
    @ResponseBody
    public ProductDto saveUpdatedProduct(@RequestBody ProductDto productDto) {

        return productService.editProduct(productDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    @ResponseBody
    public ImageResponseDto deleteProduct(@RequestParam Long productId) {

        return productService.deleteProduct(productId);
    }

    @GetMapping("/info/{id}")
    public String getProductInfoPage(@PathVariable Long id, Model model) {

        model.addAttribute("product", productService.getProductById(id));

        return "product/productInfo";
    }
}
