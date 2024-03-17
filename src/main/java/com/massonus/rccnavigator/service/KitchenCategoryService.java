package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.KitchenCategoryDto;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.repo.KitchenCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class KitchenCategoryService {

    private final KitchenCategoryRepo categoryRepo;

    @Autowired
    public KitchenCategoryService(KitchenCategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public KitchenCategory saveKitchenCategory(final KitchenCategory kitchenCategory) {
        return categoryRepo.save(kitchenCategory);
    }

    public List<KitchenCategory> getAllCategories() {

        return categoryRepo.findAll().stream()
                .sorted(Comparator.comparing(KitchenCategory::getId))
                .toList();
    }

    public List<KitchenCategory> getAllCategoriesExceptOne(final Long categoryId) {
        return categoryRepo.findAll().stream()
                .filter(a -> !a.getId().equals(categoryId))
                .toList();
    }

    public KitchenCategory getCategoryById(Long id) {
        return categoryRepo.findKitchenCategoryById(id);
    }

    public KitchenCategory getCategoryByTitle(String title) {
        return categoryRepo.findKitchenCategoryByTitleContainingIgnoreCase(title);
    }

    public KitchenCategoryDto editCategory(final KitchenCategoryDto categoryDto) {
        getCategoryById(categoryDto.getCategoryId()).setTitle(categoryDto.getTitle());
        return categoryDto;
    }

    public Long deleteCategory(Long id) {

        categoryRepo.delete(getCategoryById(id));
        return id;
    }

}
