package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.Image;
import com.massonus.rccnavigator.entity.Product;
import com.massonus.rccnavigator.service.CompanyService;
import com.massonus.rccnavigator.service.ImageService;
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

    @Autowired
    public CompanyController(CompanyService companyService, ImageService imageService) {
        this.companyService = companyService;
        this.imageService = imageService;
    }

    @GetMapping
    public String getAllCompanies(Model model) {

        List<Company> companies = companyService.getAllCompanies();

        model.addAttribute("companies", companies);

        return "company/allCompanies";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new-company")
    public String newCompany(@Valid Company company,
                             @RequestParam("file") MultipartFile multipartFile) {

        Image uploadImage;
        try {
            uploadImage = imageService.upload(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        companyService.saveCompany(company, uploadImage);
        Long companyId = companyService.getCompanyByTitle(company.getTitle()).getId();

        return "redirect:/products/" + companyId;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "company/companyEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit/{id}")
    public String saveUpdatedCompany(@PathVariable Long id,
                                     Company company) {

        companyService.editCompany(id, company);

        return "redirect:/companies";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        Company companyById = companyService.getCompanyById(id);
        companyService.deleteCompany(companyById);
        return "redirect:/companies";
    }

}
