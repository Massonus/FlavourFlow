package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.repo.KitchenCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

    public Set<KitchenCategory> getAllCategories() {

        return new HashSet<>(categoryRepo.findAll());
    }

    public KitchenCategory getCategoryById(Long id) {
        return categoryRepo.findKitchenCategoryById(id);
    }

    public KitchenCategory getCategoryByTitle(String title) {
        return categoryRepo.findKitchenCategoryByTitleContainingIgnoreCase(title);
    }

    public KitchenCategory editCategory(Long id, KitchenCategory category) {

        KitchenCategory categoryById = getCategoryById(id);
        categoryById.setTitle(category.getTitle());

        return categoryById;

    }

    public void deleteCategory(Long id) {

        categoryRepo.delete(getCategoryById(id));
    }

}
