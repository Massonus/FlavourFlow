package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.service.CompanyTypeService;
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
import java.util.Objects;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final ImageService imageService;
    private final KitchenCategoryService kitchenCategoryService;
    private final CompanyTypeService companyTypeService;

    @Autowired
    public CompanyController(CompanyService companyService, ImageService imageService, KitchenCategoryService kitchenCategoryService, CompanyTypeService companyTypeService) {
        this.companyService = companyService;
        this.imageService = imageService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyTypeService = companyTypeService;
    }

    @GetMapping
    public String getAllCompanies(Model model) {

        List<Company> companies = companyService.getAllCompanies();

        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("types", companyTypeService.getAllTypes());
        model.addAttribute("companies", companies);

        return "company/allCompanies";
    }

    @GetMapping("/filter")
    public String getAllFilterCompanies(Model model,
                                        @RequestParam(required = false) Long categoryId) {

        List<Company> companies = companyService.getAllCompanies();

        if (Objects.nonNull(categoryId)) {
            companies = companies.stream()
                    .filter(c -> c.getKitchenCategory().getId().equals(categoryId))
                    .toList();
        }

        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("types", companyTypeService.getAllTypes());
        model.addAttribute("companies", companies);

        return "company/allCompanies";

    }

    @GetMapping("/add-company")
    public String getAddCompanyForm(Model model) {

        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("types", companyTypeService.getAllTypes());

        return "company/addCompany";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new-company")
    public String newCompany(@Valid Company company,
                             @RequestParam Long categoryId,
                             @RequestParam Long typeId,
                             @RequestParam("file") MultipartFile multipartFile) {

        Image uploadImage;
        try {
            uploadImage = imageService.upload(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        companyService.saveCompany(company, uploadImage, kitchenCategoryService.getCategoryById(categoryId), companyTypeService.getTypeById(typeId));
        companyService.getCompanyByTitle(company.getTitle());

        return "redirect:/admin/panel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("types", companyTypeService.getAllTypes());
        model.addAttribute("company", company);
        return "company/companyEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit/{id}")
    public String saveUpdatedCompany(@PathVariable Long id,
                                     @RequestParam Long categoryId,
                                     @RequestParam Long typeId,
                                     Company company) {

        companyService.editCompany(id, company, kitchenCategoryService.getCategoryById(categoryId), companyTypeService.getTypeById(typeId));

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
