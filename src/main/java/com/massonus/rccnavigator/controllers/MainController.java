package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.service.CompanyService;
import com.massonus.rccnavigator.service.CompanyCountryService;
import com.massonus.rccnavigator.service.KitchenCategoryService;
import com.massonus.rccnavigator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.util.Objects;

@Controller
public class MainController {

    private final ProductService productService;
    private final CompanyService companyService;
    private final KitchenCategoryService kitchenCategoryService;
    private final CompanyCountryService companyCountryService;

    @Autowired
    public MainController(ProductService productService, CompanyService companyService, KitchenCategoryService kitchenCategoryService, CompanyCountryService companyCountryService) {
        this.productService = productService;
        this.companyService = companyService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyCountryService = companyCountryService;
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

    @GetMapping("/search")
    public String findProductByTitle(@RequestParam String title, Model model) {

        boolean isEmpty = companyService.getAllCompaniesByTitleContainingIgnoreCase(title).isEmpty();
        if (!isEmpty) {
            model.addAttribute("companies", companyService.getAllCompaniesByTitleContainingIgnoreCase(title));
            model.addAttribute("categories", kitchenCategoryService.getAllCategories());
            model.addAttribute("types", companyCountryService.getAllTypes());
            return "company/allCompanies";
        } else {
            model.addAttribute("products", productService.getAllProductsByTitleContainingIgnoreCase(title));
            return "product/allProducts";
        }
    }
}
