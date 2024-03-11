package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.CompanyDto;
import com.massonus.rccnavigator.dto.CompanyFilterDto;
import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.MessageItemType;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final KitchenCategoryService categoryService;
    private final CompanyCountryService countryService;
    private final RatingService ratingService;
    private final MessageService messageService;

    @Autowired
    public CompanyController(CompanyService companyService, KitchenCategoryService categoryService, CompanyCountryService countryService, RatingService ratingService, MessageService messageService) {
        this.companyService = companyService;
        this.categoryService = categoryService;
        this.countryService = countryService;
        this.ratingService = ratingService;
        this.messageService = messageService;
    }

    @GetMapping
    public String getAllCompanies(Model model,
                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(required = false) Long categoryId,
                                  @RequestParam(required = false) Long countryId,
                                  @RequestParam(required = false) String sort,
                                  @RequestParam(required = false) String search) {

        final CompanyFilterDto companyFilterDto = new CompanyFilterDto();
        companyFilterDto.setCategoryId(categoryId);
        companyFilterDto.setCountryId(countryId);

        int pageSize = 3;

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Company> companyPage = companyService.getCompaniesInPage(companyFilterDto, pageable, sort, search);

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("companies", companyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", companyPage.getTotalPages());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("countryId", countryId);
        model.addAttribute("sort", sort == null ? "Default" : sort);
        model.addAttribute("search", search);

        return "company/allCompanies";
    }

    @GetMapping("/info/{id}")
    public String getCompany(@AuthenticationPrincipal User user, @PathVariable Long id, Model model) {
        Company company = companyService.getCompanyById(id);

        model.addAttribute("user", user);
        model.addAttribute("company", company);
        model.addAttribute("messages", messageService.getMessagesItemType(MessageItemType.COMPANY));
        return "company/companyInfo";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String getAddCompanyForm(Model model) {

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("countries", countryService.getAllCountries());

        return "company/addCompany";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public void newCompany(@RequestBody CompanyDto companyDto) {

        companyService.saveCompany(companyDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("company", company);
        return "company/companyEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/edit")
    public void saveUpdatedCompany(@RequestBody CompanyDto companyDto) {

        companyService.editCompany(companyDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public void deleteCompany(@RequestParam Long companyId) {
        Company companyById = companyService.getCompanyById(companyId);
        companyService.deleteCompany(companyById);
    }

    @PostMapping("/rate-company/{id}")
    public String rateCompany(@PathVariable Long id, @AuthenticationPrincipal User author, @RequestParam Integer rate) {
        Company companyById = companyService.getCompanyById(id);

        ratingService.rateCompany(author, companyById, rate);

        return "redirect:/company/info/" + id;
    }

}
