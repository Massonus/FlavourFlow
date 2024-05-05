package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.service.CompanyService;
import com.massonus.flavourflow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class MainController {

    private final CompanyService companyService;

    private final ProductService productService;

    @Autowired
    public MainController(CompanyService companyService, ProductService productService) {
        this.companyService = companyService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHome() {

        return "index";
    }

    @GetMapping("/search")
    public String getSearchItems(@RequestParam String search) {

        if (!companyService.getSearchCompanies(search).isEmpty()) {
            return "redirect:/company?search=" + search;
        } else if (!productService.getProductsByTitleContainingIgnoreCase(search).isEmpty()) {
            return "redirect:/product/all-products?search=" + search;
        } else {
            return "redirect:/";
        }
    }
}
