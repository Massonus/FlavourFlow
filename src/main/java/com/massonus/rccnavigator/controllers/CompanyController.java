package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.service.CompanyService;
import com.massonus.rccnavigator.service.ImageService;
import com.massonus.rccnavigator.service.KitchenCategoryService;
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
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    private final ImageService imageService;
    private final KitchenCategoryService kitchenCategoryService;

    @Autowired
    public CompanyController(CompanyService companyService, ImageService imageService, KitchenCategoryService kitchenCategoryService) {
        this.companyService = companyService;
        this.imageService = imageService;
        this.kitchenCategoryService = kitchenCategoryService;
    }

    @GetMapping
    public String getAllCompanies(Model model) {

        List<Company> companies = companyService.getAllCompanies();

        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("companies", companies);

        return "company/allCompanies";
    }

    @GetMapping("/filter")
    public String getAllFilterCompanies(Model model, @RequestParam Long categoryId) {

        List<Company> filteredCompanies = companyService.getAllCompanies().stream()
                .filter(c -> c.getCategory().getId().equals(categoryId))
                .toList();

        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("companies", filteredCompanies);

        return "company/allCompanies";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new-company")
    public String newCompany(@Valid Company company,
                             @RequestParam Long categoryId,
                             @RequestParam("file") MultipartFile multipartFile) {

        Image uploadImage;
        try {
            uploadImage = imageService.upload(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        companyService.saveCompany(company, uploadImage, kitchenCategoryService.getCategoryById(categoryId));
        companyService.getCompanyByTitle(company.getTitle());

        return "redirect:/admin/panel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("company", company);
        return "company/companyEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit/{id}")
    public String saveUpdatedCompany(@PathVariable Long id,
                                     @RequestParam Long categoryId,
                                     Company company) {

        companyService.editCompany(id, company, kitchenCategoryService.getCategoryById(categoryId));

        return "redirect:/admin/panel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        Company companyById = companyService.getCompanyById(id);
        companyService.deleteCompany(companyById);
        return "redirect:/admin/panel";
    }

    /*@GetMapping("/companiesByCategory/{id}")
    public String findProductByCategory(@PathVariable Long id, Model model) {
        Set<Company> companies = companyService.getProductsByCategoryId(id);
        model.addAttribute("companies", companies);
        model.addAttribute("categories", categoryService.getAll());
        return "products";
    }*/

}
