package com.massonus.rccnavigator.controllers;

import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.service.KitchenCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class KitchenCategoryController {


    private final KitchenCategoryService categoryService;

    @Autowired
    public KitchenCategoryController(KitchenCategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/add-new-category")
    public String getAddCategoryForm() {

        return "category/addCategory";

    }

    @PostMapping("/add-new-category")
    public String addCategoryPost(@Valid KitchenCategory category) {

        categoryService.saveKitchenCategory(category);

        return "redirect:/admin/panel";

    }


    @GetMapping("/edit-category/{id}")
    public String getCategoryEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("category", categoryService.getCategoryById(id));

        return "category/editCategory";

    }

    @PostMapping("/edit-category/{id}")
    public String categoryPostEdit(@PathVariable Long id,
                                   @RequestParam String title) {

        categoryService.editCategory(id, title);

        return "redirect:/admin/panel";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);

        return "redirect:/admin/panel";

    }
}
