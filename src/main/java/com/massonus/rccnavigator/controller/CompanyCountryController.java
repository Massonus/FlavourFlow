package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.entity.CompanyCountry;
import com.massonus.rccnavigator.service.CompanyCountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/country")
public class CompanyCountryController {

    private final CompanyCountryService countryService;

    @Autowired
    public CompanyCountryController(CompanyCountryService countryService) {
        this.countryService = countryService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add-new-country")
    public String getAddTypeForm() {

        return "country/addCountry";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add-new-country")
    public String addTypePost(@Valid CompanyCountry type) {

        countryService.saveCompanyType(type);

        return "redirect:/admin/panel";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit-country/{id}")
    public String getTypeEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("country", countryService.getTypeById(id));

        return "country/editCountry";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit-country/{id}")
    public String editTypePost(@PathVariable Long id,
                               @RequestParam String title) {

        countryService.editType(id, title);

        return "redirect:/admin/panel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete-country/{id}")
    public String deleteType(@PathVariable Long id) {

        countryService.deleteType(id);

        return "redirect:/admin/panel";

    }
}
