package com.massonus.flavourflow.controller;

import com.massonus.flavourflow.dto.KitchenCategoryDto;
import com.massonus.flavourflow.entity.KitchenCategory;
import com.massonus.flavourflow.service.KitchenCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
@PreAuthorize("hasAuthority('ADMIN')")
public class KitchenCategoryController {

    private final KitchenCategoryService categoryService;

    @Autowired
    public KitchenCategoryController(KitchenCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public String getAddCategoryForm() {

        return "category/addCategory";

    }

    @PostMapping("/add")
    @ResponseBody
    public KitchenCategory addCategoryPost(@RequestBody KitchenCategory category) {

        return categoryService.saveKitchenCategory(category);

    }

    @GetMapping("/edit/{id}")
    public String getCategoryEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("category", categoryService.getCategoryById(id));

        return "category/editCategory";

    }

    @PutMapping("/edit")
    @ResponseBody
    public KitchenCategoryDto categoryPutEdit(@RequestBody KitchenCategoryDto categoryDto) {

        return categoryService.editCategory(categoryDto);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Long deleteCategory(@RequestParam Long id) {

        return categoryService.deleteCategory(id);
    }
}
