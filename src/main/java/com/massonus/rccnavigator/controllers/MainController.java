package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.service.CompanyService;
import com.massonus.rccnavigator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Objects;

@Controller
public class MainController {

    private final ProductService productService;
    private final CompanyService companyService;

    @Autowired
    public MainController(ProductService productService, CompanyService companyService) {
        this.productService = productService;
        this.companyService = companyService;
    }

    @GetMapping(value = "/static/css/{cssFile}")
    public @ResponseBody byte[] getFile(@PathVariable("cssFile") String cssFile) {


        try (InputStream in = getClass()
                .getResourceAsStream("/templates/" + cssFile)) {
            return Objects.requireNonNull(in).readAllBytes();

        } catch (Exception e) {
            String error = "ERROR: css file (/templates/" + cssFile + ") not found";
            return error.getBytes();
        }
    }

    @GetMapping("/")
    public String getHome() {

        return "index";
    }

    @RequestMapping("/search")
    public String findProductByTitle(@RequestParam String title, @RequestParam String searchType, Model model) {

        if (searchType.equals("company")) {
            model.addAttribute("companies", companyService.getAllCompaniesByTitleContainingIgnoreCase(title));
            return "company/allCompanies";
        } else {
            model.addAttribute("products", productService.getAllProductsByTitleContainingIgnoreCase(title));
            return "product/allProducts";
        }
    }
}
