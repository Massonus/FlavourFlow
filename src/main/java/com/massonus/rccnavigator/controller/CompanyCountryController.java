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
    public String getAddCountryForm() {

        return "country/addCountry";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public void addCountry(@RequestBody CountryDto countryDto) {

        countryService.saveCompanyCountry(countryDto);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String getCountryEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("country", countryService.getCountryById(id));

        return "country/editCountry";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/edit")
    public void editCountry(@RequestBody CountryDto countryDto) {

        countryService.editCountry(countryDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Long id) {

        countryService.deleteType(id);

        return "redirect:/admin/panel";

    }
}
