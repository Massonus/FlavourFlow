package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final RatingService ratingService;

    @Autowired
    public CompanyController(CompanyService companyService, ImageService imageService, KitchenCategoryService kitchenCategoryService, CompanyTypeService companyTypeService, RatingService ratingService) {
        this.companyService = companyService;
        this.imageService = imageService;
        this.kitchenCategoryService = kitchenCategoryService;
        this.companyTypeService = companyTypeService;
        this.ratingService = ratingService;
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
                                        @RequestParam(required = false) Long categoryId,
                                        @RequestParam(required = false) Long typeId) {

        List<Company> companies = companyService.getAllCompanies();

        if (Objects.nonNull(categoryId)) {
            companies = companies.stream()
                    .filter(c -> c.getKitchenCategory().getId().equals(categoryId))
                    .toList();
        }

        if (Objects.nonNull(typeId)) {
            companies = companies.stream()
                    .filter(c -> c.getCompanyType().getId().equals(typeId))
                    .toList();
        }

        model.addAttribute("categories", kitchenCategoryService.getAllCategories());
        model.addAttribute("types", companyTypeService.getAllTypes());
        model.addAttribute("companies", companies);

        return "company/allCompanies";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

        Image uploadImage = imageService.upload(multipartFile);

        companyService.saveCompany(company, uploadImage, kitchenCategoryService.getCategoryById(categoryId), companyTypeService.getTypeById(typeId));

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
                                     @RequestParam("file") MultipartFile multipartFile,
                                     @RequestParam String imageLink,
                                     Company company) {

        companyService.editCompany(id, company, kitchenCategoryService.getCategoryById(categoryId), companyTypeService.getTypeById(typeId), multipartFile, imageLink);

        return "redirect:/admin/panel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        Company companyById = companyService.getCompanyById(id);
        companyService.deleteCompany(companyById);
        return "redirect:/admin/panel";
    }


    @RequestMapping("/sort")
    public String getAllSortedCompanies(@RequestParam String sort, Model model) {

        List<Company> companies = (companyService.getSortedCompanies(sort));

        model.addAttribute("companies", companies);
        model.addAttribute("types", companyTypeService.getAllTypes());
        model.addAttribute("categories", kitchenCategoryService.getAllCategories());

        return "company/allCompanies";
    }

    @GetMapping("/{id}")
    public String getCompany(@AuthenticationPrincipal User user, @PathVariable Long id, Model model) {
        Company company = companyService.getCompanyById(id);

        model.addAttribute("user", user);
        model.addAttribute("company", company);
        return "company/companyInfo";
    }

    @PostMapping("/rate-company/{id}")
    public String rateCompany(@PathVariable Long id, @AuthenticationPrincipal User author, @RequestParam Integer rate) {
        Company companyById = companyService.getCompanyById(id);

        ratingService.rateCompany(author, companyById, rate);

        return "redirect:/companies/" + id;
    }

}
