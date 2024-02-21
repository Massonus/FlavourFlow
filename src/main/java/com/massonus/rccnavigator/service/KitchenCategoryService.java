package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.repo.KitchenCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class KitchenCategoryService {

    private final KitchenCategoryRepo kitchenCategoryRepo;

    @Autowired
    public KitchenCategoryService(KitchenCategoryRepo kitchenCategoryRepo) {
        this.kitchenCategoryRepo = kitchenCategoryRepo;
    }

    public void saveKitchenCategory(final KitchenCategory kitchenCategory) {

        kitchenCategoryRepo.save(kitchenCategory);
    }

    public Set<KitchenCategory> getAllCategories() {

        return new HashSet<>(kitchenCategoryRepo.findAll());
    }

    public KitchenCategory getCategoryById(Long id) {
        return kitchenCategoryRepo.findKitchenCategoryById(id);
    }

    public KitchenCategory getCategoryByTitle(String title) {
        return kitchenCategoryRepo.findKitchenCategoryByTitle(title);
    }

    public KitchenCategory editCategory(Long id, KitchenCategory category) {

        KitchenCategory categoryById = getCategoryById(id);
        categoryById.setTitle(category.getTitle());

        return categoryById;

    }

    public void deleteCategory(Long id) {

        kitchenCategoryRepo.delete(getCategoryById(id));
    }

}
