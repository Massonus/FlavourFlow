package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.CompanyType;
import com.massonus.rccnavigator.service.CompanyTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/type")
public class CompanyTypeController {

    private final CompanyTypeService typeService;

    @Autowired
    public CompanyTypeController(CompanyTypeService typeService) {
        this.typeService = typeService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add-new-type")
    public String getAddTypeForm() {

        return "type/addType";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add-new-type")
    public String addTypePost(@Valid CompanyType type) {

        typeService.saveCompanyType(type);

        return "redirect:/admin/panel";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit-type/{id}")
    public String getTypeEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("type", typeService.getTypeById(id));

        return "type/editType";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit-type/{id}")
    public String editTypePost(@PathVariable Long id,
                               @RequestParam String title) {

        typeService.editType(id, title);

        return "redirect:/admin/panel";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete-type/{id}")
    public String deleteType(@PathVariable Long id) {

        typeService.deleteType(id);

        return "redirect:/admin/panel";

    }
}
