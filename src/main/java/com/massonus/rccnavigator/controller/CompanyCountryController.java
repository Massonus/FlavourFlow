package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.CountryDto;
import com.massonus.rccnavigator.service.CompanyCountryService;
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
    @GetMapping("/add")
    public String getAddTypeForm() {

        return "country/addCountry";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public void addTypePost(@RequestBody CountryDto countryDto) {

        countryService.saveCompanyCountry(countryDto);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String getTypeEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("country", countryService.getCountryById(id));

        return "country/editCountry";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit/{id}")
    public String editTypePost(@PathVariable Long id,
                               @RequestParam String title) {

        countryService.editType(id, title);

        return "redirect:/admin/panel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable Long id) {

        countryService.deleteType(id);

        return "redirect:/admin/panel";

    }
}
