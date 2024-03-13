package com.massonus.rccnavigator.controller;

import com.massonus.rccnavigator.dto.KitchenCategoryDto;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.service.KitchenCategoryService;
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
    public void categoryPutEdit(@RequestBody KitchenCategoryDto categoryDto) {

        categoryService.editCategory(categoryDto);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Long deleteCategory(@RequestParam Long id) {

        return categoryService.deleteCategory(id);
    }
}
