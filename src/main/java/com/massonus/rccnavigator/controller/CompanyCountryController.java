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
@PreAuthorize("hasAuthority('ADMIN')")
public class CompanyCountryController {

    private final CompanyCountryService countryService;

    @Autowired
    public CompanyCountryController(CompanyCountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/add")
    public String getAddCountryForm() {

        return "country/addCountry";

    }

    @PostMapping("/add")
    public void addCountry(@RequestBody CountryDto countryDto) {

        countryService.saveCompanyCountry(countryDto);

    }

    @GetMapping("/edit/{id}")
    public String getCountryEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("country", countryService.getCountryById(id));

        return "country/editCountry";

    }

    @PutMapping("/edit")
    public void editCountry(@RequestBody CountryDto countryDto) {

        countryService.editCountry(countryDto);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Long deleteCountry(@RequestParam Long id) {

        return countryService.deleteCountry(id);
    }
}
